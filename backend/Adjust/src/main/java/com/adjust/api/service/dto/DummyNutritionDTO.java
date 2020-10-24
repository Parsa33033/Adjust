package com.adjust.api.service.dto;

import java.util.List;

public class DummyNutritionDTO extends NutritionDTO {

    private List<DummyMealsForNutritionDTO> mealTypes;

    private List<DummyFoodDTO> foods;

    public DummyNutritionDTO() {
    }

    public DummyNutritionDTO(NutritionDTO nutritionDTO) {
        this.setDescription(nutritionDTO.getDescription());
        this.setProteinPerUnit(nutritionDTO.getProteinPerUnit());
        this.setName(nutritionDTO.getName());
        this.setFatInUnit(nutritionDTO.getFatInUnit());
        this.setCarbsPerUnit(nutritionDTO.getCarbsPerUnit());
        this.setCaloriesPerUnit(nutritionDTO.getCaloriesPerUnit());
        this.setId(nutritionDTO.getId());
        this.setCaloryBoundryMax(nutritionDTO.getCaloryBoundryMax());
        this.setCaloryBoundryMin(nutritionDTO.getCaloryBoundryMin());
        this.setMinUnitLow(nutritionDTO.getMinUnitLow());
        this.setMinUnitMax(nutritionDTO.getMinUnitMax());
        this.setMinUnitNormal(nutritionDTO.getMinUnitNormal());
        this.setUnit(nutritionDTO.getUnit());
    }

    public List<DummyMealsForNutritionDTO> getMealTypes() {
        return mealTypes;
    }

    public void setMealTypes(List<DummyMealsForNutritionDTO> mealTypes) {
        this.mealTypes = mealTypes;
    }

    public List<DummyFoodDTO> getFoods() {
        return foods;
    }

    public void setFoods(List<DummyFoodDTO> foods) {
        this.foods = foods;
    }
}
