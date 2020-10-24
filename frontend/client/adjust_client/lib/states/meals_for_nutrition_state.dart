import 'package:adjust_client/model/enum/meal_type.dart';
import 'package:adjust_client/model/meals_for_nutrition.dart';

final MealsForNutritionState mealsForNutritionStateInit = MealsForNutritionState(0, MealType.BREAKFAST, 0);

class MealsForNutritionState extends MealsForNutrition {
  MealsForNutritionState(int id, MealType mealType, int nutritionId)
      : super(id, mealType, nutritionId);
}
