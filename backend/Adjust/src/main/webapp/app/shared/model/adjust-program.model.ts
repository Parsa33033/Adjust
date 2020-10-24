import { Moment } from 'moment';
import { IDevelopment } from 'app/shared/model/development.model';

export interface IAdjustProgram {
  id?: number;
  createdAt?: string;
  expirationDate?: string;
  medicalProfileDone?: boolean;
  bodyCompositionDone?: boolean;
  nutritionDone?: boolean;
  fitnessDone?: boolean;
  paid?: boolean;
  seenByClient?: boolean;
  seenBySpecialist?: boolean;
  designed?: boolean;
  bodyCompositionId?: number;
  fitnessProgramId?: number;
  nutritionProgramId?: number;
  developments?: IDevelopment[];
  adjustClientId?: number;
  specialistId?: number;
}

export const defaultValue: Readonly<IAdjustProgram> = {
  medicalProfileDone: false,
  bodyCompositionDone: false,
  nutritionDone: false,
  fitnessDone: false,
  paid: false,
  seenByClient: false,
  seenBySpecialist: false,
  designed: false,
};
