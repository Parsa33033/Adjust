package com.adjust.api.service.dto;

import java.util.List;

public class DummyMealDTO extends MealDTO {

    private List<DummyNutritionDTO> nutritionList;

    public DummyMealDTO() {
    }

    public DummyMealDTO(MealDTO mealDTO) {
        this.setNutritionProgramId(mealDTO.getNutritionProgramId());
        this.setNumber(mealDTO.getNumber());
        this.setId(mealDTO.getId());
        this.setMealType(mealDTO.getMealType());
    }

    public List<DummyNutritionDTO> getNutritionList() {
        return nutritionList;
    }

    public void setNutritionList(List<DummyNutritionDTO> nutritionList) {
        this.nutritionList = nutritionList;
    }
}
