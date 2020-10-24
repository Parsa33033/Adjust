package com.adjust.api.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import com.adjust.api.domain.enumeration.MealType;

/**
 * A DTO for the {@link com.adjust.api.domain.Meal} entity.
 */
public class MealDTO implements Serializable {
    
    private Long id;

    private MealType mealType;

    private Integer number;

    private Set<NutritionDTO> nutritions = new HashSet<>();

    private Long nutritionProgramId;
    
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Set<NutritionDTO> getNutritions() {
        return nutritions;
    }

    public void setNutritions(Set<NutritionDTO> nutritions) {
        this.nutritions = nutritions;
    }

    public Long getNutritionProgramId() {
        return nutritionProgramId;
    }

    public void setNutritionProgramId(Long nutritionProgramId) {
        this.nutritionProgramId = nutritionProgramId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MealDTO)) {
            return false;
        }

        return id != null && id.equals(((MealDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MealDTO{" +
            "id=" + getId() +
            ", mealType='" + getMealType() + "'" +
            ", number=" + getNumber() +
            ", nutritions='" + getNutritions() + "'" +
            ", nutritionProgramId=" + getNutritionProgramId() +
            "}";
    }
}
