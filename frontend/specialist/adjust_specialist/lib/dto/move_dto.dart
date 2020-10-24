import 'package:adjust_specialist/model/enum/move_type.dart';
import 'package:adjust_specialist/model/enum/workout_location.dart';
import 'package:adjust_specialist/model/move.dart';
import 'package:json_annotation/json_annotation.dart';

part 'move_dto.g.dart';

@JsonSerializable()
class MoveDTO extends Move {
  MoveDTO(
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

  factory MoveDTO.fromJson(Map<String, dynamic> json) =>
      _$MoveDTOFromJson(json);

  Map<String, dynamic> toJson() => _$MoveDTOToJson(this);
}
