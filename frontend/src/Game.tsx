import React, { useState } from 'react';
import { GamePacket } from './Network/Usecase/gamePacket';
import { RoomPlayer } from './types/gamePacketType';

export function Game() {
  const roomName: number = 12345;
  const socket = new GamePacket();

  const [response, setResponse] = useState<string[]>([]);

  const onSubmitHandler = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    const gameInput = (event.target as HTMLFormElement).game.value;
    const commandInput = (event.target as HTMLFormElement).command.value;
    const payloadInput = (event.target as HTMLFormElement).payload.value;
    setGameCommandAndGetResponse(gameInput, commandInput, payloadInput);
  };

  const onSubmitHandler2 = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    const gameInput = (event.target as HTMLFormElement).game.value;
    const commandInput = (event.target as HTMLFormElement).command.value;
    const payloadInput = (event.target as HTMLFormElement).payload.value;
    getGameCommandAndGetResponse(gameInput, commandInput, payloadInput);
  };

  async function setGameCommandAndGetResponse(
    gameInput: any,
    commandInput: string,
    payloadInput: string,
  ) {
    const roomPlayer: RoomPlayer = {
      roomId: roomName,
      playerId: 'testPlayer',
      playerName: 'seokyoung.cho',
    };
    let result = (await socket.setGameCommand(
      'game.setting',
      roomName,
      roomPlayer,
      gameInput,
      commandInput,
      payloadInput,
    )) as string;
    console.log('[*] setGameCommand result : ' + result);
    setResponse([result, ...response]);
  }

  async function getGameCommandAndGetResponse(
    gameInput: any,
    commandInput: string,
    payloadInput: string,
  ) {
    const roomPlayer: RoomPlayer = {
      roomId: roomName,
      playerId: 'testPlayer',
      playerName: 'seokyoung.cho',
    };
    let result = (await socket.getGameCommand(
      'game.info',
      roomName,
      roomPlayer,
      gameInput,
      commandInput,
      payloadInput,
      10,
    )) as string[];
    console.log('[*] getGameCommand result : ' + result);
    setResponse([...result, ...response]);
  }

  return (
    <div className="Game">
      <form onSubmit={onSubmitHandler}>
        <input type="text" name="game" />
        <input type="text" name="command" />
        <input type="text" name="payload" />
        <input type="submit" value="Command" />
        {response &&
          response.map((response, idx) => {
            return <p key={idx}> {response} </p>;
          })}
      </form>
      <form onSubmit={onSubmitHandler2}>
        <input type="text" name="game" />
        <input type="text" name="command" />
        <input type="text" name="payload" />
        <input type="submit" value="Command" />
        {response &&
          response.map((response, idx) => {
            return <p key={idx}> {response} </p>;
          })}
      </form>
    </div>
  );
}
