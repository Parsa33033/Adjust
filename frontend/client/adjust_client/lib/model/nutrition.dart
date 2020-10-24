
class Nutrition {

  int id;

  String name;

  String description;

  int unit;

  int adjustNutritionId;

  int minUnitLow;

  int minUnitNormal;

  int minUnitMax;

  int caloryBoundryMin;

  int caloryBoundryMax;

  int caloriesPerUnit;

  int proteinPerUnit;

  int carbsPerUnit;

  int fatInUnit;

  Nutrition(
      this.id,
      this.name,
      this.description,
      this.unit,
      this.adjustNutritionId,
      this.minUnitLow,
      this.minUnitNormal,
      this.minUnitMax,
      this.caloryBoundryMin,
      this.caloryBoundryMax,
      this.caloriesPerUnit,
      this.proteinPerUnit,
      this.carbsPerUnit,
      this.fatInUnit);
}