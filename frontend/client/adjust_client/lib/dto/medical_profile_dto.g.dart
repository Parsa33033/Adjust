// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'medical_profile_dto.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

MedicalProfileDTO _$MedicalProfileDTOFromJson(Map<String, dynamic> json) {
  return MedicalProfileDTO(
    json['id'] as int,
    json['haveLostWeight'] as bool,
    json['drowsiness'] as bool,
    json['goodNightSleep'] as bool,
    json['hoursOfTraningPerWeek'] as int,
    json['loveForSugar'] as bool,
    json['overEating'] as bool,
    _$enumDecodeNullable(_$MaritalStatusEnumMap, json['maritalStatus']),
    json['childrenNumber'] as int,
    _$enumDecodeNullable(_$EducationEnumMap, json['education']),
    _$enumDecodeNullable(_$OccupationEnumMap, json['occupation']),
    json['hoursOfWorkDaily'] as int,
    _$enumDecodeNullable(_$FinancialStatusEnumMap, json['financialStatus']),
    json['city'] as String,
    json['useOfMedicine'] as bool,
    json['medicines'] as String,
    json['haveAllergy'] as bool,
    json['allergy'] as String,
    json['haveDisease'] as bool,
    _$enumDecodeNullable(_$BloodTypeEnumMap, json['bloodType']),
    json['bodyCompositionId'] as int,
    (json['diseaseList'] as List)
        ?.map((e) =>
            e == null ? null : DiseaseDTO.fromJson(e as Map<String, dynamic>))
        ?.toList(),
  )..bodyComposition = json['bodyComposition'] == null
      ? null
      : BodyCompositionDTO.fromJson(
          json['bodyComposition'] as Map<String, dynamic>);
}

Map<String, dynamic> _$MedicalProfileDTOToJson(MedicalProfileDTO instance) =>
    <String, dynamic>{
      'id': instance.id,
      'haveLostWeight': instance.haveLostWeight,
      'drowsiness': instance.drowsiness,
      'goodNightSleep': instance.goodNightSleep,
      'hoursOfTraningPerWeek': instance.hoursOfTraningPerWeek,
      'loveForSugar': instance.loveForSugar,
      'overEating': instance.overEating,
      'maritalStatus': _$MaritalStatusEnumMap[instance.maritalStatus],
      'childrenNumber': instance.childrenNumber,
      'education': _$EducationEnumMap[instance.education],
      'occupation': _$OccupationEnumMap[instance.occupation],
      'hoursOfWorkDaily': instance.hoursOfWorkDaily,
      'financialStatus': _$FinancialStatusEnumMap[instance.financialStatus],
      'city': instance.city,
      'useOfMedicine': instance.useOfMedicine,
      'medicines': instance.medicines,
      'haveAllergy': instance.haveAllergy,
      'allergy': instance.allergy,
      'haveDisease': instance.haveDisease,
      'bloodType': _$BloodTypeEnumMap[instance.bloodType],
      'bodyCompositionId': instance.bodyCompositionId,
      'diseaseList': instance.diseaseList,
      'bodyComposition': instance.bodyComposition,
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

const _$MaritalStatusEnumMap = {
  MaritalStatus.MARRIED: 'MARRIED',
  MaritalStatus.SINGLE: 'SINGLE',
};

const _$EducationEnumMap = {
  Education.NONE: 'NONE',
  Education.LOW: 'LOW',
  Education.DIPLOMA: 'DIPLOMA',
  Education.BACHELOR: 'BACHELOR',
  Education.MASTERS: 'MASTERS',
  Education.DOCTORATE: 'DOCTORATE',
};

const _$OccupationEnumMap = {
  Occupation.NONE: 'NONE',
  Occupation.STUDENT: 'STUDENT',
  Occupation.FREELANCER: 'FREELANCER',
  Occupation.LABOR: 'LABOR',
  Occupation.EMPLOYEE: 'EMPLOYEE',
  Occupation.MEDICAL: 'MEDICAL',
  Occupation.ENGINEERING: 'ENGINEERING',
  Occupation.MANAGER: 'MANAGER',
  Occupation.HOUSEWIFE: 'HOUSEWIFE',
  Occupation.OTHERS: 'OTHERS',
};

const _$FinancialStatusEnumMap = {
  FinancialStatus.BAD: 'BAD',
  FinancialStatus.NORMAL: 'NORMAL',
  FinancialStatus.GOOD: 'GOOD',
  FinancialStatus.PERFECT: 'PERFECT',
};

const _$BloodTypeEnumMap = {
  BloodType.A: 'A',
  BloodType.B: 'B',
  BloodType.AB: 'AB',
  BloodType.O: 'O',
};
