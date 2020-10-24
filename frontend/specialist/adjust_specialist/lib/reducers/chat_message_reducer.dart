

import 'package:adjust_specialist/actions/chat_message_action.dart';
import 'package:adjust_specialist/states/app_state.dart';
import 'package:adjust_specialist/states/chat_message_state.dart';
import 'package:adjust_specialist/states/conversation_state.dart';

AppState messageReducer(AppState state, dynamic action) {
  if (action is GetMessagesAction) {
    ConversationState conversationState = state.conversationState;
    conversationState.messages = action.payload != null ? action.payload : conversationState;
    state.conversationState = conversationState;
    return state;
  } else {
    return state;
  }
}