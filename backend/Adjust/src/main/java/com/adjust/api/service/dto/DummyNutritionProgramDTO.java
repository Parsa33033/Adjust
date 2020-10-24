package com.adjust.api.service.dto;

import java.util.List;

public class DummyNutritionProgramDTO extends NutritionProgramDTO {

    private List<DummyMealDTO> meals;

    public DummyNutritionProgramDTO() {
    }

    public DummyNutritionProgramDTO(NutritionProgramDTO nutritionProgramDTO) {
        this.setDescription(nutritionProgramDTO.getDescription());
        this.setProteinPercentage(nutritionProgramDTO.getProteinPercentage());
        this.setFatPercentage(nutritionProgramDTO.getFatPercentage());
        this.setDailyCalories(nutritionProgramDTO.getDailyCalories());
        this.setCarbsPercentage(nutritionProgramDTO.getCarbsPercentage());
        this.setId(nutritionProgramDTO.getId());
    }

    public List<DummyMealDTO> getMeals() {
        return meals;
    }

    public void setMeals(List<DummyMealDTO> meals) {
        this.meals = meals;
    }
}
