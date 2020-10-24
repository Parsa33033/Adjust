import 'package:adjust_specialist/dto/food_dto.dart';
import 'package:adjust_specialist/model/nutrition.dart';
import 'package:json_annotation/json_annotation.dart';

import 'meals_for_nutrition_dto.dart';

part 'nutrition_dto.g.dart';

@JsonSerializable()
class NutritionDTO extends Nutrition {
  List<MealsForNutritionDTO> mealTypes;
  List<FoodDTO> foods;

  NutritionDTO(
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

  factory NutritionDTO.fromJson(Map<String, dynamic> json) =>
      _$NutritionDTOFromJson(json);

  Map<String, dynamic> toJson() => _$NutritionDTOToJson(this);
}
