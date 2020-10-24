import 'package:adjust_client/dto/body_composition_dto.dart';
import 'package:adjust_client/dto/client_dto.dart';
import 'package:adjust_client/dto/fitness_program_dto.dart';
import 'package:adjust_client/dto/nutrition_program_dto.dart';
import 'package:adjust_client/dto/development_dto.dart';
import 'package:adjust_client/dto/specialist_dto.dart';
import 'package:adjust_client/model/program.dart';
import 'package:json_annotation/json_annotation.dart';

part 'program_dto.g.dart';

@JsonSerializable()
class ProgramDTO extends Program {
  BodyCompositionDTO bodyComposition;

  FitnessProgramDTO fitnessProgram;

  NutritionProgramDTO nutritionProgram;

  List<DevelopmentDTO> developments;

  ClientDTO adjustClient;

  SpecialistDTO specialist;

  ProgramDTO(
      int id,
      DateTime createdAt,
      DateTime expirationDate,
      bool medicalProfileDone,
      bool bodyCompositionDone,
      bool nutritionDone,
      bool fitnessDone,
      bool paid,
      bool seenByClient,
      bool seenBySpecialist,
      bool designed,
      int bodyCompositionId,
      int fitnessProgramId,
      int nutritionProgramId,
      int adjustClientId,
      int specialistId,
      this.bodyComposition,
      this.fitnessProgram,
      this.nutritionProgram,
      this.developments,
      this.adjustClient,
      this.specialist)
      : super(
            id,
            createdAt,
            expirationDate,
            medicalProfileDone,
            bodyCompositionDone,
            nutritionDone,
            fitnessDone,
            paid,
            seenByClient,
            seenBySpecialist,
            designed,
            bodyCompositionId,
            fitnessProgramId,
            nutritionProgramId,
            adjustClientId,
            specialistId);

  factory ProgramDTO.fromJson(Map<String, dynamic> json) =>
      _$ProgramDTOFromJson(json);

  Map<String, dynamic> toJson() => _$ProgramDTOToJson(this);
}
