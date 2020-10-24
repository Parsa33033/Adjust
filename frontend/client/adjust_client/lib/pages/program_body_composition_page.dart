import 'dart:convert';
import 'dart:ffi';
import 'dart:io';
import 'dart:math';
import 'dart:typed_data';
import 'package:adjust_client/actions/program_action.dart';
import 'package:adjust_client/components/adjust_dialog.dart';
import 'package:adjust_client/components/adjust_raised_button.dart';
import 'package:adjust_client/components/adjust_text.dart';
import 'package:adjust_client/components/adjust_text_field.dart';
import 'package:adjust_client/components/preloader.dart';
import 'package:adjust_client/config/i18n.dart';
import 'package:adjust_client/constants/adjust_colors.dart';
import 'package:adjust_client/constants/words.dart';
import 'package:adjust_client/dto/body_composition_dto.dart';
import 'package:adjust_client/dto/client_dto.dart';
import 'package:adjust_client/dto/program_dto.dart';
import 'package:adjust_client/dto/nutrition_program_dto.dart';
import 'package:adjust_client/main.dart';
import 'package:adjust_client/model/client.dart';
import 'package:adjust_client/model/enum/activity_type.dart';
import 'package:adjust_client/model/enum/bmi_category.dart';
import 'package:adjust_client/model/enum/body_fat_category.dart';
import 'package:adjust_client/model/enum/body_frame_size.dart';
import 'package:adjust_client/model/enum/body_type.dart';
import 'package:adjust_client/model/enum/gender.dart';
import 'package:adjust_client/model/enum/goal.dart';
import 'package:adjust_client/model/enum/workout_location.dart';
import 'package:adjust_client/model/enum/workout_type.dart';
import 'package:adjust_client/pages/main_page.dart';
import 'package:adjust_client/pages/program_page.dart';
import 'package:adjust_client/states/app_state.dart';
import 'package:adjust_client/states/client_state.dart';
import 'package:adjust_client/states/specialist_state.dart';
import 'package:age/age.dart';
import 'package:avatar_glow/avatar_glow.dart';
import 'package:enum_to_string/enum_to_string.dart';
import 'package:expandable_card/expandable_card.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:image_picker/image_picker.dart';
import 'package:persian_number_utility/persian_number_utility.dart';
import 'package:redux/redux.dart';
import 'package:shamsi_date/shamsi_date.dart';
import 'package:simple_image_crop/simple_image_crop.dart';
import 'package:stepper_counter_swipe/stepper_counter_swipe.dart';

class ProgramBodyCompositionPage extends StatefulWidget {
  SpecialistState specialist;

  ProgramBodyCompositionPage(this.specialist);

  @override
  _ProgramBodyCompositionPageState createState() => _ProgramBodyCompositionPageState();
}

class _ProgramBodyCompositionPageState extends State<ProgramBodyCompositionPage> {
  TextEditingController nameTextFieldController;
  TextEditingController genderTextFieldController;
  TextEditingController ageTextFieldController;
  TextEditingController heightTextFieldController;
  TextEditingController weightTextFieldController;
  TextEditingController wristTextFieldController;
  TextEditingController waistTextFieldController;
  TextEditingController muscleMassTextFieldController;
  TextEditingController fatMassTextFieldController;

  final _formKey = GlobalKey<FormState>();

  Image _bodyImage;
  Image _bodyCompositionImage;
  Image _bloodTestImage;

  List<int> _bodyImageFile;
  List<int> _bodyCompositionFile;
  List<int> _bloodTestFile;

  final picker = ImagePicker();

  int heightNuminal;
  int heightDecimal;
  int weightNuminal;
  int weightDecimal;
  int wristNuminal;
  int wristDecimal;
  int waistNuminal;
  int waistDecimal;
  int muscleMassNuminal;
  int muscleMassDecimal;
  int fatMassNuminal;
  int fatMassDecimal;

