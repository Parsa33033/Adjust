import 'package:adjust_specialist/model/enum/meal_type.dart';
import 'package:adjust_specialist/model/meal.dart';
import 'package:adjust_specialist/states/nutrition_state.dart';

class MealState extends Meal {
  List<NutritionState> nutritionList;

  MealState(int id, MealType mealType, int number, int nutritionProgramId, this.nutritionList) : super(id, mealType, number, nutritionProgramId);
}
