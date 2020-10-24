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
import 'package:adjust_client/pages/course_tutorial_page.dart';
import 'package:adjust_client/pages/main_page.dart';
import 'package:adjust_client/pages/order_page.dart';
import 'package:adjust_client/pages/tutorial_video_page.dart';
import 'package:adjust_client/states/app_state.dart';
import 'package:adjust_client/states/course_state.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:persian_number_utility/persian_number_utility.dart';
import 'package:redux/redux.dart';

class CoursePage extends StatefulWidget {
  @override
  _CoursePageState createState() => _CoursePageState();
}

class _CoursePageState extends State<CoursePage> {
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
            t.tutorial.courses,
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
                  child: state.courseListState.courses.length <= 0 ?
                  Container(
                      child: Center(
                        child: AdjustText(t.main.empty_list(t.tutorial.courses)),
                      )
                  )
                  :ListView.builder(
                      itemCount: state.courseListState.courses.length,
                      itemBuilder: (BuildContext context, int pos) {
                        CourseState courseState = state.courseListState.courses[pos];
                        Image image = Image.memory(Uint8List.fromList(base64Decode(courseState.thumbnail)));
                        int ownedTutorialsNum = 0;
                        int freeTutorialsNum = 0;
                        state.clientTutorialsState.items.forEach((clientTutorial) {
                          courseState.tutorials.forEach((e) {
                            if (e.title == clientTutorial.title) ownedTutorialsNum ++;
                          });
                        });
                        freeTutorialsNum = courseState.tutorials.where((tut) => tut.tokenPrice == 0).length;
                        return AdjustInfoButton(
                          id: courseState.id.toString(),
                          width: 150,
                          height: 150,
                          title: e == null ? "" : getNumber(courseState.tutorials.length.toString()) + " ${t.tutorial.video}",
                          description: "${t.tutorial.your_tutorial_num}: ${getNumber((ownedTutorialsNum + freeTutorialsNum).toString())}",
                          name: e == null ? "" : courseState.title,
                          fontSize: 14,
                          isVertical: false,
                          primaryColor: ORANGE_COLOR,
                          primaryColorLight: ORANGE_COLOR,
                          secondaryColor: WHITE_COLOR,
                          image: image,
                          func: () async {
                            if (courseState.tutorials.length >= 1) {
                              Navigator.of(context).push(MaterialPageRoute(builder: (context) => CourseTutorialPage(courseState)));
                            }
                          }
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
