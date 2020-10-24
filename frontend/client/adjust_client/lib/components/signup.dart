import 'package:adjust_client/actions/user_action.dart';
import 'package:adjust_client/components/adjust_dialog.dart';
import 'package:adjust_client/components/adjust_raised_button.dart';
import 'package:adjust_client/components/adjust_text_field.dart';
import 'package:adjust_client/components/preloader.dart';
import 'package:adjust_client/config/i18n.dart';
import 'package:adjust_client/constants/adjust_colors.dart';
import 'package:adjust_client/constants/words.dart';
import 'package:adjust_client/dto/user_dto.dart';
import 'package:adjust_client/pages/main_page.dart';
import 'package:adjust_client/pages/profile_page.dart';
import 'package:adjust_client/states/app_state.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:redux/redux.dart';
import 'package:string_validator/string_validator.dart';

class SignUp extends StatefulWidget {
  Function func;

  SignUp({this.func});

  @override
  _SignUpState createState() => _SignUpState();
}

class _SignUpState extends State<SignUp> {
  TextEditingController emailTextFieldController;
  TextEditingController passwordTextFieldController;
  TextEditingController passwordConfirmTextFieldController;

  final _formKey = GlobalKey<FormState>();

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    emailTextFieldController = TextEditingController();
    passwordTextFieldController = TextEditingController();
    passwordConfirmTextFieldController = TextEditingController();
  }

  @override
  Widget build(BuildContext context) {
    return StoreConnector<AppState, AppState>(
      converter: (Store store) => store.state,
      builder: (BuildContext context, AppState state) {
        return Container(
            child: Form(
          child: SingleChildScrollView(
            //            padding: EdgeInsets.all(20),
            child: Column(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: <Widget>[
                AdjustTextField(
                    textDirection: TextDirection.ltr,
                    controller: emailTextFieldController,
                    hintText: t.user.email,
                    enabled: true,
                    icon: Icon(
                      Icons.email,
                      color: GREEN_COLOR,
                    ),
                    isPassword: false,
                    primaryColor: GREEN_COLOR,
                    validator: (String value) {
                      if (!isEmail(value)) {
                        return t.user.wrong_email;
                      }
                      return null;
                    },
                    padding: 0,
                    margin: 20),
                AdjustTextField(
                    textDirection: TextDirection.ltr,
                    controller: passwordTextFieldController,
                    hintText: t.user.password,
                    enabled: true,
                    icon: Icon(
                      Icons.lock,
                      color: RED_COLOR,
                    ),
                    isPassword: true,
                    primaryColor: RED_COLOR,
                    validator: (String value) {
                      if (isNull(value)) {
                        return t.user.fill;
                      }
                      if (isNumeric(value) || isAlpha(value)) {
                        return t.user.wrong_password;
                      }
                      return null;
                    },
                    padding: 0,
                    margin: 20),
                AdjustTextField(
                    textDirection: TextDirection.ltr,
                    controller: passwordConfirmTextFieldController,
                    hintText: t.user.password_repeat,
                    enabled: true,
                    icon: Icon(
                      Icons.lock,
                      color: ORANGE_COLOR,
                    ),
                    isPassword: true,
                    primaryColor: ORANGE_COLOR,
                    validator: (String value) {
                      if (value != passwordTextFieldController.text) {
                        return t.user.pass_not_match;
                      }
                      return null;
                    },
                    padding: 0,
                    margin: 20),
                SizedBox(
                  height: 40,
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                  children: <Widget>[
                    AdjustRaisedButton(
                      text: t.main.back,
                      textDirection: txtDir,
                      primaryColor: GREEN_COLOR,
                      secondaryColor: GREEN_COLOR,
                      height: 50,
                      width: 90,
                      onPressed: () {
                        this.widget.func();
                      },
                    ),
                    AdjustRaisedButton(
                      text: t.user.register,
                      textDirection: txtDir,
                      primaryColor: GREEN_COLOR,
                      secondaryColor: GREEN_COLOR,
                      height: 50,
                      width: 90,
                      onPressed: () async {
                        if (_formKey.currentState.validate()) {
                          preloader(context);

                          String login = emailTextFieldController.text.trim().toLowerCase();
                          String email = login;
                          String password = passwordTextFieldController.text;

                          ManagedUserDTO userDTO = ManagedUserDTO(
                            password,
                            login,
                            null,
                            null,
                            email,
                            null,
                            null,
                          );

                          int i = await registerUser(context, userDTO);
                          if (i == 1) {
                            Navigator.of(context, rootNavigator: true)
                                .pop("dialog");
                            Navigator.of(context).pushReplacement(
                                MaterialPageRoute(
                                    builder: (context) => ProfilePage()));
                          } else if (i == 0) {
                            Navigator.of(context, rootNavigator: true)
                                .pop("dialog");
                            showAdjustDialog(
                                context, t.user.registration_failed, false, null, null);
                          }
                        }
                      },
                    )
                  ],
                ),
                SizedBox(
                  height: 20,
                )
              ],
            ),
          ),
          key: _formKey,
        ));
      },
    );
  }

  @override
  void dispose() {
    // TODO: implement dispose
    emailTextFieldController.dispose();
    passwordTextFieldController.dispose();
    passwordConfirmTextFieldController.dispose();
    super.dispose();
  }
}
