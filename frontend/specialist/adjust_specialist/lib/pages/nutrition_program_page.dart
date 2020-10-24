import 'dart:math';
import 'dart:core';

import 'package:adjust_specialist/actions/program_action.dart';
import 'package:adjust_specialist/components/adjust_dialog.dart';
import 'package:adjust_specialist/components/adjust_gridview_selector.dart';
import 'package:adjust_specialist/components/adjust_raised_button.dart';
import 'package:adjust_specialist/components/adjust_text.dart';
import 'package:adjust_specialist/components/adjust_text_field.dart';
import 'package:adjust_specialist/components/icon_stepper.dart';
import 'package:adjust_specialist/config/i18n.dart';
import 'package:adjust_specialist/constants/adjust_colors.dart';
import 'package:adjust_specialist/constants/words.dart';
import 'package:adjust_specialist/dto/meal_dto.dart';
import 'package:adjust_specialist/dto/nutrition_dto.dart';
import 'package:adjust_specialist/dto/nutrition_program_dto.dart';
import 'package:adjust_specialist/dto/program_dto.dart';
import 'package:adjust_specialist/main.dart';
import 'package:adjust_specialist/model/enum/meal_type.dart';
import 'package:adjust_specialist/pages/main_page.dart';
import 'package:adjust_specialist/states/app_state.dart';
import 'package:adjust_specialist/states/nutrition_state.dart';
import 'package:adjust_specialist/states/program_state.dart';
import 'package:autocomplete_textfield/autocomplete_textfield.dart';
import 'package:enum_to_string/enum_to_string.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:redux/redux.dart';
import 'package:stepo/stepo.dart';


class NutritionProgramPage extends StatefulWidget {
  int programIndex;
  ProgramState programState;

  NutritionProgramPage(this.programIndex, this.programState);

  @override
  _NutritionProgramPageState createState() => _NutritionProgramPageState();
}

class _NutritionProgramPageState extends State<NutritionProgramPage> {
  int selectedIndex = 0;
  int selectedMeal = 0;
  TextEditingController mealNameTextEditingController;

  NutritionProgramDTO nutritionProgramDTO;
  List<MealDTO> mealDTOList = List<MealDTO>();
  Set<NutritionState> nutritionStateSet = Set<NutritionState>();
  List<NutritionDTO> nutritionDTOList = List<NutritionDTO>();

  TextEditingController descriptionTextEditingController;
  TextEditingController caloriesTextEditingController;
  TextEditingController proteinPercentageTextEditingController;
  TextEditingController carbsPercentageTextEditingController;
  TextEditingController fatPercentageTextEditingController;

  double calories;
  int proteinP;
  int carbsP;
  int fatP;

  double caloriesSet;
  int proteinPSet;
  int carbsPSet;
  int fatPSet;

  int p;

  @override
  initState() {
    super.initState();
    mealNameTextEditingController = TextEditingController();
    descriptionTextEditingController = TextEditingController();
    caloriesTextEditingController = TextEditingController();
    proteinPercentageTextEditingController = TextEditingController();
    carbsPercentageTextEditingController = TextEditingController();
    fatPercentageTextEditingController = TextEditingController();

    calories = 0;
    proteinP = 0;
    carbsP = 0;
    fatP = 0;

    caloriesSet = 0;
    proteinPSet = 0;
    carbsPSet = 0;
    fatPSet = 0;

    p = 0;
  }

  void setPercentage() {
    setState(() {
      calories = double.parse(caloriesTextEditingController.text);
      proteinP = int.parse(proteinPercentageTextEditingController.text);
      carbsP = int.parse(carbsPercentageTextEditingController.text);
      fatP = int.parse(fatPercentageTextEditingController.text);
      p = this.proteinP + this.fatP + this.carbsP;
    });
  }

  Widget steps(BuildContext context, int index) {
    if (index == 0) {
      return firstMainNutritionProgramPage(context);
    } else if (index == 1) {
      return secondNutritionProgramPage(context);
    } else if (index == 2) {
      return thirdMealsProgramPage(context);
    } else if (index == 3) {
      return fourthSetMealsProgramPage(context);
    } else if (index == 4) {
      return fifthDescriptionPage();
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        backgroundColor: WHITE_COLOR,
        appBar: AppBar(
          title: Align(
              alignment: Alignment.centerRight,
              child: FittedBox(
                fit: BoxFit.scaleDown,
                child: AdjustText(
                    t.nutrition.program + " " +
                        this.widget.programState.adjustClient.firstName +
                        " " +
                        this.widget.programState.adjustClient.lastName,
                    fontSize: 20,
                    color: WHITE_COLOR
                ),
              )),
          backgroundColor: GREEN_COLOR,
          elevation: 4,
          leading: InkWell(
            child: Icon(Icons.arrow_back),
            onTap: () {
              Navigator.of(context).pop();
            },
          ),
        ),
        body: Container(
          color: WHITE_COLOR,
          child: Column(
            children: <Widget>[
              Container(
                // margin: EdgeInsets.all(5.0),
                  decoration: BoxDecoration(
                    color: WHITE_COLOR,
                    boxShadow: [
                      BoxShadow(
                        color: Colors.grey,
                        spreadRadius: 1.0,
                        blurRadius: 2.0,
                      )
                    ],
                    // borderRadius: BorderRadius.circular(5.0),
                  ),
                  child: Container(
                    child: IconStepper(
                      key: nutritionIconStepperStateKey,
                      direction: Axis.horizontal,
                      stepColor: GREEN_COLOR,
                      activeStepColor: WHITE_COLOR,
                      enableNextPreviousButtons: false,
                      lineColor: GREEN_COLOR,
                      // lineDotRadius: 2,
                      lineLength: 75,
                      onStepReached: (value) {
                        setState(() {
                          selectedIndex = value;
                        });
                      },
                      enableStepTapping: false,
                      steppingEnabled: true,
                      icons: [
                        Icon(Icons.filter_1),
                        Icon(Icons.filter_2),
                        Icon(Icons.filter_3),
                        Icon(Icons.filter_4),
                        Icon(Icons.filter_5),
                      ],
                    ),
                  )),
//            Container(
//              decoration: BoxDecoration(
//                border: Border.all(width: 0.1),
//              ),
//              padding: EdgeInsets.all(8.0),
//              alignment: Alignment.centerLeft,
//              child: Text(header()),
//            ),
              Expanded(
                child: Container(
                  margin: EdgeInsets.all(5.0),
                  child: Center(
                    child: steps(context, selectedIndex),
                  ),
                ),
              ),
            ],
          ),
        ));
  }

