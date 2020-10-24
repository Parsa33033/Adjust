import { IMealsForNutrition } from 'app/shared/model/meals-for-nutrition.model';
import { IFood } from 'app/shared/model/food.model';
import { IMeal } from 'app/shared/model/meal.model';

export interface INutrition {
  id?: number;
  name?: string;
  description?: any;
  unit?: number;
  minUnitLow?: number;
  minUnitNormal?: number;
  minUnitMax?: number;
  caloryBoundryMin?: number;
  caloryBoundryMax?: number;
  caloriesPerUnit?: number;
  proteinPerUnit?: number;
  carbsPerUnit?: number;
  fatInUnit?: number;
  mealTypes?: IMealsForNutrition[];
  foods?: IFood[];
  meals?: IMeal[];
}

export const defaultValue: Readonly<INutrition> = {};
