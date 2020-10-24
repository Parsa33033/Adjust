import 'dart:convert';
import 'dart:typed_data';
import 'dart:math';

import 'package:adjust_client/actions/jwt.dart';
import 'package:adjust_client/actions/chat_message_action.dart';
import 'package:adjust_client/components/adjust_dialog.dart';
import 'package:adjust_client/components/adjust_info_button.dart';
import 'package:adjust_client/components/adjust_text.dart';
import 'package:adjust_client/components/preloader.dart';
import 'package:adjust_client/config/i18n.dart';
import 'package:adjust_client/config/localization.dart';
import 'package:adjust_client/config/stomp.dart';
import 'package:adjust_client/constants/adjust_colors.dart';
import 'package:adjust_client/constants/words.dart';
import 'package:adjust_client/pages/chat_page.dart';
import 'package:adjust_client/pages/program_development_page.dart';
import 'package:adjust_client/pages/fitness_program_page.dart';
import 'package:adjust_client/pages/nutrition_program_page.dart';
import 'package:adjust_client/states/app_state.dart';
import 'package:adjust_client/states/program_state.dart';
import 'package:adjust_client/states/specialist_state.dart';
import 'package:expansion_card/expansion_card.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:flutter_slidable/flutter_slidable.dart';
import 'package:persian_number_utility/persian_number_utility.dart';
import 'package:redux/redux.dart';
import 'package:stomp_dart_client/stomp.dart';
import 'package:stomp_dart_client/stomp_config.dart';
import 'package:stomp_dart_client/stomp_frame.dart';

class ProgramPage extends StatefulWidget {
  @override
  _ProgramPageState createState() => _ProgramPageState();
}

