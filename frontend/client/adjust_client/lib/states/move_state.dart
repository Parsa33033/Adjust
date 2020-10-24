import 'package:adjust_client/model/enum/move_type.dart';
import 'package:adjust_client/model/enum/workout_location.dart';
import 'package:adjust_client/model/move.dart';

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
