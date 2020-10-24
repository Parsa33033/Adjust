import 'package:adjust_specialist/model/enum/blood_type.dart';
import 'package:adjust_specialist/model/enum/education.dart';
import 'package:adjust_specialist/model/enum/financial_status.dart';
import 'package:adjust_specialist/model/enum/marital_status.dart';
import 'package:adjust_specialist/model/enum/occupation.dart';
import 'package:adjust_specialist/model/medical_profile.dart';

import 'body_composition_state.dart';
import 'disease_state.dart';

class MedicalProfileState extends MedicalProfile {
  List<DiseaseState> diseaseList;
  BodyCompositionState bodyComposition;
  MedicalProfileState(
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
