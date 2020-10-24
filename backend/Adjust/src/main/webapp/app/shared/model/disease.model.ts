import { IMedicalProfile } from 'app/shared/model/medical-profile.model';

export interface IDisease {
  id?: number;
  name?: string;
  proteinPercentage?: number;
  carbsPercentage?: number;
  fatPercentage?: number;
  medicalProfiles?: IMedicalProfile[];
}

export const defaultValue: Readonly<IDisease> = {};