  bool agreed = false;

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    nameTextFieldController = TextEditingController();
    genderTextFieldController = TextEditingController();
    ageTextFieldController = TextEditingController();
    heightTextFieldController = TextEditingController();
    weightTextFieldController = TextEditingController();
    wristTextFieldController = TextEditingController();
    waistTextFieldController = TextEditingController();
    muscleMassTextFieldController = TextEditingController();
    fatMassTextFieldController = TextEditingController();

    ClientState clientState = store.state.clientState;

    // name
    nameTextFieldController.text =
        clientState.firstName + " " + clientState.lastName;

    // gender
    genderTextFieldController.text =
    GENDER_LIST[EnumToString.parse(clientState.gender)];
    AgeDuration age;

    // Find out your age
    age = Age.dateDifference(
        fromDate: clientState.birthDate,
        toDate: DateTime.now(),
        includeToDate: false);
    ageTextFieldController.text = age.years.toString();

    heightNuminal = 0;
    heightDecimal = 0;
    weightNuminal = 0;
    weightDecimal = 0;
    wristNuminal = 0;
    wristDecimal = 0;
    waistNuminal = 0;
    waistDecimal = 0;
    muscleMassNuminal = 0;
    muscleMassDecimal = 0;
    fatMassNuminal = 0;
    fatMassDecimal = 0;
    agreed = false;

