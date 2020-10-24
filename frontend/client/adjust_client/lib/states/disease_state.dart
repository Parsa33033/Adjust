import 'package:adjust_client/model/disease.dart';

class DiseaseState extends Disease {
  DiseaseState(int id, String name, int proteinPercentage, int carbsPercentage,
      int fatPercentage)
      : super(id, name, proteinPercentage, carbsPercentage, fatPercentage);
}
