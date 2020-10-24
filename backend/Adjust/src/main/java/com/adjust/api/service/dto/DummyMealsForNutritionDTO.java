package com.adjust.api.service.dto;

public class DummyMealsForNutritionDTO extends MealsForNutritionDTO {

    public DummyMealsForNutritionDTO() {
    }

    public DummyMealsForNutritionDTO(MealsForNutritionDTO mealsForNutritionDTO) {
        this.setId(mealsForNutritionDTO.getId());
        this.setMealType(mealsForNutritionDTO.getMealType());
        this.setNutritionId(mealsForNutritionDTO.getNutritionId());
    }
}
