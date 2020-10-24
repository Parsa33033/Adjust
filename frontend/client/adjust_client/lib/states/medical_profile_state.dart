import 'package:adjust_client/model/enum/blood_type.dart';
import 'package:adjust_client/model/enum/education.dart';
import 'package:adjust_client/model/enum/financial_status.dart';
import 'package:adjust_client/model/enum/marital_status.dart';
import 'package:adjust_client/model/enum/occupation.dart';
import 'package:adjust_client/model/medical_profile.dart';

import 'body_composition_state.dart';
import 'disease_state.dart';

final MedicalProfileState medicalProfileStateInit = MedicalProfileState(
    0,
    false,
    false,
    false,
    0,
    false,
    false,
    MaritalStatus.SINGLE,
    0,
    Education.LOW,
    Occupation.OTHERS,
    0,
    FinancialStatus.BAD,
    "",
    false,
    null,
    false,
    "",
    false,
    BloodType.O,
    0,
    List(),
    bodyCompositionStateInit);

class MedicalProfileState extends MedicalProfile {
  List<DiseaseState> diseaseList;
  BodyCompositionState bodyComposition;

  MedicalProfileState(int id,
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
      this.diseaseList,
      this.bodyComposition)
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
}