class _ProgramPageState extends State<ProgramPage> {
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
            t.program.programs,
            fontSize: 20,
            color: WHITE_COLOR,
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
      body: StoreConnector<AppState, AppState>(
        converter: (Store store) => store.state,
        builder: (BuildContext context, AppState state) {
          return Container(
            color: YELLOW_COLOR.withAlpha(35),
            child: Column(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: <Widget>[
                Expanded(
                  child: state.programListState.programs.length <= 0 ?
                  Container(
                      child: Center(
                        child: AdjustText(t.main.empty_list(t.program.programs)),
                      )
                  )
                  :ListView.builder(
                      itemCount: state.programListState.programs.length,
                      itemBuilder: (BuildContext context, int pos) {
                        ProgramState program = state
                            .programListState.programs.reversed
                            .toList()[pos];
                        return Slidable(
                          actionPane: SlidableDrawerActionPane(),
                          actions: <Widget>[
                            InkWell(
                              child: Container(
                                color: RED_COLOR,
                                padding: EdgeInsets.all(7),
                                child: Column(
                                  children: <Widget>[
                                    Align(
                                      alignment: Alignment.center,
                                      child: Container(
                                        height: 40,
                                        width: 40,
                                        child: Image.asset(
                                            "assets/workout_icon.png"),
                                      ),
                                    ),
                                    FittedBox(
                                      fit: BoxFit.scaleDown,
                                      child: AdjustText(
                                        t.fitness.program,
                                        fontSize: 13,
                                        color: WHITE_COLOR,
                                      ),
                                    )
                                  ],
                                ),
                              ),
                              onTap: () {
                                if (program.fitnessProgram != null) {
                                  Navigator.of(context).push(MaterialPageRoute(
                                      builder: (context) =>
                                          FitnessProgramPage(pos)));
                                } else {
                                  showAdjustDialog(
                                      context,
                                      t.fitness.not_ready,
                                      false,
                                      null,
                                      RED_COLOR);
                                }
                              },
                            ),
                            InkWell(
                              child: Container(
                                color: GREEN_COLOR,
                                padding: EdgeInsets.all(7),
                                child: Column(
                                  children: <Widget>[
                                    Container(
                                      height: 40,
                                      width: 40,
                                      child: Image.asset(
                                          "assets/nutrition_icon.png"),
                                    ),
                                    FittedBox(
                                      fit: BoxFit.scaleDown,
                                      child: AdjustText(
                                        t.nutrition.program,
                                        fontSize: 13,
                                        color: WHITE_COLOR,
                                      ),
                                    )
                                  ],
                                ),
                              ),
                              onTap: () {
                                if (program.nutritionProgram != null) {
                                  Navigator.of(context).push(MaterialPageRoute(
                                      builder: (context) =>
                                          NutritionProgramPage(pos)));
                                } else {
                                  showAdjustDialog(
                                      context,
                                      t.nutrition.not_ready,
                                      false,
                                      null,
                                      GREEN_COLOR);
                                }
                              },
                            )
                          ],
                          secondaryActions: <Widget>[
                            InkWell(
                              child: Container(
                                color: ORANGE_COLOR,
                                padding: EdgeInsets.all(7),
                                child: Column(
                                  children: <Widget>[
                                    Align(
                                      alignment: Alignment.center,
                                      child: Container(
                                          height: 40,
                                          width: 40,
                                          child: Icon(Icons.show_chart)),
                                    ),
                                    FittedBox(
                                      fit: BoxFit.scaleDown,
                                      child: AdjustText(
                                        t.progress.progress,
                                        fontSize: 13,
                                        color: WHITE_COLOR,
                                      ),
                                    )
                                  ],
                                ),
                              ),
                              onTap: () {
                                if (program.nutritionProgram != null &&
                                    program.fitnessProgram != null &&
                                    program.bodyComposition != null) {
                                  Navigator.of(context).push(MaterialPageRoute(
                                      builder: (context) =>
                                          ProgramDevelopmentPage(program.id, pos)));
                                } else {
                                  showAdjustDialog(
                                      context,
                                      t.program.not_ready,
                                      false,
                                      null,
                                      ORANGE_COLOR);
                                }
                              },
                            ),
                            InkWell(
                              child: Container(
                                color: YELLOW_COLOR,
                                padding: EdgeInsets.all(7),
                                child: Column(
                                  children: <Widget>[
                                    Container(
                                        height: 40,
                                        width: 40,
                                        child: Icon(Icons.message)),
                                    FittedBox(
                                      fit: BoxFit.scaleDown,
                                      child: AdjustText(
                                        t.main.question,
                                        fontSize: 13,
                                        color: WHITE_COLOR,
                                      ),
                                    )
                                  ],
                                ),
                              ),
                              onTap: () async {
                                preloader(context);
                                int i = await getMessages(context, program.adjustClientId, program.specialistId);
                                if (i == 1) {
                                  if (mainStompClient.connected) {
                                    Navigator.of(context, rootNavigator: true).pop("dialog");
                                    Navigator.of(context).push(MaterialPageRoute(builder: (context) => ChatPage(clientState: program.adjustClient, specialistState: program.specialist,)));
                                  } else {
                                    mainStompClient.activate();
                                  }
                                } else {
                                  Navigator.of(context, rootNavigator: true).pop("dialog");
                                  showAdjustDialog(context, t.user.failure, false, null, ORANGE_COLOR);
                                }
                              },
                            )
                          ],
                          child: Container(
                            decoration: BoxDecoration(
                                color: WHITE_COLOR,
                                border: Border(
                                    bottom: BorderSide(
                                        width: 1, color: YELLOW_COLOR))),
                            child: ExpansionCard(
                              backgroundColor: YELLOW_COLOR.withAlpha(55),
                              trailing: Icon(
                                Icons.description,
                                color: YELLOW_COLOR,
                                size: 40,
                              ),
                              title: Row(
                                mainAxisAlignment:
                                    MainAxisAlignment.spaceBetween,
                                children: <Widget>[
                                  Expanded(
                                    flex: 1,
                                    child: Icon(
                                      Icons.arrow_back_ios,
                                      color: FONT_COLOR,
                                    ),
                                  ),
                                  Expanded(
                                    flex: 8,
                                    child: Container(
                                      padding: EdgeInsets.only(
                                          left: 20,
                                          right: 20,
                                          bottom: 20,
                                          top: 0),
                                      alignment: adjustAlignment,
                                      child: Column(
                                        crossAxisAlignment:
                                            CrossAxisAlignment.start,
                                        children: <Widget>[
                                          Container(
                                            alignment: adjustAlignment,
                                            child: AdjustText(
                                                getNumber(t.program.fitness_nutrition_program + " - " + t.main.date + ": " +
                                                    jalaliToString(
                                                        georgianToJalali(
                                                            program
                                                                .createdAt))),
                                              fontSize: 13,
                                              color: FONT_COLOR
                                            ),
                                          ),
                                          Container(
                                            alignment: adjustAlignment,
                                            child: AdjustText(
                                              t.main.specialist + ": " +
                                                  program.specialist
                                                      .firstName +
                                                  " " +
                                                  program.specialist
                                                      .lastName,
                                                fontSize: 13,
                                                color: FONT_COLOR
                                            ),
                                          )
                                        ],
                                      ),
                                    ),
                                  ),
                                  Expanded(
                                    flex: 1,
                                    child: Icon(
                                      Icons.arrow_forward_ios,
                                      color: FONT_COLOR,
                                    ),
                                  )
                                ],
                              ),
                              children: <Widget>[
                                Container(
                                  padding: EdgeInsets.all(20),
                                  child: AdjustText(
                                    t.program.box_instruction
                                  )
                                )
                              ],
                            ),
                          ),
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
