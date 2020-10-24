
import 'dart:math';

import 'package:adjust_client/components/adjust_dropdown_field.dart';
import 'package:adjust_client/components/adjust_body_type_quiz.dart';
import 'package:adjust_client/components/adjust_ruler_picker.dart';
import 'package:adjust_client/components/adjust_text.dart';
import 'package:adjust_client/config/i18n.dart';
import 'package:adjust_client/constants/adjust_colors.dart';
import 'package:adjust_client/constants/words.dart';
import 'package:adjust_client/dto/body_composition_dto.dart';
import 'package:adjust_client/main.dart';
import 'package:adjust_client/model/enum/activity_type.dart';
import 'package:adjust_client/model/enum/bmi_category.dart';
import 'package:adjust_client/model/enum/body_fat_category.dart';
import 'package:adjust_client/model/enum/body_frame_size.dart';
import 'package:adjust_client/model/enum/body_type.dart';
import 'package:adjust_client/model/enum/gender.dart';
import 'package:adjust_client/model/enum/goal.dart';
import 'package:adjust_client/model/enum/workout_location.dart';
import 'package:adjust_client/model/enum/workout_type.dart';
import 'package:adjust_client/states/app_state.dart';
import 'package:adjust_client/states/body_composition_state.dart';
import 'package:adjust_client/states/client_state.dart';
import 'package:age/age.dart';
import 'package:enum_to_string/enum_to_string.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:redux/redux.dart';
import 'package:step_progress_indicator/step_progress_indicator.dart';
import 'package:toast/toast.dart';

import 'adjust_raised_button.dart';

typedef SetBodyCompositionDTO(BodyCompositionDTO);

class AdjustBodyComposition extends StatefulWidget {
  SetBodyCompositionDTO setBodyCompositionDTO;
  Function func;

  AdjustBodyComposition(this.setBodyCompositionDTO, this.func);

  @override
  _AdjustBodyCompositionState createState() => _AdjustBodyCompositionState();
}

class _AdjustBodyCompositionState extends State<AdjustBodyComposition> {
  int _currentStep = 1;
  int _totalSteps = 11;

  PageController _pageController;
  PageController _quizPageController = PageController();

  ClientState clientState;
  BodyCompositionState bodyCompositionState;

  RulerPickerController _heightRulerPickerController = RulerPickerController();
  TextEditingController _heightTextEditingController = TextEditingController();

  RulerPickerController _weightRulerPickerController = RulerPickerController();
  TextEditingController _weightTextEditingController = TextEditingController();

  RulerPickerController _wristRulerPickerController = RulerPickerController();
  TextEditingController _wristTextEditingController = TextEditingController();

  RulerPickerController _neckRulerPickerController = RulerPickerController();
  TextEditingController _neckTextEditingController = TextEditingController();

  RulerPickerController _waistRulerPickerController = RulerPickerController();
  TextEditingController _waistTextEditingController = TextEditingController();

  RulerPickerController _abdomenRulerPickerController = RulerPickerController();
  TextEditingController _abdomenTextEditingController = TextEditingController();

  RulerPickerController _hipRulerPickerController = RulerPickerController();
  TextEditingController _hipTextEditingController = TextEditingController();

  double height = 0;
  double weight = 0;
  double wrist = 0;
  double neck = 0;
  double waist = 0;
  double abdomen = 0;
  double hip = 0;

  int age = 0;

  bool bodyTypeQuizComplete = false;

  final _formKey = GlobalKey<FormState>();

  String workoutLocation;
  TextEditingController _workoutLocationTEC = TextEditingController();

  String goal;
  TextEditingController _goalTEC = TextEditingController();

  String activityType;
  TextEditingController _activityTypeTEC = TextEditingController();

  String workoutType;
  TextEditingController _workoutTypeTEC = TextEditingController();

  int bodyTypeSum;

  void bodyTypeAdd(int index) {
    setState(() {
      this.bodyTypeSum += index;
    });
  }

  void setWorkoutLocation(String workoutLocation) {
    setState(() {
      this.workoutLocation = workoutLocation;
    });
  }

  void setGoal(String goal) {
    setState(() {
      this.goal = goal;
    });
  }

  void setActivityType(String activityType) {
    setState(() {
      this.activityType = activityType;
    });
  }

  void setWorkoutType(String workoutType) {
    setState(() {
      this.workoutType = workoutType;
    });
  }

  double minIdealWeight() {
    if (clientState.gender == Gender.MALE)
      return 50 + (0.91 * (bodyCompositionState.height - 152.4));
    else
      return 45.5 + (0.91 * (bodyCompositionState.height - 152.4));
  }

  double maxIdealWeight() {
    double bmi = 24.9;
    return 2.2 * bmi + (3.5 * bmi) * (bodyCompositionState.height / 100 - 1.5);
  }

  double bmi() {
    return bodyCompositionState.weight /
        pow((bodyCompositionState.height / 100), 2);
  }

