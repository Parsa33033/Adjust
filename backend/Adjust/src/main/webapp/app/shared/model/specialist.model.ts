import { Moment } from 'moment';
import { IAdjustProgram } from 'app/shared/model/adjust-program.model';
import { IConversation } from 'app/shared/model/conversation.model';
import { Gender } from 'app/shared/model/enumerations/gender.model';

export interface ISpecialist {
  id?: number;
  username?: string;
  firstName?: string;
  lastName?: string;
  birthday?: string;
  gender?: Gender;
  degree?: string;
  field?: string;
  resume?: any;
  stars?: number;
  imageContentType?: string;
  image?: any;
  busy?: boolean;
  adjustPrograms?: IAdjustProgram[];
  conversations?: IConversation[];
}

export const defaultValue: Readonly<ISpecialist> = {
  busy: false,
};
