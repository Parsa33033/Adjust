

import 'package:adjust_client/actions/chat_message_action.dart';
import 'package:adjust_client/states/app_state.dart';
import 'package:adjust_client/states/chat_message_state.dart';
import 'package:adjust_client/states/conversation_state.dart';

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