import 'package:adjust_specialist/config/i18n.dart';
import 'package:adjust_specialist/model/enum/gender.dart';
import 'package:adjust_specialist/model/enum/meal_type.dart';
import 'package:adjust_specialist/model/enum/move_type.dart';
import 'package:adjust_specialist/model/enum/workout_type.dart';

import 'package:enum_to_string/enum_to_string.dart';



final Map<String, String> BUSY_LIST = Map<String, String>()
  ..putIfAbsent("true", () => t.main.yes)
  ..putIfAbsent("false", () => t.main.no);

final Map<String, String> GENDER_LIST = Map<String, String>()
  ..putIfAbsent(
      EnumToString.parse(Gender.FEMALE), () => t.user.female)..putIfAbsent(
      EnumToString.parse(Gender.MALE), () => t.user.male);

final Map<String, String> MUSCLE_TYPE_LIST = Map<String, String>()
  ..putIfAbsent(EnumToString.parse(MoveType.CHEST), () => t.muscles.chest)
  ..putIfAbsent(EnumToString.parse(MoveType.BACK), () => t.muscles.back)
  ..putIfAbsent(EnumToString.parse(MoveType.BICEP), () => t.muscles.biceps)
  ..putIfAbsent(EnumToString.parse(MoveType.TRICEP), () => t.muscles.triceps)
  ..putIfAbsent(EnumToString.parse(MoveType.ABS), () => t.muscles.abs)
  ..putIfAbsent(EnumToString.parse(MoveType.CALVES), () => t.muscles.calves)
  ..putIfAbsent(EnumToString.parse(MoveType.FOREARM), () => t.muscles.forearms)
  ..putIfAbsent(EnumToString.parse(MoveType.GLUTES), () => t.muscles.glutes)
  ..putIfAbsent(EnumToString.parse(MoveType.HAMSTRING), () => t.muscles.hamstring)
  ..putIfAbsent(EnumToString.parse(MoveType.LATERAL), () => t.muscles.lateral)
  ..putIfAbsent(EnumToString.parse(MoveType.SHOULDER), () => t.muscles.shoulder)
  ..putIfAbsent(EnumToString.parse(MoveType.LEG), () => t.muscles.leg)
  ..putIfAbsent(EnumToString.parse(MoveType.MISC), () => t.muscles.misc);

final Map<String, String> MEAL_TYPE_LIST = Map<String, String>()
  ..putIfAbsent(EnumToString.parse(MealType.BREAKFAST), () => t.meals.breakfast)
  ..putIfAbsent(EnumToString.parse(MealType.BRUNCH), () => t.meals.brunch)
  ..putIfAbsent(EnumToString.parse(MealType.LUNCH), () => t.meals.lunch)
  ..putIfAbsent(EnumToString.parse(MealType.AFTERNOONMEAL), () => t.meals.afternoonmeal)
  ..putIfAbsent(EnumToString.parse(MealType.DINNER), () => t.meals.dinner)
  ..putIfAbsent(EnumToString.parse(MealType.EVEMEAL), () => t.meals.evemeal)
  ..putIfAbsent(EnumToString.parse(MealType.BEFORETRAINING), () => t.meals.beforetraining)
  ..putIfAbsent(EnumToString.parse(MealType.AFTERTRAINING), () => t.meals.aftertraining)
  ..putIfAbsent(EnumToString.parse(MealType.MIDNIGHTSUPPER), () => t.meals.midnightsupper)
  ..putIfAbsent(EnumToString.parse(MealType.IFTAR), () => t.meals.iftar)
  ..putIfAbsent(EnumToString.parse(MealType.SNACK1), () => getNumber(t.meals.snack1))
  ..putIfAbsent(EnumToString.parse(MealType.SNACK2), () => getNumber(t.meals.snack2))
  ..putIfAbsent(EnumToString.parse(MealType.SNACK3), () => getNumber(t.meals.snack3))
  ..putIfAbsent(EnumToString.parse(MealType.SNACK4), () => getNumber(t.meals.snack4))
  ..putIfAbsent(EnumToString.parse(MealType.SNACK5), () => getNumber(t.meals.snack5))
  ..putIfAbsent(EnumToString.parse(MealType.SNACK6), () => getNumber(t.meals.snack6));

final List<String> MEAL_NAMES = [
  t.meals.breakfast,
  t.meals.brunch,
  t.meals.lunch,
  t.meals.afternoonmeal,
  t.meals.dinner,
  t.meals.evemeal,
  t.meals.beforetraining,
  t.meals.aftertraining,
  t.meals.midnightsupper,
  t.meals.iftar,
  t.meals.snack1,
  t.meals.snack2,
  t.meals.snack3,
  t.meals.snack4,
  t.meals.snack5,
  t.meals.snack6,
];


final Map<String, String> WORKOUT_TYPE_LIST = Map<String, String>()
  ..putIfAbsent(EnumToString.parse(WorkoutType.POWERLIFTING), () => t.fitness.workout.power)
  ..putIfAbsent(EnumToString.parse(WorkoutType.BODYBUILDING), () => t.fitness.workout.bodybuilding)
  ..putIfAbsent(EnumToString.parse(WorkoutType.BODYBUILDING_CLASSIC), () => t.fitness.workout.bodybuilding_classic)
  ..putIfAbsent(EnumToString.parse(WorkoutType.FITNESS), () => t.fitness.workout.fitness)
  ..putIfAbsent(EnumToString.parse(WorkoutType.ENDURANCE), () => t.fitness.workout.endurance)
  ..putIfAbsent(EnumToString.parse(WorkoutType.CARDIO), () => t.fitness.workout.cardio);
