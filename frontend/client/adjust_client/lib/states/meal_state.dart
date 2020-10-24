import 'package:adjust_client/model/enum/meal_type.dart';
import 'package:adjust_client/model/meal.dart';
import 'package:adjust_client/states/nutrition_state.dart';

class MealState extends Meal {
  List<NutritionState> nutritionList;

  MealState(int id, MealType mealType, int number, int nutritionProgramId, this.nutritionList) : super(id, mealType, number, nutritionProgramId);
}
