import 'package:adjust_client/model/exercise.dart';

import 'move_state.dart';

class ExerciseState extends Exercise {
  MoveState move;

  ExerciseState(
      int id, int number, int sets, int repsMin, int repsMax, int workoutId, this.move)
      : super(id, number, sets, repsMin, repsMax, workoutId);
}
