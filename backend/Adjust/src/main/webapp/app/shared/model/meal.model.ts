import { INutrition } from 'app/shared/model/nutrition.model';
import { MealType } from 'app/shared/model/enumerations/meal-type.model';

export interface IMeal {
  id?: number;
  mealType?: MealType;
  number?: number;
  nutritions?: INutrition[];
  nutritionProgramId?: number;
}

export const defaultValue: Readonly<IMeal> = {};
