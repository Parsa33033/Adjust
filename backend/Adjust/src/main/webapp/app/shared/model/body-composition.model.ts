import { Moment } from 'moment';
import { Gender } from 'app/shared/model/enumerations/gender.model';
import { BMICategory } from 'app/shared/model/enumerations/bmi-category.model';
import { BodyFatCategory } from 'app/shared/model/enumerations/body-fat-category.model';
import { BodyType } from 'app/shared/model/enumerations/body-type.model';
import { BodyFrameSize } from 'app/shared/model/enumerations/body-frame-size.model';
import { WorkoutLocation } from 'app/shared/model/enumerations/workout-location.model';
import { Goal } from 'app/shared/model/enumerations/goal.model';
import { ActivityType } from 'app/shared/model/enumerations/activity-type.model';
import { WorkoutType } from 'app/shared/model/enumerations/workout-type.model';

export interface IBodyComposition {
  id?: number;
  createdAt?: string;
  age?: number;
  gender?: Gender;
  height?: number;
  weight?: number;
  wrist?: number;
  waist?: number;
  hip?: number;
  abdomen?: number;
  neck?: number;
  waistHipRatio?: number;
  idealWeightMin?: number;
  idealWeightMax?: number;
  bestWeight?: number;
  bmi?: number;
  bmiCategory?: BMICategory;
  bmr?: number;
  lbm?: number;
  pbf?: number;
  bodyFatCategory?: BodyFatCategory;
  smm?: number;
  bodyTypeNumber?: number;
  bodyType?: BodyType;
  frameSize?: number;
  bodyFrameSize?: BodyFrameSize;
  visceralFatLevel?: number;
  workoutLocation?: WorkoutLocation;
  goal?: Goal;
  activityType?: ActivityType;
  workoutType?: WorkoutType;
  medicalProfileId?: number;
  adjustProgramId?: number;
}

export const defaultValue: Readonly<IBodyComposition> = {};
