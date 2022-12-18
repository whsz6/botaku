export type Message = {
  roomId: string;
  fromPerson: string;
  message: string;
  date: number;
};

export interface SendRequest {
  roomId: string;
  fromPerson: string;
  message: string;
  date: number;
}

export interface RecvRequest {
  roomId: string;
  offset: number;
  size: number;
}
