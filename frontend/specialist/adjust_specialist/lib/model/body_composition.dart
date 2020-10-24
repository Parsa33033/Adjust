

import 'enum/activity_type.dart';
import 'enum/bmi_category.dart';
import 'enum/body_fat_category.dart';
import 'enum/body_frame_size.dart';
import 'enum/body_type.dart';
import 'enum/gender.dart';
import 'enum/goal.dart';
import 'enum/workout_location.dart';
import 'enum/workout_type.dart';

class BodyComposition {
  int id;

  DateTime createdAt;

  int age;

  Gender gender;

  double height;

  double weight;

  double wrist;

  double waist;

  double hip;

  double abdomen;

  double neck;

  double waistHipRatio;

  double idealWeightMin;

  double idealWeightMax;

  double bestWeight;

  double bmi;

  BMICategory bmiCategory;

  double bmr;

  double lbm;

  double pbf;

  BodyFatCategory bodyFatCategory;

  double smm;

  double bodyTypeNumber;

  BodyType bodyType;

  double frameSize;

  BodyFrameSize bodyFrameSize;

  double visceralFatLevel;

  WorkoutLocation workoutLocation;

  Goal goal;

  ActivityType activityType;

  WorkoutType workoutType;

  BodyComposition(
      this.id,
      this.createdAt,
      this.age,
      this.gender,
      this.height,
      this.weight,
      this.wrist,
      this.waist,
      this.hip,
      this.abdomen,
      this.neck,
      this.waistHipRatio,
      this.idealWeightMin,
      this.idealWeightMax,
      this.bestWeight,
      this.bmi,
      this.bmiCategory,
      this.bmr,
      this.lbm,
      this.pbf,
      this.bodyFatCategory,
      this.smm,
      this.bodyTypeNumber,
      this.bodyType,
      this.frameSize,
      this.bodyFrameSize,
      this.visceralFatLevel,
      this.workoutLocation,
      this.goal,
      this.activityType,
      this.workoutType);
}