import React from 'react';
import { useEffect, useState, useMemo } from 'react';
import { ChatMessagePacket } from './Network/Usecase/chatMessagePacket';

let messageIndex: number = 0;

export function Chatting() {
  const roomName = 'testWithReact';
  const socket = new ChatMessagePacket();
  const [messages, setMessages] = useState<string[]>([]);

  useEffect(() => {
    recvMessageAndSetMessage(roomName, messageIndex);
  }, []);

  const onSubmitHandler = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    const text = (event.target as HTMLFormElement).text.value;
    socket.sendMessage(roomName, text).then(() => {
      messageIndex = 0;
      recvMessageAndSetMessage(roomName, messageIndex);
    });
  };

  const onClickHandler = (event: React.FormEvent<HTMLButtonElement>) => {
    event.preventDefault();
    recvMessageAndSetMessage(roomName, messageIndex);
  };

  async function recvMessageAndSetMessage(roomName: string, Index: number) {
    let result = (await socket.recvMessage(
      roomName,
      Index,
      Index + 10,
    )) as string[];
    messageIndex += result.length;
    setMessages([...result]);
  }

  return (
    <div className="Chatting">
      <form onSubmit={onSubmitHandler}>
        <input type="text" name="text" />
        <input type="submit" value="Upload" />
      </form>
      {messages &&
        messages.map((message, idx) => {
          return <p key={idx}> {message} </p>;
        })}
      <button onClick={onClickHandler}>더 ...</button>
    </div>
  );
}
