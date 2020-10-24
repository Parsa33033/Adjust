import { Moment } from 'moment';

export interface IDevelopment {
  id?: number;
  date?: string;
  nutritionScore?: number;
  fitnessScore?: number;
  weight?: number;
  bmi?: number;
  lbm?: number;
  pbf?: number;
  adjustProgramId?: number;
}

export const defaultValue: Readonly<IDevelopment> = {};
