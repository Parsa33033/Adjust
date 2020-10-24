// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'chat_message_dto.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

ChatMessageDTO _$ChatMessageDTOFromJson(Map<String, dynamic> json) {
  return ChatMessageDTO(
    json['id'] as int,
    json['sender'] as String,
    json['receiver'] as String,
    json['text'] as String,
    json['image'] as String,
    json['imageContentType'] as String,
    json['voice'] as String,
    json['voiceContentType'] as String,
    json['seen'] as bool,
  );
}

Map<String, dynamic> _$ChatMessageDTOToJson(ChatMessageDTO instance) =>
    <String, dynamic>{
      'id': instance.id,
      'sender': instance.sender,
      'receiver': instance.receiver,
      'text': instance.text,
      'image': instance.image,
      'imageContentType': instance.imageContentType,
      'voice': instance.voice,
      'voiceContentType': instance.voiceContentType,
      'seen': instance.seen,
    };
