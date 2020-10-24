import 'package:adjust_client/dto/nutrition_dto.dart';
import 'package:adjust_client/model/enum/meal_type.dart';
import 'package:adjust_client/model/meal.dart';
import 'package:json_annotation/json_annotation.dart';

part 'meal_dto.g.dart';

@JsonSerializable()
class MealDTO extends Meal {
  List<NutritionDTO> nutritionList;

  MealDTO(int id, MealType mealType, int number, int nutritionProgramId, this.nutritionList)
      : super(id, mealType, number, nutritionProgramId);

  factory MealDTO.fromJson(Map<String, dynamic> json) =>
      _$MealDTOFromJson(json);

  Map<String, dynamic> toJson() => _$MealDTOToJson(this);
}
