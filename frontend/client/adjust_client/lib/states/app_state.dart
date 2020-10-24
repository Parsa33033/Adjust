import 'package:adjust_client/states/authentication_state.dart';
import 'package:adjust_client/states/conversation_state.dart';
import 'package:adjust_client/states/course_state.dart';
import 'package:adjust_client/states/order_item_state.dart';
import 'package:adjust_client/states/client_state.dart';
import 'package:adjust_client/states/fitness_program_state.dart';
import 'package:adjust_client/states/nutrition_program_state.dart';
import 'package:adjust_client/states/program_state.dart';
import 'package:adjust_client/states/shopping_state.dart';
import 'package:adjust_client/states/specialist_state.dart';
import 'package:adjust_client/states/token_state.dart';
import 'package:adjust_client/states/tutorial_state.dart';
import 'package:adjust_client/states/user_state.dart';

import 'cart_state.dart';

AppState appStateInit = AppState(
    userState: userStateInit,
    authenticationState: authenticationStateInit,
    clientState: clientStateInit,
    shoppingState: shoppingStateInit,
    tokenState: tokenStateInit,
    cartState: cartStateInit,
    tutorialState: tutorialStateInit,
    tutorialListState: tutorialListStateInit,
    courseListState: courseListStateInit,
    clientTutorialsState: clientTutorialsStateInit,
    programListState: programListStateInit,
    specialistListState: specialistListStateInit,
    nutritionProgramState: nutritionProgramStateInit,
    fitnessProgramState: fitnessProgramStateInit,
    conversationState: conversationStateInit,
    );

class AppState {
  UserState userState;
  AuthenticationState authenticationState;
  ClientState clientState;
  ShoppingState shoppingState;
  TokenState tokenState;
  CartState cartState;
  TutorialState tutorialState;
  TutorialListState tutorialListState;
  CourseListState courseListState;
  ClientTutorialsState clientTutorialsState;
  ProgramListState programListState;
  SpecialistListState specialistListState;
  NutritionProgramState nutritionProgramState;
  FitnessProgramState fitnessProgramState;
  ConversationState conversationState;

  AppState(
      {this.userState,
      this.authenticationState,
      this.clientState,
      this.shoppingState,
      this.tokenState,
      this.cartState,
      this.tutorialState,
      this.tutorialListState,
      this.courseListState,
      this.clientTutorialsState,
      this.programListState,
      this.specialistListState,
      this.nutritionProgramState,
      this.fitnessProgramState,
      this.conversationState});
}
