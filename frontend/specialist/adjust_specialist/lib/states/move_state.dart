import 'package:adjust_specialist/model/enum/move_type.dart';
import 'package:adjust_specialist/model/enum/workout_location.dart';
import 'package:adjust_specialist/model/move.dart';

final MoveStateList moveStateListInit = MoveStateList(moves: List());

class MoveStateList {
  List<MoveState> moves;
  MoveStateList({this.moves});
}

class MoveState extends Move {
  MoveState(
      int id,
      String name,
      String description,
      String muscleName,
      MoveType moveType,
      WorkoutLocation place,
      String equipment,
      String picture,
      String pictureContentType)
      : super(id, name, description, muscleName, moveType, place, equipment,
            picture, pictureContentType);
}
