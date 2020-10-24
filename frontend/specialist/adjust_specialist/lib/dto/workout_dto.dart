import 'package:adjust_specialist/dto/exercise_dto.dart';
import 'package:adjust_specialist/model/workout.dart';
import 'package:json_annotation/json_annotation.dart';

part 'workout_dto.g.dart';

@JsonSerializable()
class WorkoutDTO extends Workout {
  List<ExerciseDTO> exercises;

  WorkoutDTO(int id, int dayNumber, int fitnessProgramId, this.exercises)
      : super(id, dayNumber, fitnessProgramId);

  factory WorkoutDTO.fromJson(Map<String, dynamic> json) =>
      _$WorkoutDTOFromJson(json);

  Map<String, dynamic> toJson() => _$WorkoutDTOToJson(this);
}