  Widget secondNutritionProgramPage(BuildContext context) {
    List<NutritionState> nutritionStateList =
        store.state.nutritionStateList.nutritions;
    return Container(
        height: MediaQuery
            .of(context)
            .size
            .height,
        width: MediaQuery
            .of(context)
            .size
            .width,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          children: <Widget>[
            Expanded(
                flex: 7,
                child: Container(
                  padding: EdgeInsets.all(5),
                  child: AdjustText(
                      t.nutrition.choose_nutrition
                  ),
                )),
            Expanded(
                flex: 78,
                child: Container(
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                    children: <Widget>[
                      Expanded(
                        flex: 5,
                        child: Column(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: <Widget>[
                            Icon(Icons.keyboard_arrow_up),
                            Icon(Icons.keyboard_arrow_down)
                          ],
                        ),
                      ),
                      Expanded(
                        flex: 95,
                        child: GridView.count(
                            crossAxisCount: 2,
                            children: nutritionStateList.map((e) {
                              return AdjustGridViewItem(
                                  selectedFontColor: WHITE_COLOR,
                                  selectedColor: GREEN_COLOR,
                                  name: e.name,
                                  notSelectedFontColor: FONT_COLOR,
                                  notSelectedColor: WHITE_COLOR,
                                  isSelected: (value) {
                                    if (value) {
                                      nutritionStateSet.add(e);
                                    } else {
                                      nutritionStateSet.remove(e);
                                    }
                                  });
                            }).toList()),
                      )
                    ],
                  ),
                )
            ),
            Expanded(
              flex: 15,
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                children: <Widget>[
                  Expanded(
                      flex: 5,
                      child: Container(
                        margin: EdgeInsets.all(10),
                        child: AdjustRaisedButton(
                          textDirection: TextDirection.rtl,
                          width: 200,
                          height: 60,
                          fontColor: WHITE_COLOR,
                          text: t.main.back,
                          secondaryColor: GREEN_COLOR,
                          fontSize: 16,
                          primaryColor: GREEN_COLOR,
                          onPressed: () {
                            nutritionIconStepperStateKey.currentState
                                .previous();
                          },
                        ),
                      )),
                  Expanded(
                      flex: 5,
                      child: Container(
                        margin: EdgeInsets.all(10),
                        child: AdjustRaisedButton(
                          textDirection: TextDirection.rtl,
                          width: 200,
                          height: 60,
                          fontColor: WHITE_COLOR,
                          text: t.main.ok,
                          secondaryColor: GREEN_COLOR,
                          fontSize: 16,
                          primaryColor: GREEN_COLOR,
                          onPressed: () {
                            if (nutritionStateSet.length >= 1) {
                              nutritionDTOList =
                                  this.nutritionStateSet.map((nutritionState) {
                                    NutritionDTO nutritionDTO = NutritionDTO(
                                        nutritionState.id,
                                        nutritionState.name,
                                        nutritionState.description,
                                        0,
                                        nutritionState.adjustNutritionId,
                                        nutritionState.minUnitLow,
                                        nutritionState.minUnitNormal,
                                        nutritionState.minUnitMax,
                                        nutritionState.caloryBoundryMin,
                                        nutritionState.caloryBoundryMax,
                                        nutritionState.caloriesPerUnit,
                                        nutritionState.proteinPerUnit,
                                        nutritionState.carbsPerUnit,
                                        nutritionState.fatInUnit,
                                        null,
                                        null);

                                    return nutritionDTO;
                                  }).toList();
                              disperseUnitsInNutrition();
                              nutritionIconStepperStateKey.currentState.next();
                            } else {
                              showAdjustDialog(context, t.nutrition
                                  .atleast_one_nutrition,
                                  false, null, GREEN_COLOR);
                            }
                          },
                        ),
                      ))
                ],
              ),
            )
          ],
        ));
  }


  int proteinCaloryInUnit = 4;
  int carbsCaloryInUnit = 4;
  int fatCaloryInUnit = 9;

  void setLowUnit(int i, int minUnit) {
    setState(() {
      this.nutritionDTOList[i].unit = minUnit;
      caloriesSet +=
          (this.nutritionDTOList[i].caloriesPerUnit * minUnit).toDouble();
      proteinPSet += this.nutritionDTOList[i].proteinPerUnit * minUnit;
      carbsPSet += this.nutritionDTOList[i].carbsPerUnit * minUnit;
      fatPSet += this.nutritionDTOList[i].fatInUnit * minUnit;
    });
  }

  void disperseUnitsInNutrition() {
    for (var i = 0; i < this.nutritionDTOList.length; i++) {
      int minUnit;
      if (this.calories <= this.nutritionDTOList[i].caloryBoundryMin) {
        minUnit = this.nutritionDTOList[i].minUnitLow;
        setLowUnit(i, minUnit);
      } else if (this.calories > this.nutritionDTOList[i].caloryBoundryMin &&
          this.calories <= this.nutritionDTOList[i].caloryBoundryMax) {
        minUnit = this.nutritionDTOList[i].minUnitNormal;
        setLowUnit(i, minUnit);
      } else {
        minUnit = this.nutritionDTOList[i].minUnitMax;
        setLowUnit(i, minUnit);
      }
    }

    int chosenIndex = -1;
    while (caloriesSet <= calories) {
      int tot = proteinCaloryInUnit * proteinPSet +
          carbsCaloryInUnit * carbsPSet + fatCaloryInUnit * fatPSet;

      int proteinPSetPercentage = tot == 0 ? 0 : ((proteinPSet *
          proteinCaloryInUnit / tot) * 100).toInt();
      int carbsPSetPercentage = tot == 0 ? 0 : ((carbsPSet * carbsCaloryInUnit /
          tot) * 100).toInt();
      int fatPSetPercentage = tot == 0 ? 0 : ((fatPSet * fatCaloryInUnit /
          tot) * 100).toInt();

      int proteinPercentageDif = proteinP -
          proteinPSetPercentage; // can be negative
      int carbsPercentageDif = carbsP - carbsPSetPercentage; // can be negative
      int fatPercentageDif = fatP - fatPSetPercentage; // can be negative
      int i = chooseClosestNutrition(
          chosenIndex,
          proteinPercentageDif,
          carbsPercentageDif,
          fatPercentageDif);
      chosenIndex = i;
      setState(() {
        this.nutritionDTOList[i].unit++;
        caloriesSet += this.nutritionDTOList[i].caloriesPerUnit;
        proteinPSet += this.nutritionDTOList[i].proteinPerUnit;
        carbsPSet += this.nutritionDTOList[i].carbsPerUnit;
        fatPSet += this.nutritionDTOList[i].fatInUnit;
      });
    }
  }

  int chooseClosestNutrition(int chosenIndex,
      int proteinPercentageDif,
      int carbsPercentageDif,
      int fatPercentageDif) {
    int highestPercentageIndex;
    int maxPercentage = max(
        proteinPercentageDif, max(carbsPercentageDif, fatPercentageDif));
    if (maxPercentage == proteinPercentageDif) highestPercentageIndex = 0;
    if (maxPercentage == carbsPercentageDif) highestPercentageIndex = 1;
    if (maxPercentage == fatPercentageDif) highestPercentageIndex = 2;
    var nutProbabilityToChoose = List<double>.filled(
        nutritionDTOList.length, 0);
    for (var i = 0; i < nutritionDTOList.length; i++) {
//      if (chosenIndex == i) continue;
      int p = nutritionDTOList[i].proteinPerUnit;
      int c = nutritionDTOList[i].carbsPerUnit;
      int f = nutritionDTOList[i].fatInUnit;

      int total = p * proteinCaloryInUnit + c * carbsCaloryInUnit +
          f * fatCaloryInUnit;
      int proteinPercentage = ((p * proteinCaloryInUnit / total) * 100).toInt();
      int carbsPercentage = ((c * carbsCaloryInUnit / total) * 100).toInt();
      int fatPercentage = ((f * fatCaloryInUnit / total) * 100).toInt();
      int a = (proteinPercentageDif - proteinPercentage).abs() +
          (carbsPercentageDif - carbsPercentage).abs() +
          (fatPercentageDif - fatPercentage).abs();

      var percentage = [proteinPercentage, carbsPercentage, fatPercentage];
      int b = percentage[highestPercentageIndex];
      int bMaxVal = proteinPercentage + carbsPercentage + fatPercentage;
      int aMaxVal = max(proteinPercentageDif, proteinPercentage) +
          max(carbsPercentageDif, carbsPercentage) +
          max(fatPercentageDif, fatPercentage);
      nutProbabilityToChoose[i] =
          (0.5 * (1 / (a / aMaxVal)) + 0.5 * (b / bMaxVal)).toDouble();
    }
    return nutProbabilityToChoose.indexOf(nutProbabilityToChoose.reduce((max)));
  }

  int chooseBestNutrition(int lastChosenIndex,
      int proteinPercentageDif,
      int carbsPercentageDif,
      int fatPercentageDif) {
    double best = double.infinity;
    double temp = best;
    int index = 0;
    for (var i = 0; i < this.nutritionDTOList.length; i++) {
      if (i == lastChosenIndex) continue;
      int p = nutritionDTOList[i].proteinPerUnit;
      int c = nutritionDTOList[i].carbsPerUnit;
      int f = nutritionDTOList[i].fatInUnit;
      int total = p + c + f;
      int proteinPercentage = ((p / total) * 100).toInt();
      int carbsPercentage = ((c / total) * 100).toInt();
      int fatPercentage = ((f / total) * 100).toInt();
      int a;
      a = (proteinPercentageDif - proteinPercentage) +
          (carbsPercentageDif - carbsPercentage) +
          (fatPercentageDif - fatPercentage);
      temp = best;
      print("${nutritionDTOList[i]
          .name} has protein: ${proteinPercentage}%  carbs:${carbsPercentage}%  fat:${fatPercentage}%  -- dif(p,c,f) = (${proteinPercentageDif},${carbsPercentageDif},${fatPercentageDif}) and a=${a}");
      best = min(a.toDouble(), best);
      if (temp != best) index = i;
    }
    print(index);
    return index;
  }

  Widget thirdMealsProgramPage(BuildContext context) {
    return Container(
        height: MediaQuery
            .of(context)
            .size
            .height,
        width: MediaQuery
            .of(context)
            .size
            .width,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          children: <Widget>[
            Expanded(
                flex: 10,
                child: Container(
                  padding: EdgeInsets.only(top: 15),
                  child: AdjustText(
                    t.nutrition.choose_meal,
                    fontSize: 12,
                  ),
                )),
            Expanded(
              flex: 10,
              child: Container(
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                    children: <Widget>[
                      Expanded(
                        flex: 5,
                        child: Icon(Icons.keyboard_arrow_left),
                      ),
                      Expanded(
                        flex: 15,
                        child: InkWell(
                          child: Container(
                              padding: EdgeInsets.all(5),
                              child: FittedBox(
                                fit: BoxFit.scaleDown,
                                child: Icon(
                                  Icons.add,
                                  size: 40,
                                ),
                              )),
                          onTap: () {
                            showDialog(
                                context: context,
                                child: Dialog(
                                  child: Container(
                                      alignment: adjustAlignment,
                                      height: 300,
                                      child: SingleChildScrollView(
                                        child: Column(
                                          children: <Widget>[
                                            Container(
                                              alignment: Alignment.centerRight,
                                              height: 50,
                                              width: 200,
                                              child: Directionality(
                                                textDirection: txtDir,
                                                child: SimpleAutoCompleteTextField(
                                                  style: TextStyle(
                                                      fontFamily: "Iransans",
                                                      fontSize: 16,
                                                      color: FONT_COLOR,
                                                      locale: Locale(
                                                          "fa", "IR")),
                                                  decoration: InputDecoration(
                                                    focusColor: GREEN_COLOR,
                                                    hoverColor: GREEN_COLOR,
                                                    fillColor: GREEN_COLOR,
                                                    border: OutlineInputBorder(
                                                      borderRadius:
                                                      BorderRadius.all(
                                                          Radius.circular(5)),
                                                      borderSide: BorderSide(
                                                          color: GREEN_COLOR,
                                                          width: 2,
                                                          style: BorderStyle
                                                              .solid),
                                                    ),
                                                    enabledBorder:
                                                    OutlineInputBorder(
                                                      borderRadius:
                                                      BorderRadius.all(
                                                          Radius.circular(5)),
                                                      borderSide: BorderSide(
                                                          color: GREEN_COLOR,
                                                          width: 2,
                                                          style: BorderStyle
                                                              .solid),
                                                    ),
                                                    focusedBorder:
                                                    OutlineInputBorder(
                                                      borderRadius:
                                                      BorderRadius.all(
                                                          Radius.circular(5)),
                                                      borderSide: BorderSide(
                                                          color: GREEN_COLOR,
                                                          width: 2,
                                                          style: BorderStyle
                                                              .solid),
                                                    ),
                                                    disabledBorder:
                                                    OutlineInputBorder(
                                                      borderRadius:
                                                      BorderRadius.all(
                                                          Radius.circular(5)),
                                                      borderSide: BorderSide(
                                                          color: GREEN_COLOR,
                                                          width: 2,
                                                          style: BorderStyle
                                                              .solid),
                                                    ),
                                                    labelText: t.nutrition
                                                        .choose_meal,
                                                    prefixIcon: null,
                                                    hintStyle: TextStyle(
                                                        fontFamily: "Iransans",
                                                        fontSize: 16,
                                                        color: FONT_COLOR),
                                                    labelStyle: TextStyle(
                                                        fontFamily: "Iransans",
                                                        fontSize: 16,
                                                        color: FONT_COLOR),
                                                    enabled: true,
                                                  ),
                                                  controller:
                                                  mealNameTextEditingController,
                                                  suggestions: MEAL_NAMES,
//                                          textChanged: (text) => mealNameTextEditingController.text = text,
                                                  clearOnSubmit: false,
                                                  textSubmitted: (text) =>
                                                      setState(() {
                                                        if (text != "") {
                                                          mealNameTextEditingController
                                                              .text = text;
                                                        }
                                                      }),
                                                ),
                                              ),
                                            ),
                                            Container(
                                              padding: EdgeInsets.all(10),
                                              child: Row(
                                                mainAxisAlignment:
                                                MainAxisAlignment.spaceEvenly,
                                                children: <Widget>[
                                                  Expanded(
                                                      flex: 5,
                                                      child: Container(
                                                        padding: EdgeInsets.all(
                                                            5),
                                                        child: AdjustRaisedButton(
                                                          textDirection:
                                                          TextDirection.rtl,
                                                          width: 200,
                                                          height: 60,
                                                          fontColor: WHITE_COLOR,
                                                          text: t.main.back,
                                                          secondaryColor: GREEN_COLOR,
                                                          fontSize: 16,
                                                          primaryColor: GREEN_COLOR,
                                                          onPressed: () {
                                                            Navigator.of(
                                                                context,
                                                                rootNavigator:
                                                                true)
                                                                .pop("dialog");
                                                          },
                                                        ),
                                                      )),
                                                  Expanded(
                                                      flex: 5,
                                                      child: Container(
                                                        padding: EdgeInsets.all(
                                                            5),
                                                        child: AdjustRaisedButton(
                                                          textDirection:
                                                          TextDirection.rtl,
                                                          width: 200,
                                                          height: 60,
                                                          fontColor: WHITE_COLOR,
                                                          text: t.main.ok,
                                                          secondaryColor: GREEN_COLOR,
                                                          fontSize: 16,
                                                          primaryColor: GREEN_COLOR,
                                                          onPressed: () {
                                                            if (mealNameTextEditingController
                                                                .text !=
                                                                "" &&
                                                                mealNameTextEditingController
                                                                    .text !=
                                                                    null) {
                                                              setState(() {
                                                                this
                                                                    .mealDTOList
                                                                    .add(
                                                                    MealDTO(
                                                                        null,
                                                                        EnumToString
                                                                            .fromString(
                                                                            MealType
                                                                                .values,
                                                                            mealNameTextEditingController
                                                                                .text),
                                                                        null,
                                                                        null,
                                                                        this
                                                                            .nutritionDTOList));
                                                                mealNameTextEditingController
                                                                    .text = "";
                                                              });
                                                            }
                                                          },
                                                        ),
                                                      ))
                                                ],
                                              ),
                                            )
                                          ],
                                        ),
                                      )),
                                ));
                          },
                        ),
                      ),
                      Expanded(
                          flex: 75,
                          child: Container(
                              alignment: Alignment.centerRight,
                              height: 60,
                              width: 300,
                              child: Directionality(
                                textDirection: txtDir,
                                child: ListView.builder(
                                  scrollDirection: Axis.horizontal,
                                  itemCount: this.mealDTOList.length,
                                  itemBuilder: (BuildContext context, int pos) {
                                    MealDTO mealDTO = this.mealDTOList[pos];
                                    return InkWell(
                                      child: Container(
                                        height: 60,
                                        width: 80,
                                        margin: EdgeInsets.all(2),
                                        padding: EdgeInsets.all(5),
                                        child: FittedBox(
                                          fit: BoxFit.scaleDown,
                                          child: AdjustText(
                                              MEAL_TYPE_LIST[mealDTO.mealType],
                                              color: GREEN_COLOR,
                                              fontSize: 14
                                          ),
                                        ),
                                      ),
                                      onTap: () {
                                        showAdjustDialog(
                                            context,
                                            t.nutrition.want_meal_removed,
                                            true, () {
                                          setState(() {
                                            this
                                                .mealDTOList
                                                .remove(this.mealDTOList[pos]);
                                          });
                                        }, GREEN_COLOR);
                                      },
                                    );
                                  },
                                ),
                              ))),
                      Expanded(
                        flex: 5,
                        child: Icon(Icons.keyboard_arrow_right),
                      ),
                    ],
                  )),
            ),
            Expanded(
                flex: 10,
                child: Container(
                  padding: EdgeInsets.only(top: 15),
                  child: AdjustText(
                    t.nutrition.choose_unit,
                    fontSize: 12,
                  ),
                )),
            Expanded(
                flex: 50,
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                  children: <Widget>[
                    Expanded(
                      flex: 5,
                      child: Column(
                        mainAxisAlignment: MainAxisAlignment.spaceBetween,
                        children: <Widget>[
                          Icon(Icons.keyboard_arrow_up),
                          Icon(Icons.keyboard_arrow_down)
                        ],
                      ),
                    ),
                    Expanded(
                      flex: 95,
                      child: Container(
                        child: ListView.builder(
                          itemCount: this.nutritionDTOList.length,
                          itemBuilder: (BuildContext context, int pos) {
                            NutritionDTO nutritionDTO = this
                                .nutritionDTOList[pos];
                            return Container(
                              alignment: Alignment.centerRight,
                              child: Row(
                                mainAxisAlignment: MainAxisAlignment
                                    .spaceEvenly,
                                children: <Widget>[
                                  Expanded(
                                      flex: 40,
                                      child: Container(
                                        height: 100,
                                        width: 100,
                                        padding: EdgeInsets.all(10),
                                        child: FittedBox(
                                          fit: BoxFit.scaleDown,
                                          child: Directionality(
                                            textDirection: TextDirection.ltr,
                                            child: Stepo(
                                              key: UniqueKey(),
                                              width: 300,
                                              //Optional
                                              backgroundColor: Colors
                                                  .deepPurple,
                                              //Optional
                                              style: Style.horizontal,
                                              //Optional
                                              textColor: Colors.white,
                                              //Optional
                                              animationDuration:
                                              Duration(milliseconds: 0),
                                              //Optional
                                              iconColor: Colors.white,
                                              //Optional
                                              fontSize: 20,
                                              //Optional
                                              iconSize: 30,
                                              //Optional
                                              initialCounter: nutritionDTO.unit,
                                              //Optional
                                              lowerBound: 0,
                                              //Optional
                                              upperBound: 2000,
                                              //Optional
                                              onIncrementClicked: (counter) {
                                                //Optional
                                                if (this.mounted) {
                                                  setState(() {
                                                    this.nutritionDTOList[pos]
                                                        .unit++;
                                                    this.caloriesSet +=
                                                        nutritionDTO
                                                            .caloriesPerUnit;
                                                    this.proteinPSet +=
                                                        nutritionDTO
                                                            .proteinPerUnit;
                                                    this.carbsPSet +=
                                                        nutritionDTO
                                                            .carbsPerUnit;
                                                    this.fatPSet +=
                                                        nutritionDTO.fatInUnit;
                                                  });
                                                }
                                              },
                                              onDecrementClicked: (counter) {
                                                //Optional
                                                if (this.mounted) {
                                                  setState(() {
                                                    this.nutritionDTOList[pos]
                                                        .unit--;
                                                    this.caloriesSet -=
                                                        nutritionDTO
                                                            .caloriesPerUnit;
                                                    this.proteinPSet -=
                                                        nutritionDTO
                                                            .proteinPerUnit;
                                                    this.carbsPSet -=
                                                        nutritionDTO
                                                            .carbsPerUnit;
                                                    this.fatPSet -=
                                                        nutritionDTO.fatInUnit;
                                                  });
                                                }
                                              },
                                            ),
                                          ),
                                        ),
                                      )),
                                  Expanded(
                                      flex: 60,
                                      child: Container(
                                          padding: EdgeInsets.all(10),
                                          child: Column(
                                            children: <Widget>[
                                              FittedBox(
                                                fit: BoxFit.scaleDown,
                                                child: AdjustText(
                                                    nutritionDTO.name,
                                                    color: FONT_COLOR,
                                                    fontSize: 15
                                                ),
                                              ),
                                              FittedBox(
                                                fit: BoxFit.scaleDown,
                                                child: AdjustText(
                                                  t.nutrition.calory +
                                                      " " +
                                                      nutritionDTO
                                                          .caloriesPerUnit
                                                          .toString() +
                                                      " " +
                                                      t.nutrition.protein +
                                                      " " +
                                                      nutritionDTO
                                                          .proteinPerUnit
                                                          .toString() +
                                                      " " +
                                                      t.nutrition.carbs +
                                                      " " +
                                                      nutritionDTO.carbsPerUnit
                                                          .toString() +
                                                      " " +
                                                      t.nutrition.fat +
                                                      " " +
                                                      nutritionDTO.fatInUnit
                                                          .toString() +
                                                      " ",
                                                  fontSize: 15,
                                                ),
                                              ),
                                            ],
                                          ))),
                                ],
                              ),
                            );
                          },
                        ),
                      ),
                    )
                  ],
                )
            ),
            Expanded(
                flex: 25,
                child: Container(
                  height: 100,
                  width: MediaQuery
                      .of(context)
                      .size
                      .width,
                  child: FittedBox(
                    fit: BoxFit.scaleDown,
                    child: DataTable(
                      columns: <DataColumn>[
                        DataColumn(
                            label: Expanded(
                              child: Align(
                                alignment: Alignment.centerRight,
                                child: AdjustText(
                                  t.nutrition.fat,
                                  fontSize: 13,
                                ),
                              ),
                            )),
                        DataColumn(
                            label: Expanded(
                              child: Align(
                                alignment: Alignment.centerRight,
                                child: AdjustText(
                                  t.nutrition.carbs,
                                  fontSize: 13,
                                ),
                              ),
                            )),
                        DataColumn(
                            label: Expanded(
                              child: Align(
                                alignment: Alignment.centerRight,
                                child: AdjustText(
                                  t.nutrition.protein,
                                  fontSize: 13,
                                ),
                              ),
                            )),
                        DataColumn(
                            label: Expanded(
                              child: Align(
                                alignment: Alignment.centerRight,
                                child: AdjustText(
                                  t.nutrition.calory + " " + t.main.daily,
                                  fontSize: 13,
                                ),
                              ),
                            )),
                      ],
                      rows: <DataRow>[
                        DataRow(cells: <DataCell>[
                          DataCell(Align(
                            alignment: Alignment.centerRight,
                            child: AdjustText(
                              getNumber(this.fatP.toString() + "%",),
                              fontSize: 13,
                            ),
                          )),
                          DataCell(Align(
                            alignment: Alignment.centerRight,
                            child: AdjustText(
                              getNumber(this.carbsP.toString() + "%",),
                              fontSize: 13,
                            ),
                          )),
                          DataCell(Align(
                            alignment: Alignment.centerRight,
                            child: AdjustText(
                              getNumber(this.proteinP.toString() + "%",),
                              fontSize: 13,
                            ),
                          )),
                          DataCell(Align(
                            alignment: Alignment.centerRight,
                            child: AdjustText(
                              getNumber(
                                this.caloriesTextEditingController.text,),
                              fontSize: 13,
                            ),
                          )),
                        ]),
                        DataRow(cells: <DataCell>[
                          DataCell(Align(
                            alignment: Alignment.centerRight,
                            child: AdjustText(
                              getNumber((this.fatPSet != 0 &&
                                  this.proteinPSet != 0 &&
                                  this.carbsPSet != 0)
                                  ? ((this.fatPSet * fatCaloryInUnit /
                                  (this.fatPSet * fatCaloryInUnit +
                                      this.proteinPSet * proteinCaloryInUnit +
                                      this.carbsPSet * carbsCaloryInUnit)) *
                                  100)
                                  .round()
                                  .toString()
                                  : "0" + "%",),
                              fontSize: 13,
                            ),
                          )),
                          DataCell(Align(
                            alignment: Alignment.centerRight,
                            child: AdjustText(
                                getNumber((this.fatPSet != 0 &&
                                    this.proteinPSet != 0 &&
                                    this.carbsPSet != 0)
                                    ? ((this.carbsPSet * carbsCaloryInUnit /
                                    (this.fatPSet * fatCaloryInUnit +
                                        this.proteinPSet * proteinCaloryInUnit +
                                        this.carbsPSet * carbsCaloryInUnit)) *
                                    100)
                                    .round()
                                    .toString()
                                    : "0" + "%",),
                                fontSize: 13
                            ),
                          )),
                          DataCell(Align(
                            alignment: Alignment.centerRight,
                            child: AdjustText(
                                getNumber((this.fatPSet != 0 &&
                                    this.proteinPSet != 0 &&
                                    this.carbsPSet != 0)
                                    ? ((this.proteinPSet * proteinCaloryInUnit /
                                    (this.fatPSet * fatCaloryInUnit +
                                        this.proteinPSet * proteinCaloryInUnit +
                                        this.carbsPSet * carbsCaloryInUnit)) *
                                    100)
                                    .round()
                                    .toString()
                                    : "0" + "%",),
                                fontSize: 13
                            ),
                          )),
                          DataCell(Align(
                            alignment: Alignment.centerRight,
                            child: AdjustText(
                              getNumber(this.caloriesSet.round().toString(),),
                              fontSize: 13,
                            ),
                          )),
                        ])
                      ],
                    ),
                  ),
                )),
            Expanded(
              flex: 15,
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                children: <Widget>[
                  Expanded(
                      flex: 5,
                      child: Container(
                        margin: EdgeInsets.all(10),
                        child: AdjustRaisedButton(
                          textDirection: TextDirection.rtl,
                          width: 200,
                          height: 60,
                          fontColor: WHITE_COLOR,
                          text: t.main.back,
                          secondaryColor: GREEN_COLOR,
                          fontSize: 16,
                          primaryColor: GREEN_COLOR,
                          onPressed: () {
                            setState(() {
                              this.nutritionStateSet = Set<NutritionState>();
                              caloriesSet = 0;
                              proteinPSet = 0;
                              carbsPSet = 0;
                              fatPSet = 0;
                            });
                            nutritionIconStepperStateKey.currentState
                                .previous();
                          },
                        ),
                      )),
                  Expanded(
                      flex: 5,
                      child: Container(
                        margin: EdgeInsets.all(10),
                        child: AdjustRaisedButton(
                          textDirection: TextDirection.rtl,
                          width: 200,
                          height: 60,
                          fontColor: WHITE_COLOR,
                          text: t.main.ok,
                          secondaryColor: GREEN_COLOR,
                          fontSize: 16,
                          primaryColor: GREEN_COLOR,
                          onPressed: () {
                            if (this.mealDTOList.length > 0) {
                              this.nutritionProgramDTO.dailyCalories =
                                  this.caloriesSet;
                              int total = this.proteinPSet +
                                  this.carbsPSet +
                                  this.fatPSet;
                              this.nutritionProgramDTO.proteinPercentage =
                                  ((this.proteinPSet / total) * 100).round();
                              this.nutritionProgramDTO.carbsPercentage =
                                  ((this.carbsPSet / total) * 100).round();
                              this.nutritionProgramDTO.fatPercentage =
                                  ((this.fatPSet / total) * 100).round();
                              List<NutritionDTO> l = []
                                ..addAll(this.nutritionDTOList);
                              this.mealDTOList.forEach((mealDTO) {
                                mealDTO.nutritionList =
                                    nutritionDTOList.map((nutritionDTO) {
                                      NutritionDTO temp = NutritionDTO(
                                          nutritionDTO.id,
                                          nutritionDTO.name,
                                          nutritionDTO.description,
                                          0,
                                          nutritionDTO.adjustNutritionId,
                                          nutritionDTO.minUnitLow,
                                          nutritionDTO.minUnitNormal,
                                          nutritionDTO.minUnitMax,
                                          nutritionDTO.caloryBoundryMin,
                                          nutritionDTO.caloryBoundryMax,
                                          nutritionDTO.caloriesPerUnit,
                                          nutritionDTO.proteinPerUnit,
                                          nutritionDTO.carbsPerUnit,
                                          nutritionDTO.fatInUnit,
                                          null,
                                          null);
                                      return temp;
                                    }).toList();
                              });
                              nutritionIconStepperStateKey.currentState.next();
                            } else {
                              showAdjustDialog(context, t.nutrition
                                  .atleast_one_meal, false,
                                  null, GREEN_COLOR);
                            }
                          },
                        ),
                      ))
                ],
              ),
            )
          ],
        ));
  }

  Widget fourthSetMealsProgramPage(BuildContext context) {
    return Container(
        height: MediaQuery
            .of(context)
            .size
            .height,
        width: MediaQuery
            .of(context)
            .size
            .width,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          children: <Widget>[
            Expanded(
                flex: 10,
                child: Container(
                  padding: EdgeInsets.all(5),
                  child: AdjustText(
                    t.nutrition.choose_meal,
                  ),
                )),
            Expanded(
              flex: 20,
              child: Container(
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                    children: <Widget>[
                      Expanded(
                        flex: 5,
                        child: Icon(Icons.keyboard_arrow_left),
                      ),
                      Expanded(
                          flex: 75,
                          child: Container(
                              alignment: Alignment.centerRight,
                              height: 60,
                              width: 300,
                              child: Directionality(
                                textDirection: txtDir,
                                child: ListView.builder(
                                  scrollDirection: Axis.horizontal,
                                  itemCount: this.mealDTOList.length,
                                  itemBuilder: (BuildContext context, int pos) {
                                    MealDTO mealDTO = this.mealDTOList[pos];
                                    return InkWell(
                                      child: Container(
                                        height: 60,
                                        width: 100,
                                        child: Column(
                                          children: <Widget>[
                                            Container(
                                              height: 40,
                                              width: 100,
                                              margin: EdgeInsets.all(2),
                                              padding: EdgeInsets.all(5),
                                              child: FittedBox(
                                                fit: BoxFit.scaleDown,
                                                child: AdjustText(
                                                    MEAL_TYPE_LIST[mealDTO
                                                        .mealType],
                                                    color: GREEN_COLOR,
                                                    fontSize: 14
                                                ),
                                              ),
                                            ),
                                            this.selectedMeal == pos
                                                ? Container(
                                              height: 5,
                                              width: 30,
                                              child: Divider(
                                                height: 5,
                                                thickness: 2,
                                                color: GREEN_COLOR,
                                              ),
                                            )
                                                : Container(
                                              height: 5,
                                              width: 30,
                                            )
                                          ],
                                        ),
                                      ),
                                      onTap: () {
                                        setState(() {
                                          selectedMeal = pos;
                                        });
                                      },
                                    );
                                  },
                                ),
                              ))),
                      Expanded(
                        flex: 5,
                        child: Icon(Icons.keyboard_arrow_right),
                      ),
                    ],
                  )),
            ),
            Expanded(
                flex: 60,
                child: Container(
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                    children: <Widget>[
                      Expanded(
                        flex: 5,
                        child: Column(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: <Widget>[
                            Icon(Icons.keyboard_arrow_up),
                            Icon(Icons.keyboard_arrow_down)
                          ],
                        ),
                      ),
                      Expanded(
                        flex: 95,
                        child: Container(
                          child: ListView.builder(
                            itemCount: this.nutritionDTOList.length,
                            itemBuilder: (BuildContext context, int pos) {
                              NutritionDTO nutritionDTO =
                              this.mealDTOList[selectedMeal].nutritionList[pos];
                              return Container(
                                alignment: Alignment.centerRight,
                                child: Row(
                                  mainAxisAlignment: MainAxisAlignment
                                      .spaceEvenly,
                                  children: <Widget>[
                                    Expanded(
                                      flex: 50,
                                      child: Container(
                                        height: 100,
                                        width: 200,
                                        child: Stepo(
                                          key: UniqueKey(),
                                          width: 150,
                                          //Optional
                                          backgroundColor: Colors.deepPurple,
                                          //Optional
                                          style: Style.horizontal,
                                          //Optional
                                          textColor: Colors.white,
                                          //Optional
                                          animationDuration: Duration(
                                              milliseconds: 0),
                                          //Optional
                                          iconColor: Colors.white,
                                          //Optional
                                          fontSize: 20,
                                          //Optional
                                          iconSize: 30,
                                          //Optional
                                          initialCounter: nutritionDTO.unit,
                                          //Optional
                                          lowerBound: 0,
                                          //Optional
                                          upperBound: nutritionDTOList[pos]
                                              .unit,
                                          //Optional
                                          onIncrementClicked: (counter) {
                                            //Optional
                                            if (this.mounted &&
                                                nutritionDTOList[pos].unit >
                                                    0) {
                                              setState(() {
                                                nutritionDTOList[pos].unit--;
                                                nutritionDTO.unit++;
                                              });
                                            }
                                          },
                                          onDecrementClicked: (counter) {
                                            //Optional
                                            if (this.mounted &&
                                                nutritionDTO.unit > 0) {
                                              setState(() {
                                                nutritionDTOList[pos].unit++;
                                                nutritionDTO.unit--;
                                              });
                                            }
                                          },
                                        ),
                                      ),
                                    ),
                                    Expanded(
                                        flex: 25,
                                        child: Container(
                                          alignment: Alignment.centerRight,
                                          padding: EdgeInsets.all(10),
                                          child: FittedBox(
                                            fit: BoxFit.scaleDown,
                                            child: AdjustText(
                                              getNumber(this
                                                  .nutritionDTOList[pos]
                                                  .unit
                                                  .toString() +
                                                  " " + t.nutrition.unit,),
                                              fontSize: 14,
                                            ),
                                          ),
                                        )),
                                    Expanded(
                                        flex: 25,
                                        child: Container(
                                          alignment: Alignment.centerRight,
                                          padding: EdgeInsets.all(10),
                                          child: FittedBox(
                                            fit: BoxFit.scaleDown,
                                            child: AdjustText(
                                                nutritionDTO.name,
                                                fontSize: 15
                                            ),
                                          ),
                                        )),
                                  ],
                                ),
                              );
                            },
                          ),
                        ),
                      )
                    ],
                  ),
                )
            ),
            Expanded(
              flex: 15,
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                children: <Widget>[
                  Expanded(
                      flex: 5,
                      child: Container(
                        margin: EdgeInsets.all(10),
                        child: AdjustRaisedButton(
                          textDirection: TextDirection.rtl,
                          width: 200,
                          height: 60,
                          fontColor: WHITE_COLOR,
                          text: t.main.back,
                          secondaryColor: GREEN_COLOR,
                          fontSize: 16,
                          primaryColor: GREEN_COLOR,
                          onPressed: () {
                            setState(() {
                              this.nutritionStateSet = Set<NutritionState>();
                              caloriesSet = 0;
                              proteinPSet = 0;
                              carbsPSet = 0;
                              fatPSet = 0;
                              this.nutritionDTOList.forEach((element) {
                                element.unit = 0;
                              });
                            });
                            disperseUnitsInNutrition();
                            nutritionIconStepperStateKey.currentState
                                .previous();
                          },
                        ),
                      )),
                  Expanded(
                      flex: 5,
                      child: Container(
                        margin: EdgeInsets.all(10),
                        child: AdjustRaisedButton(
                          textDirection: TextDirection.rtl,
                          width: 200,
                          height: 60,
                          fontColor: WHITE_COLOR,
                          text: t.main.ok,
                          secondaryColor: GREEN_COLOR,
                          fontSize: 16,
                          primaryColor: GREEN_COLOR,
                          onPressed: () {
                            //
                            nutritionIconStepperStateKey.currentState.next();
                          },
                        ),
                      ))
                ],
              ),
            )
          ],
        ));
  }

  Widget firstMainNutritionProgramPage(BuildContext context) {
    return StoreConnector<AppState, AppState>(
      converter: (Store store) => store.state,
      builder: (BuildContext context, AppState state) {
        return Container(
            height: MediaQuery
                .of(context)
                .size
                .height - 100,
            width: MediaQuery
                .of(context)
                .size
                .width,
            padding: EdgeInsets.all(10),
            child: SingleChildScrollView(
              child: Column(
                children: <Widget>[
                  Container(
                    height: 100,
                    child: AdjustTextField(
                        textInputType: TextInputType.number,
                        fontColor: GREEN_COLOR,
                        maxLines: 1,
                        textAlign: TextAlign.right,
                        textDirection: TextDirection.rtl,
                        controller: caloriesTextEditingController,
                        hintText: t.nutrition.calory,
                        enabled: true,
                        icon: null,
                        validator: (String val) {
                          if (val == null || val == "") {
                            return t.user.fill;
                          } else
                            return null;
                        },
                        isPassword: false,
                        primaryColor: GREEN_COLOR,
                        padding: 0,
                        margin: 20),
                  ),
                  Container(
                    height: 100,
                    child: AdjustTextField(
                        textInputType: TextInputType.number,
                        fontColor: GREEN_COLOR,
                        maxLines: 1,
                        textAlign: TextAlign.right,
                        textDirection: TextDirection.rtl,
                        controller: fatPercentageTextEditingController,
                        hintText: t.nutrition.fat_percentage,
                        enabled: true,
                        icon: null,
                        validator: (String val) {
                          if (val == null || val == "") {
                            return t.user.fill;
                          } else
                            return null;
                        },
                        isPassword: false,
                        primaryColor: GREEN_COLOR,
                        padding: 0,
                        margin: 20),
                  ),
                  Container(
                    height: 100,
                    child: AdjustTextField(
                        textInputType: TextInputType.number,
                        fontColor: GREEN_COLOR,
                        maxLines: 1,
                        textAlign: TextAlign.right,
                        textDirection: TextDirection.rtl,
                        controller: carbsPercentageTextEditingController,
                        hintText: t.nutrition.carbs_percentage,
                        enabled: true,
                        icon: null,
                        validator: (String val) {
                          if (val == null || val == "") {
                            return t.user.fill;
                          } else
                            return null;
                        },
                        isPassword: false,
                        primaryColor: GREEN_COLOR,
                        padding: 0,
                        margin: 20),
                  ),
                  Container(
                    height: 100,
                    child: AdjustTextField(
                        textInputType: TextInputType.number,
                        fontColor: GREEN_COLOR,
                        maxLines: 1,
                        textAlign: TextAlign.right,
                        textDirection: TextDirection.rtl,
                        controller: proteinPercentageTextEditingController,
                        hintText: t.nutrition.protein_percentage,
                        enabled: true,
                        icon: null,
                        validator: (String val) {
                          if (val == null || val == "") {
                            return t.user.fill;
                          } else
                            return null;
                        },
                        isPassword: false,
                        primaryColor: GREEN_COLOR,
                        padding: 0,
                        margin: 20),
                  ),
                  Container(
                    height: 80,
                    width: 280,
                    padding: EdgeInsets.all(10),
                    child: AdjustRaisedButton(
                      text: t.main.ok,
                      textDirection: TextDirection.rtl,
                      primaryColor: GREEN_COLOR,
                      secondaryColor: GREEN_COLOR,
                      height: 50,
                      width: 90,
                      onPressed: () {
                        if (caloriesTextEditingController.text != null &&
                            caloriesTextEditingController.text != "") {
                          if (proteinPercentageTextEditingController.text !=
                              null &&
                              proteinPercentageTextEditingController.text !=
                                  "" &&
                              carbsPercentageTextEditingController.text !=
                                  null &&
                              carbsPercentageTextEditingController.text != "" &&
                              fatPercentageTextEditingController.text != null &&
                              fatPercentageTextEditingController.text != "") {
                            double calory = double.parse(
                                caloriesTextEditingController.text);
                            setPercentage();
                            if (carbsP + proteinP + fatP == 100) {
                              nutritionProgramDTO = NutritionProgramDTO(
                                  null,
                                  calory,
                                  proteinP,
                                  carbsP,
                                  fatP,
                                  null,
                                  null);
                              nutritionIconStepperStateKey.currentState.next();
                            } else {
                              showAdjustDialog(
                                  context,
                                  t.nutrition.addition_must_be_100,
                                  false,
                                  null,
                                  GREEN_COLOR);
                            }
                          } else {
                            showAdjustDialog(
                                context,
                                t.nutrition.fill_percentage,
                                false,
                                null,
                                GREEN_COLOR);
                          }
                        } else {
                          showAdjustDialog(
                              context,
                              t.nutrition.fill_calory,
                              false,
                              null,
                              GREEN_COLOR);
                        }
//                        iconStepperStateKey.currentState.next();
                      },
                    ),
                  )
                ],
              ),
            ));
      },
    );
  }

  Widget fifthDescriptionPage() {
    return Container(
      child: SingleChildScrollView(
        child: StoreConnector<AppState, AppState>(
          converter: (Store store) => store.state,
          builder: (BuildContext context, AppState state) {
            return Container(
                child: SingleChildScrollView(
                  child: Column(
                    children: <Widget>[
                      Container(
                        padding: EdgeInsets.all(20),
                        child: AdjustText(
                            t.main.description,
                            color: GREEN_COLOR,
                            fontSize: 18
                        ),
                      ),
                      AdjustTextField(
                          fontColor: FONT_COLOR,
                          maxLines: 8,
                          textAlign: TextAlign.right,
                          textDirection: TextDirection.rtl,
                          controller: descriptionTextEditingController,
                          hintText: t.main.description,
                          enabled: true,
                          icon: null,
                          validator: (String val) {
                            if (val == null || val == "") {
                              return t.user.fill;
                            } else
                              return null;
                          },
                          isPassword: false,
                          primaryColor: GREEN_COLOR,
                          padding: 0,
                          margin: 20),
                      Row(
                        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                        children: <Widget>[
                          Expanded(
                              flex: 5,
                              child: Container(
                                margin: EdgeInsets.all(10),
                                child: AdjustRaisedButton(
                                  textDirection: TextDirection.rtl,
                                  width: 200,
                                  height: 60,
                                  fontColor: WHITE_COLOR,
                                  text: t.main.back,
                                  secondaryColor: GREEN_COLOR,
                                  fontSize: 16,
                                  primaryColor: GREEN_COLOR,
                                  onPressed: () {
                                    nutritionIconStepperStateKey.currentState
                                        .previous();
                                  },
                                ),
                              )),
                          Expanded(
                              flex: 5,
                              child: Container(
                                margin: EdgeInsets.all(10),
                                child: AdjustRaisedButton(
                                  textDirection: TextDirection.rtl,
                                  width: 200,
                                  height: 60,
                                  fontColor: WHITE_COLOR,
                                  text: t.main.ok,
                                  secondaryColor: GREEN_COLOR,
                                  fontSize: 16,
                                  primaryColor: GREEN_COLOR,
                                  onPressed: () {
                                    showAdjustDialog(
                                        context, t.user.sure_with_decision,
                                        true,
                                            () async {
                                          ProgramState programState =
                                              this.widget.programState;
                                          this.nutritionProgramDTO.description =
                                              descriptionTextEditingController
                                                  .text;
                                          this.nutritionProgramDTO.meals =
                                              this.mealDTOList;
                                          ProgramDTO programDTO = ProgramDTO(
                                              programState.id,
                                              null,
                                              null,
                                              null,
                                              null,
                                              null,
                                              null,
                                              null,
                                              null,
                                              null,
                                              null,
                                              null,
                                              null,
                                              null,
                                              null,
                                              null,
                                              null,
                                              null,
                                              this.nutritionProgramDTO,
                                              null,
                                              null,
                                              null);
                                          int i = await designNutritionProgram(
                                              context, programDTO);
                                          if (i == 1) {
                                            showAdjustDialog(
                                                context, t.user.success, false,
                                                null, GREEN_COLOR);
                                            Navigator.of(context)
                                                .pushReplacement(
                                                MaterialPageRoute(
                                                    builder: (context) =>
                                                        MainPage()));
                                          } else {
                                            showAdjustDialog(
                                                context, t.user.failure, false,
                                                null, GREEN_COLOR);
                                          }
                                        }, GREEN_COLOR);
                                  },
                                ),
                              ))
                        ],
                      ),
                    ],
                  ),
                ));
          },
        ),
      ),
    );
  }

  @override
  void dispose() {
    // TODO: implement dispose
    mealNameTextEditingController.dispose();
    descriptionTextEditingController.dispose();
    caloriesTextEditingController.dispose();
    proteinPercentageTextEditingController.dispose();
    carbsPercentageTextEditingController.dispose();
    fatPercentageTextEditingController.dispose();
    super.dispose();
  }
}
