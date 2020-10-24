import 'dart:async';
import 'dart:convert';
import 'dart:typed_data';

import 'package:adjust_client/actions/client_action.dart';
import 'package:adjust_client/actions/program_action.dart';
import 'package:adjust_client/actions/shopping_action.dart';
import 'package:adjust_client/actions/specialist_action.dart';
import 'package:adjust_client/actions/tutorial_action.dart';
import 'package:adjust_client/components/adjust_dialog.dart';
import 'package:adjust_client/components/adjust_text.dart';
import 'package:adjust_client/components/dashboard.dart';
import 'package:adjust_client/components/preloader.dart';
import 'package:adjust_client/config/i18n.dart';
import 'package:adjust_client/config/stomp.dart';
import 'package:adjust_client/constants/adjust_colors.dart';
import 'package:adjust_client/constants/words.dart';
import 'package:adjust_client/notifications/adjust_state_change_notification.dart';
import 'package:adjust_client/pages/course_page.dart';
import 'package:adjust_client/pages/fitness_program_page.dart';
import 'package:adjust_client/pages/menu_page.dart';
import 'package:adjust_client/pages/nutrition_program_page.dart';
import 'package:adjust_client/pages/program_page.dart';
import 'package:adjust_client/pages/program_request_page.dart';
import 'package:adjust_client/pages/shopping_page.dart';
import 'package:adjust_client/pages/specialist_page.dart';
import 'package:adjust_client/pages/tutorial_page.dart';
import 'package:adjust_client/pages/tutorial_video_page.dart';
import 'package:adjust_client/states/app_state.dart';
import 'package:adjust_client/states/client_state.dart';
import 'package:adjust_client/states/nutrition_program_state.dart';
import 'package:curved_navigation_bar/curved_navigation_bar.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:flutter_zoom_drawer/flutter_zoom_drawer.dart';
import 'package:persian_datetime_picker/utils/consts.dart';
import 'package:redux/redux.dart';
import 'package:stomp_dart_client/stomp.dart';

import '../main.dart';

final ZoomDrawerController zoomDrawerController = ZoomDrawerController();
final StreamController<int> mainPageStreamController =
    StreamController<int>.broadcast();
final Stream<int> mainPageStream = mainPageStreamController.stream;

class MainPage extends StatefulWidget {
  @override
  _MainPageState createState() => _MainPageState();
}

