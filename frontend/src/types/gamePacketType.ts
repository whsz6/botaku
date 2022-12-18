export interface RoomPlayer {
  roomId: number;
  playerId: string;
  playerName: string;
}

// https://engineering.linecorp.com/ko/blog/typescript-enum-tree-shaking/
const GameType = {
  TICHU: 'TICHU',
  MIGHTY: 'MIGHTY',
} as const;
export type GameType = typeof GameType[keyof typeof GameType];

export interface SetGameRequest {
  roomId: number;
  fromPlayer: RoomPlayer;
  gameType: GameType;
  gameCommand: string;
  payload: string;
}

export interface GetGameRequest {
  roomId: number;
  fromPlayer: RoomPlayer;
  gameType: GameType;
  gameCommand: string;
  payload: string;
  size: number;
}
