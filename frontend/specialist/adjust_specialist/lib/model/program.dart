

class Program {

  int id;

  DateTime createdAt;

  DateTime expirationDate;

  bool medicalProfileDone;

  bool bodyCompositionDone;

  bool nutritionDone;

  bool fitnessDone;

  bool paid;

  bool seenByClient;

  bool seenBySpecialist;

  bool designed;

  int bodyCompositionId;

  int fitnessProgramId;

  int nutritionProgramId;

  int adjustClientId;

  int specialistId;

  Program(
      this.id,
      this.createdAt,
      this.expirationDate,
      this.medicalProfileDone,
      this.bodyCompositionDone,
      this.nutritionDone,
      this.fitnessDone,
      this.paid,
      this.seenByClient,
      this.seenBySpecialist,
      this.designed,
      this.bodyCompositionId,
      this.fitnessProgramId,
      this.nutritionProgramId,
      this.adjustClientId,
      this.specialistId);
}