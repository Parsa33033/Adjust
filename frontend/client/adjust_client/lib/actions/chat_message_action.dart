import 'dart:convert';
import 'dart:io';

import 'package:adjust_client/actions/jwt.dart';
import 'package:adjust_client/constants/urls.dart';
import 'package:adjust_client/dto/chat_message_dto.dart';
import 'package:adjust_client/main.dart';
import 'package:adjust_client/model/chat_message.dart';
import 'package:adjust_client/states/app_state.dart';
import 'package:adjust_client/states/chat_message_state.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:http/http.dart' as http;

class GetMessagesAction {
  List<ChatMessageState> payload;

  GetMessagesAction({this.payload});
}

Future<int> getMessages(
    BuildContext context, int clientId, int specialistId) async {
  String jwt = await getJwt(context);

  Map<String, String> headers = Map<String, String>()
    ..putIfAbsent("Authorization", () => "Bearer " + jwt);

  http.Response response = await http.get(
      CONVERSATION_URL + "?client-id=${clientId}&specialist-id=${specialistId}",
      headers: headers);
  if (response.statusCode == HttpStatus.ok) {
    List l = jsonDecode(utf8.decode(response.bodyBytes));
    List<ChatMessageState> messageStateList = l.map((e) {
      ChatMessageDTO chatMessageDTO = ChatMessageDTO.fromJson(e);
      ChatMessageState messageState = ChatMessageState(
          chatMessageDTO.id,
          chatMessageDTO.sender,
          chatMessageDTO.receiver,
          chatMessageDTO.text,
          chatMessageDTO.image,
          chatMessageDTO.imageContentType,
          chatMessageDTO.voice,
          chatMessageDTO.voiceContentType,
          chatMessageDTO.seen);
      return messageState;
    }).toList();

    try {
      StoreProvider.of<AppState>(context)
          .dispatch(GetMessagesAction(payload: messageStateList));
    } catch (e) {
      store.dispatch(GetMessagesAction(payload: messageStateList));
    }
    return 1;
  }
  return 0;
}
