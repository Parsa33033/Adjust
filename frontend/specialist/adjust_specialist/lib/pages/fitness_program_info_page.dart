import 'dart:convert';

import 'package:adjust_specialist/components/adjust_text.dart';
import 'package:adjust_specialist/config/i18n.dart';
import 'package:adjust_specialist/constants/adjust_colors.dart';
import 'package:adjust_specialist/constants/words.dart';
import 'package:adjust_specialist/states/app_state.dart';
import 'package:adjust_specialist/states/exercise_state.dart';
import 'package:adjust_specialist/states/fitness_program_state.dart';
import 'package:adjust_specialist/states/workout_state.dart';
import 'package:expandable_card/expandable_card.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:persian_number_utility/persian_number_utility.dart';
import 'package:redux/redux.dart';
import 'package:spincircle_bottom_bar/modals.dart';
import 'package:spincircle_bottom_bar/spincircle_bottom_bar.dart';

class FitnessProgramInfoPage extends StatefulWidget {
  int programIndex;

  FitnessProgramInfoPage(this.programIndex);

  @override
  _FitnessProgramInfoPageState createState() => _FitnessProgramInfoPageState();
}

class _FitnessProgramInfoPageState extends State<FitnessProgramInfoPage> {
  Widget content;
  int selected;

  @override
  initState() {
    super.initState();
    content = Container();
    selected = -1;
  }

  void exerciseInfo(ExerciseState exercise) {
    showDialog(
        context: context,
        child: Dialog(
          child: Container(
//                            height: MediaQuery.of(context).size.height * 8/10,
//                              width: MediaQuery.of(context).size.width,
              padding: EdgeInsets.all(10),
              child: SingleChildScrollView(
                child: Column(
                  children: <Widget>[
                    Align(
                      alignment: Alignment.center,
                      child: AdjustText(
                        exercise.move.name,
                      ),
                    ),
                    Container(
                      height: 10,
                      width: 100,
                      child: Divider(
                        thickness: 2,
                        height: 5,
                      ),
                    ),
//                    Container(
//                      padding: EdgeInsets.all(10),
//                      child: Align(
//                        alignment: Alignment.center,
//                        child: Directionality(
//                          textDirection: TextDirection.rtl,
//                          child: Text(
//                            exercise.,
//                            style: TextStyle(
//                                fontFamily: "Iransans",
//                                color: FONT_COLOR,
//                                fontSize: 16),
//                          ),
//                        ),
//                      ),
//                    ),
                    Container(
                        alignment: Alignment.topCenter,
                        padding: EdgeInsets.all(5),
                        child: FittedBox(
                          fit: BoxFit.scaleDown,
                          child: DataTable(
                            columns: <DataColumn>[
                              DataColumn(
                                  label: Expanded(
                                    child: Align(
                                      alignment: adjustAlignment,
                                      child: AdjustText(
                                        t.fitness.quantity,
                                        fontSize: 13,
                                      ),
                                    ),
                                  )
                              ),
                              DataColumn(
                                  label: Expanded(
                                    child: Align(
                                      alignment: adjustAlignment,
                                      child: AdjustText(
                                        t.fitness.attribute,
                                        fontSize: 13,
                                      ),
                                    ),
                                  )
                              ),
                            ],
                            rows: <DataRow>[
                              DataRow(cells: <DataCell>[
                                DataCell(Align(
                                  alignment: Alignment.center,
                                  child: AdjustText(
                                    getNumber(exercise.sets.toString()),
                                    fontSize: 13,
                                  ),
                                )),
                                DataCell(Align(
                                  alignment: adjustAlignment,
                                  child: AdjustText(
                                    t.fitness.sets,
                                    fontSize: 13,
                                  ),
                                ))
                              ]),
                              DataRow(cells: <DataCell>[
                                DataCell(Align(
                                  alignment: Alignment.center,
                                  child: AdjustText(
                                    getNumber(exercise.repsMin.toString()),
                                    fontSize: 13,
                                  ),
                                )),
                                DataCell(Align(
                                  alignment: adjustAlignment,
                                  child: AdjustText(
                                    t.fitness.min_rep,
                                    fontSize: 13,
                                  ),
                                ))
                              ]),
                              DataRow(cells: <DataCell>[
                                DataCell(Align(
                                  alignment: Alignment.center,
                                  child: AdjustText(
                                    getNumber(exercise.repsMax.toString()),
                                    fontSize: 13,
                                  ),
                                )),
                                DataCell(Align(
                                  alignment: adjustAlignment,
                                  child: AdjustText(
                                    t.fitness.max_rep,
                                    fontSize: 13,
                                  ),
                                ))
                              ]),
                              DataRow(cells: <DataCell>[
                                DataCell(Align(
                                  alignment: Alignment.center,
                                  child: AdjustText(
                                    getNumber(exercise.move.muscleName),
                                    fontSize: 13,
                                  ),
                                )),
                                DataCell(Align(
                                  alignment: adjustAlignment,
                                  child: AdjustText(
                                    t.fitness.muscle,
                                    fontSize: 13,
                                  ),
                                ))
                              ]),
                              DataRow(cells: <DataCell>[
                                DataCell(Align(
                                    alignment: Alignment.center,
                                    child: AdjustText(
                                      getNumber(exercise.move.equipment),
                                      fontSize: 13,
                                    )
                                )),
                                DataCell(Align(
                                  alignment: adjustAlignment,
                                  child: AdjustText(
                                    t.fitness.equipment,
                                    fontSize: 13,
                                  ),
                                ))
                              ]),
                            ],
                          ),
                        )
                    ),
//                    Container(
//                      height: 55,
//                      alignment: Alignment.center,
//                      padding: EdgeInsets.all(10),
//                      child: Directionality(
//                        textDirection: TextDirection.rtl,
//                        child: Text(
//                          "انواع",
//                          style: TextStyle(
//                              fontFamily: "Iransans",
//                              color: FONT_COLOR,
//                              fontSize: 13),
//                        ),
//                      ),
//                    ),
                    Container(
                        height: 200,
                        width: 200,
                        padding: EdgeInsets.all(20),
                        child: exercise.move.picture != null ? Image.memory(base64Decode(exercise.move.picture)) : Container()
                    ),
                  ],
                ),
              )),
        ));
  }

