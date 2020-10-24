package com.adjust.api.service.dto;

import java.io.Serializable;
import com.adjust.api.domain.enumeration.MealType;

/**
 * A DTO for the {@link com.adjust.api.domain.MealsForNutrition} entity.
 */
public class MealsForNutritionDTO implements Serializable {
    
    private Long id;

    private MealType mealType;


    private Long nutritionId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MealType getMealType() {
        return mealType;
    }

    public void setMealType(MealType mealType) {
        this.mealType = mealType;
    }

    public Long getNutritionId() {
        return nutritionId;
    }

    public void setNutritionId(Long nutritionId) {
        this.nutritionId = nutritionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MealsForNutritionDTO)) {
            return false;
        }

        return id != null && id.equals(((MealsForNutritionDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MealsForNutritionDTO{" +
            "id=" + getId() +
            ", mealType='" + getMealType() + "'" +
            ", nutritionId=" + getNutritionId() +
            "}";
    }
}
