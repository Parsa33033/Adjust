// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'move_dto.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

MoveDTO _$MoveDTOFromJson(Map<String, dynamic> json) {
  return MoveDTO(
    json['id'] as int,
    json['name'] as String,
    json['description'] as String,
    json['muscleName'] as String,
    _$enumDecodeNullable(_$MoveTypeEnumMap, json['moveType']),
    _$enumDecodeNullable(_$WorkoutLocationEnumMap, json['place']),
    json['equipment'] as String,
    json['picture'] as String,
    json['pictureContentType'] as String,
  );
}

Map<String, dynamic> _$MoveDTOToJson(MoveDTO instance) => <String, dynamic>{
      'id': instance.id,
      'name': instance.name,
      'description': instance.description,
      'muscleName': instance.muscleName,
      'moveType': _$MoveTypeEnumMap[instance.moveType],
      'place': _$WorkoutLocationEnumMap[instance.place],
      'equipment': instance.equipment,
      'picture': instance.picture,
      'pictureContentType': instance.pictureContentType,
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

const _$MoveTypeEnumMap = {
  MoveType.CHEST: 'CHEST',
  MoveType.SHOULDER: 'SHOULDER',
  MoveType.TRAPEZOID: 'TRAPEZOID',
  MoveType.BACK: 'BACK',
  MoveType.LATERAL: 'LATERAL',
  MoveType.BICEP: 'BICEP',
  MoveType.TRICEP: 'TRICEP',
  MoveType.FOREARM: 'FOREARM',
  MoveType.LEG: 'LEG',
  MoveType.HAMSTRING: 'HAMSTRING',
  MoveType.GLUTES: 'GLUTES',
  MoveType.CALVES: 'CALVES',
  MoveType.ABS: 'ABS',
  MoveType.AEROBICS: 'AEROBICS',
  MoveType.TRX: 'TRX',
  MoveType.MULTIMUSCLE: 'MULTIMUSCLE',
  MoveType.MISC: 'MISC',
};

const _$WorkoutLocationEnumMap = {
  WorkoutLocation.HOME: 'HOME',
  WorkoutLocation.GYM: 'GYM',
};
