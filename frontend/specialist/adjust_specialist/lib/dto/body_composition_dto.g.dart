// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'body_composition_dto.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

BodyCompositionDTO _$BodyCompositionDTOFromJson(Map<String, dynamic> json) {
  return BodyCompositionDTO(
    json['id'] as int,
    json['createdAt'] == null
        ? null
        : DateTime.parse(json['createdAt'] as String),
    json['age'] as int,
    _$enumDecodeNullable(_$GenderEnumMap, json['gender']),
    (json['height'] as num)?.toDouble(),
    (json['weight'] as num)?.toDouble(),
    (json['wrist'] as num)?.toDouble(),
    (json['waist'] as num)?.toDouble(),
    (json['hip'] as num)?.toDouble(),
    (json['abdomen'] as num)?.toDouble(),
    (json['neck'] as num)?.toDouble(),
    (json['waistHipRatio'] as num)?.toDouble(),
    (json['idealWeightMin'] as num)?.toDouble(),
    (json['idealWeightMax'] as num)?.toDouble(),
    (json['bestWeight'] as num)?.toDouble(),
    (json['bmi'] as num)?.toDouble(),
    _$enumDecodeNullable(_$BMICategoryEnumMap, json['bmiCategory']),
    (json['bmr'] as num)?.toDouble(),
    (json['lbm'] as num)?.toDouble(),
    (json['pbf'] as num)?.toDouble(),
    _$enumDecodeNullable(_$BodyFatCategoryEnumMap, json['bodyFatCategory']),
    (json['smm'] as num)?.toDouble(),
    (json['bodyTypeNumber'] as num)?.toDouble(),
    _$enumDecodeNullable(_$BodyTypeEnumMap, json['bodyType']),
    (json['frameSize'] as num)?.toDouble(),
    _$enumDecodeNullable(_$BodyFrameSizeEnumMap, json['bodyFrameSize']),
    (json['visceralFatLevel'] as num)?.toDouble(),
    _$enumDecodeNullable(_$WorkoutLocationEnumMap, json['workoutLocation']),
    _$enumDecodeNullable(_$GoalEnumMap, json['goal']),
    _$enumDecodeNullable(_$ActivityTypeEnumMap, json['activityType']),
    _$enumDecodeNullable(_$WorkoutTypeEnumMap, json['workoutType']),
  );
}

Map<String, dynamic> _$BodyCompositionDTOToJson(BodyCompositionDTO instance) =>
    <String, dynamic>{
      'id': instance.id,
      'createdAt': instance.createdAt?.toIso8601String(),
      'age': instance.age,
      'gender': _$GenderEnumMap[instance.gender],
      'height': instance.height,
      'weight': instance.weight,
      'wrist': instance.wrist,
      'waist': instance.waist,
      'hip': instance.hip,
      'abdomen': instance.abdomen,
      'neck': instance.neck,
      'waistHipRatio': instance.waistHipRatio,
      'idealWeightMin': instance.idealWeightMin,
      'idealWeightMax': instance.idealWeightMax,
      'bestWeight': instance.bestWeight,
      'bmi': instance.bmi,
      'bmiCategory': _$BMICategoryEnumMap[instance.bmiCategory],
      'bmr': instance.bmr,
      'lbm': instance.lbm,
      'pbf': instance.pbf,
      'bodyFatCategory': _$BodyFatCategoryEnumMap[instance.bodyFatCategory],
      'smm': instance.smm,
      'bodyTypeNumber': instance.bodyTypeNumber,
      'bodyType': _$BodyTypeEnumMap[instance.bodyType],
      'frameSize': instance.frameSize,
      'bodyFrameSize': _$BodyFrameSizeEnumMap[instance.bodyFrameSize],
      'visceralFatLevel': instance.visceralFatLevel,
      'workoutLocation': _$WorkoutLocationEnumMap[instance.workoutLocation],
      'goal': _$GoalEnumMap[instance.goal],
      'activityType': _$ActivityTypeEnumMap[instance.activityType],
      'workoutType': _$WorkoutTypeEnumMap[instance.workoutType],
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

const _$GenderEnumMap = {
  Gender.MALE: 'MALE',
  Gender.FEMALE: 'FEMALE',
};

const _$BMICategoryEnumMap = {
  BMICategory.UNDERWEIGHT: 'UNDERWEIGHT',
  BMICategory.NORMAL: 'NORMAL',
  BMICategory.OVERWEIGHT: 'OVERWEIGHT',
  BMICategory.OBESE: 'OBESE',
  BMICategory.HIGHOBESE: 'HIGHOBESE',
  BMICategory.EXTRAOBESE: 'EXTRAOBESE',
};

const _$BodyFatCategoryEnumMap = {
  BodyFatCategory.ESSENTIAL: 'ESSENTIAL',
  BodyFatCategory.ATHLETE: 'ATHLETE',
  BodyFatCategory.FITNESS: 'FITNESS',
  BodyFatCategory.AVERAGE: 'AVERAGE',
  BodyFatCategory.OBESE: 'OBESE',
};

const _$BodyTypeEnumMap = {
  BodyType.MESOMORPH: 'MESOMORPH',
  BodyType.ECTOMORPH: 'ECTOMORPH',
  BodyType.ENDOMORPH: 'ENDOMORPH',
};

const _$BodyFrameSizeEnumMap = {
  BodyFrameSize.SMALL: 'SMALL',
  BodyFrameSize.MEDIUM: 'MEDIUM',
  BodyFrameSize.LARGE: 'LARGE',
};

const _$WorkoutLocationEnumMap = {
  WorkoutLocation.HOME: 'HOME',
  WorkoutLocation.GYM: 'GYM',
};

const _$GoalEnumMap = {
  Goal.DECREASEWEIGHT: 'DECREASEWEIGHT',
  Goal.INCREASEWEIGHT: 'INCREASEWEIGHT',
};

const _$ActivityTypeEnumMap = {
  ActivityType.SEDENTARY: 'SEDENTARY',
  ActivityType.LIGHT: 'LIGHT',
  ActivityType.MODERATE: 'MODERATE',
  ActivityType.HIGH: 'HIGH',
  ActivityType.EXTRA: 'EXTRA',
};

const _$WorkoutTypeEnumMap = {
  WorkoutType.POWERLIFTING: 'POWERLIFTING',
  WorkoutType.BODYBUILDING: 'BODYBUILDING',
  WorkoutType.BODYBUILDING_CLASSIC: 'BODYBUILDING_CLASSIC',
  WorkoutType.FITNESS: 'FITNESS',
  WorkoutType.ENDURANCE: 'ENDURANCE',
  WorkoutType.CARDIO: 'CARDIO',
};
