
import 'enum/workout_type.dart';

class Workout {
  int id;

  int dayNumber;

  int fitnessProgramId;

  Workout(this.id, this.dayNumber, this.fitnessProgramId);
}


class ExerciseType {
  WorkoutType workoutType;
  int sets;
  int minReps;
  int maxReps;
  ExerciseType(this.workoutType, this.sets, this.minReps, this.maxReps);
}

final Map<WorkoutType, ExerciseType> exerciseForWorkout = Map<WorkoutType, ExerciseType>()
  ..putIfAbsent(WorkoutType.POWERLIFTING, () => ExerciseType(WorkoutType.POWERLIFTING, 3, 2, 4))
  ..putIfAbsent(WorkoutType.BODYBUILDING, () => ExerciseType(WorkoutType.BODYBUILDING, 3, 4, 6))
  ..putIfAbsent(WorkoutType.BODYBUILDING, () => ExerciseType(WorkoutType.BODYBUILDING, 3, 6, 8))
  ..putIfAbsent(WorkoutType.BODYBUILDING_CLASSIC, () => ExerciseType(WorkoutType.BODYBUILDING_CLASSIC, 3, 8, 12))
  ..putIfAbsent(WorkoutType.FITNESS, () => ExerciseType(WorkoutType.FITNESS, 3, 12, 15))
  ..putIfAbsent(WorkoutType.ENDURANCE, () => ExerciseType(WorkoutType.ENDURANCE, 3, 15, 30))
  ..putIfAbsent(WorkoutType.CARDIO, () => ExerciseType(WorkoutType.CARDIO, 3, 30, 50));