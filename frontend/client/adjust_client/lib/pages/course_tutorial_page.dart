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
import 'package:adjust_client/states/course_state.dart';
import 'package:adjust_client/states/tutorial_state.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:persian_number_utility/persian_number_utility.dart';
import 'package:redux/redux.dart';

class CourseTutorialPage extends StatefulWidget {
  CourseState courseState;
  CourseTutorialPage(this.courseState);
  @override
  _CourseTutorialPageState createState() => _CourseTutorialPageState();
}

class _CourseTutorialPageState extends State<CourseTutorialPage> {
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
            t.tutorial.videos + " " + this.widget.courseState.title,
            fontSize: 20,
            color: WHITE_COLOR,
          ),
        ),
        backgroundColor: GREEN_COLOR,
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
                      itemCount: this.widget.courseState.tutorials.length,
                      itemBuilder: (BuildContext context, int pos) {
                        TutorialState tutorialState = this.widget.courseState.tutorials[pos];
                        Uint8List imageByte = Uint8List.fromList(base64Decode(tutorialState.thumbnail));
                        Image image = Image.memory(imageByte);
                        bool isClientTutorial = false;
                        bool isFree = false;
                        state.clientTutorialsState.items.forEach((element) {
                          if (element.id == tutorialState.id) {
                            isClientTutorial = true;
                            return;
                          }
                        });
                        if (tutorialState.tokenPrice == 0) {
                          isClientTutorial = true;
                          isFree = true;
                        };
                        return AdjustInfoButton(
                          id: tutorialState.id.toString(),
                          width: 150,
                          height: 150,
                          title: e == null ? "" : tutorialState.description,
                          description: isFree ? t.main.free : e == null
                              ? ""
                              : isClientTutorial
                              ? ""
                              : getNumber(tutorialState.tokenPrice.round().toString() + " " + t.main.token),
                          name: e == null ? "" : tutorialState.title,
                          fontSize: 16,
                          isVertical: false,
                          primaryColor: (isClientTutorial || isFree) ? WHITE_COLOR : GREEN_COLOR,
                          primaryColorLight:
                          (isClientTutorial || isFree) ? WHITE_COLOR : GREEN_COLOR,
                          secondaryColor: (isClientTutorial || isFree) ? GREEN_COLOR : WHITE_COLOR,
                          image: image,
                          func: () async {
                            if (isClientTutorial) {
                              Navigator.of(context).push(MaterialPageRoute(
                                  builder: (context) => TutorialVideoPage(tutorialState)));
                            } else {
                              if (store.state.clientState.token <
                                  tutorialState.tokenPrice) {
                                showAdjustDialog(
                                    context,
                                    t.tutorial.not_enough_token,
                                    false,
                                    null,
                                    GREEN_COLOR);
                              } else {
                                showAdjustDialog(
                                    context,
                                    t.tutorial.token_deduction(tutorialState.tokenPrice.round().toString()),
                                    true, () async {
                                  int i = await buyTutorial(context, tutorialState.id);
                                  if (i == 1) {
                                    i = await getClientToken(context);
                                    if (i == 1) {
                                      mainPageStreamController.add(1);
                                      Store<AppState> s;
                                      try {
                                        s = StoreProvider.of<AppState>(context);
                                      } catch(e) {
                                        s = store;
                                      }

                                      Navigator.of(context).push(
                                          MaterialPageRoute(
                                              builder: (context) =>
                                                  TutorialVideoPage(tutorialState)));
                                    } else if (i == 0){
                                      showAdjustDialog(context, t.user.failure, false, null, GREEN_COLOR);
                                    }
                                  } else if (i == 0) {
                                    Navigator.of(context).pop();
                                  } else if (i == 2) {
                                    // client has the tutorial already
                                    showAdjustDialog(context, t.tutorial.has_tutorial, false, null, GREEN_COLOR);
                                  } else if (i == 3) {
                                    // client does not have enough token
                                    showAdjustDialog(context, t.tutorial.not_enough_token, false, null, GREEN_COLOR);
                                  }
                                }, GREEN_COLOR);
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
