import 'dart:convert';
import 'dart:io';

import 'package:adjust_specialist/actions/jwt.dart';
import 'package:adjust_specialist/constants/urls.dart';
import 'package:adjust_specialist/dto/body_composition_dto.dart';
import 'package:adjust_specialist/dto/client_dto.dart';
import 'package:adjust_specialist/dto/fitness_program_dto.dart';
import 'package:adjust_specialist/dto/food_dto.dart';
import 'package:adjust_specialist/dto/medical_profile_dto.dart';
import 'package:adjust_specialist/dto/move_dto.dart';
import 'package:adjust_specialist/dto/nutrition_dto.dart';
import 'package:adjust_specialist/dto/nutrition_program_dto.dart';
import 'package:adjust_specialist/dto/development_dto.dart';
import 'package:adjust_specialist/dto/program_dto.dart';
import 'package:adjust_specialist/dto/specialist_dto.dart';
import 'package:adjust_specialist/main.dart';
import 'package:adjust_specialist/model/food.dart';
import 'package:adjust_specialist/states/app_state.dart';
import 'package:adjust_specialist/states/body_composition_state.dart';
import 'package:adjust_specialist/states/client_state.dart';
import 'package:adjust_specialist/states/development_state.dart';
import 'package:adjust_specialist/states/disease_state.dart';
import 'package:adjust_specialist/states/exercise_state.dart';
import 'package:adjust_specialist/states/fitness_program_state.dart';
import 'package:adjust_specialist/states/food_state.dart';
import 'package:adjust_specialist/states/meal_state.dart';
import 'package:adjust_specialist/states/meals_for_nutrition_state.dart';
import 'package:adjust_specialist/states/medical_profile_state.dart';
import 'package:adjust_specialist/states/move_state.dart';
import 'package:adjust_specialist/states/nutrition_program_state.dart';
import 'package:adjust_specialist/states/nutrition_state.dart';
import 'package:adjust_specialist/states/development_state.dart';
import 'package:adjust_specialist/states/program_state.dart';
import 'package:adjust_specialist/states/specialist_state.dart';
import 'package:adjust_specialist/states/workout_state.dart';
import 'package:flutter/material.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:http/http.dart' as http;

class GetProgramListAction {
  ProgramListState payload;

  GetProgramListAction({this.payload});
}

class SetProgramListAction {
  ProgramListState payload;

  SetProgramListAction({this.payload});
}

class SetNutritionProgramAction {
  NutritionProgramState payload;

  SetNutritionProgramAction({this.payload});
}

class SetFitnessProgramAction {
  FitnessProgramState payload;

  SetFitnessProgramAction({this.payload});
}

class GetAdjustNutritionsAction {
  NutritionStateList payload;

  GetAdjustNutritionsAction({this.payload});
}

class GetAdjustMovesAction {
  MoveStateList payload;

  GetAdjustMovesAction({this.payload});
}

