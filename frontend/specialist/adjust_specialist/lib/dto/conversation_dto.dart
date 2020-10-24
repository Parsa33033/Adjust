import 'package:adjust_specialist/dto/client_dto.dart';
import 'package:adjust_specialist/dto/chat_message_dto.dart';
import 'package:adjust_specialist/dto/specialist_dto.dart';
import 'package:adjust_specialist/model/conversation.dart';
import 'package:json_annotation/json_annotation.dart';

part 'conversation_dto.g.dart';

@JsonSerializable()
class ConversationDTO extends Conversation {
  List<ChatMessageDTO> messages;

  ConversationDTO(int id, int adjustClientId, int specialistId, this.messages)
      : super(id, adjustClientId, specialistId);

  factory ConversationDTO.fromJson(Map<String, dynamic> json) =>
      _$ConversationDTOFromJson(json);

  Map<String, dynamic> toJson() => _$ConversationDTOToJson(this);
}
