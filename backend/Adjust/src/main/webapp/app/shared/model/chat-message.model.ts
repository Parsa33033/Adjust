export interface IChatMessage {
  id?: number;
  sender?: string;
  receiver?: string;
  text?: any;
  imageContentType?: string;
  image?: any;
  voiceContentType?: string;
  voice?: any;
  seen?: boolean;
  conversationId?: number;
}

export const defaultValue: Readonly<IChatMessage> = {
  seen: false,
};