Future<int> getSpecialistPrograms(BuildContext context) async {
  String jwt = await getJwt(context);

  Map<String, String> headers = Map<String, String>()
    ..putIfAbsent("Authorization", () => "Bearer " + jwt);

  http.Response response = await http.get(PROGRAM_URL, headers: headers);
  if (response.statusCode == HttpStatus.ok) {
    List l = jsonDecode(utf8.decode(response.bodyBytes));
    List<ProgramState> programList = l.map((e) {
      ProgramDTO programDTO = ProgramDTO.fromJson(e);

      List<DevelopmentState> developmentListState =
      programDTO.developments.map((developmentDTO) {
        DevelopmentState developmentState = DevelopmentState(
            developmentDTO.id,
            developmentDTO.date,
            developmentDTO.nutritionScore,
            developmentDTO.fitnessScore,
            developmentDTO.weight,
            developmentDTO.bmi,
            developmentDTO.lbm,
            developmentDTO.pbf,
            developmentDTO.adjustProgramId);
        return developmentState;
      }).toList();

      BodyCompositionDTO bodyCompositionDTO = programDTO.bodyComposition;
      BodyCompositionState bodyCompositionState = BodyCompositionState(
          bodyCompositionDTO.id,
          bodyCompositionDTO.createdAt,
          bodyCompositionDTO.age,
          bodyCompositionDTO.gender,
          bodyCompositionDTO.height,
          bodyCompositionDTO.weight,
          bodyCompositionDTO.wrist,
          bodyCompositionDTO.waist,
          bodyCompositionDTO.hip,
          bodyCompositionDTO.abdomen,
          bodyCompositionDTO.neck,
          bodyCompositionDTO.waistHipRatio,
          bodyCompositionDTO.idealWeightMin,
          bodyCompositionDTO.idealWeightMax,
          bodyCompositionDTO.bestWeight,
          bodyCompositionDTO.bmi,
          bodyCompositionDTO.bmiCategory,
          bodyCompositionDTO.bmr,
          bodyCompositionDTO.lbm,
          bodyCompositionDTO.pbf,
          bodyCompositionDTO.bodyFatCategory,
          bodyCompositionDTO.smm,
          bodyCompositionDTO.bodyTypeNumber,
          bodyCompositionDTO.bodyType,
          bodyCompositionDTO.frameSize,
          bodyCompositionDTO.bodyFrameSize,
          bodyCompositionDTO.visceralFatLevel,
          bodyCompositionDTO.workoutLocation,
          bodyCompositionDTO.goal,
          bodyCompositionDTO.activityType,
          bodyCompositionDTO.workoutType);

      NutritionProgramDTO nutritionProgramDTO = programDTO.nutritionProgram;
      NutritionProgramState nutritionProgramState = null;
      if (nutritionProgramDTO != null && nutritionProgramDTO.meals != null) {
        List<MealState> meals = nutritionProgramDTO.meals.map((mealDTO) {
          List<NutritionState> nutritions =
          mealDTO.nutritionList.map((nutritionDTO) {
            // set foods
            List<FoodState> foods = nutritionDTO.foods.map((foodDTO) {
              FoodState foodState = FoodState(foodDTO.id, foodDTO.name,
                  foodDTO.description, foodDTO.nutritionId);
              return foodState;
            }).toList();
            // set meals for nutrition state
            List<MealsForNutritionState> mealTypes =
            nutritionDTO.mealTypes.map((mealTypeListDTO) {
              MealsForNutritionState mealsForNutritionState =
              MealsForNutritionState(mealTypeListDTO.id,
                  mealTypeListDTO.mealType, mealTypeListDTO.nutritionId);
              return mealsForNutritionState;
            }).toList();

            NutritionState nutritionState = NutritionState(
                nutritionDTO.id,
                nutritionDTO.name,
                nutritionDTO.description,
                nutritionDTO.unit,
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
                mealTypes,
                foods);

            return nutritionState;
          }).toList();
          MealState mealState = MealState(mealDTO.id, mealDTO.mealType,
              mealDTO.number, mealDTO.nutritionProgramId, nutritions);
          return mealState;
        }).toList();
        nutritionProgramState = NutritionProgramState(
            nutritionProgramDTO.id,
            nutritionProgramDTO.dailyCalories,
            nutritionProgramDTO.proteinPercentage,
            nutritionProgramDTO.carbsPercentage,
            nutritionProgramDTO.fatPercentage,
            nutritionProgramDTO.description,
            meals);
      }

      FitnessProgramDTO fitnessProgramDTO = programDTO.fitnessProgram;
      FitnessProgramState fitnessProgramState = null;
      if (fitnessProgramDTO != null && fitnessProgramDTO.workouts != null) {
        // set workout state list
        List<WorkoutState> workoutStateList =
        fitnessProgramDTO.workouts.map((workoutDTO) {
          // set Exercise State list
          List<ExerciseState> exerciseStateList =
          workoutDTO.exercises.map((exerciseDTO) {
            MoveDTO moveDTO = exerciseDTO.move;

            // set move state
            MoveState moveState = MoveState(
                moveDTO.id,
                moveDTO.name,
                moveDTO.description,
                moveDTO.muscleName,
                moveDTO.moveType,
                moveDTO.place,
                moveDTO.equipment,
                moveDTO.picture,
                moveDTO.pictureContentType);

            // set exercise state
            return ExerciseState(
                exerciseDTO.id,
                exerciseDTO.number,
                exerciseDTO.sets,
                exerciseDTO.repsMin,
                exerciseDTO.repsMax,
                exerciseDTO.workoutId,
                moveState);
          }).toList();

          //set WorkoutState
          WorkoutState workoutState = WorkoutState(
              workoutDTO.id,
              workoutDTO.dayNumber,
              workoutDTO.fitnessProgramId,
              exerciseStateList);
          return workoutState;
        }).toList();
        fitnessProgramState = FitnessProgramState(
            fitnessProgramDTO.id,
            fitnessProgramDTO.type,
            fitnessProgramDTO.description,
            workoutStateList);
      }

      //set medical profile state
      ClientDTO clientDTO = programDTO.adjustClient;
      MedicalProfileDTO medicalProfileDTO = clientDTO.medicalProfile;
      BodyCompositionDTO clientBodyCompositionDTO = medicalProfileDTO
          .bodyComposition;
      BodyCompositionState clientBodyCompositionState = BodyCompositionState(
          clientBodyCompositionDTO.id,
          clientBodyCompositionDTO.createdAt,
          clientBodyCompositionDTO.age,
          clientBodyCompositionDTO.gender,
          clientBodyCompositionDTO.height,
          clientBodyCompositionDTO.weight,
          clientBodyCompositionDTO.wrist,
          clientBodyCompositionDTO.waist,
          clientBodyCompositionDTO.hip,
          clientBodyCompositionDTO.abdomen,
          clientBodyCompositionDTO.neck,
          clientBodyCompositionDTO.waistHipRatio,
          clientBodyCompositionDTO.idealWeightMin,
          clientBodyCompositionDTO.idealWeightMax,
          clientBodyCompositionDTO.bestWeight,
          clientBodyCompositionDTO.bmi,
          clientBodyCompositionDTO.bmiCategory,
          clientBodyCompositionDTO.bmr,
          clientBodyCompositionDTO.lbm,
          clientBodyCompositionDTO.pbf,
          clientBodyCompositionDTO.bodyFatCategory,
          clientBodyCompositionDTO.smm,
          clientBodyCompositionDTO.bodyTypeNumber,
          clientBodyCompositionDTO.bodyType,
          clientBodyCompositionDTO.frameSize,
          clientBodyCompositionDTO.bodyFrameSize,
          clientBodyCompositionDTO.visceralFatLevel,
          clientBodyCompositionDTO.workoutLocation,
          clientBodyCompositionDTO.goal,
          clientBodyCompositionDTO.activityType,
          clientBodyCompositionDTO.workoutType);

      List<DiseaseState> diseaseListState =
      medicalProfileDTO.diseaseList.map((diseaseDTO) {
        return DiseaseState(
            diseaseDTO.id,
            diseaseDTO.name,
            diseaseDTO.proteinPercentage,
            diseaseDTO.carbsPercentage,
            diseaseDTO.fatPercentage);
      }).toList();

      MedicalProfileState medicalProfileState = MedicalProfileState(
          medicalProfileDTO.id,
          medicalProfileDTO.haveLostWeight,
          medicalProfileDTO.drowsiness,
          medicalProfileDTO.goodNightSleep,
          medicalProfileDTO.hoursOfTraningPerWeek,
          medicalProfileDTO.loveForSugar,
          medicalProfileDTO.overEating,
          medicalProfileDTO.maritalStatus,
          medicalProfileDTO.childrenNumber,
          medicalProfileDTO.education,
          medicalProfileDTO.occupation,
          medicalProfileDTO.hoursOfWorkDaily,
          medicalProfileDTO.financialStatus,
          medicalProfileDTO.city,
          medicalProfileDTO.useOfMedicine,
          medicalProfileDTO.medicines,
          medicalProfileDTO.haveAllergy,
          medicalProfileDTO.allergy,
          medicalProfileDTO.haveDisease,
          medicalProfileDTO.bloodType,
          medicalProfileDTO.bodyCompositionId,
          diseaseListState,
          clientBodyCompositionState);

      ClientState clientState = ClientState(
        clientDTO.id,
        clientDTO.username,
        clientDTO.firstName,
        clientDTO.lastName,
        clientDTO.birthDate,
        clientDTO.age,
        clientDTO.gender,
        clientDTO.token,
        clientDTO.score,
        clientDTO.image,
        clientDTO.imageContentType,
        clientDTO.medicalProfileId,
        medicalProfileState,);

      SpecialistDTO specialistDTO = programDTO.specialist;
      SpecialistState specialistState = SpecialistState(
          specialistDTO.id,
          specialistDTO.username,
          specialistDTO.firstName,
          specialistDTO.lastName,
          specialistDTO.birthday,
          specialistDTO.gender,
          specialistDTO.degree,
          specialistDTO.field,
          specialistDTO.resume,
          specialistDTO.stars,
          specialistDTO.image,
          specialistDTO.imageContentType,
          specialistDTO.busy);

      ProgramState programState = ProgramState(
          programDTO.id,
          programDTO.createdAt,
          programDTO.expirationDate,
          programDTO.medicalProfileDone,
          programDTO.bodyCompositionDone,
          programDTO.nutritionDone,
          programDTO.fitnessDone,
          programDTO.paid,
          programDTO.seenByClient,
          programDTO.seenBySpecialist,
          programDTO.designed,
          programDTO.bodyCompositionId,
          programDTO.fitnessProgramId,
          programDTO.nutritionProgramId,
          programDTO.adjustClientId,
          programDTO.specialistId,
          bodyCompositionState,
          fitnessProgramState,
          nutritionProgramState,
          developmentListState,
          clientState,
          specialistState);
      return programState;
    }).toList();
    try {
      StoreProvider.of<AppState>(context).dispatch(
          GetProgramListAction(payload: ProgramListState(programList)));
      return 1;
    } catch (e) {
      store.dispatch(
          GetProgramListAction(payload: ProgramListState(programList)));
      return 1;
    }
  }
  return 0;
}


