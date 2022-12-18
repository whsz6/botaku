import { BotakuSocket } from '../botakuSocket';
import {
  Message,
  RecvRequest,
  SendRequest,
} from '../../types/chatMessagePacketType';

export class ChatMessagePacket {
  messages: string[];
  socket: BotakuSocket;
  constructor() {
    this.messages = [];
    this.socket = new BotakuSocket();
  }
  public async sendMessage(roomId: string, toMessage: string) {
    try {
      const sendRequest: SendRequest = {
        roomId: roomId,
        fromPerson: 'react',
        message: toMessage,
        date: 1.2,
      };
      let result = await this.socket.oneToOne('chat.send', sendRequest);
      console.log('[*] send message : ', result);
    } catch (error) {
      console.error(error);
    }
  }

  public async recvMessage(roomId: string, offset: number, size: number) {
    let recvMessages: string[];
    console.log('[*] recv message offset : ', offset, ' , size : ', size);
    try {
      const recvRequest: RecvRequest = {
        roomId: roomId,
        offset: offset,
        size: size,
      };
      recvMessages = [...this.messages];
      let recvData: Message[] = (await this.socket.oneToMany(
        'chat.recv',
        10,
        recvRequest,
      )) as Message[];
      recvData.forEach((recv) => {
        console.log('[*] recv message : ', recv['message']);
        recvMessages.push(recv['message']);
      });
      return recvMessages;
    } catch (error) {
      console.error(error);
    }
  }
}
