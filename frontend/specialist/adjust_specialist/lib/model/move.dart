import 'enum/move_type.dart';
import 'enum/workout_location.dart';

class Move {
  int id;

  String name;

  String description;

  String muscleName;

  MoveType moveType;

  WorkoutLocation place;

  String equipment;

  String picture;

  String pictureContentType;

  Move(this.id, this.name, this.description, this.muscleName, this.moveType,
      this.place, this.equipment, this.picture, this.pictureContentType);
}

