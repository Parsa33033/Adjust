import 'dart:convert';
import 'dart:typed_data';
import 'dart:math';

import 'package:adjust_client/actions/shopping_action.dart';
import 'package:adjust_client/actions/client_action.dart';
import 'package:adjust_client/actions/tutorial_action.dart';
import 'package:adjust_client/components/adjust_dialog.dart';
import 'package:adjust_client/components/adjust_info_button.dart';
import 'package:adjust_client/components/adjust_raised_button.dart';
import 'package:adjust_client/components/adjust_text.dart';
import 'package:adjust_client/components/dashboard.dart';
import 'package:adjust_client/components/preloader.dart';
import 'package:adjust_client/config/i18n.dart';
import 'package:adjust_client/constants/adjust_colors.dart';
import 'package:adjust_client/constants/words.dart';
import 'package:adjust_client/main.dart';
import 'package:adjust_client/model/shopping_item.dart';
import 'package:adjust_client/model/tutorial.dart';
import 'package:adjust_client/notifications/adjust_state_change_notification.dart';
import 'package:adjust_client/pages/main_page.dart';
import 'package:adjust_client/pages/order_page.dart';
import 'package:adjust_client/pages/tutorial_video_page.dart';
import 'package:adjust_client/states/app_state.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:persian_number_utility/persian_number_utility.dart';
import 'package:redux/redux.dart';

class TutorialPage extends StatefulWidget {
  @override
  _TutorialPageState createState() => _TutorialPageState();
}

class _TutorialPageState extends State<TutorialPage> {
  @override
  void initState() {
    // TODO: implement initState
    super.initState();
  }


  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Align(
          alignment: adjustAlignment,
          child: AdjustText(
            t.tutorial.videos,
            fontSize: 20,
            color: WHITE_COLOR,
          ),
        ),
        backgroundColor: ORANGE_COLOR,
        elevation: 4,
        leading: InkWell(
          child: Icon(Icons.arrow_back),
          onTap: () {
            Navigator.of(context).pop();
          },
        ),
      ),
      body: StoreConnector<AppState, AppState>(
        converter: (Store store) => store.state,
        builder: (BuildContext context, AppState state) {
          return Container(
            color: LIGHT_GREY_COLOR,
            child: Column(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: <Widget>[
                Expanded(
                  child: ListView.builder(
                      itemCount: state.tutorialListState.items.length,
                      itemBuilder: (BuildContext context, int pos) {
                        Tutorial e = state.tutorialListState.items[pos];
                        Uint8List imageByte = Uint8List.fromList(base64Decode(e.thumbnail));
                        Image image = Image.memory(imageByte);
                        bool isClientTutorial = false;
                        state.clientTutorialsState.items.forEach((element) {
                          if (element.title == e.title) {
                            isClientTutorial = true;
                            return;
                          }
                        });
                        return AdjustInfoButton(
                          id: e.id.toString(),
                          width: 150,
                          height: 150,
                          title: e == null ? "" : e.description,
                          description: e == null
                              ? ""
                              : isClientTutorial
                                  ? ""
                                  : getNumber(e.tokenPrice.round().toString() + " " + t.main.token),
                          name: e == null ? "" : e.title,
                          fontSize: 14,
                          isVertical: false,
                          primaryColor: isClientTutorial ? ORANGE_COLOR : ORANGE_COLOR,
                          primaryColorLight:
                              isClientTutorial ? ORANGE_COLOR : ORANGE_COLOR,
                          secondaryColor: isClientTutorial ? WHITE_COLOR : WHITE_COLOR,
                          image: image,
                          func: () async {
                            if (isClientTutorial) {
                              Navigator.of(context).push(MaterialPageRoute(
                                  builder: (context) => TutorialVideoPage(e)));
                            } else {
                              if (store.state.clientState.token <
                                  e.tokenPrice) {
                                showAdjustDialog(
                                    context,
                                    t.tutorial.not_enough_token,
                                    false,
                                    null,
                                    ORANGE_COLOR);
                              } else {
                                showAdjustDialog(
                                    context,
                                    t.tutorial.token_deduction(e.tokenPrice.round().toString()),
                                    true, () async {
                                  int i = await buyTutorial(context, e.id);
                                  if (i == 1) {
                                    i = await getClientToken(context);
                                    if (i == 1) {
                                      mainPageStreamController.add(1);
                                      Navigator.of(context).push(
                                          MaterialPageRoute(
                                              builder: (context) =>
                                                  TutorialVideoPage(e)));
                                    } else if (i == 0){
                                      showAdjustDialog(context, t.user.failure, false, null, ORANGE_COLOR);
                                    }
                                  } else if (i == 0) {
                                    Navigator.of(context).pop();
                                  } else if (i == 2) {
                                    // client has the tutorial already
                                    showAdjustDialog(context, t.tutorial.has_tutorial, false, null, ORANGE_COLOR);
                                  } else if (i == 3) {
                                    // client does not have enough token
                                    showAdjustDialog(context, t.tutorial.not_enough_token, false, null, ORANGE_COLOR);
                                  }
                                }, ORANGE_COLOR);
                              }

                            }
                          },
                        );
                      }),
                ),
              ],
            ),
          );
        },
      ),
    );
  }
}