Future<int> setProgramDevelopment(BuildContext context,
    DevelopmentDTO programDevelopmentDTO, int programIndex) async {
  String jwt = await getJwt(context);

  Map<String, String> headers = Map<String, String>()
    ..putIfAbsent("Authorization", () => "Bearer " + jwt)..putIfAbsent(
        "Content-Type", () => "application/json");

  String content = jsonEncode(programDevelopmentDTO.toJson());

  http.Response response = await http.post(PROGRAM_DEVELOPMENT_URL,
      headers: headers, body: content, encoding: Encoding.getByName("UTF-8"));
  if (response.statusCode == HttpStatus.ok) {
    List l = jsonDecode(utf8.decode(response.bodyBytes));
    List<DevelopmentState> programDevelopmentStateList = l.map((e) {
      DevelopmentDTO developmentDTO =
      DevelopmentDTO.fromJson(e);
      DevelopmentState programDevelopmentState = DevelopmentState(
          developmentDTO.id,
          developmentDTO.date,
          developmentDTO.nutritionScore,
          developmentDTO.fitnessScore,
          developmentDTO.weight,
          developmentDTO.bmi,
          developmentDTO.lbm,
          developmentDTO.pbf,
          developmentDTO.adjustProgramId);
      return programDevelopmentState;
    }).toList();

    ProgramState programState =
    store.state.programListState.programs.reversed.toList()[programIndex];
    programState.developments = programDevelopmentStateList;
    store.state.programListState.programs.reversed.toList()[programIndex] =
        programState;
    store.dispatch(SetProgramListAction(payload: store.state.programListState));
    return 1;
  }
  return 0;
}

