import 'package:adjust_client/dto/tutorial_dto.dart';
import 'package:adjust_client/model/client.dart';
import 'package:adjust_client/model/enum/gender.dart';
import 'package:json_annotation/json_annotation.dart';

import 'package:adjust_client/dto/medical_profile_dto.dart';
import 'package:adjust_client/dto/order_dto.dart';

part 'client_dto.g.dart';

@JsonSerializable()
class ClientDTO extends Client {

  MedicalProfileDTO medicalProfile;
  List<OrderDTO> orders;
  List<TutorialDTO> tutorialList;

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
      this.orders,
      this.tutorialList)
      : super(id, username, firstName, lastName, birthDate, age, gender, token,
            score, image, imageContentType, medicalProfileId);

  factory ClientDTO.fromJson(Map<String, dynamic> json) =>
      _$ClientDTOFromJson(json);

  Map<String, dynamic> toJson() => _$ClientDTOToJson(this);
}
