import 'package:adjust_client/model/nutrition.dart';
import 'package:adjust_client/states/food_state.dart';

import 'meals_for_nutrition_state.dart';

class NutritionState extends Nutrition {
  List<MealsForNutritionState> mealTypes;
  List<FoodState> foods;

  NutritionState(
      int id,
      String name,
      String description,
      int unit,
      int adjustNutritionId,
      int minUnitLow,
      int minUnitNormal,
      int minUnitMax,
      int caloryBoundryMin,
      int caloryBoundryMax,
      int caloriesPerUnit,
      int proteinPerUnit,
      int carbsPerUnit,
      int fatInUnit,
      this.mealTypes,
      this.foods)
      : super(
            id,
            name,
            description,
            unit,
            adjustNutritionId,
            minUnitLow,
            minUnitNormal,
            minUnitMax,
            caloryBoundryMin,
            caloryBoundryMax,
            caloriesPerUnit,
            proteinPerUnit,
            carbsPerUnit,
            fatInUnit);
}
