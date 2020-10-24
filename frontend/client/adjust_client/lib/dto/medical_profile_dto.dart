
import 'package:adjust_client/dto/body_composition_dto.dart';
import 'package:adjust_client/model/enum/blood_type.dart';
import 'package:adjust_client/model/enum/education.dart';
import 'package:adjust_client/model/enum/financial_status.dart';
import 'package:adjust_client/model/enum/marital_status.dart';
import 'package:adjust_client/model/enum/occupation.dart';
import 'package:adjust_client/model/medical_profile.dart';
import 'package:adjust_client/dto/disease_dto.dart';
import 'package:json_annotation/json_annotation.dart';

part 'medical_profile_dto.g.dart';

@JsonSerializable()
class MedicalProfileDTO extends MedicalProfile {
  List<DiseaseDTO> diseaseList;
  BodyCompositionDTO bodyComposition;
  MedicalProfileDTO(
      int id,
      bool haveLostWeight,
      bool drowsiness,
      bool goodNightSleep,
      int hoursOfTraningPerWeek,
      bool loveForSugar,
      bool overEating,
      MaritalStatus maritalStatus,
      int childrenNumber,
      Education education,
      Occupation occupation,
      int hoursOfWorkDaily,
      FinancialStatus financialStatus,
      String city,
      bool useOfMedicine,
      String medicines,
      bool haveAllergy,
      String allergy,
      bool haveDisease,
      BloodType bloodType,
      int bodyCompositionId,
      this.diseaseList)
      : super(
            id,
            haveLostWeight,
            drowsiness,
            goodNightSleep,
            hoursOfTraningPerWeek,
            loveForSugar,
            overEating,
            maritalStatus,
            childrenNumber,
            education,
            occupation,
            hoursOfWorkDaily,
            financialStatus,
            city,
            useOfMedicine,
            medicines,
            haveAllergy,
            allergy,
            haveDisease,
            bloodType,
            bodyCompositionId);

  factory MedicalProfileDTO.fromJson(Map<String, dynamic> json) =>
      _$MedicalProfileDTOFromJson(json);

  Map<String, dynamic> toJson() => _$MedicalProfileDTOToJson(this);
}
