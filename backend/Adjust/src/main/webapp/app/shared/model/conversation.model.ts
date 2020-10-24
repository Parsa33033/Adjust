import { IChatMessage } from 'app/shared/model/chat-message.model';

export interface IConversation {
  id?: number;
  messages?: IChatMessage[];
  adjustClientId?: number;
  specialistId?: number;
}

export const defaultValue: Readonly<IConversation> = {};
