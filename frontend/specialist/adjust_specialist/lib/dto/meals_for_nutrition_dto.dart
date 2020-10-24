import 'package:adjust_specialist/model/enum/meal_type.dart';
import 'package:adjust_specialist/model/meals_for_nutrition.dart';
import 'package:json_annotation/json_annotation.dart';
part 'meals_for_nutrition_dto.g.dart';

@JsonSerializable()
class MealsForNutritionDTO extends MealsForNutrition {
  MealsForNutritionDTO(int id, MealType mealType, int nutritionId)
      : super(id, mealType, nutritionId);

  factory MealsForNutritionDTO.fromJson(Map<String, dynamic> json) =>
      _$MealsForNutritionDTOFromJson(json);

  Map<String, dynamic> toJson() => _$MealsForNutritionDTOToJson(this);
}
