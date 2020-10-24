// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'disease_dto.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

DiseaseDTO _$DiseaseDTOFromJson(Map<String, dynamic> json) {
  return DiseaseDTO(
    json['id'] as int,
    json['name'] as String,
    json['proteinPercentage'] as int,
    json['carbsPercentage'] as int,
    json['fatPercentage'] as int,
  );
}

Map<String, dynamic> _$DiseaseDTOToJson(DiseaseDTO instance) =>
    <String, dynamic>{
      'id': instance.id,
      'name': instance.name,
      'proteinPercentage': instance.proteinPercentage,
      'carbsPercentage': instance.carbsPercentage,
      'fatPercentage': instance.fatPercentage,
    };