  double bmr() {
    double w = bodyCompositionState.weight;
    if (clientState.gender == Gender.MALE) {
      if (age <= 18) {
        return (w * 17.5) + 651;
      } else if (age > 18 && age < 30) {
        return (w * 15.3) + 679;
      } else if (age > 30) {
        return (w * 11.6) + 879;
      }
    } else if (clientState.gender == Gender.FEMALE) {
      if (age <= 18) {
        return (w * 12.2) + 746;
      } else if (age > 18 && age < 30) {
        return (w * 14.7) + 496;
      } else if (age > 30) {
        return (w * 8.7) + 829;
      }
    }
  }

  BMICategory getBmiCategory() {
    double x = bmi();
    if (x <= 18.5) {
      return BMICategory.UNDERWEIGHT;
    } else if (x > 18.5 && x <= 24.9) {
      return BMICategory.NORMAL;
    } else if (x > 24.9 && x <= 29.9) {
      return BMICategory.OVERWEIGHT;
    } else if (x > 29.9 && x <= 34.9) {
      return BMICategory.OBESE;
    } else if (x > 34.9 && x <= 39.9) {
      return BMICategory.HIGHOBESE;
    } else if (x > 39.9) {
      return BMICategory.EXTRAOBESE;
    }
  }

  double pbf() {
    if (clientState.gender == Gender.MALE) {
      return 86.010 * log(abdomen - neck) - 70.041 * log(height) + 36.76;
    } else if (clientState.gender == Gender.FEMALE) {
      return 163.205 * log(waist + hip - neck) - 97.684 * log(height) - 78.387;
    }
  }

  BodyFatCategory getBodyFatCategory() {
    double p = pbf();
    if (clientState.gender == Gender.MALE) {
      if (p <= 6) {
        return BodyFatCategory.ESSENTIAL;
      } else if (p > 6 && p <= 14) {
        return BodyFatCategory.ATHLETE;
      } else if (p > 14 && p <= 18) {
        return BodyFatCategory.FITNESS;
      } else if (p > 18 && p <= 25) {
        return BodyFatCategory.AVERAGE;
      } else if (p > 25) {
        return BodyFatCategory.OBESE;
      }
    } else if (clientState.gender == Gender.FEMALE) {
      if (p <= 14) {
        return BodyFatCategory.ESSENTIAL;
      } else if (p > 14 && p <= 21) {
        return BodyFatCategory.ATHLETE;
      } else if (p > 21 && p <= 25) {
        return BodyFatCategory.FITNESS;
      } else if (p > 25 && p <= 32) {
        return BodyFatCategory.AVERAGE;
      } else if (p > 32) {
        return BodyFatCategory.OBESE;
      }
    }
  }

  double lbm() {
    return 100 - pbf();
  }

  double getBodyFrame() {
    return height / wrist;
  }

  BodyFrameSize getBodyFrameSize() {
    double r = getBodyFrame();
    if (clientState.gender == Gender.MALE) {
      if (r > 10.4) {
        return BodyFrameSize.SMALL;
      } else if (r > 9.6 && r <= 10.4) {
        return BodyFrameSize.MEDIUM;
      } else if (r <= 9.6){
        return BodyFrameSize.LARGE;
      }
    } else if (clientState.gender == Gender.FEMALE) {
      if (r > 11) {
        return BodyFrameSize.SMALL;
      } else if (r > 10.1 && r <= 11) {
        return BodyFrameSize.MEDIUM;
      } else if (r <= 10.1){
        return BodyFrameSize.LARGE;
      }
    }
  }

  BodyType getBodyType(double bodyTypeNum) {
    double x = bodyTypeNum / 11;
    if (bodyTypeNum < 1.5) {
      return BodyType.ENDOMORPH;
    } else if (bodyTypeNum >= 1.5 && bodyTypeNum < 2.5) {
      return BodyType.MESOMORPH;
    } else if (bodyTypeNum >= 2.5) {
      return BodyType.ECTOMORPH;
    }
  }

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    _pageController = PageController(initialPage: 0);
    bodyTypeSum = 0;
    bodyCompositionState =
        store.state.clientState.medicalProfile.bodyComposition;
    clientState = store.state.clientState;
    AgeDuration a = Age.dateDifference(
        fromDate: clientState.birthDate,
        toDate: DateTime.now(),
        includeToDate: false);
    age = a.years;

    if (bodyCompositionState != null) {
      height =
          bodyCompositionState.height == null ? 0 : bodyCompositionState.height;
      weight =
          bodyCompositionState.weight == null ? 0 : bodyCompositionState.weight;
      wrist =
          bodyCompositionState.wrist == null ? 0 : bodyCompositionState.wrist;
      neck = bodyCompositionState.neck == null ? 0 : bodyCompositionState.neck;
      waist =
          bodyCompositionState.waist == null ? 0 : bodyCompositionState.waist;
      abdomen = bodyCompositionState.abdomen == null
          ? 0
          : bodyCompositionState.abdomen;
      hip = bodyCompositionState.hip == null ? 0 : bodyCompositionState.hip;
    }

    _heightTextEditingController.text = height.toString();
    _weightTextEditingController.text = weight.toString();
    _wristTextEditingController.text = wrist.toString();
    _neckTextEditingController.text = neck.toString();
    _waistTextEditingController.text = waist.toString();
    _abdomenTextEditingController.text = abdomen.toString();
    _hipTextEditingController.text = hip.toString();

