import { websocketConfig, RSOCKET_ROUTING } from './botakuSocketConfig';
import {
  WellKnownMimeType,
  encodeCompositeMetadata,
  encodeRoute,
} from 'rsocket-composite-metadata';
import cbor from 'cbor';
import { RSocket, RSocketConnector, Payload } from 'rsocket-core';
import { WebsocketClientTransport } from 'rsocket-websocket-client';

const setupOptions = {
  dataMimeType: WellKnownMimeType.APPLICATION_CBOR.toString(),
  metadataMimeType: WellKnownMimeType.MESSAGE_RSOCKET_ROUTING.toString(),
  keepAlive: 1000000,
  lifetime: 100000,
};

// https://github.com/rsocket/rsocket/blob/master/Extensions/CompositeMetadata.md
// SETUP 프레임이나 METADATA_PUSH 프레임에서만 사용가능한 것으로 확인되며, 일반 통신 프레임에서는 사용할 수 없는 것으로 확인됨.
function encodeCompositeMetaData(route: string) {
  const map = new Map();
  map.set(RSOCKET_ROUTING, encodeRoute(route));
  const compositeMetaData = encodeCompositeMetadata(map);
  console.log('[*] encodeCompositeMetadata : ', compositeMetaData);
  return compositeMetaData;
}

export class BotakuSocket {
  socket: RSocketConnector;
  client?: RSocket | null;
  constructor() {
    this.socket = new RSocketConnector({
      setup: setupOptions,
      transport: new WebsocketClientTransport({
        url: websocketConfig.url,
        wsCreator: (url) => {
          return new WebSocket(url);
        },
      }),
    });
  }

  async connect() {
    try {
      this.client = await this.socket.connect();
    } catch (error) {
      this.client = null;
      console.log(error);
    }
  }

  async oneToOne<T>(to: string, sendRequest: T) {
    await this.connect();
    console.log('[*] oneToOne 진입 전 socket 상태 : ', this.client);
    let response: string = '';
    return new Promise<string>((resolve, reject) => {
      this.client &&
        this.client.requestResponse(
          {
            data: cbor.encode(sendRequest),
            metadata: encodeRoute(to),
          },
          {
            onError: (error: Error) => {
              console.error('[*] oneToOne error : ', error);
              reject(error);
            },
            onNext: (payload: Payload, isComplete: boolean) => {
              console.log('[*] onNext 데이터 송신 완료');
              response = this.cborOutputDecoding(payload);
              console.log(`[*] oneToOne next : ${response} | ${isComplete}`);
              resolve(response);
            },
            // mono 는 onNext 가 onComplete 를 대신한다.
            // https://g-egg.tistory.com/78
            onComplete: () => {
              console.log('[*] onComplete');
              resolve(response);
            },
            onExtension: () => {
              console.log('[*] onExtension');
            },
          },
        );
    });
  }

  async oneToMany<T extends { size: number }>(
    to: string,
    initRequest: number,
    recvRequest: T,
  ): Promise<Object[]> {
    let count = 0;
    const pressure = recvRequest.size;
    await this.connect();
    console.log('[*] oneToMany 진입 전 socket 상태 : ', this.client);
    let responses: Object[] = [];
    let response: string = '';
    return new Promise<Object[]>((resolve, reject) => {
      this.client &&
        this.client.requestStream(
          {
            data: cbor.encode(recvRequest),
            metadata: encodeRoute(to),
          },
          initRequest,
          {
            onError: (error: Error) => {
              console.error('[*] oneToMany error : ', error);
              reject(error);
            },
            onNext: (payload, isComplete) => {
              count++;
              response = this.cborOutputDecoding(payload);
              console.log(`[*] oneToMany next : ${response} | ${isComplete}`);
              if (payload.data !== null && payload.data !== undefined) {
                responses.push(response);
              }
              if (count == pressure) {
                console.log('oneToMany resolve');
                resolve(responses);
              }
            },
            onComplete: () => {
              console.log('[*] 데이터 수신 완료');
              resolve(responses);
            },
            onExtension: () => {},
          },
        );
    });
  }

  cborOutputDecoding(payload: Payload): string {
    let response: string = '';
    try {
      response = cbor.decode(payload.data as Buffer);
    } catch (error: any) {
      console.log(
        '[*] cbor decoding error name : ' +
          error.name +
          ', message : ' +
          error.message,
      );
      console.log('[*] cbor 형태로 전송이 되지 않아 그대로 삽입');
      response = payload.data?.toString('utf-8') as string;
    }
    return response;
  }
}
