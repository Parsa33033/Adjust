

class ChatMessage {
  int id;

  String sender;

  String receiver;

  String text;

  String image;

  String imageContentType;

  String voice;

  String voiceContentType;
  bool seen;

  ChatMessage(
      this.id,
      this.sender,
      this.receiver,
      this.text,
      this.image,
      this.imageContentType,
      this.voice,
      this.voiceContentType,
      this.seen);
}
