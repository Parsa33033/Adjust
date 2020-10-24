import { ProgramType } from 'app/shared/model/enumerations/program-type.model';

export interface IProgramDescription {
  id?: number;
  text?: any;
  type?: ProgramType;
}

export const defaultValue: Readonly<IProgramDescription> = {};
