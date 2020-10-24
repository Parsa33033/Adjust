import 'dart:async';

import 'package:adjust_specialist/components/adjust_text.dart';
import 'package:adjust_specialist/config/i18n.dart';
import 'package:adjust_specialist/config/stomp.dart';
import 'package:adjust_specialist/constants/adjust_colors.dart';
import 'package:adjust_specialist/dto/chat_message_dto.dart';
import 'package:adjust_specialist/main.dart';
import 'package:adjust_specialist/states/app_state.dart';
import 'package:adjust_specialist/states/client_state.dart';
import 'package:adjust_specialist/states/specialist_state.dart';
import 'package:dash_chat/dash_chat.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:stomp_dart_client/stomp_frame.dart';


class ChatPage extends StatefulWidget {
  SpecialistState specialistState;
  ClientState clientState;
  ChatPage(this.specialistState, this.clientState);

  @override
  _ChatPageState createState() => _ChatPageState();
}


class _ChatPageState extends State<ChatPage> {
  final GlobalKey<DashChatState> _chatViewKey = GlobalKey<DashChatState>();

  ChatUser user;

  ChatUser otherUser;

  List<ChatMessage> messages = List<ChatMessage>();
  var m = List<ChatMessage>();

  var i = 0;

  StompInstance stompInstance;
  String username;
  int clientId;
  int specialistId;
  ClientState clientState;
  SpecialistState specialistState;

  @override
  void initState() {
    super.initState();
    AppState state = store.state;
    username = state.userState.login;
    clientState = this.widget.clientState;
    specialistState = this.widget.specialistState;
    user = ChatUser(
      name: specialistState.username,
      firstName: specialistState.firstName,
      lastName: specialistState.lastName,
      uid: specialistState.username,
    );
    otherUser = ChatUser(
      name: clientState.username,
      uid: clientState.username,
    );
    clientId = clientState.id;
    specialistId = specialistState.id;
    initiateSession();
  }

  void initiateSession() {
    messages = store.state.conversationState.messages.map((e) {
      ChatUser u = e.sender == user.name ? user : otherUser;
      ChatMessage chatMessage = ChatMessage(text: e.text, user: u);
      return chatMessage;
    }).toList();

    mainStompClient.subscribe(destination: "/topic/"+ StompInstance.getUsername() +"/reply", headers: StompInstance.getHeaders(), callback: (StompFrame frame) {
      ChatMessage chatMessage = ChatMessage(
          text: frame.body.toString(),
          user: otherUser
      );
      if (this.mounted) {
        setState(() {
          messages.add(chatMessage);
        });
      }
    });
  }

  void systemMessage() {
    Timer(Duration(milliseconds: 300), () {
      if (i < 6) {
        setState(() {
          messages = [...messages, m[i]];
        });
        i++;
      }
      Timer(Duration(milliseconds: 300), () {
        _chatViewKey.currentState.scrollController
          ..animateTo(
            _chatViewKey.currentState.scrollController.position.maxScrollExtent,
            curve: Curves.easeOut,
            duration: const Duration(milliseconds: 300),
          );
      });
    });
  }

  void onSend(ChatMessage message) async {
    ChatMessageDTO messageDTO = ChatMessageDTO(0, username, this.widget.specialistState.username, message.text, "", "", "", "", false);
    StompInstance.sendMessage(context, mainStompClient, messageDTO);

    setState(() {
      messages = [...messages, message];
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Align(
            alignment: adjustAlignment,
            child: AdjustText(
              t.main.questions + " " + clientState.firstName + " " + clientState.lastName,
              fontSize: 20, color: WHITE_COLOR
            )
          ),
          backgroundColor: YELLOW_COLOR,
          elevation: 4,
          leading: InkWell(
            child: Icon(Icons.arrow_back),
            onTap: () {
              Navigator.of(context).pop();
            },
          ),
        ),
        body: Container(
          alignment: adjustAlignment,
          child: DashChat(
            inputTextDirection: txtDir,
            key: _chatViewKey,
            inverted: false,
            onSend: onSend,
            sendOnEnter: true,
            textInputAction: TextInputAction.send,
            user: user,
            inputDecoration:
            InputDecoration.collapsed(hintText: t.main.message),
            dateFormat: DateFormat('yyyy-MMM-dd'),
            timeFormat: DateFormat('HH:mm'),
            messages: messages,
            showUserAvatar: false,
            showAvatarForEveryMessage: false,
            scrollToBottom: true,
            onPressAvatar: (ChatUser user) {
              print("OnPressAvatar: ${user.name}");
            },
            onLongPressAvatar: (ChatUser user) {
              print("OnLongPressAvatar: ${user.name}");
            },
            inputMaxLines: 5,
            messageContainerPadding: EdgeInsets.only(left: 5.0, right: 5.0),
            alwaysShowSend: true,
            inputTextStyle: TextStyle(fontSize: 16.0, fontFamily: "Iransans"),
            inputContainerStyle: BoxDecoration(
              border: Border.all(width: 0.0),
              color: Colors.white,
            ),
            onQuickReply: (Reply reply) {
              setState(() {
                messages.add(ChatMessage(
                    text: reply.value, createdAt: DateTime.now(), user: user, ));
                messages = [...messages];
              });

              Timer(Duration(milliseconds: 300), () {
                _chatViewKey.currentState.scrollController
                  ..animateTo(
                    _chatViewKey
                        .currentState.scrollController.position.maxScrollExtent,
                    curve: Curves.easeOut,
                    duration: const Duration(milliseconds: 300),
                  );

                if (i == 0) {
                  systemMessage();
                  Timer(Duration(milliseconds: 600), () {
                    systemMessage();
                  });
                } else {
                  systemMessage();
                }
              });
            },
            onLoadEarlier: () {
              print("loading...");
            },
            shouldShowLoadEarlier: false,
            showTraillingBeforeSend: true,
          ),
        )
    );
  }
}
