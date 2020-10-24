

import 'enum/blood_type.dart';
import 'enum/education.dart';
import 'enum/financial_status.dart';
import 'enum/marital_status.dart';
import 'enum/occupation.dart';

class MedicalProfile {
  int id;

  bool haveLostWeight;

  bool drowsiness;

  bool goodNightSleep;

  int hoursOfTraningPerWeek;

  bool loveForSugar;

  bool overEating;

  MaritalStatus maritalStatus;

  int childrenNumber;

  Education education;

  Occupation occupation;

  int hoursOfWorkDaily;

  FinancialStatus financialStatus;

  String city;

  bool useOfMedicine;

  String medicines;

  bool haveAllergy;

  String allergy;

  bool haveDisease;

  BloodType bloodType;

  int bodyCompositionId;

  MedicalProfile(
      this.id,
      this.haveLostWeight,
      this.drowsiness,
      this.goodNightSleep,
      this.hoursOfTraningPerWeek,
      this.loveForSugar,
      this.overEating,
      this.maritalStatus,
      this.childrenNumber,
      this.education,
      this.occupation,
      this.hoursOfWorkDaily,
      this.financialStatus,
      this.city,
      this.useOfMedicine,
      this.medicines,
      this.haveAllergy,
      this.allergy,
      this.haveDisease,
      this.bloodType,
      this.bodyCompositionId);
}





