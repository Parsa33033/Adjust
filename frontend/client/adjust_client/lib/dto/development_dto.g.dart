// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'development_dto.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

DevelopmentDTO _$DevelopmentDTOFromJson(Map<String, dynamic> json) {
  return DevelopmentDTO(
    json['id'] as int,
    json['date'] == null ? null : DateTime.parse(json['date'] as String),
    (json['nutritionScore'] as num)?.toDouble(),
    (json['fitnessScore'] as num)?.toDouble(),
    (json['weight'] as num)?.toDouble(),
    (json['bmi'] as num)?.toDouble(),
    (json['lbm'] as num)?.toDouble(),
    (json['pbf'] as num)?.toDouble(),
    json['adjustProgramId'] as int,
  );
}

Map<String, dynamic> _$DevelopmentDTOToJson(DevelopmentDTO instance) =>
    <String, dynamic>{
      'id': instance.id,
      'date': instance.date?.toIso8601String(),
      'nutritionScore': instance.nutritionScore,
      'fitnessScore': instance.fitnessScore,
      'weight': instance.weight,
      'bmi': instance.bmi,
      'lbm': instance.lbm,
      'pbf': instance.pbf,
      'adjustProgramId': instance.adjustProgramId,
    };
