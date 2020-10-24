import 'package:adjust_client/model/specialist.dart';
import 'package:adjust_client/model/body_composition.dart';
import 'package:adjust_client/model/client.dart';
import 'package:adjust_client/model/fitness_program.dart';
import 'package:adjust_client/model/nutrition_program.dart';
import 'package:adjust_client/model/program.dart';
import 'package:adjust_client/states/body_composition_state.dart';
import 'package:adjust_client/states/client_state.dart';
import 'package:adjust_client/states/fitness_program_state.dart';
import 'package:adjust_client/states/development_state.dart';
import 'package:adjust_client/states/specialist_state.dart';

import 'nutrition_program_state.dart';

final ProgramListState programListStateInit = ProgramListState(List());

class ProgramListState {
  List<ProgramState> programs;

  ProgramListState(this.programs);
}

class ProgramState extends Program {
  BodyCompositionState bodyComposition;

  FitnessProgramState fitnessProgram;

  NutritionProgramState nutritionProgram;

  List<DevelopmentState> developments;

  ClientState adjustClient;

  SpecialistState specialist;

  ProgramState(
      int id,
      DateTime createdAt,
      DateTime expirationDate,
      bool medicalProfileDone,
      bool bodyCompositionDone,
      bool nutritionDone,
      bool fitnessDone,
      bool paid,
      bool seenByClient,
      bool seenBySpecialist,
      bool designed,
      int bodyCompositionId,
      int fitnessProgramId,
      int nutritionProgramId,
      int adjustClientId,
      int specialistId,
      this.bodyComposition,
      this.fitnessProgram,
      this.nutritionProgram,
      this.developments,
      this.adjustClient,
      this.specialist)
      : super(
            id,
            createdAt,
            expirationDate,
            medicalProfileDone,
            bodyCompositionDone,
            nutritionDone,
            fitnessDone,
            paid,
            seenByClient,
            seenBySpecialist,
            designed,
            bodyCompositionId,
            fitnessProgramId,
            nutritionProgramId,
            adjustClientId,
            specialistId);
}
