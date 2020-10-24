import { MoveType } from 'app/shared/model/enumerations/move-type.model';
import { WorkoutLocation } from 'app/shared/model/enumerations/workout-location.model';

export interface IMove {
  id?: number;
  name?: string;
  description?: any;
  muscleName?: string;
  moveType?: MoveType;
  place?: WorkoutLocation;
  equipment?: string;
  pictureContentType?: string;
  picture?: any;
}

export const defaultValue: Readonly<IMove> = {};
