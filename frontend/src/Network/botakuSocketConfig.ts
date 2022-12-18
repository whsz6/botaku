import { WellKnownMimeType } from 'rsocket-composite-metadata';

const websocketConfig = {
  url: 'ws://localhost:7000/rsocket',
  debug: true,
};

// rsocket.io 공식 홈페이지를 참고하면, 웹 브라우저에서는 tcp 를 지원하지 않는다고 한다.
const tcpConfig = {
  host: '127.0.0.1',
  port: 7000,
};

const METADATA_MIME_TYPE = 'application/cholab.botaku.metadata+json';
const RSOCKET_ROUTING = WellKnownMimeType.MESSAGE_RSOCKET_ROUTING;

export { websocketConfig, tcpConfig, METADATA_MIME_TYPE, RSOCKET_ROUTING };
