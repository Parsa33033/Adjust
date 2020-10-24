import 'package:adjust_specialist/model/chat_message.dart';
import 'package:json_annotation/json_annotation.dart';

part 'chat_message_dto.g.dart';

@JsonSerializable()
class ChatMessageDTO extends ChatMessage {
  ChatMessageDTO(
      int id,
      String sender,
      String receiver,
      String text,
      String image,
      String imageContentType,
      String voice,
      String voiceContentType,
      bool seen)
      : super(id, sender, receiver, text, image, imageContentType, voice,
            voiceContentType, seen);

  factory ChatMessageDTO.fromJson(Map<String, dynamic> json) =>
      _$ChatMessageDTOFromJson(json);

  Map<String, dynamic> toJson() => _$ChatMessageDTOToJson(this);
}
