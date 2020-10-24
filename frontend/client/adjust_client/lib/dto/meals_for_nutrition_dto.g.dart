// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'meals_for_nutrition_dto.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

MealsForNutritionDTO _$MealsForNutritionDTOFromJson(Map<String, dynamic> json) {
  return MealsForNutritionDTO(
    json['id'] as int,
    _$enumDecodeNullable(_$MealTypeEnumMap, json['mealType']),
    json['nutritionId'] as int,
  );
}

Map<String, dynamic> _$MealsForNutritionDTOToJson(
        MealsForNutritionDTO instance) =>
    <String, dynamic>{
      'id': instance.id,
      'mealType': _$MealTypeEnumMap[instance.mealType],
      'nutritionId': instance.nutritionId,
    };

T _$enumDecode<T>(
  Map<T, dynamic> enumValues,
  dynamic source, {
  T unknownValue,
}) {
  if (source == null) {
    throw ArgumentError('A value must be provided. Supported values: '
        '${enumValues.values.join(', ')}');
  }

  final value = enumValues.entries
      .singleWhere((e) => e.value == source, orElse: () => null)
      ?.key;

  if (value == null && unknownValue == null) {
    throw ArgumentError('`$source` is not one of the supported values: '
        '${enumValues.values.join(', ')}');
  }
  return value ?? unknownValue;
}

T _$enumDecodeNullable<T>(
  Map<T, dynamic> enumValues,
  dynamic source, {
  T unknownValue,
}) {
  if (source == null) {
    return null;
  }
  return _$enumDecode<T>(enumValues, source, unknownValue: unknownValue);
}

const _$MealTypeEnumMap = {
  MealType.BREAKFAST: 'BREAKFAST',
  MealType.LUNCH: 'LUNCH',
  MealType.DINNER: 'DINNER',
  MealType.BRUNCH: 'BRUNCH',
  MealType.EVEMEAL: 'EVEMEAL',
  MealType.AFTERNOONMEAL: 'AFTERNOONMEAL',
  MealType.BEFORETRAINING: 'BEFORETRAINING',
  MealType.AFTERTRAINING: 'AFTERTRAINING',
  MealType.MIDNIGHTSUPPER: 'MIDNIGHTSUPPER',
  MealType.IFTAR: 'IFTAR',
  MealType.SNACK1: 'SNACK1',
  MealType.SNACK2: 'SNACK2',
  MealType.SNACK3: 'SNACK3',
  MealType.SNACK4: 'SNACK4',
  MealType.SNACK5: 'SNACK5',
  MealType.SNACK6: 'SNACK6',
};
