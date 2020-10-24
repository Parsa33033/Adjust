import 'package:adjust_specialist/states/authentication_state.dart';
import 'package:adjust_specialist/states/conversation_state.dart';
import 'package:adjust_specialist/states/client_state.dart';
import 'package:adjust_specialist/states/fitness_program_state.dart';
import 'package:adjust_specialist/states/move_state.dart';
import 'package:adjust_specialist/states/nutrition_program_state.dart';
import 'package:adjust_specialist/states/program_state.dart';
import 'package:adjust_specialist/states/specialist_state.dart';
import 'package:adjust_specialist/states/user_state.dart';

import 'nutrition_state.dart';


AppState appStateInit = AppState(
    userStateInit,
    authenticationStateInit,
    specialistStateInit,
    programListStateInit,
    nutritionProgramStateInit,
    fitnessProgramStateInit,
    conversationStateInit,
    nutritionStateListInit,
    moveStateListInit);

class AppState {
  UserState userState;
  AuthenticationState authenticationState;
  SpecialistState specialistState;
  ProgramListState programListState;
  NutritionProgramState nutritionProgramState;
  FitnessProgramState fitnessProgramState;
  ConversationState conversationState;
  NutritionStateList nutritionStateList;
  MoveStateList moveStateList;

  AppState(this.userState,
      this.authenticationState,
      this.specialistState,
      this.programListState,
      this.nutritionProgramState,
      this.fitnessProgramState,
      this.conversationState,
      this.nutritionStateList,
      this.moveStateList);
}
