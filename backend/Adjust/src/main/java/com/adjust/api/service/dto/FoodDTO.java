package com.adjust.api.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.adjust.api.domain.Food} entity.
 */
public class FoodDTO implements Serializable {
    
    private Long id;

    private String name;

    private String description;


    private Long nutritionId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!(o instanceof FoodDTO)) {
            return false;
        }

        return id != null && id.equals(((FoodDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FoodDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", nutritionId=" + getNutritionId() +
            "}";
    }
}