Future<int> getAdjustNutritionList(BuildContext context) async {
  String jwt = await getJwt(context);
  Map<String, String> headers = Map<String, String>()
    ..putIfAbsent("Authorization", () => "Bearer " + jwt);
  http.Response response =
  await http.get(ADJUST_NUTRITION_URL, headers: headers);
  if (response.statusCode == HttpStatus.ok) {
    List l = jsonDecode(utf8.decode(response.bodyBytes));
    List<NutritionState> nutritionStateList = l.map((e) {
      NutritionDTO nutritionDTO = NutritionDTO.fromJson(e);

      // food list
      List<FoodState> foodStateList = nutritionDTO.foods.map((foodDTO) {
        FoodState foodState = FoodState(
            foodDTO.id, foodDTO.name, foodDTO.description, foodDTO.nutritionId);
        return foodState;
      }).toList();

      // meals for nutrition list
      List<MealsForNutritionState> mealTypes =
      nutritionDTO.mealTypes.map((mealTypeListDTO) {
        MealsForNutritionState mealsForNutritionState =
        MealsForNutritionState(mealTypeListDTO.id,
            mealTypeListDTO.mealType, mealTypeListDTO.nutritionId);
        return mealsForNutritionState;
      }).toList();

      // nutrition state
      NutritionState nutritionState = NutritionState(
          nutritionDTO.id,
          nutritionDTO.name,
          nutritionDTO.description,
          nutritionDTO.unit,
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
          mealTypes,
          foodStateList);
      return nutritionState;
    }).toList();
    try {
      StoreProvider.of<AppState>(context).dispatch(GetAdjustNutritionsAction(
          payload: NutritionStateList(nutritionStateList)));
    } catch (e) {
      store.dispatch(GetAdjustNutritionsAction(
          payload: NutritionStateList(nutritionStateList)));
    }
    return 1;
  }
  return 0;
}

