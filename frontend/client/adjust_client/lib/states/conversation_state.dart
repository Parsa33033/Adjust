
import 'package:adjust_client/states/chat_message_state.dart';

final ConversationState conversationStateInit = ConversationState(List());

class ConversationState {
  List<ChatMessageState> messages;

  ConversationState(this.messages);
}