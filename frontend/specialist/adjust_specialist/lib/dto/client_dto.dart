import 'package:adjust_specialist/model/client.dart';
import 'package:adjust_specialist/model/enum/gender.dart';
import 'package:json_annotation/json_annotation.dart';

import 'package:adjust_specialist/dto/medical_profile_dto.dart';
import 'package:adjust_specialist/dto/order_dto.dart';

part 'client_dto.g.dart';

@JsonSerializable()
class ClientDTO extends Client {

  MedicalProfileDTO medicalProfile;
  List<OrderDTO> orders;

  ClientDTO(
      int id,
      String username,
      String firstName,
      String lastName,
      DateTime birthDate,
      int age,
      Gender gender,
      double token,
      double score,
      String image,
      String imageContentType,
      int medicalProfileId,
      this.medicalProfile,
      this.orders)
      : super(id, username, firstName, lastName, birthDate, age, gender, token,
            score, image, imageContentType, medicalProfileId);

  factory ClientDTO.fromJson(Map<String, dynamic> json) =>
      _$ClientDTOFromJson(json);

  Map<String, dynamic> toJson() => _$ClientDTOToJson(this);
}
