

import 'enum/meal_type.dart';

class Meal {
  int id;

  MealType mealType;

  int number;

  int nutritionProgramId;

  Meal(this.id, this.mealType, this.number, this.nutritionProgramId);
}