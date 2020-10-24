import 'package:adjust_client/model/chat_message.dart';

class ChatMessageState extends ChatMessage {
  ChatMessageState(
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
}
