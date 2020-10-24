import 'package:adjust_client/model/body_composition.dart';
import 'package:adjust_client/model/client.dart';
import 'package:adjust_client/model/enum/activity_type.dart';
import 'package:adjust_client/model/enum/bmi_category.dart';
import 'package:adjust_client/model/enum/body_fat_category.dart';
import 'package:adjust_client/model/enum/body_frame_size.dart';
import 'package:adjust_client/model/enum/body_type.dart';
import 'package:adjust_client/model/enum/gender.dart';
import 'package:adjust_client/model/enum/goal.dart';
import 'package:adjust_client/model/enum/workout_location.dart';
import 'package:adjust_client/model/enum/workout_type.dart';
import 'package:json_annotation/json_annotation.dart';

part 'body_composition_dto.g.dart';

@JsonSerializable()
class BodyCompositionDTO extends BodyComposition {
  BodyCompositionDTO(
      int id,
      DateTime createdAt,
      int age,
      Gender gender,
      double height,
      double weight,
      double wrist,
      double waist,
      double hip,
      double abdomen,
      double neck,
      double waistHipRatio,
      double idealWeightMin,
      double idealWeightMax,
      double bestWeight,
      double bmi,
      BMICategory bmiCategory,
      double bmr,
      double lbm,
      double pbf,
      BodyFatCategory bodyFatCategory,
      double smm,
      double bodyTypeNumber,
      BodyType bodyType,
      double frameSize,
      BodyFrameSize bodyFrameSize,
      double visceralFatLevel,
      WorkoutLocation workoutLocation,
      Goal goal,
      ActivityType activityType,
      WorkoutType workoutType)
      : super(
            id,
            createdAt,
            age,
            gender,
            height,
            weight,
            wrist,
            waist,
            hip,
            abdomen,
            neck,
            waistHipRatio,
            idealWeightMin,
            idealWeightMax,
            bestWeight,
            bmi,
            bmiCategory,
            bmr,
            lbm,
            pbf,
            bodyFatCategory,
            smm,
            bodyTypeNumber,
            bodyType,
            frameSize,
            bodyFrameSize,
            visceralFatLevel,
            workoutLocation,
            goal,
            activityType,
            workoutType);

  factory BodyCompositionDTO.fromJson(Map<String, dynamic> json) =>
      _$BodyCompositionDTOFromJson(json);

  Map<String, dynamic> toJson() => _$BodyCompositionDTOToJson(this);
}
