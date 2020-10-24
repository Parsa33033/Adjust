import 'package:adjust_client/config/i18n.dart';
import 'package:adjust_client/model/client.dart';
import 'package:adjust_client/model/enum/activity_type.dart';
import 'package:adjust_client/model/enum/gender.dart';
import 'package:adjust_client/model/enum/goal.dart';
import 'package:adjust_client/model/enum/meal_type.dart';
import 'package:adjust_client/model/enum/move_type.dart';
import 'package:adjust_client/model/enum/workout_location.dart';
import 'package:adjust_client/model/enum/workout_type.dart';
import 'package:adjust_client/model/move.dart';
import 'package:enum_to_string/enum_to_string.dart';


final Map<String, String> GENDER_LIST = Map<String, String>()
  ..putIfAbsent(EnumToString.parse(Gender.FEMALE), () => t.user.female)
  ..putIfAbsent(EnumToString.parse(Gender.MALE), () => t.user.male);

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
  ..putIfAbsent(EnumToString.parse(MoveType.LEG), () => t.muscles.trx)
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

final Map<String, String> WORKOUT_LOCATIONS = Map<String, String>()
  ..putIfAbsent(EnumToString.parse(WorkoutLocation.GYM), () => t.body_composition.workout_location.gym)
  ..putIfAbsent(EnumToString.parse(WorkoutLocation.HOME), () => t.body_composition.workout_location.home);

final Map<String, String> GOALS = Map<String, String>()
  ..putIfAbsent(EnumToString.parse(Goal.DECREASEWEIGHT), () => t.body_composition.goal.decrease_weight)
  ..putIfAbsent(EnumToString.parse(Goal.INCREASEWEIGHT), () => t.body_composition.goal.increase_weight);

final Map<String, String> ACTIVITY_TYPES = Map<String, String>()
  ..putIfAbsent(EnumToString.parse(ActivityType.SEDENTARY), () => t.body_composition.activity_type.sedentary)
  ..putIfAbsent(EnumToString.parse(ActivityType.LIGHT), () => t.body_composition.activity_type.light)
  ..putIfAbsent(EnumToString.parse(ActivityType.MODERATE), () => getNumber(t.body_composition.activity_type.moderate))
  ..putIfAbsent(EnumToString.parse(ActivityType.HIGH), () => t.body_composition.activity_type.high)
  ..putIfAbsent(EnumToString.parse(ActivityType.EXTRA), () => t.body_composition.activity_type.extra);
