// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'program_dto.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

ProgramDTO _$ProgramDTOFromJson(Map<String, dynamic> json) {
  return ProgramDTO(
    json['id'] as int,
    json['createdAt'] == null
        ? null
        : DateTime.parse(json['createdAt'] as String),
    json['expirationDate'] == null
        ? null
        : DateTime.parse(json['expirationDate'] as String),
    json['medicalProfileDone'] as bool,
    json['bodyCompositionDone'] as bool,
    json['nutritionDone'] as bool,
    json['fitnessDone'] as bool,
    json['paid'] as bool,
    json['seenByClient'] as bool,
    json['seenBySpecialist'] as bool,
    json['designed'] as bool,
    json['bodyCompositionId'] as int,
    json['fitnessProgramId'] as int,
    json['nutritionProgramId'] as int,
    json['adjustClientId'] as int,
    json['specialistId'] as int,
    json['bodyComposition'] == null
        ? null
        : BodyCompositionDTO.fromJson(
            json['bodyComposition'] as Map<String, dynamic>),
    json['fitnessProgram'] == null
        ? null
        : FitnessProgramDTO.fromJson(
            json['fitnessProgram'] as Map<String, dynamic>),
    json['nutritionProgram'] == null
        ? null
        : NutritionProgramDTO.fromJson(
            json['nutritionProgram'] as Map<String, dynamic>),
    (json['developments'] as List)
        ?.map((e) => e == null
            ? null
            : DevelopmentDTO.fromJson(e as Map<String, dynamic>))
        ?.toList(),
    json['adjustClient'] == null
        ? null
        : ClientDTO.fromJson(json['adjustClient'] as Map<String, dynamic>),
    json['specialist'] == null
        ? null
        : SpecialistDTO.fromJson(json['specialist'] as Map<String, dynamic>),
  );
}

Map<String, dynamic> _$ProgramDTOToJson(ProgramDTO instance) =>
    <String, dynamic>{
      'id': instance.id,
      'createdAt': instance.createdAt?.toIso8601String(),
      'expirationDate': instance.expirationDate?.toIso8601String(),
      'medicalProfileDone': instance.medicalProfileDone,
      'bodyCompositionDone': instance.bodyCompositionDone,
      'nutritionDone': instance.nutritionDone,
      'fitnessDone': instance.fitnessDone,
      'paid': instance.paid,
      'seenByClient': instance.seenByClient,
      'seenBySpecialist': instance.seenBySpecialist,
      'designed': instance.designed,
      'bodyCompositionId': instance.bodyCompositionId,
      'fitnessProgramId': instance.fitnessProgramId,
      'nutritionProgramId': instance.nutritionProgramId,
      'adjustClientId': instance.adjustClientId,
      'specialistId': instance.specialistId,
      'bodyComposition': instance.bodyComposition,
      'fitnessProgram': instance.fitnessProgram,
      'nutritionProgram': instance.nutritionProgram,
      'developments': instance.developments,
      'adjustClient': instance.adjustClient,
      'specialist': instance.specialist,
    };