    _bodyImage = null;
    _bodyCompositionImage = null;
    _bloodTestImage = null;
  }

  Future<Uint8List> getImage() async {
    File imageFile = await ImagePicker.pickImage(source: ImageSource.camera);
    Uint8List imageByte = imageFile.readAsBytesSync();
    return imageByte;
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
            return ExpandableCardPage(
              page: page(state),
              expandableCard: ExpandableCard(
                minHeight: 100,
                backgroundColor: GREEN_COLOR,
                children: <Widget>[
                  SingleChildScrollView(
                    child: StoreConnector<AppState, AppState>(
                      converter: (Store store) => store.state,
                      builder: (BuildContext context, AppState state) {
                        return Container(
                            height: 400,
                            width: MediaQuery
                                .of(context)
                                .size
                                .width,
                            child: SingleChildScrollView(
                              child: Column(
                                children: <Widget>[
                                  Container(
                                    padding: EdgeInsets.only(bottom: 20),
                                    child: AdjustText(
                                      t.main.description,
                                      color: WHITE_COLOR,
                                    ),
                                  ),
                                  Container(
                                      height: 40,
                                      width: MediaQuery
                                          .of(context)
                                          .size
                                          .width,
                                      alignment: adjustAlignment,
                                      child: Directionality(
                                          textDirection: txtDir,
                                          child: Row(
                                            children: <Widget>[
                                              Expanded(
                                                flex: 8,
                                                child: Container(
                                                    height: 40,
                                                    padding: EdgeInsets.only(
                                                        right: 20, bottom: 20),
                                                    child: FittedBox(
                                                      fit: BoxFit.scaleDown,
                                                      child: Align(
                                                          alignment: adjustAlignment,
                                                          child: AdjustText(
                                                            t.main
                                                                .tick_to_confirm +
                                                                ":",
                                                            fontSize: 13,
                                                            color: WHITE_COLOR,
                                                          )
                                                      ),
                                                    )
                                                ),
                                              ),
                                              Expanded(
                                                flex: 2,
                                                child: Container(
                                                  height: 40,
                                                  padding: EdgeInsets.only(
                                                      right: 20, bottom: 20),
                                                  child: Checkbox(
                                                    value: agreed,
                                                    onChanged: (bool val) {
                                                      setState(() {
                                                        agreed = val;
                                                      });
                                                    },
                                                  ),
                                                ),
                                              )
                                            ],
                                          ))),
                                  Container(
                                    height: 400,
                                    child: SingleChildScrollView(
                                        padding: EdgeInsets.all(15),
                                        child: Column(
                                          children: <Widget>[
                                            AdjustText(
                                                t.body_composition.terms,
                                                color: WHITE_COLOR
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
            );
          },
        ));
  }

  Widget page(AppState state) {
    return Container(
      color: LIGHT_GREY_COLOR,
      child: Column(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        children: <Widget>[
          Expanded(
            flex: 20,
            child: Container(
              child: Stack(
                children: <Widget>[
                  Positioned(
                    top: 0,
                    right: 0,
                    bottom: 50,
                    left: 0,
                    child: Container(
                      color: GREEN_COLOR,
                    ),
                  ),
                  Positioned(
                    bottom: 0,
                    left: 0,
                    right: 0,
                    height: 100,
                    child: Container(
                      child: Image.asset("assets/adjust_logo.png"),
                    ),
                  ),
                  Positioned(
                      left: 20,
                      top: 50,
                      child: Container(
                        height: 50,
                        width: 50,
                        child: InkWell(
                          child: Icon(
                            Icons.arrow_back,
                            color: WHITE_COLOR,
                            size: 50,
                          ),
                          onTap: () {
                            Navigator.of(context).pop();
                          },
                        ),
                      )),
                ],
              ),
            ),
          ),
          Expanded(
            flex: 80,
            child: Container(
              child: SingleChildScrollView(
                  child: Form(
                    key: _formKey,
                    child: Column(
                      mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                      children: <Widget>[
                        AdjustTextField(
                            textAlign: txtAlign,
                            textDirection: txtDir,
                            controller: nameTextFieldController,
                            hintText: t.user.firstname + " " + t.main.and +
                                " " + t.user.lastname,
                            enabled: false,
                            icon: Icon(
                              Icons.person,
                              color: GREY_COLOR,
                            ),
                            isPassword: false,
                            primaryColor: GREY_COLOR,
                            validator: (String value) {
                              if (value == null || value == "") {
                                return t.user.fill;
                              }
                              return null;
                            },
                            padding: 0,
                            margin: 20),
                        AdjustTextField(
                            textAlign: txtAlign,
                            textDirection: txtDir,
                            controller: genderTextFieldController,
                            hintText: t.user.gender,
                            enabled: false,
                            icon: Icon(
                              Icons.person,
                              color: GREY_COLOR,
                            ),
                            isPassword: false,
                            primaryColor: GREY_COLOR,
                            validator: (String value) {
                              if (value == null || value == "") {
                                return t.user.fill;
                              }
                              return null;
                            },
                            padding: 0,
                            margin: 20),
                        AdjustTextField(
                            textAlign: txtAlign,
                            textDirection: txtDir,
                            controller: ageTextFieldController,
                            hintText: t.user.age,
                            enabled: false,
                            icon: Icon(
                              Icons.person,
                              color: GREY_COLOR,
                            ),
                            isPassword: false,
                            primaryColor: GREY_COLOR,
                            validator: (String value) {
                              if (value == null || value == "") {
                                return t.user.fill;
                              }
                              return null;
                            },
                            padding: 0,
                            margin: 20),
                        decimalNumberPicker(
                            context,
                            heightTextFieldController,
                            t.body_composition.height + " (180.5 " +
                                t.body_composition.centimeter + ")" + "*",
                            GREEN_COLOR,
                            t.body_composition.height + " " + t.main.to + " " +
                                t.body_composition.centimeter,
                            this.heightNuminal,
                            this.heightDecimal, (value) {
                          if (value == null || value == "") {
                            return t.user.fill;
                          }
                          return null;
                        }),
                        decimalNumberPicker(
                            context,
                            weightTextFieldController,
                            t.body_composition.weight + " (70.5 " +
                                t.body_composition.kilogram + ")" + "*",
                            RED_COLOR,
                            t.body_composition.weight + " " + t.main.to + " " +
                                t.body_composition.kilogram,
                            this.weightNuminal,
                            this.weightDecimal, (value) {
                          if (value == null || value == "") {
                            return t.user.fill;
                          }
                          return null;
                        }),
                        decimalNumberPicker(
                            context,
                            wristTextFieldController,
                            t.body_composition.wrist + " (18.5 " +
                                t.body_composition.centimeter + ")" + "*",
                            ORANGE_COLOR,
                            t.body_composition.wrist + " " + t.main.to + " " +
                                t.body_composition.centimeter,
                            this.wristNuminal,
                            this.wristDecimal, (value) {
                          if (value == null || value == "") {
                            return t.user.fill;
                          }
                          return null;
                        }),
                        decimalNumberPicker(
                            context,
                            waistTextFieldController,
                            t.body_composition.waist + " (88.5 " +
                                t.body_composition.centimeter + ")" + "*",
                            GREEN_COLOR,
                            t.body_composition.waist + " " + t.main.to + " " +
                                t.body_composition.centimeter,
                            this.wristNuminal,
                            this.wristDecimal, (value) {
                          if (value == null || value == "") {
                            return t.user.fill;
                          }
                          return null;
                        }),
                        decimalNumberPicker(
                            context,
                            muscleMassTextFieldController,
                            t.body_composition.muscle_mass + " (" +
                                t.body_composition.kilogram + ")",
                            RED_COLOR,
                            t.body_composition.muscle_mass + " " + t.main.to +
                                " " + t.body_composition.kilogram,
                            this.wristNuminal,
                            this.wristDecimal, (value) {
                          return null;
                        }),
                        decimalNumberPicker(
                            context,
                            fatMassTextFieldController,
                            t.body_composition.fat_mass + " (" +
                                t.body_composition.kilogram + ")",
                            ORANGE_COLOR,
                            t.body_composition.fat_mass + " " + t.main.to +
                                " " + t.body_composition.kilogram,
                            this.wristNuminal,
                            this.wristDecimal, (value) {
                          return null;
                        }),
                        Container(
                          padding: EdgeInsets.all(20),
                          child: Row(
                            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                            children: <Widget>[
                              Expanded(
                                  flex: 5,
                                  child: Column(
                                    children: <Widget>[
                                      Container(
                                          padding: const EdgeInsets.all(8.0),
                                          child: RaisedButton(
                                            color: GREEN_COLOR,
                                            child: AdjustText(
                                              t.main.pick_image,
                                              fontSize: 14,
                                              color: WHITE_COLOR,
                                            ),
                                            onPressed: () async {
                                              Uint8List imageByte =
                                              await getImage();
                                              _bodyImageFile =
                                              List<int>.of(imageByte);
                                              Image image = Image.memory(
                                                  imageByte);
                                              setState(() {
                                                _bodyImage = image;
                                              });
                                            },
                                          )),
                                      _bodyImage != null
                                          ? InkWell(
                                        child: Container(
                                          height: 60,
                                          width: 40,
                                          child: _bodyImage,
                                        ),
                                        onTap: () {
                                          showDialog(
                                              context: context,
                                              child: Dialog(
                                                child: _bodyImage,
                                              ));
                                        },
                                      )
                                          : Container()
                                    ],
                                  )),
                              Expanded(
                                flex: 5,
                                child: Container(
                                  padding: EdgeInsets.only(right: 20),
                                  child: AdjustText(
                                    t.body_composition.body_image,
                                    fontSize: 14,
                                  ),
                                ),
                              )
                            ],
                          ),
                        ),
                        Container(
                          padding: EdgeInsets.all(20),
                          child: Row(
                            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                            children: <Widget>[
                              Expanded(
                                  flex: 5,
                                  child: Column(
                                    children: <Widget>[
                                      Container(
                                          padding: const EdgeInsets.all(8.0),
                                          child: RaisedButton(
                                            color: RED_COLOR,
                                            child: Text(
                                              t.main.pick_image,
                                              style: TextStyle(
                                                  fontFamily: "Iransans",
                                                  color: WHITE_COLOR,
                                                  fontSize: 14),
                                            ),
                                            onPressed: () async {
                                              Uint8List imageByte =
                                              await getImage();
                                              _bodyCompositionFile =
                                              List<int>.of(imageByte);
                                              Image image = Image.memory(
                                                  imageByte);
                                              setState(() {
                                                _bodyCompositionImage = image;
                                              });
                                            },
                                          )),
                                      _bodyCompositionImage != null
                                          ? InkWell(
                                        child: Container(
                                          height: 60,
                                          width: 40,
                                          child: _bodyCompositionImage,
                                        ),
                                        onTap: () {
                                          showDialog(
                                              context: context,
                                              child: Dialog(
                                                child: _bodyCompositionImage,
                                              ));
                                        },
                                      )
                                          : Container()
                                    ],
                                  )),
                              Expanded(
                                flex: 5,
                                child: Container(
                                    padding: EdgeInsets.only(right: 20),
                                    child: AdjustText(
                                      t.body_composition.body_composition_image,
                                      fontSize: 18,
                                    )
                                ),
                              )
                            ],
                          ),
                        ),
                        Container(
                          padding: EdgeInsets.all(20),
                          child: Row(
                            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                            children: <Widget>[
                              Expanded(
                                  flex: 5,
                                  child: Column(
                                    children: <Widget>[
                                      Container(
                                          padding: const EdgeInsets.all(8.0),
                                          child: RaisedButton(
                                            color: ORANGE_COLOR,
                                            child: Text(
                                              t.main.pick_image,
                                              style: TextStyle(
                                                  fontFamily: "Iransans",
                                                  color: WHITE_COLOR,
                                                  fontSize: 14),
                                            ),
                                            onPressed: () async {
                                              Uint8List imageByte =
                                              await getImage();
                                              _bloodTestFile =
                                              List<int>.of(imageByte);
                                              Image image = Image.memory(
                                                  imageByte);
                                              setState(() {
                                                _bloodTestImage = image;
                                              });
                                            },
                                          )),
                                      _bloodTestImage != null
                                          ? InkWell(
                                        child: Container(
                                            height: 60,
                                            width: 40,
                                            child: _bloodTestImage),
                                        onTap: () {
                                          showDialog(
                                              context: context,
                                              child: Dialog(
                                                child: _bloodTestImage,
                                              ));
                                        },
                                      )
                                          : Container()
                                    ],
                                  )),
                              Expanded(
                                flex: 5,
                                child: Container(
                                    padding: EdgeInsets.only(right: 20),
                                    child: AdjustText(
                                      t.body_composition.blood_test_image,
                                      fontSize: 18,
                                    )
                                ),
                              )
                            ],
                          ),
                        ),
                        Container(
                          padding: EdgeInsets.only(
                              top: 20, right: 20, left: 20, bottom: 120),
                          child: AdjustRaisedButton(
                              text: t.main.ok,
                              textDirection: txtDir,
                              primaryColor: GREEN_COLOR,
                              secondaryColor: GREEN_COLOR,
                              height: 50,
                              width: MediaQuery
                                  .of(context)
                                  .size
                                  .width,
                              onPressed: () async {
                                double price = await getProgramPrice(context);
                                if (store.state.clientState.token < price) {
                                  showAdjustDialog(context,
                                      t.program.token_not_enough(
                                          getNumber(price.round().toString())),
                                      false, null, GREEN_COLOR);
                                } else {
                                  showAdjustDialog(
                                      context,
                                      t.program.token_deducted(
                                          getNumber(price.round().toString())),
                                      true, () async {
                                    if (_formKey.currentState.validate()) {
                                      if (agreed) {
                                        preloader(context);
                                        double height = double.parse(
                                            this.heightTextFieldController
                                                .text);
                                        double weight = double.parse(
                                            this.weightTextFieldController
                                                .text);
                                        double bmi = weight / pow(height, 2);
                                        double wrist = double.parse(
                                            this.wristTextFieldController.text);
                                        double waist = double.parse(
                                            this.waistTextFieldController.text);
                                        double muscleMass = null;
                                        if (muscleMassTextFieldController
                                            .text !=
                                            "" &&
                                            muscleMassTextFieldController
                                                .text !=
                                                null) {
                                          muscleMass = double.parse(this
                                              .muscleMassTextFieldController
                                              .text);
                                        }
                                        double fatMass = null;
                                        if (fatMassTextFieldController.text !=
                                            "" &&
                                            fatMassTextFieldController.text !=
                                                null) {
                                          fatMass = double.parse(
                                              this.fatMassTextFieldController
                                                  .text);
                                        }

                                        double lbm =
                                        state.clientState.gender == Gender.MALE
                                            ? (0.32810 * weight) +
                                            (0.33929 * height) -
                                            29.5336
                                            : (0.29569 * weight) +
                                            (0.41813 * height) -
                                            43.2933;

                                        AgeDuration age;

                                        // Find out your age
                                        age = Age.dateDifference(
                                            fromDate: state.clientState
                                                .birthDate,
                                            toDate: DateTime.now(),
                                            includeToDate: false);
                                        BodyCompositionDTO bodyCompositionDTO = BodyCompositionDTO(
                                            0,
                                            DateTime.now(),
                                            age.years,
                                            state.clientState.gender,
                                            height,
                                            weight,
                                            wrist,
                                            waist,
                                            0,
                                            0,
                                            0,
                                            0,
                                            0,
                                            0,
                                            0,
                                            0,
                                            BMICategory.HIGHOBESE,
                                            0,
                                            lbm,
                                            0,
                                            BodyFatCategory.OBESE,
                                            0,
                                            0,
                                            BodyType.ENDOMORPH,
                                            0,
                                            BodyFrameSize.LARGE,
                                            0,
                                            WorkoutLocation.GYM,
                                            Goal.DECREASEWEIGHT,
                                            ActivityType.SEDENTARY,
                                            WorkoutType.FITNESS);

                                        ProgramDTO programDTO = ProgramDTO(
                                            null,
                                            DateTime.now(),
                                            DateTime.now(),
                                            false,
                                            true,
                                            false,
                                            false,
                                            false,
                                            false,
                                            false,
                                            false,
                                            null,
                                            null,
                                            null,
                                            state.clientState.id,
                                            this.widget.specialist.id,
                                            bodyCompositionDTO,
                                            null,
                                            null,
                                            null,
                                            null,
                                            null);

                                        int i = await requestForProgram(
                                            context, programDTO);
                                        if (i == 1) {
                                          Navigator.of(
                                              context, rootNavigator: true)
                                              .pop();
                                          showAdjustDialog(
                                              context, t.user.success, true,
                                                  () {
                                                Navigator.of(context)
                                                    .pushReplacement(
                                                    MaterialPageRoute(
                                                        builder: (context) =>
                                                            MainPage()));
                                              }, GREEN_COLOR);
                                        } else {
                                          Navigator.of(
                                              context, rootNavigator: true)
                                              .pop();
                                          showAdjustDialog(
                                              context, t.user.failure, false,
                                              null, GREEN_COLOR);
                                        }
                                      } else {
                                        showAdjustDialog(
                                            context,
                                            t.program.accept,
                                            false,
                                            null,
                                            GREEN_COLOR);
                                      }
                                    }
                                  }, GREEN_COLOR);
                                }
                              }),
                        )
                      ],
                    ),
                  )),
            ),
          )
        ],
      ),
    );
  }

  Widget decimalNumberPicker(BuildContext context,
      TextEditingController controller,
      String text,
      Color color,
      String pickerText,
      int numinal,
      int decimal,
      Function validator) {
    return InkWell(
      child: AdjustTextField(
          textDirection: txtDir,
          textAlign: txtAlign,
          controller: controller,
          hintText: text,
          enabled: false,
          icon: Icon(
            Icons.location_on,
            color: color,
          ),
          isPassword: false,
          primaryColor: color,
          validator: (String value) {
            return validator(value);
          },
          padding: 0,
          margin: 20),
      onTap: () {
        showDialog(
            context: context,
            child: Dialog(
              child: Container(
                  color: color,
                  height: 300,
                  width: 200,
                  padding: EdgeInsets.all(20),
                  child: SingleChildScrollView(
                    child: Column(
                      children: <Widget>[
                        AdjustText(
                            pickerText,
                            fontSize: 18,
                            color: WHITE_COLOR
                        ),
                        Align(
                            alignment: Alignment.center,
                            child: Center(
                              child: Row(
                                mainAxisAlignment:
                                MainAxisAlignment.spaceEvenly,
                                children: <Widget>[
                                  Container(
                                    height: 150,
                                    padding: const EdgeInsets.all(8.0),
                                    child: StepperSwipe(
                                      initialValue: numinal,
                                      speedTransitionLimitCount: 8,
                                      //Trigger count for fast counting
                                      onChanged: (int value) {
                                        numinal = value;
                                      },
                                      firstIncrementDuration:
                                      Duration(milliseconds: 120),
                                      //Unit time before fast counting
                                      secondIncrementDuration:
                                      Duration(milliseconds: 60),
                                      //Unit time during fast counting
                                      direction: Axis.vertical,
                                      dragButtonColor: color,
                                      withNaturalNumbers: false,
                                      withPlusMinus: true,
                                      maxValue: 500,
                                      stepperValue: numinal,
                                      withFastCount: true,
                                    ),
                                  ),
                                  Text(
                                    ".",
                                    style: TextStyle(fontSize: 25),
                                  ),
                                  Container(
                                    height: 150,
                                    padding: const EdgeInsets.all(8.0),
                                    child: StepperSwipe(
                                      initialValue: decimal,
                                      speedTransitionLimitCount: 8,
                                      //Trigger count for fast counting
                                      onChanged: (int value) {
                                        decimal = value;
                                      },
                                      firstIncrementDuration:
                                      Duration(milliseconds: 120),
                                      //Unit time before fast counting
                                      secondIncrementDuration:
                                      Duration(milliseconds: 60),
                                      //Unit time during fast counting
                                      direction: Axis.vertical,
                                      dragButtonColor: color,
                                      withNaturalNumbers: true,
                                      withPlusMinus: true,
                                      maxValue: 9,
                                      stepperValue: decimal,
                                      withFastCount: true,
                                    ),
                                  ),
                                ],
                              ),
                            )),
                        AdjustRaisedButton(
                          width: 60,
                          height: 40,
                          textDirection: txtDir,
                          text: t.main.ok,
                          fontColor: WHITE_COLOR,
                          primaryColor: color,
                          secondaryColor: color,
                          fontSize: 16,
                          onPressed: () {
                            controller.text =
                                (numinal + (decimal / 10)).toString();
                            Navigator.of(context, rootNavigator: true)
                                .pop("dialog");
                          },
                        )
                      ],
                    ),
                  )),
            ));
      },
    );
  }

  @override
  void dispose() {
    // TODO: implement dispose
    nameTextFieldController.dispose();
    genderTextFieldController.dispose();
    ageTextFieldController.dispose();
    heightTextFieldController.dispose();
    weightTextFieldController.dispose();
    wristTextFieldController.dispose();
    waistTextFieldController.dispose();
    muscleMassTextFieldController.dispose();
    fatMassTextFieldController.dispose();
    super.dispose();
  }
}