  Widget fitnessTable(WorkoutState workout) {
    return Container(
        padding: EdgeInsets.all(10),
        alignment: Alignment.topCenter,
        child: FittedBox(
          fit: BoxFit.scaleDown,
          child: DataTable(
              columns: <DataColumn>[
                DataColumn(
                    label: Expanded(
                      child: Align(
                        alignment: adjustAlignment,
                        child: AdjustText(
                          t.fitness.workout.set_and_reps_number,
                        ),
                      ),
                    )
                ),
                DataColumn(
                    label: Expanded(
                      child: Align(
                        alignment: adjustAlignment,
                        child: AdjustText(
                          t.fitness.move,
                        ),
                      ),
                    )
                )
              ],
              rows: workout.exercises.map((exercise) {
                return DataRow(cells: <DataCell>[
                  DataCell(InkWell(
                    child: Align(
                      alignment: Alignment.center,
                      child: AdjustText(
                          exercise.repsMin == exercise.repsMax ?
                          getNumber(exercise.sets.toString() +
                              " " + t.fitness.set + " - " +
                              exercise.repsMin.toString() + " " + t.fitness.reps,)
                              :
                          getNumber(exercise.sets.toString() +
                              " " + t.fitness.set + " - " +
                              exercise.repsMin.toString() +
                              " " + t.main.till + " " +
                              exercise.repsMax.toString() + " " + t.fitness.reps,)
                      ),
                    ),
                    onTap: () {
                      exerciseInfo(exercise);
                    },
                  )),
                  DataCell(InkWell(
                    child: Align(
                      alignment: adjustAlignment,
                      child: AdjustText(
                        exercise.move.name,
                      ),
                    ),
                    onTap: () {
                      exerciseInfo(exercise);
                    },
                  )),
                ]);
              }).toList()),
        )
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Align(
          alignment: adjustAlignment,
          child: AdjustText(
            t.fitness.program,
            fontSize: 20,
            color: WHITE_COLOR,
          ),
        ),
        backgroundColor: RED_COLOR,
        elevation: 4,
        leading: InkWell(
          child: Icon(Icons.arrow_back),
          onTap: () {
            Navigator.of(context).pop();
          },
        ),
      ),
      body: ExpandableCardPage(
        page: StoreConnector<AppState, AppState>(
          converter: (Store store) => store.state,
          builder: (BuildContext context, AppState state) {
            FitnessProgramState fitnessProgramState = state.programListState
                .programs.reversed.toList()[this.widget.programIndex].fitnessProgram;
            return Container(
                height: MediaQuery.of(context).size.height,
//                color: RED_COLOR.withAlpha(25),
                child: SingleChildScrollView(
                  child: Column(
                    children: <Widget>[
                      Container(
                        height: 60,
                        width: MediaQuery.of(context).size.width,
                        child: Directionality(
                          textDirection: txtDir,
                          child: ListView.builder(
                            itemCount: fitnessProgramState.workouts.length,
                            scrollDirection: Axis.horizontal,
                            itemBuilder: (BuildContext context, int pos) {
                              WorkoutState workout =
                              fitnessProgramState.workouts[pos];
                              return InkWell(
                                child: Column(
                                  children: <Widget>[
                                    Container(
                                      padding: EdgeInsets.only(
                                          left: 15,
                                          right: 15,
                                          top: 15,
                                          bottom: 5),
                                      child: Text(
                                        getNumber(t.main.day +
                                            workout.dayNumber.toString()),
                                        style: TextStyle(
                                            fontFamily: "Iransans",
                                            color: RED_COLOR,
                                            fontSize: 15),
                                      ),
                                    ),
                                    selected == pos
                                        ? Container(
                                      height: 5,
                                      width: 30,
                                      child: Divider(
                                        thickness: 2,
                                        indent: 1,
                                        endIndent: 1,
                                        height: 5,
                                        color: RED_COLOR,
                                      ),
                                    )
                                        : Container()
                                  ],
                                ),
                                onTap: () {
                                  setState(() {
                                    selected = pos;
                                    content = fitnessTable(workout);
                                  });
                                },
                              );
                            },
                          ),
                        ),
                      ),
                      this.content
                    ],
                  ),
                ));
          },
        ),
        expandableCard: ExpandableCard(
          minHeight: 200,
          backgroundColor: RED_COLOR,
          children: <Widget>[
            SingleChildScrollView(
              child: StoreConnector<AppState, AppState>(
                converter: (Store store) => store.state,
                builder: (BuildContext context, AppState state) {
                  FitnessProgramState fitnessProgramState = state
                      .programListState
                      .programs.reversed.toList()[this.widget.programIndex]
                      .fitnessProgram;
                  return Container(
                      child: SingleChildScrollView(
                        child: Column(
                          children: <Widget>[
                            Container(
                              padding: EdgeInsets.all(20),
                              child: Align(
                                alignment: adjustAlignment,
                                child: AdjustText(
                                  t.main.description + ":",
                                  color: WHITE_COLOR,
                                ),
                              ),
                            ),
                            Container(
                              height: 180,
                              child: SingleChildScrollView(
                                  padding: EdgeInsets.all(15),
                                  child: Column(
                                    children: <Widget>[
                                      fitnessProgramState.description == null
                                          ? Text("")
                                          : AdjustText(
                                        fitnessProgramState.description,
                                        color: WHITE_COLOR,
                                      ),
                                    ],
                                  )),
                            )
                          ],
                        ),
                      ));
                },
              ),
            )
          ],
        ),
      ),
    );
  }

  @override
  void dispose() {
    // TODO: implement dispose
    super.dispose();
  }
}
