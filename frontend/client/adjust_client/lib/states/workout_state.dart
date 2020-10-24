import 'package:adjust_client/model/exercise.dart';
import 'package:adjust_client/model/workout.dart';

import 'exercise_state.dart';

class WorkoutState extends Workout {
  List<ExerciseState> exercises;

  WorkoutState(int id, int dayNumber, int fitnessProgramId, this.exercises)
      : super(id, dayNumber, fitnessProgramId);
}
