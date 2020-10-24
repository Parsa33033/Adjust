import 'package:adjust_client/model/development.dart';
import 'package:json_annotation/json_annotation.dart';

part 'development_dto.g.dart';

@JsonSerializable()
class DevelopmentDTO extends Development {
  DevelopmentDTO(
      int id,
      DateTime date,
      double nutritionScore,
      double fitnessScore,
      double weight,
      double bmi,
      double lbm,
      double pbf,
      int adjustProgramId)
      : super(id, date, nutritionScore, fitnessScore, weight, bmi, lbm, pbf,
            adjustProgramId);

  factory DevelopmentDTO.fromJson(Map<String, dynamic> json) =>
      _$DevelopmentDTOFromJson(json);

  Map<String, dynamic> toJson() => _$DevelopmentDTOToJson(this);
}
