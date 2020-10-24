
import 'package:adjust_specialist/components/adjust_text.dart';
import 'package:adjust_specialist/config/i18n.dart';
import 'package:adjust_specialist/constants/adjust_colors.dart';
import 'package:adjust_specialist/constants/words.dart';
import 'package:adjust_specialist/states/app_state.dart';
import 'package:adjust_specialist/states/meal_state.dart';
import 'package:adjust_specialist/states/nutrition_program_state.dart';
import 'package:adjust_specialist/states/nutrition_state.dart';
import 'package:expandable_card/expandable_card.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:persian_number_utility/persian_number_utility.dart';
import 'package:redux/redux.dart';
import 'package:spincircle_bottom_bar/modals.dart';
import 'package:spincircle_bottom_bar/spincircle_bottom_bar.dart';

class NutritionProgramInfoPage extends StatefulWidget {
  int programIndex;

  NutritionProgramInfoPage(this.programIndex);

  @override
  _NutritionProgramInfoPageState createState() => _NutritionProgramInfoPageState();
}

class _NutritionProgramInfoPageState extends State<NutritionProgramInfoPage> {
  Widget content;
  int selected;

  @override
  initState() {
    super.initState();
    content = Container();
    selected = -1;
  }

  void nutritionInfo(NutritionState nutrition) {
    showDialog(
        context: context,
        child: Dialog(
          child: Container(
              padding: EdgeInsets.all(10),
              child: SingleChildScrollView(
                child: Column(
                  children: <Widget>[
                    Align(
                      alignment: Alignment.center,
                      child: AdjustText(
                        nutrition.name,
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
                    Container(
                      padding: EdgeInsets.all(10),
                      child: Align(
                        alignment: Alignment.center,
                        child: AdjustText(
                          nutrition.description,
                        ),
                      ),
                    ),
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
                                        t.nutrition.quantity_in_unit,
                                        fontSize: 13,
                                      ),
                                    ),
                                  )),
                              DataColumn(
                                  label: Expanded(
                                    child: Align(
                                      alignment: adjustAlignment,
                                      child: AdjustText(
                                        t.nutrition.content,
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
                                      getNumber(nutrition.caloriesPerUnit.toString()),
                                      fontSize: 13
                                  ),
                                )),
                                DataCell(Align(
                                  alignment: adjustAlignment,
                                  child: AdjustText(
                                    t.nutrition.calory,
                                    fontSize: 13,
                                  ),
                                ))
                              ]),
                              DataRow(cells: <DataCell>[
                                DataCell(Align(
                                  alignment: Alignment.center,
                                  child: AdjustText(
                                    getNumber(nutrition.proteinPerUnit.toString() + " " + t.nutrition.gram,),
                                    fontSize: 13,
                                  ),
                                )),
                                DataCell(Align(
                                  alignment: adjustAlignment,
                                  child: AdjustText(
                                    t.nutrition.protein,
                                    fontSize: 13,
                                  ),
                                ))
                              ]),
                              DataRow(cells: <DataCell>[
                                DataCell(Align(
                                  alignment: Alignment.center,
                                  child:  AdjustText(
                                    getNumber(nutrition.carbsPerUnit.toString() + " " + t.nutrition.gram,),
                                    fontSize: 13,
                                  ),
                                )),
                                DataCell(Align(
                                  alignment: adjustAlignment,
                                  child: AdjustText(
                                    t.nutrition.carbs,
                                    fontSize: 13,
                                  ),
                                ))
                              ]),
                              DataRow(cells: <DataCell>[
                                DataCell(Align(
                                  alignment: Alignment.center,
                                  child: AdjustText(
                                    getNumber(nutrition.fatInUnit.toString() + " " + t.nutrition.gram,),
                                    fontSize: 13,
                                  ),
                                )),
                                DataCell(Align(
                                  alignment: adjustAlignment,
                                  child: AdjustText(
                                    t.nutrition.fat,
                                    fontSize: 13,
                                  ),
                                ))
                              ]),
                            ],
                          ),
                        )
                    ),
                    Container(
                      height: 55,
                      alignment: Alignment.center,
                      padding: EdgeInsets.all(10),
                      child: AdjustText(
                        t.nutrition.types,
                        fontSize: 13,
                      ),
                    ),
                    Container(
                      height: 200,
                      padding: EdgeInsets.all(20),
                      child: Directionality(
                        textDirection: txtDir,
                        child: GridView.count(
                            crossAxisCount: 2,
                            mainAxisSpacing: 1,
                            crossAxisSpacing: 1,
                            children: nutrition.foods.map((e) {
                              return Container(
                                  color: GREEN_COLOR,
                                  padding: EdgeInsets.all(5),
                                  child: Center(
                                      child: FittedBox(
                                          fit: BoxFit.scaleDown,
                                          child: AdjustText(
                                            e.name,
                                            color: WHITE_COLOR,
                                          )
                                      )
                                  )
                              );
                            }).toList()),
                      ),
                    ),
                  ],
                ),
              )),
        ));
  }

  Widget nutritionTable(MealState meal) {
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
                          t.nutrition.unit,
                        ),
                      ),
                    )
                ),
                DataColumn(
                    label: Expanded(
                      child: Align(
                        alignment: adjustAlignment,
                        child: AdjustText(
                          t.nutrition.nutrition,
                        ),
                      ),
                    )
                )
              ],
              rows: meal.nutritionList.map((nutrition) {
                return DataRow(cells: <DataCell>[
                  DataCell(InkWell(
                    child: Align(
                        alignment: Alignment.center,
                        child: AdjustText(
                            getNumber(nutrition.unit.toString())
                        )
                    ),
                    onTap: () {
                      nutritionInfo(nutrition);
                    },
                  )),
                  DataCell(InkWell(
                    child: Align(
                        alignment: adjustAlignment,
                        child: AdjustText(
                            nutrition.name
                        )
                    ),
                    onTap: () {
                      nutritionInfo(nutrition);
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
              t.nutrition.program,
              fontSize: 20,
              color: WHITE_COLOR,
            )
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
      body: ExpandableCardPage(
        page: StoreConnector<AppState, AppState>(
          converter: (Store store) => store.state,
          builder: (BuildContext context, AppState state) {
            NutritionProgramState nutritionProgramState = state.programListState
                .programs.reversed.toList()[this.widget.programIndex].nutritionProgram;
            return Container(
                height: MediaQuery.of(context).size.height,
//                color: GREEN_COLOR.withAlpha(25),
                child: SingleChildScrollView(
                  child: Column(
                    children: <Widget>[
                      Container(
                        height: 60,
                        width: MediaQuery.of(context).size.width,
                        child: Directionality(
                          textDirection: txtDir,
                          child: ListView.builder(
                            itemCount: nutritionProgramState.meals.length,
                            scrollDirection: Axis.horizontal,
                            itemBuilder: (BuildContext context, int pos) {
                              MealState mealState =
                              nutritionProgramState.meals[pos];
                              return InkWell(
                                child: Column(
                                  children: <Widget>[
                                    Container(
                                        padding: EdgeInsets.only(
                                            left: 15,
                                            right: 15,
                                            top: 15,
                                            bottom: 5),
                                        child: AdjustText(
                                          getNumber(MEAL_TYPE_LIST[mealState.mealType]),
                                          fontSize: 15,
                                          color: GREEN_COLOR,
                                        )
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
                                        color: GREEN_COLOR,
                                      ),
                                    )
                                        : Container()
                                  ],
                                ),
                                onTap: () {
                                  setState(() {
                                    selected = pos;
                                    content = nutritionTable(mealState);
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
          backgroundColor: GREEN_COLOR,
          children: <Widget>[
            SingleChildScrollView(
              child: StoreConnector<AppState, AppState>(
                converter: (Store store) => store.state,
                builder: (BuildContext context, AppState state) {
                  NutritionProgramState nutritionProgramState = state
                      .programListState
                      .programs.reversed.toList()[this.widget.programIndex]
                      .nutritionProgram;
                  return Container(
                      child: SingleChildScrollView(
                        child: Column(
                          children: <Widget>[
                            Container(
                              width: MediaQuery.of(context).size.width,
                              child: FittedBox(
                                fit: BoxFit.scaleDown,
                                child: DataTable(
                                  columns: <DataColumn>[
                                    DataColumn(
                                        label: Expanded(
                                          child: Align(
                                            alignment: adjustAlignment,
                                            child: AdjustText(
                                              t.nutrition.fat,
                                              color: WHITE_COLOR,
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
                                                t.nutrition.carbs,
                                                color: WHITE_COLOR,
                                                fontSize: 13,
                                              )
                                          ),
                                        )
                                    ),
                                    DataColumn(
                                        label: Expanded(
                                          child: Align(
                                            alignment: adjustAlignment,
                                            child: AdjustText(
                                              t.nutrition.protein,
                                              color: WHITE_COLOR,
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
                                              t.nutrition.calory + " " + t.main.daily,
                                              color: WHITE_COLOR,
                                              fontSize: 13,
                                            ),
                                          ),
                                        )
                                    ),
                                  ],
                                  rows: <DataRow>[
                                    DataRow(cells: <DataCell>[
                                      DataCell(Align(
                                          alignment: adjustAlignment,
                                          child: AdjustText(
                                            getNumber(nutritionProgramState.fatPercentage
                                                .toString() +
                                                "%"),
                                            color: WHITE_COLOR,
                                            fontSize: 13,
                                          )
                                      )),
                                      DataCell(Align(
                                          alignment: adjustAlignment,
                                          child: AdjustText(
                                            getNumber(nutritionProgramState
                                                .carbsPercentage
                                                .toString() +
                                                "%",),
                                            color: WHITE_COLOR,
                                            fontSize: 13,
                                          )
                                      )),
                                      DataCell(Align(
                                          alignment: adjustAlignment,
                                          child: AdjustText(
                                            getNumber(nutritionProgramState
                                                .proteinPercentage
                                                .toString() +
                                                "%",),
                                            color: WHITE_COLOR,
                                            fontSize: 13,
                                          )
                                      )),
                                      DataCell(Align(
                                          alignment: adjustAlignment,
                                          child: AdjustText(
                                              getNumber(nutritionProgramState.dailyCalories
                                                  .round()
                                                  .toString(),),
                                              color: WHITE_COLOR,
                                              fontSize: 13
                                          )
                                      )),
                                    ])
                                  ],
                                ),
                              ),
                            ),
                            Container(
                              padding: EdgeInsets.all(20),
                              child: Align(
                                  alignment: adjustAlignment,
                                  child: AdjustText(
                                    t.main.description + ":",
                                    color: WHITE_COLOR,
                                  )
                              ),
                            ),
                            Container(
                              height: 180,
                              child: SingleChildScrollView(
                                  padding: EdgeInsets.all(15),
                                  child: Column(
                                    children: <Widget>[
                                      nutritionProgramState.description == null
                                          ? Text("")
                                          : AdjustText(
                                        nutritionProgramState.description,
                                        color: WHITE_COLOR,
                                      )
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