    _heightRulerPickerController.value = height;
    _weightRulerPickerController.value = weight;
    _wristRulerPickerController.value = wrist;
    _neckRulerPickerController.value = neck;
    _waistRulerPickerController.value = waist;
    _abdomenRulerPickerController.value = abdomen;
    _hipRulerPickerController.value = hip;
  }

  @override
  Widget build(BuildContext context) {
    return StoreConnector<AppState, AppState>(
        converter: (Store store) => store.state,
        builder: (BuildContext context, AppState state) {
          return Container(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: <Widget>[
                Expanded(
                  flex: 2,
                  child: Row(
                    textDirection: txtDir,
                    mainAxisAlignment: MainAxisAlignment.spaceAround,
                    children: <Widget>[
                      _currentStep == _totalSteps
                          ? Expanded(
                              flex: 2,
                              child: Container(),
                            )
                          : Expanded(
                              flex: 2,
                              child: Container(
                                height: 80,
                                width: 80,
                                padding: EdgeInsets.only(left: 5, right: 5),
                                child: InkWell(
                                  child: Column(
                                    children: <Widget>[
                                      Icon(
                                        Icons.arrow_right,
                                        size: 38,
                                      ),
                                      FittedBox(
                                        fit: BoxFit.scaleDown,
                                        child: AdjustText(t.main.next),
                                      )
                                    ],
                                  ),
                                  onTap: () {
                                    if (_currentStep < _totalSteps) {
                                      switch (_currentStep) {
                                        case 9: {
                                          if (_formKey.currentState.validate()) {
                                            setState(() {
                                              _currentStep++;
                                              int page = _currentStep - 1;
                                              _pageController.animateToPage(page,
                                                  duration: Duration(seconds: 1),
                                                  curve: ElasticOutCurve());
                                            });
                                          }
                                        }; break;
                                        case 10: {
                                          if (bodyTypeQuizComplete) {
                                            setState(() {
                                              _currentStep++;
                                              int page = _currentStep - 1;
                                              _pageController.animateToPage(page,
                                                  duration: Duration(seconds: 1),
                                                  curve: ElasticOutCurve());
                                            });
                                          } else {
                                            Toast.show(
                                                t.body_composition.body_type.quiz_not_finished,
                                                context,
                                                duration: Toast.LENGTH_SHORT,
                                                gravity:  Toast.BOTTOM
                                            );
                                          }
                                        }; break;
                                        default: {
                                          setState(() {
                                            _currentStep++;
                                            int page = _currentStep - 1;
                                            _pageController.animateToPage(page,
                                                duration: Duration(seconds: 1),
                                                curve: ElasticOutCurve());
                                          });
                                          if (_currentStep == 10) {
                                            bodyTypeSum = 0;
                                          }
                                        }
                                      }

                                    }
                                  },
                                ),
                              )),
                      Expanded(
                          flex: 6,
                          child: Container(
                            padding: EdgeInsets.all(25),
                            child: StepProgressIndicator(
                              totalSteps: _totalSteps,
                              currentStep: _currentStep,
                              selectedColor: GREEN_COLOR,
                              unselectedColor: YELLOW_COLOR,
                            ),
                          )),
                      _currentStep == 1
                          ? Expanded(
                              flex: 2,
                              child: Container(),
                            )
                          : Expanded(
                              flex: 2,
                              child: Container(
                                height: 80,
                                width: 80,
                                padding: EdgeInsets.only(left: 5, right: 5),
                                child: InkWell(
                                  child: Column(
                                    children: <Widget>[
                                      Icon(
                                        Icons.arrow_left,
                                        size: 38,
                                      ),
                                      FittedBox(
                                        fit: BoxFit.scaleDown,
                                        child: AdjustText(t.main.previous),
                                      )
                                    ],
                                  ),
                                  onTap: () {
                                    if (_currentStep > 1) {
                                      setState(() {
                                        _currentStep--;
                                        int page = _currentStep - 1;
                                        _pageController.animateToPage(page,
                                            duration: Duration(seconds: 1),
                                            curve: ElasticOutCurve());
                                      });
                                    }
                                    if (_currentStep == 10) {
                                      setState(() {
                                        bodyTypeSum = 0;
                                        bodyTypeQuizComplete = false;
                                      });
                                    }
                                  },
                                ),
                              )),
                    ],
                  ),
                ),

                Expanded(
                    flex: 8,
                    child: PageView(
                      controller: _pageController,
                      pageSnapping: false,
                      physics: NeverScrollableScrollPhysics(),
                      onPageChanged: (int page) {
                        setState(() {
                          _currentStep = page + 1;
                        });
                      },
                      children: <Widget>[
                        firstPage(),
                        Container(
                            child: section(
                                context,
                                t.body_composition.height,
                                t.body_composition.centimeter,
                                t.body_composition.notes.height,
                                clientState.gender == Gender.MALE ? Image.asset("assets/male_height.png") : Image.asset("assets/female_height.png"),
                                _heightRulerPickerController,
                                _heightTextEditingController)),
                        Container(
                            child: section(
                                context,
                                t.body_composition.weight,
                                t.body_composition.kilogram,
                                t.body_composition.notes.weight,
                                Image.asset("assets/scale.png"),
                                _weightRulerPickerController,
                                _weightTextEditingController)),
                        Container(
                            child: section(
                                context,
                                t.body_composition.wrist,
                                t.body_composition.centimeter,
                                t.body_composition.notes.wrist,
                                clientState.gender == Gender.MALE ? Image.asset("assets/male_wrist.png") : Image.asset("assets/female_wrist.png"),
                                _wristRulerPickerController,
                                _wristTextEditingController)),
                        Container(
                            child: section(
                                context,
                                t.body_composition.neck,
                                t.body_composition.centimeter,
                                t.body_composition.notes.neck,
                                clientState.gender == Gender.MALE ? Image.asset("assets/male_neck.png") : Image.asset("assets/female_neck.png"),
                                _neckRulerPickerController,
                                _neckTextEditingController)),
                        Container(
                            child: section(
                                context,
                                t.body_composition.waist,
                                t.body_composition.centimeter,
                                t.body_composition.notes.waist,
                                clientState.gender == Gender.MALE ? Image.asset("assets/male_waist.png") : Image.asset("assets/female_waist.png"),
                                _waistRulerPickerController,
                                _waistTextEditingController)),
                        Container(
                            child: section(
                                context,
                                t.body_composition.abdomen,
                                t.body_composition.centimeter,
                                t.body_composition.notes.abdomen,
                                clientState.gender == Gender.MALE ? Image.asset("assets/male_abdomen.png") : Image.asset("assets/female_abdomen.png"),
                                _abdomenRulerPickerController,
                                _abdomenTextEditingController)),
                        Container(
                            child: section(
                                context,
                                t.body_composition.hip,
                                t.body_composition.centimeter,
                                t.body_composition.notes.hip,
                                clientState.gender == Gender.MALE ? Image.asset("assets/male_hip.png") : Image.asset("assets/female_hip.png"),
                                _hipRulerPickerController,
                                _hipTextEditingController)),
                        infoPage(),
                        bodyTypePage(),
                        lastPage(),
                      ],
                    )),
//                Expanded(
//                  flex: 1,
//                  child: RaisedButton(
//                    child: Text("Click"),
//                    onPressed: () {
//                      print(_textEditingController.text);
//                    },
//                  ),
//                )
              ],
            ),
          );
        });
  }

  Widget section(
      BuildContext context,
      String text,
      String scale,
      String description,
      Image image,
      RulerPickerController controller,
      TextEditingController textEditingController) {
    return Column(
      mainAxisAlignment: MainAxisAlignment.spaceEvenly,
      children: <Widget>[
        Expanded(
            flex: 2,
            child: Container(
              padding: EdgeInsets.all(10),
              child: Container(
                  padding: EdgeInsets.all(10),
                  child: FittedBox(
                    fit: BoxFit.scaleDown,
                    child: AdjustText(
                      text +
                          ": " +
                          getNumber(double.parse(textEditingController.text)
                              .toInt()
                              .toString()) +
                          " " +
                          scale,
                      fontSize: 25,
                    ),
                  )),
            )),
        Expanded(
          flex: 3,
          child: Container(
            padding: EdgeInsets.all(10),
            child: image,
          ),
        ),
        Expanded(
          flex: 3,
          child: Container(
              padding: EdgeInsets.all(10),
              child: SingleChildScrollView(
                child: AdjustText(
                  description,
                  fontSize: 13,
                ),
              )),
        ),
        Expanded(
          flex: 2,
          child: Container(
            height: 90,
            child: RulerPicker(
              controller: controller,
              backgroundColor: GREEN_COLOR,
              textColor: WHITE_COLOR,
              pickerColor: RED_COLOR,
              currentValue: double.parse(textEditingController.text),
              height: 90,
              onValueChange: (value) {
                setState(() {
                  textEditingController.text = value.toInt().toString();
                });
              },
              width: MediaQuery.of(context).size.width,
            ),
          ),
        ),
      ],
    );
  }

  Widget firstPage() {
    return Container(
      padding: EdgeInsets.all(30),
      child: Center(
          child: SingleChildScrollView(
        child: AdjustText(t.body_composition.notes.first_page),
      )),
    );
  }

  Widget infoPage() {
    return Container(
      padding: EdgeInsets.all(40),
      child: SingleChildScrollView(
          child: Form(
        key: _formKey,
        child: Column(
          children: <Widget>[
            Container(
              padding: EdgeInsets.all(5),
              child: AdjustDropDownField(
                  itemsMap: WORKOUT_LOCATIONS,
                  items: [
                    WORKOUT_LOCATIONS[EnumToString.parse(WorkoutLocation.GYM)],
                    WORKOUT_LOCATIONS[EnumToString.parse(WorkoutLocation.HOME)],
                  ],
                  value: workoutLocation == null
                      ? null
                      : WORKOUT_LOCATIONS[workoutLocation],
                  setValue: setWorkoutLocation,
                  textDirection: txtDir,
                  alignment: adjustAlignment,
                  controller: _workoutLocationTEC,
                  hintText:
                      t.body_composition.workout_location.workout_location,
                  enabled: true,
                  icon: Icon(
                    Icons.question_answer,
                    color: YELLOW_COLOR,
                  ),
                  validator: (String val) {
                    if (val == null || val == "") {
                      return t.user.choose;
                    } else
                      return null;
                  },
                  isPassword: false,
                  primaryColor: YELLOW_COLOR,
                  padding: 0,
                  margin: 10),
            ),
            Container(
              padding: EdgeInsets.all(5),
              child: AdjustDropDownField(
                  itemsMap: GOALS,
                  items: [
                    GOALS[EnumToString.parse(Goal.DECREASEWEIGHT)],
                    GOALS[EnumToString.parse(Goal.INCREASEWEIGHT)],
                  ],
                  value: goal == null ? null : GOALS[goal],
                  setValue: setGoal,
                  textDirection: txtDir,
                  alignment: adjustAlignment,
                  controller: _goalTEC,
                  hintText: t.body_composition.goal.goal,
                  enabled: true,
                  icon: Icon(
                    Icons.question_answer,
                    color: YELLOW_COLOR,
                  ),
                  validator: (String val) {
                    if (val == null || val == "") {
                      return t.user.choose;
                    } else
                      return null;
                  },
                  isPassword: false,
                  primaryColor: YELLOW_COLOR,
                  padding: 0,
                  margin: 10),
            ),
            Container(
              padding: EdgeInsets.all(5),
              child: AdjustDropDownField(
                  itemsMap: ACTIVITY_TYPES,
                  items: [
                    ACTIVITY_TYPES[EnumToString.parse(ActivityType.SEDENTARY)],
                    ACTIVITY_TYPES[EnumToString.parse(ActivityType.LIGHT)],
                    ACTIVITY_TYPES[EnumToString.parse(ActivityType.MODERATE)],
                    ACTIVITY_TYPES[EnumToString.parse(ActivityType.HIGH)],
                    ACTIVITY_TYPES[EnumToString.parse(ActivityType.EXTRA)],
                  ],
                  value: activityType == null
                      ? null
                      : ACTIVITY_TYPES[activityType],
                  setValue: setActivityType,
                  textDirection: txtDir,
                  alignment: adjustAlignment,
                  controller: _activityTypeTEC,
                  hintText: t.body_composition.activity_type.activity_type,
                  enabled: true,
                  icon: Icon(
                    Icons.question_answer,
                    color: YELLOW_COLOR,
                  ),
                  validator: (String val) {
                    if (val == null || val == "") {
                      return t.user.choose;
                    } else
                      return null;
                  },
                  isPassword: false,
                  primaryColor: YELLOW_COLOR,
                  padding: 0,
                  margin: 10),
            ),
            Container(
              padding: EdgeInsets.all(5),
              child: AdjustDropDownField(
                  itemsMap: WORKOUT_TYPE_LIST,
                  items: [
                    WORKOUT_TYPE_LIST[
                        EnumToString.parse(WorkoutType.POWERLIFTING)],
                    WORKOUT_TYPE_LIST[
                        EnumToString.parse(WorkoutType.BODYBUILDING)],
                    WORKOUT_TYPE_LIST[
                        EnumToString.parse(WorkoutType.BODYBUILDING_CLASSIC)],
                    WORKOUT_TYPE_LIST[EnumToString.parse(WorkoutType.FITNESS)],
                    WORKOUT_TYPE_LIST[
                        EnumToString.parse(WorkoutType.ENDURANCE)],
                    WORKOUT_TYPE_LIST[EnumToString.parse(WorkoutType.CARDIO)],
                  ],
                  value: workoutType == null
                      ? null
                      : WORKOUT_TYPE_LIST[workoutType],
                  setValue: setWorkoutType,
                  textDirection: txtDir,
                  alignment: adjustAlignment,
                  controller: _workoutTypeTEC,
                  hintText: t.body_composition.workout_type.workout_type,
                  enabled: true,
                  icon: Icon(
                    Icons.question_answer,
                    color: YELLOW_COLOR,
                  ),
                  validator: (String val) {
                    if (val == null || val == "") {
                      return t.user.choose;
                    } else
                      return null;
                  },
                  isPassword: false,
                  primaryColor: YELLOW_COLOR,
                  padding: 0,
                  margin: 10),
            )
          ],
        ),
      )),
    );
  }

  Widget bodyTypePage() {
    return Container(
      padding: EdgeInsets.all(40),
      child: PageView(
        controller: _quizPageController,
        physics: NeverScrollableScrollPhysics(),
        children: <Widget>[
          AdjustBodyTypeQuiz(
            tag: "${t.main.question} ${getNumber("1 از 10")}",
            tagColor: GREEN_COLOR,
            tagSize: 20,
            question: t.body_composition.body_type.body_status,
            questionColor: GREEN_COLOR,
            questionSize: 20,
            answers:  [t.body_composition.body_type.body_status_answers.a1,
              t.body_composition.body_type.body_status_answers.a2,
              t.body_composition.body_type.body_status_answers.a3,],
            answerButtonColor: GREEN_COLOR,
            answerColor: WHITE_COLOR,
            bodyTypeAdd: (index) {
              bodyTypeAdd(index);
              _quizPageController.nextPage(duration: Duration(seconds: 1), curve: ElasticOutCurve());
            },
          ),
          AdjustBodyTypeQuiz(
            tag: "${t.main.question} ${getNumber("2 از 10")}",
            tagColor: GREEN_COLOR,
            tagSize: 20,
            question: t.body_composition.body_type.body_shape,
            questionColor: GREEN_COLOR,
            questionSize: 20,
            answers:  [t.body_composition.body_type.body_shape_answers.a1,
              t.body_composition.body_type.body_shape_answers.a2,
              t.body_composition.body_type.body_shape_answers.a3,],
            answerButtonColor: GREEN_COLOR,
            answerColor: WHITE_COLOR,
            bodyTypeAdd: (index) {
              bodyTypeAdd(index);
              _quizPageController.nextPage(duration: Duration(seconds: 1), curve: ElasticOutCurve());
            },
          ),
          AdjustBodyTypeQuiz(
            tag: "${t.main.question} ${getNumber("3 از 10")}",
            tagColor: GREEN_COLOR,
            tagSize: 20,
            question: t.body_composition.body_type.childhood,
            questionColor: GREEN_COLOR,
            questionSize: 20,
            answers:  [t.body_composition.body_type.childhood_answers.a1,
              t.body_composition.body_type.childhood_answers.a2,
              t.body_composition.body_type.childhood_answers.a3,],
            answerButtonColor: GREEN_COLOR,
            answerColor: WHITE_COLOR,
            bodyTypeAdd: (index) {
              bodyTypeAdd(index);
              _quizPageController.nextPage(duration: Duration(seconds: 1), curve: ElasticOutCurve());
            },
          ),
          AdjustBodyTypeQuiz(
            tag: "${t.main.question} ${getNumber("4 از 10")}",
            tagColor: GREEN_COLOR,
            tagSize: 20,
            question: t.body_composition.body_type.activity,
            questionColor: GREEN_COLOR,
            questionSize: 20,
            answers:  [t.body_composition.body_type.activity_answers.a1,
              t.body_composition.body_type.activity_answers.a2,
              t.body_composition.body_type.activity_answers.a3,],
            answerButtonColor: GREEN_COLOR,
            answerColor: WHITE_COLOR,
            bodyTypeAdd: (index) {
              bodyTypeAdd(index);
              _quizPageController.nextPage(duration: Duration(seconds: 1), curve: ElasticOutCurve());
            },
          ),
          AdjustBodyTypeQuiz(
            tag: "${t.main.question} ${getNumber("5 از 10")}",
            tagColor: GREEN_COLOR,
            tagSize: 20,
            question: t.body_composition.body_type.metabolism,
            questionColor: GREEN_COLOR,
            questionSize: 20,
            answers:  [t.body_composition.body_type.metabolism_answers.a1,
              t.body_composition.body_type.metabolism_answers.a2,
              t.body_composition.body_type.metabolism_answers.a3,],
            answerButtonColor: GREEN_COLOR,
            answerColor: WHITE_COLOR,
            bodyTypeAdd: (index) {
              bodyTypeAdd(index);
              _quizPageController.nextPage(duration: Duration(seconds: 1), curve: ElasticOutCurve());
            },
          ),
          AdjustBodyTypeQuiz(
            tag: "${t.main.question} ${getNumber("6 از 10")}",
            tagColor: GREEN_COLOR,
            tagSize: 20,
            question: t.body_composition.body_type.people_say,
            questionColor: GREEN_COLOR,
            questionSize: 20,
            answers:  [t.body_composition.body_type.people_say_answers.a1,
              t.body_composition.body_type.people_say_answers.a2,
              t.body_composition.body_type.people_say_answers.a3,],
            answerButtonColor: GREEN_COLOR,
            answerColor: WHITE_COLOR,
            bodyTypeAdd: (index) {
              bodyTypeAdd(index);
              _quizPageController.nextPage(duration: Duration(seconds: 1), curve: ElasticOutCurve());
            },
          ),
          AdjustBodyTypeQuiz(
            tag: "${t.main.question} ${getNumber("7 از 10")}",
            tagColor: GREEN_COLOR,
            tagSize: 20,
            question: t.body_composition.body_type.wrist,
            questionColor: GREEN_COLOR,
            questionSize: 18,
            answers:  [t.body_composition.body_type.wrist_answers.a1,
              t.body_composition.body_type.wrist_answers.a2,
              t.body_composition.body_type.wrist_answers.a3,],
            answerButtonColor: GREEN_COLOR,
            answerColor: WHITE_COLOR,
            bodyTypeAdd: (index) {
              bodyTypeAdd(index);
              _quizPageController.nextPage(duration: Duration(seconds: 1), curve: ElasticOutCurve());
            },
          ),
          AdjustBodyTypeQuiz(
            tag: "${t.main.question} ${getNumber("8 از 10")}",
            tagColor: GREEN_COLOR,
            tagSize: 20,
            question: t.body_composition.body_type.body_shape,
            questionColor: GREEN_COLOR,
            questionSize: 20,
            answers:  [t.body_composition.body_type.body_shape_answers.a1,
              t.body_composition.body_type.body_shape_answers.a2,
              t.body_composition.body_type.body_shape_answers.a3,],
            answerButtonColor: GREEN_COLOR,
            answerColor: WHITE_COLOR,
            bodyTypeAdd: (index) {
              bodyTypeAdd(index);
              _quizPageController.nextPage(duration: Duration(seconds: 1), curve: ElasticOutCurve());
            },
          ),
          AdjustBodyTypeQuiz(
            tag: "${t.main.question} ${getNumber("9 از 10")}",
            tagColor: GREEN_COLOR,
            tagSize: 20,
            question: t.body_composition.body_type.hungry,
            questionColor: GREEN_COLOR,
            questionSize: 20,
            answers:  [t.body_composition.body_type.hungry_answers.a1,
              t.body_composition.body_type.hungry_answers.a2,
              t.body_composition.body_type.hungry_answers.a3,],
            answerButtonColor: GREEN_COLOR,
            answerColor: WHITE_COLOR,
            bodyTypeAdd: (index) {
              bodyTypeAdd(index);
              _quizPageController.nextPage(duration: Duration(seconds: 1), curve: ElasticOutCurve());
            },
          ),
          AdjustBodyTypeQuiz(
            tag: "${t.main.question} ${getNumber("10 از 10")}",
            tagColor: GREEN_COLOR,
            tagSize: 20,
            question: t.body_composition.body_type.people_perception,
            questionColor: GREEN_COLOR,
            questionSize: 20,
            answers:  [t.body_composition.body_type.people_perception_answers.a1,
              t.body_composition.body_type.people_perception_answers.a2,
              t.body_composition.body_type.people_perception_answers.a3,],
            answerButtonColor: GREEN_COLOR,
            answerColor: WHITE_COLOR,
            bodyTypeAdd: (index) {
              bodyTypeAdd(index);
              _quizPageController.nextPage(duration: Duration(seconds: 1), curve: ElasticOutCurve());
              setState(() {
                bodyTypeQuizComplete = true;
              });
            },
          ),
          Container(
            child: Center(
              child: AdjustText(
                t.main.done,
                fontSize: 25,
                color: GREEN_COLOR,
              ),
            ),
          )
        ],
      )
    );
  }

  Widget lastPage() {
    return StoreConnector<AppState, AppState>(
      converter: (Store store) => store.state,
      builder: (BuildContext context, AppState state) {
        return Container(
          padding: EdgeInsets.all(30),
          child: Center(
              child: SingleChildScrollView(
                  child: Column(
            children: <Widget>[
              FittedBox(
                fit: BoxFit.scaleDown,
                child: AdjustText(
                  t.body_composition.height +
                      ": " +
                      getNumber(double.parse(_heightTextEditingController.text)
                          .toInt()
                          .toString()) +
                      " " +
                      t.body_composition.centimeter,
                  fontSize: 18,
                ),
              ),
              FittedBox(
                fit: BoxFit.scaleDown,
                child: AdjustText(
                  t.body_composition.weight +
                      ": " +
                      getNumber(double.parse(_weightTextEditingController.text)
                          .toInt()
                          .toString()) +
                      " " +
                      t.body_composition.kilogram,
                  fontSize: 18,
                ),
              ),
              FittedBox(
                fit: BoxFit.scaleDown,
                child: AdjustText(
                  t.body_composition.wrist +
                      ": " +
                      getNumber(double.parse(_wristTextEditingController.text)
                          .toInt()
                          .toString()) +
                      " " +
                      t.body_composition.centimeter,
                  fontSize: 18,
                ),
              ),
              FittedBox(
                fit: BoxFit.scaleDown,
                child: AdjustText(
                  t.body_composition.neck +
                      ": " +
                      getNumber(double.parse(_neckTextEditingController.text)
                          .toInt()
                          .toString()) +
                      " " +
                      t.body_composition.centimeter,
                  fontSize: 18,
                ),
              ),
              FittedBox(
                fit: BoxFit.scaleDown,
                child: AdjustText(
                  t.body_composition.waist +
                      ": " +
                      getNumber(double.parse(_waistTextEditingController.text)
                          .toInt()
                          .toString()) +
                      " " +
                      t.body_composition.centimeter,
                  fontSize: 18,
                ),
              ),
              FittedBox(
                fit: BoxFit.scaleDown,
                child: AdjustText(
                  t.body_composition.abdomen +
                      ": " +
                      getNumber(double.parse(_abdomenTextEditingController.text)
                          .toInt()
                          .toString()) +
                      " " +
                      t.body_composition.centimeter,
                  fontSize: 18,
                ),
              ),
              FittedBox(
                fit: BoxFit.scaleDown,
                child: AdjustText(
                  t.body_composition.hip +
                      ": " +
                      getNumber(double.parse(_hipTextEditingController.text)
                          .toInt()
                          .toString()) +
                      " " +
                      t.body_composition.centimeter,
                  fontSize: 18,
                ),
              ),
              workoutLocation != null
                  ? FittedBox(
                      fit: BoxFit.scaleDown,
                      child: AdjustText(
                        t.body_composition.workout_location.workout_location +
                            ": " +
                            WORKOUT_LOCATIONS[workoutLocation],
                        fontSize: 18,
                      ),
                    )
                  : Container(),
              goal != null
                  ? FittedBox(
                      fit: BoxFit.scaleDown,
                      child: AdjustText(
                        t.body_composition.goal.goal + ": " + GOALS[goal],
                        fontSize: 18,
                      ),
                    )
                  : Container(),
              activityType != null
                  ? FittedBox(
                      fit: BoxFit.scaleDown,
                      child: AdjustText(
                        t.body_composition.activity_type.activity_type +
                            ": " +
                            ACTIVITY_TYPES[activityType],
                        fontSize: 18,
                      ),
                    )
                  : Container(),
              workoutType != null
                  ? FittedBox(
                      fit: BoxFit.scaleDown,
                      child: AdjustText(
                        t.body_composition.workout_type.workout_type +
                            ": " +
                            WORKOUT_TYPE_LIST[workoutType],
                        fontSize: 18,
                      ),
                    )
                  : Container(),
              Container(
                height: 130,
                width: MediaQuery.of(context).size.width,
                padding: EdgeInsets.all(30),
                child: Row(
                  textDirection: txtDir,
                  mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                  children: <Widget>[
                    Expanded(
                        flex: 5,
                        child: Container(
                          padding: EdgeInsets.all(10),
                          child: AdjustRaisedButton(
                            width: 60,
                            height: 80,
                            textDirection: txtDir,
                            text: t.main.ok,
                            fontColor: WHITE_COLOR,
                            primaryColor: GREEN_COLOR,
                            secondaryColor: GREEN_COLOR,
                            fontSize: 16,
                            onPressed: () {
                              height = double.parse(
                                  _heightTextEditingController.text);
                              weight = double.parse(
                                  _weightTextEditingController.text);
                              wrist = double.parse(
                                  _wristTextEditingController.text);
                              neck =
                                  double.parse(_neckTextEditingController.text);
                              waist = double.parse(
                                  _waistTextEditingController.text);
                              abdomen = double.parse(
                                  _abdomenTextEditingController.text);
                              hip =
                                  double.parse(_hipTextEditingController.text);

                              double minIdealW = minIdealWeight();
                              double maxIdealW = maxIdealWeight();
                              double bestW = (minIdealW + maxIdealW) / 2;
                              

                              if (getBodyFrameSize() == BodyFrameSize.LARGE) bodyTypeSum += 1;
                              else if  (getBodyFrameSize() == BodyFrameSize.MEDIUM) bodyTypeSum += 2;
                              else if  (getBodyFrameSize() == BodyFrameSize.SMALL) bodyTypeSum += 3;

                              double bodyTypeNum = bodyTypeSum / 11;

                              this.widget.setBodyCompositionDTO(
                                  BodyCompositionDTO(
                                      0,
                                      DateTime.now(),
                                      age,
                                      state.clientState.gender,
                                      height,
                                      weight,
                                      wrist,
                                      waist,
                                      hip,
                                      abdomen,
                                      neck,
                                      waist / hip,
                                      minIdealW,
                                      maxIdealW,
                                      bestW,
                                      bmi(),
                                      getBmiCategory(),
                                      bmr(),
                                      lbm(),
                                      pbf(),
                                      getBodyFatCategory(),
                                      pbf(),
                                      bodyTypeNum,
                                      getBodyType(bodyTypeNum),
                                      getBodyFrame(),
                                      getBodyFrameSize(),
                                      0,
                                      EnumToString.fromString(
                                          WorkoutLocation.values,
                                          workoutLocation),
                                      EnumToString.fromString(
                                          Goal.values, goal),
                                      EnumToString.fromString(
                                          ActivityType.values, activityType),
                                      EnumToString.fromString(
                                          WorkoutType.values, workoutType)));
                              this.widget.func();
                            },
                          ),
                        )),
                    Expanded(
                        flex: 5,
                        child: Container(
                          padding: EdgeInsets.all(10),
                          child: AdjustRaisedButton(
                            width: 60,
                            height: 80,
                            textDirection: txtDir,
                            text: t.main.cancel,
                            fontColor: WHITE_COLOR,
                            primaryColor: GREEN_COLOR,
                            secondaryColor: GREEN_COLOR,
                            fontSize: 16,
                            onPressed: () {
                              Navigator.of(context, rootNavigator: true)
                                  .pop("dialog");
                            },
                          ),
                        )),
                  ],
                ),
              )
            ],
          ))),
        );
      },
    );
  }

  @override
  void dispose() {
    // TODO: implement dispose
    _pageController.dispose();
    _heightRulerPickerController.dispose();
    _heightTextEditingController.dispose();

    _weightRulerPickerController.dispose();
    _weightTextEditingController.dispose();

    _wristRulerPickerController.dispose();
    _wristTextEditingController.dispose();

    _neckRulerPickerController.dispose();
    _neckTextEditingController.dispose();

    _waistRulerPickerController.dispose();
    _waistTextEditingController.dispose();

    _abdomenRulerPickerController.dispose();
    _abdomenTextEditingController.dispose();

    _hipRulerPickerController.dispose();
    _hipTextEditingController.dispose();

    _workoutLocationTEC.dispose();
    _goalTEC.dispose();
    _activityTypeTEC.dispose();
    _workoutTypeTEC.dispose();

    super.dispose();
  }
}