class _MainPageState extends State<MainPage>
    with SingleTickerProviderStateMixin {
  Widget _content;
  GlobalKey _bottomNavigationKey = GlobalKey();

  String firstName;
  String lastName;
  double token;
  double score;
  Image _image;

  StompInstance stompInstance;
  StompClient stompClient;

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    mainPageStream.asBroadcastStream().listen((event) {
      setMainPageState(true);
    });
    stompInstance = StompInstance.getInstance();
    stompInstance.stompClient.activate();

    fetchDependencies();

    _content = mainMenu();
    setMainPageState(false);
  }

  void fetchDependencies() async {
    await getShoppingItems(context);
    await getTokenItems(context);
    await getClientPrograms(context);
  }

  void setMainPageState(bool fromNotification) {
    ClientState clientState = store.state.clientState;
    if (fromNotification) {
      setState(() {
        stateSetter(clientState);
      });
    } else {
      stateSetter(clientState);
    }
  }

  void stateSetter(ClientState clientState) {
    firstName = clientState.firstName;
    lastName = clientState.lastName;
    token = clientState.token;
    score = clientState.score;
    if (clientState.image == null) {
      _image = Image.asset("assets/adjust_logo1.png");
    } else {
      Uint8List imageByte = Uint8List.fromList(base64Decode(clientState.image));
      _image = Image.memory(imageByte);
    }
  }

  @override
  Widget build(BuildContext context) {
    return ZoomDrawer(
      controller: zoomDrawerController,
      menuScreen: MenuPage(image: _image),
      mainScreen: mainPage(),
      borderRadius: 24.0,
      showShadow: false,
      angle: 0.0,
      backgroundColor: Colors.grey[300],
      slideWidth: MediaQuery.of(context).size.width * (true ? -.45 : 0.65),
    );
  }

  Widget mainPage() {
    return SafeArea(
      child: Scaffold(
        bottomNavigationBar: CurvedNavigationBar(
          key: _bottomNavigationKey,
          backgroundColor: LIGHT_GREY_COLOR,
          color: GREEN_COLOR,
          index: 1,
          items: <Widget>[
            Icon(
              Icons.open_in_browser,
              size: 30,
              color: LIGHT_GREY_COLOR,
            ),
            CircleAvatar(
                radius: 30, child: Image.asset("assets/adjust_logo1.png")),
            Icon(
              Icons.shopping_cart,
              size: 30,
              color: LIGHT_GREY_COLOR,
            ),
          ],
          onTap: (index) async {
            //Handle button tap
            if (index == 0) {
              setState(() {
                _content = Container(child: ProgramRequestPage());
              });
            } else if (index == 1) {
              setState(() {
                _content = mainMenu();
              });
            } else {
              getShoppingItems(context);
              setState(() {
                _content = ShopingPage();
              });
            }
          },
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
                        flex: 25,
                        child: Dashboard(
                          firstName: firstName,
                          lastName: lastName,
                          token: token,
                          score: score,
                          image: _image,
                        )),
                    Expanded(
                        flex: 5,
                        child: Container(
                          padding: EdgeInsets.only(top: 20),
                          child: Divider(
                            color: SHADOW_COLOR,
                            endIndent: 20,
                            indent: 20,
                            thickness: 2,
                          ),
                        )),
                    Expanded(flex: 70, child: _content),
                  ],
                ));
          },
        ),
      ),
    );
  }

  Widget mainMenu() {
    return Container(
      padding: EdgeInsets.only(bottom: 60, left: 20, right: 20, top: 20),
      child: Column(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        children: <Widget>[
          Expanded(
            flex: 5,
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: <Widget>[
                Expanded(
                  flex: 5,
                  child: menuItem(t.fitness.program+ " " + t.main.my ,
                      "assets/workout_icon.png", RED_COLOR, () {
                    Store<AppState> store = StoreProvider.of<AppState>(context);
                    if (store.state.programListState.programs.length >= 1&&
                        store.state.programListState.programs.reversed
                            .toList()[0]
                            .fitnessProgram != null) {
                      store.dispatch(SetFitnessProgramAction(
                          payload: store
                              .state.programListState.programs.reversed
                              .toList()[0]
                              .fitnessProgram));
                      Navigator.of(context).push(MaterialPageRoute(
                          builder: (context) => FitnessProgramPage(0)));
                    } else {
                      showAdjustDialog(context, t.program.not_exist,
                          false, null, RED_COLOR);
                    }
                  }),
                ),
                Expanded(
                  flex: 5,
                  child: menuItem(t.nutrition.program + " " + t.main.my,
                      "assets/nutrition_icon.png", GREEN_COLOR, () {
                    Store<AppState> store = StoreProvider.of<AppState>(context);
                    if (store.state.programListState.programs.length >= 1 &&
                        store.state.programListState.programs.reversed
                                .toList()[0]
                                .nutritionProgram != null) {
                      store.dispatch(SetNutritionProgramAction(
                          payload: store
                              .state.programListState.programs.reversed
                              .toList()[0]
                              .nutritionProgram));
                      Navigator.of(context).push(MaterialPageRoute(
                          builder: (context) => NutritionProgramPage(0)));
                    } else {
                      showAdjustDialog(context, t.program.not_exist,
                          false, null, GREEN_COLOR);
                    }
                  }),
                )
              ],
            ),
          ),
          Expanded(
            flex: 5,
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: <Widget>[
                Expanded(
                  flex: 5,
                  child: menuItem(t.tutorial.tutorial, "assets/game_icon.png", ORANGE_COLOR,
                      () async {
                    preloader(context);
                    int j = await getClientTutorials(context);
                    Store<AppState> s;
                    try {
                      s = StoreProvider.of<AppState>(context);
                    } catch (e) {
                      s = store;
                    }

                    s.state.clientState.tutorialList = s.state.clientTutorialsState.items;
                    s.dispatch(UpdateClientAction(payload: s.state.clientState));

//                    int i = await getTutorials(context);
                    int k = await getCourseList(context);
                    if (k == 1 && j == 1) {
                      Navigator.of(context, rootNavigator: true).pop("dialog");
                      Navigator.of(context).push(MaterialPageRoute(
                          builder: (context) => CoursePage()));
                    }
                  }),
                ),
                Expanded(
                  flex: 5,
                  child: menuItem(
                      t.program.programs, "assets/tutorial_icon.png", YELLOW_COLOR,
                      () async {
                    preloader(context);
                    int i = await getClientPrograms(context);
                    if (i == 1) {
                      Navigator.of(context, rootNavigator: true).pop("dialog");
                      Navigator.of(context).push(MaterialPageRoute(
                          builder: (context) => ProgramPage()));
                    } else {
                      Navigator.of(context, rootNavigator: true).pop("dialog");
                      showAdjustDialog(
                          context, t.user.failure, false, null, GREEN_COLOR);
                    }
                  }),
                )
              ],
            ),
          ),
        ],
      ),
    );
  }

  Widget menuItem(String text, String imageAsset, Color color, Function func) {
    return InkWell(
      child: Container(
        padding: EdgeInsets.only(top: 15),
        margin: EdgeInsets.all(10),
        decoration: BoxDecoration(
          color: WHITE_COLOR,
          boxShadow: [
            BoxShadow(
                color: SHADOW_COLOR,
                offset: Offset(2, 2),
                spreadRadius: 5,
                blurRadius: 5)
          ],
          borderRadius: BorderRadius.all(Radius.circular(10)),
        ),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          children: <Widget>[
            Expanded(
              flex: 60,
              child: Image(
                image: AssetImage(imageAsset),
              ),
            ),
            Expanded(
              flex: 10,
              child: Divider(
                thickness: 1,
                color: color,
                indent: 10,
                endIndent: 10,
              ),
            ),
            Expanded(
              flex: 30,
              child: Container(
                  padding: EdgeInsets.all(7),
                  decoration: BoxDecoration(
                      color: color,
                      borderRadius: BorderRadius.only(
                          bottomLeft: Radius.circular(10),
                          bottomRight: Radius.circular(5))),
                  child: Center(
                    child: AdjustText(
                      text,
                      color: WHITE_COLOR,
                      fontSize: 14,
                    ),
                  )),
            )
          ],
        ),
      ),
      onTap: () {
        func();
      },
    );
  }

  @override
  void dispose() {
    // TODO: implement dispose
    super.dispose();
  }
}
