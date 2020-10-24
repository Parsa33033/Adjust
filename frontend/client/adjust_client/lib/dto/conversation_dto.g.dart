// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'conversation_dto.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

ConversationDTO _$ConversationDTOFromJson(Map<String, dynamic> json) {
  return ConversationDTO(
    json['id'] as int,
    json['adjustClientId'] as int,
    json['specialistId'] as int,
    (json['messages'] as List)
        ?.map((e) => e == null
            ? null
            : ChatMessageDTO.fromJson(e as Map<String, dynamic>))
        ?.toList(),
  );
}

Map<String, dynamic> _$ConversationDTOToJson(ConversationDTO instance) =>
    <String, dynamic>{
      'id': instance.id,
      'adjustClientId': instance.adjustClientId,
      'specialistId': instance.specialistId,
      'messages': instance.messages,
    };
