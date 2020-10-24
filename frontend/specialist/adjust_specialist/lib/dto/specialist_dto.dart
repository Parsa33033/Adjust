import 'package:adjust_specialist/model/specialist.dart';
import 'package:adjust_specialist/model/client.dart';
import 'package:adjust_specialist/model/enum/gender.dart';
import 'package:json_annotation/json_annotation.dart';

part 'specialist_dto.g.dart';

@JsonSerializable()
class SpecialistListDTO {
  List<SpecialistDTO> specialists;

  SpecialistListDTO(this.specialists);
}

@JsonSerializable()
class SpecialistDTO extends Specialist {
  SpecialistDTO(
      int id,
      String username,
      String firstName,
      String lastName,
      DateTime birthday,
      Gender gender,
      String degree,
      String field,
      String resume,
      double stars,
      String image,
      String imageContentType,
      bool busy)
      : super(id, username, firstName, lastName, birthday, gender, degree,
            field, resume, stars, image, imageContentType, busy);

  factory SpecialistDTO.fromJson(Map<String, dynamic> json) =>
      _$SpecialistDTOFromJson(json);

  Map<String, dynamic> toJson() => _$SpecialistDTOToJson(this);
}
