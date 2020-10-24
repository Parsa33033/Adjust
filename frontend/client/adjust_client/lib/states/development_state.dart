import 'package:adjust_client/model/development.dart';

final DevelopmentState developmentStateInit = DevelopmentState(0, DateTime.now(), 0, 0, 0, 0, 0, 0, 0);

class DevelopmentState extends Development {
  DevelopmentState(
      int id,
      DateTime date,
      double nutritionScore,
      double fitnessScore,
      double weight,
      double bmi,
      double lbm,
      double pbf,
      int adjustProgramId)
      : super(id, date, nutritionScore, fitnessScore, weight, bmi, lbm, pbf,
            adjustProgramId);
}
