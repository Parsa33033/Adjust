import { Moment } from 'moment';
import { IOrder } from 'app/shared/model/order.model';
import { IAdjustProgram } from 'app/shared/model/adjust-program.model';
import { IConversation } from 'app/shared/model/conversation.model';
import { ITutorial } from 'app/shared/model/tutorial.model';
import { Gender } from 'app/shared/model/enumerations/gender.model';

export interface IAdjustClient {
  id?: number;
  username?: string;
  firstName?: string;
  lastName?: string;
  birthDate?: string;
  age?: number;
  gender?: Gender;
  token?: number;
  score?: number;
  imageContentType?: string;
  image?: any;
  medicalProfileId?: number;
  orders?: IOrder[];
  adjustPrograms?: IAdjustProgram[];
  conversations?: IConversation[];
  tutorials?: ITutorial[];
}

export const defaultValue: Readonly<IAdjustClient> = {};
