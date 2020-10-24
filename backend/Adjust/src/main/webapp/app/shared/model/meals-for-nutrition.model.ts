import { MealType } from 'app/shared/model/enumerations/meal-type.model';

export interface IMealsForNutrition {
  id?: number;
  mealType?: MealType;
  nutritionId?: number;
}

export const defaultValue: Readonly<IMealsForNutrition> = {};
