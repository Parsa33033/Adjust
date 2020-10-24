import { IDisease } from 'app/shared/model/disease.model';
import { MaritalStatus } from 'app/shared/model/enumerations/marital-status.model';
import { Education } from 'app/shared/model/enumerations/education.model';
import { Occupation } from 'app/shared/model/enumerations/occupation.model';
import { FinancialStatus } from 'app/shared/model/enumerations/financial-status.model';
import { BloodType } from 'app/shared/model/enumerations/blood-type.model';

export interface IMedicalProfile {
  id?: number;
  haveLostWeight?: boolean;
  drowsiness?: boolean;
  goodNightSleep?: boolean;
  hoursOfTraningPerWeek?: number;
  loveForSugar?: boolean;
  overEating?: boolean;
  maritalStatus?: MaritalStatus;
  childrenNumber?: number;
  education?: Education;
  occupation?: Occupation;
  hoursOfWorkDaily?: number;
  financialStatus?: FinancialStatus;
  city?: string;
  useOfMedicine?: boolean;
  medicines?: any;
  haveAllergy?: boolean;
  allergy?: string;
  haveDisease?: boolean;
  bloodType?: BloodType;
  bodyCompositionId?: number;
  diseases?: IDisease[];
  adjustClientId?: number;
}

export const defaultValue: Readonly<IMedicalProfile> = {
  haveLostWeight: false,
  drowsiness: false,
  goodNightSleep: false,
  loveForSugar: false,
  overEating: false,
  useOfMedicine: false,
  haveAllergy: false,
  haveDisease: false,
};
