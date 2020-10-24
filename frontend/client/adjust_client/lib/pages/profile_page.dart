import 'dart:convert';
import 'dart:ffi';
import 'dart:io';
import 'dart:typed_data';
import 'package:adjust_client/actions/client_action.dart';
import 'package:adjust_client/actions/user_action.dart';
import 'package:adjust_client/components/adjust_date_picker.dart';
import 'package:adjust_client/components/adjust_dialog.dart';
import 'package:adjust_client/components/adjust_dropdown_field.dart';
import 'package:adjust_client/components/adjust_raised_button.dart';
import 'package:adjust_client/components/adjust_text.dart';
import 'package:adjust_client/components/adjust_text_field.dart';
import 'package:adjust_client/components/preloader.dart';
import 'package:adjust_client/config/i18n.dart';
import 'package:adjust_client/constants/adjust_colors.dart';
import 'package:adjust_client/config/localization.dart';
import 'package:adjust_client/constants/words.dart';
import 'package:adjust_client/dto/client_dto.dart';
import 'package:adjust_client/dto/user_dto.dart';
import 'package:adjust_client/main.dart';
import 'package:adjust_client/model/client.dart';
import 'package:adjust_client/model/enum/gender.dart';
import 'package:adjust_client/notifications/adjust_state_change_notification.dart';
import 'package:adjust_client/pages/start_page.dart';
import 'package:adjust_client/states/app_state.dart';
import 'package:avatar_glow/avatar_glow.dart';
import 'package:enum_to_string/enum_to_string.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:image_picker/image_picker.dart';
import 'package:persian_datetime_picker/persian_datetime_picker.dart';
import 'package:persian_number_utility/persian_number_utility.dart';
import 'package:redux/redux.dart';
import 'package:shamsi_date/shamsi_date.dart';
import 'package:simple_image_crop/simple_image_crop.dart';
import 'package:string_validator/string_validator.dart';

import 'main_page.dart';

class ProfilePage extends StatefulWidget {
  Image image;
  bool isFromMainPage;

  ProfilePage({this.image, this.isFromMainPage}) {
    this.isFromMainPage =
        this.isFromMainPage == null ? false : this.isFromMainPage;
  }

  @override
  _ProfilePageState createState() => _ProfilePageState();
}

class _ProfilePageState extends State<ProfilePage> {
  TextEditingController emailTextEditingController;
  TextEditingController firstNameTextFieldController;
  TextEditingController lastNameTextFieldController;
  TextEditingController birthDateConfirmTextFieldController;
  Image avatarImage;
  String genderValue;

  final _formKey = GlobalKey<FormState>();
  final _imgCropKey = GlobalKey<ImgCropState>();

  File _image;
  final picker = ImagePicker();

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    emailTextEditingController = TextEditingController();
    firstNameTextFieldController = TextEditingController();
    lastNameTextFieldController = TextEditingController();
    birthDateConfirmTextFieldController = TextEditingController();
    avatarImage = Image.asset("assets/adjust_logo1.png");

