package com.adjust.api.service.dto;

public class DummyFoodDTO extends FoodDTO {

    public DummyFoodDTO() {
    }

    public DummyFoodDTO(FoodDTO foodDTO) {
        this.setId(foodDTO.getId());
        this.setDescription(foodDTO.getDescription());
        this.setName(foodDTO.getName());
        this.setNutritionId(foodDTO.getNutritionId());
    }
}
