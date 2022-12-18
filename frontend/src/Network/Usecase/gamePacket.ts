import { sizeOfFrame } from 'rsocket-core';
import {
  GameType,
  GetGameRequest,
  RoomPlayer,
  SetGameRequest,
} from '../../types/gamePacketType';
import { BotakuSocket } from '../botakuSocket';

export class GamePacket {
  socket: BotakuSocket;
  constructor() {
    this.socket = new BotakuSocket();
  }
  async setGameCommand(
    port: string,
    roomId: number,
    roomPlayer: RoomPlayer,
    gameType: GameType,
    gameCommand: string,
    payload: string,
  ) {
    try {
      const sendRequest: SetGameRequest = {
        roomId: roomId,
        fromPlayer: roomPlayer,
        gameType: gameType,
        gameCommand: gameCommand,
        payload: payload,
      };
      let result = await this.socket.oneToOne(port, sendRequest);
      console.log('[*] send game packet : ', result);
      return result;
    } catch (error) {
      console.error(error);
    }
  }

  async getGameCommand(
    port: string,
    roomId: number,
    roomPlayer: RoomPlayer,
    gameType: GameType,
    gameCommand: string,
    payload: string,
    size: number,
  ) {
    try {
      const sendRequest: GetGameRequest = {
        roomId: roomId,
        fromPlayer: roomPlayer,
        gameType: gameType,
        gameCommand: gameCommand,
        payload: payload,
        size: size,
      };
      let result = await this.socket.oneToMany(port, 10, sendRequest);
      console.log('[*] send game packet : ', result);
      return result;
    } catch (error) {
      console.error(error);
    }
  }
}