    AppState state = store.state;
    setAppState(state);
  }

  void setAppState(AppState state) {
    setState(() {
      emailTextEditingController.text = state.userState.email;
      firstNameTextFieldController.text = state.clientState.firstName;
      lastNameTextFieldController.text = state.clientState.lastName;
      Jalali jalali = Jalali.fromDateTime(state.clientState.birthDate);
      birthDateConfirmTextFieldController.text = getNumber(
          jalali.year.toString() +
              "/" +
              jalali.month.toString() +
              "/" +
              jalali.day.toString());
      genderValue = state.clientState.gender == null
          ? null
          : EnumToString.parse(state.clientState.gender);
      if (this.widget.image != null) {
        avatarImage = this.widget.image;
      }
    });
  }

  Future getImage(bool fromCamera) async {
    File imageFile;
    if (fromCamera) {
      imageFile = await ImagePicker.pickImage(source: ImageSource.camera);
    } else {
      imageFile = await ImagePicker.pickImage(source: ImageSource.gallery);
    }
    setState(() {
      _image = imageFile;
    });
  }

  Widget _buildCropImage(Uint8List imageByte, AppState state) {
    return Scaffold(
      body: Container(
        child: Stack(
          children: <Widget>[
            Positioned(
              child: Container(
                color: Colors.black,
                child: ImgCrop(
                  key: _imgCropKey,
                  chipRadius: 150, // crop area radius
                  chipShape: 'circle', // crop type "circle" or "rect"
                  image: MemoryImage(imageByte), // you selected image file
                ),
              ),
            ),
            Positioned(
                right: 40,
                bottom: 40,
                child: Container(
                    child: InkWell(
                  child: Container(
                      height: 60,
                      width: 60,
                      decoration: BoxDecoration(
                        color: GREEN_COLOR,
                        borderRadius: BorderRadius.all(Radius.circular(50)),
                      ),
                      child: Center(
                          child: AdjustText(
                        t.main.capture,
                        fontSize: 14,
                        color: WHITE_COLOR,
                      ))),
                  onTap: () async {
                    preloader(context);
                    File imageFile = await _imgCropKey.currentState
                        .cropCompleted(_image, pictureQuality: 512);
                    Uint8List image = await imageFile.readAsBytes();
                    ClientDTO clientDTO = ClientDTO(
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        base64Encode(image),
                        "image/jpeg",
                        null,
                        null,
                        null,
                        null);

                    int i = await updateClient(context, clientDTO);
                    if (i == 1) {
                      mainPageStreamController.add(1);
                      Navigator.of(context, rootNavigator: true).pop("dialog");
                      Navigator.of(context, rootNavigator: true).pop("dialog");
                      Navigator.of(context, rootNavigator: true).pop("dialog");
                      List<int> imgList = base64Decode(state.clientState.image);
                      Uint8List imgByte = Uint8List.fromList(imgList);
                      setState(() {
                        avatarImage = Image.memory(imgByte);
                      });
                    } else if (i == 0) {
                      Navigator.of(context, rootNavigator: true).pop("dialog");
                      Navigator.of(context, rootNavigator: true).pop("dialog");
                      Navigator.of(context, rootNavigator: true).pop("dialog");
                      showAdjustDialog(
                          context, t.user.failure, false, null, null);
                    }
                  },
                )))
          ],
        ),
      ),
    );
  }

  void setGenderValue(String genderValue) {
    setState(() {
      this.genderValue = genderValue;
    });
  }

  void setBirthDay(String birthDay) {
    setState(() {
      this.birthDateConfirmTextFieldController.text = birthDay;
    });
  }

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Scaffold(
          body: StoreConnector<AppState, AppState>(
        converter: (Store store) => store.state,
        builder: (BuildContext context, AppState state) {
          return Container(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: <Widget>[
                Expanded(
                  flex: 35,
                  child: Container(
                    child: Stack(
                      children: <Widget>[
                        Positioned(
                          top: 0,
                          right: 0,
                          bottom: 115,
                          left: 0,
                          child: Container(
                            color: GREEN_COLOR,
                          ),
                        ),
                        this.widget.isFromMainPage
                            ? Positioned(
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
                                ))
                            : Container(),
                        Positioned(
                          bottom: 0,
                          left: 0,
                          right: 0,
                          height: 200,
                          child: Container(
                              child: Column(
                            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                            children: <Widget>[
                              Expanded(
                                flex: 8,
                                child: AvatarGlow(
                                  glowColor: Colors.blue,
                                  endRadius: 120.0,
                                  duration: Duration(milliseconds: 2000),
                                  repeat: true,
                                  showTwoGlows: true,
                                  repeatPauseDuration:
                                      Duration(milliseconds: 100),
                                  child: Material(
                                      elevation: 8.0,
                                      shape: CircleBorder(),
                                      child: InkWell(
                                        child: CircleAvatar(
                                          backgroundColor: Colors.grey[100],
                                          backgroundImage: avatarImage.image,
                                          radius: 60.0,
                                        ),
                                        onTap: () async {
                                          showDialog(
                                              context: context,
                                              child: Container(
                                                height: 400,
                                                child: SingleChildScrollView(
                                                  child: Column(
                                                    children: <Widget>[
                                                      Container(
                                                        padding:
                                                            EdgeInsets.all(20),
                                                        child: CircleAvatar(
                                                          backgroundColor:
                                                              Colors.grey[100],
                                                          backgroundImage:
                                                              avatarImage.image,
                                                          radius: 150.0,
                                                        ),
                                                      ),
                                                      Container(
                                                        padding:
                                                            EdgeInsets.all(20),
                                                        child: Row(
                                                          mainAxisAlignment:
                                                              MainAxisAlignment
                                                                  .spaceEvenly,
                                                          children: <Widget>[
                                                            AdjustRaisedButton(
                                                              text: t
                                                                  .main.gallery,
                                                              height: 40,
                                                              width: 90,
                                                              textDirection:
                                                                  txtDir,
                                                              primaryColor:
                                                                  GREEN_COLOR,
                                                              secondaryColor:
                                                                  GREEN_COLOR,
                                                              fontSize: 18,
                                                              fontColor:
                                                                  WHITE_COLOR,
                                                              onPressed:
                                                                  () async {
                                                                await getImage(
                                                                    false);
                                                                showDialog(
                                                                    context:
                                                                        context,
                                                                    child: _buildCropImage(
                                                                        await _image
                                                                            .readAsBytes(),
                                                                        state));
                                                              },
                                                            ),
                                                            AdjustRaisedButton(
                                                              text:
                                                                  t.main.camera,
                                                              height: 40,
                                                              width: 90,
                                                              textDirection:
                                                                  txtDir,
                                                              primaryColor:
                                                                  GREEN_COLOR,
                                                              secondaryColor:
                                                                  GREEN_COLOR,
                                                              fontSize: 18,
                                                              fontColor:
                                                                  WHITE_COLOR,
                                                              onPressed:
                                                                  () async {
                                                                await getImage(
                                                                    true);
                                                                showDialog(
                                                                    context:
                                                                        context,
                                                                    child: _buildCropImage(
                                                                        await _image
                                                                            .readAsBytes(),
                                                                        state));
                                                              },
                                                            )
                                                          ],
                                                        ),
                                                      )
                                                    ],
                                                  ),
                                                ),
                                              ));
                                        },
                                      )),
                                ),
                              ),
                              Expanded(
                                  flex: 2,
                                  child: AdjustText(
                                    t.user.choose_profile_picture,
                                  ))
                            ],
                          )),
                        )
                      ],
                    ),
                  ),
                ),
                Expanded(
                  flex: 65,
                  child: Form(
                    child: SingleChildScrollView(
                      //            padding: EdgeInsets.all(20),
                      child: Column(
                        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                        children: <Widget>[
                          SizedBox(
                            height: 20,
                          ),
                          AdjustTextField(
                              textDirection: TextDirection.ltr,
                              textAlign: TextAlign.left,
                              controller: emailTextEditingController,
                              hintText: t.user.email,
                              enabled: false,
                              icon: Icon(
                                Icons.email,
                                color: GREY_COLOR,
                              ),
                              isPassword: false,
                              primaryColor: GREY_COLOR,
                              validator: (String value) {
                                if (!isEmail(value)) {
                                  return t.user.wrong_email;
                                }
                                return null;
                              },
                              padding: 0,
                              margin: 20),
                          AdjustTextField(
                              textAlign: txtAlign,
                              textDirection: txtDir,
                              controller: firstNameTextFieldController,
                              hintText: t.user.firstname,
                              enabled: true,
                              icon: Icon(
                                Icons.person,
                                color: GREEN_COLOR,
                              ),
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
                          AdjustTextField(
                              textAlign: txtAlign,
                              textDirection: txtDir,
                              controller: lastNameTextFieldController,
                              hintText: t.user.lastname,
                              enabled: true,
                              icon: Icon(
                                Icons.person,
                                color: RED_COLOR,
                              ),
                              validator: (String val) {
                                if (val == null || val == "") {
                                  return t.user.fill;
                                } else
                                  return null;
                              },
                              isPassword: false,
                              primaryColor: RED_COLOR,
                              padding: 0,
                              margin: 20),
                          InkWell(
                            child: AdjustTextField(
                                textAlign: txtAlign,
                                textDirection: txtDir,
                                controller: birthDateConfirmTextFieldController,
                                hintText: t.user.birth,
                                enabled: false,
                                icon: Icon(
                                  Icons.calendar_today,
                                  color: ORANGE_COLOR,
                                ),
                                validator: (String val) {
                                  if (val == null || val == "") {
                                    return t.user.fill;
                                  } else
                                    return null;
                                },
                                isPassword: false,
                                primaryColor: ORANGE_COLOR,
                                padding: 0,
                                margin: 20),
                            onTap: () {
                              showDialog(
                                context: context,
                                child: Dialog(
                                  child: AdjustDatePicker((String bd) {
                                    setBirthDay(bd);
                                    Navigator.of(context, rootNavigator: true).pop("dialog");
                                  }, color: ORANGE_COLOR,),
                                )
                              );
                            },
                          ),
                          AdjustDropDownField(
                              itemsMap: GENDER_LIST,
                              items: [
                                GENDER_LIST[EnumToString.parse(Gender.MALE)],
                                GENDER_LIST[EnumToString.parse(Gender.FEMALE)]
                              ],
                              value: genderValue == null
                                  ? null
                                  : GENDER_LIST[genderValue],
                              setValue: setGenderValue,
                              textDirection: txtDir,
                              alignment: adjustAlignment,
                              controller: birthDateConfirmTextFieldController,
                              hintText: t.user.gender,
                              enabled: true,
                              icon: Icon(
                                Icons.perm_identity,
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
                              margin: 20),
                          SizedBox(
                            height: 40,
                          ),
                          Row(
                            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                            children: <Widget>[
                              AdjustRaisedButton(
                                text: t.main.default_val,
                                textDirection: txtDir,
                                primaryColor: GREEN_COLOR,
                                secondaryColor: GREEN_COLOR,
                                height: 50,
                                width: 90,
                                onPressed: () {
                                  // set field back to default
                                  showAdjustDialog(
                                      context, t.user.set_to_default, true, () {
                                    setAppState(state);
                                  }, null);
                                },
                              ),
                              AdjustRaisedButton(
                                text: t.main.update,
                                textDirection: txtDir,
                                primaryColor: GREEN_COLOR,
                                secondaryColor: GREEN_COLOR,
                                height: 50,
                                width: 90,
                                onPressed: () async {
                                  if (_formKey.currentState.validate()) {
                                    showAdjustDialog(
                                        context,
                                        t.user.sure_with_decision,
                                        true, () async {
                                      preloader(context);
                                      ClientDTO clientDTO = ClientDTO(
                                          null,
                                          null,
                                          firstNameTextFieldController.text,
                                          lastNameTextFieldController.text,
                                          jalaliToGeorgianDateTime(
                                              NumberUtility.changeDigit(
                                                  birthDateConfirmTextFieldController
                                                      .text,
                                                  NumStrLanguage.English)),
                                          null,
                                          EnumToString.fromString(
                                              Gender.values, genderValue),
                                          null,
                                          null,
                                          null,
                                          null,
                                          null,
                                          null,
                                          null,
                                          null);

                                      int i = await updateClient(
                                          context, clientDTO);
                                      if (i == 1) {
                                        if (this.widget.isFromMainPage) {
                                          Navigator.of(context,
                                                  rootNavigator: true)
                                              .pop("dialog");
                                          mainPageStreamController.add(1);
                                          showAdjustDialog(
                                              context,
                                              t.user.success,
                                              false,
                                              null,
                                              null);
                                        } else {
                                          Navigator.of(context,
                                                  rootNavigator: true)
                                              .pop("dialog");
                                          Navigator.of(context).pushReplacement(
                                              MaterialPageRoute(
                                                  builder: (context) =>
                                                      MainPage()));
                                        }
                                      } else if (i == 0) {
                                        Navigator.of(context,
                                                rootNavigator: true)
                                            .pop("dialog");
                                        showAdjustDialog(context,
                                            t.user.failure, false, null, null);
                                      }
                                    }, null);
                                  }
                                },
                              )
                            ],
                          ),
                          SizedBox(
                            height: 40,
                          )
                        ],
                      ),
                    ),
                    key: _formKey,
                  ),
                )
              ],
            ),
          );
        },
      )),
    );
  }

  @override
  void dispose() {
    // TODO: implement dispose
    emailTextEditingController.dispose();
    firstNameTextFieldController.dispose();
    lastNameTextFieldController.dispose();
    birthDateConfirmTextFieldController.dispose();
    super.dispose();
  }
}
