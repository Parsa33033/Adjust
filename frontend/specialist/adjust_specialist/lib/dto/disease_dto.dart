import 'package:adjust_specialist/model/disease.dart';
import 'package:json_annotation/json_annotation.dart';

part 'disease_dto.g.dart';

@JsonSerializable()
class DiseaseDTO extends Disease {
  DiseaseDTO(int id, String name, int proteinPercentage, int carbsPercentage,
      int fatPercentage)
      : super(id, name, proteinPercentage, carbsPercentage, fatPercentage);

  factory DiseaseDTO.fromJson(Map<String, dynamic> json) =>
      _$DiseaseDTOFromJson(json);

  Map<String, dynamic> toJson() => _$DiseaseDTOToJson(this);
}