Future<int> getAdjustMoveList(BuildContext context) async {
  String jwt = await getJwt(context);
  Map<String, String> headers = Map<String, String>()
    ..putIfAbsent("Authorization", () => "Bearer " + jwt);

  http.Response response = await http.get(ADJUST_MOVE_URL, headers: headers);
  if (response.statusCode == HttpStatus.ok) {
    List l = jsonDecode(utf8.decode(response.bodyBytes));
    List<MoveState> moveStateList = l.map((e) {
      MoveDTO moveDTO = MoveDTO.fromJson(e);
      MoveState moveState = MoveState(
          moveDTO.id,
          moveDTO.name,
          moveDTO.description,
          moveDTO.muscleName,
          moveDTO.moveType,
          moveDTO.place,
          moveDTO.equipment,
          moveDTO.picture,
          moveDTO.pictureContentType);
      return moveState;
    }).toList();

    try {
      StoreProvider.of<AppState>(context).dispatch(
          GetAdjustMovesAction(payload: MoveStateList(moves: moveStateList)));
    } catch (e) {
      store.dispatch(
          GetAdjustMovesAction(payload: MoveStateList(moves: moveStateList)));
    }
    return 1;
  }
  return 0;
}

Future<int> designNutritionProgram(BuildContext context,
    ProgramDTO programDTO) async {
  String jwt = await getJwt(context);
  Map<String, String> headers = Map<String, String>()
    ..putIfAbsent("Authorization", () => "Bearer " + jwt)..putIfAbsent(
        "Content-Type", () => "application/json");

  String content = jsonEncode(programDTO.toJson());

  http.Response response = await http.post(DESIGN_NUTRITION_PROGRAM_URL,
      headers: headers, body: content, encoding: Encoding.getByName("UTF-8"));
  if (response.statusCode == HttpStatus.ok) {
    return 1;
  }
  return 0;
}


Future<int> designFitnessProgram(BuildContext context,
    ProgramDTO programDTO) async {
  String jwt = await getJwt(context);
  Map<String, String> headers = Map<String, String>()
    ..putIfAbsent("Authorization", () => "Bearer " + jwt)..putIfAbsent(
        "Content-Type", () => "application/json");

  String content = jsonEncode(programDTO.toJson());

  http.Response response = await http.post(DESIGN_FITNESS_PROGRAM_URL,
      headers: headers, body: content, encoding: Encoding.getByName("UTF-8"));
  if (response.statusCode == HttpStatus.ok) {
    return 1;
  }
  return 0;
}