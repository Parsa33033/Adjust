import 'package:adjust_specialist/model/enum/meal_type.dart';
import 'package:adjust_specialist/model/meals_for_nutrition.dart';

final MealsForNutritionState mealsForNutritionStateInit = MealsForNutritionState(0, MealType.BREAKFAST, 0);

class MealsForNutritionState extends MealsForNutrition {
  MealsForNutritionState(int id, MealType mealType, int nutritionId)
      : super(id, mealType, nutritionId);
}
