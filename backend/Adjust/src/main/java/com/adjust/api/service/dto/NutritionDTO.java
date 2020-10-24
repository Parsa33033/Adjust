package com.adjust.api.service.dto;

import java.io.Serializable;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.adjust.api.domain.Nutrition} entity.
 */
public class NutritionDTO implements Serializable {
    
    private Long id;

    private String name;

    @Lob
    private String description;

    private Integer unit;

    private Integer minUnitLow;

    private Integer minUnitNormal;

    private Integer minUnitMax;

    private Integer caloryBoundryMin;

    private Integer caloryBoundryMax;

    private Integer caloriesPerUnit;

    private Integer proteinPerUnit;

    private Integer carbsPerUnit;

    private Integer fatInUnit;

    
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

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Integer getMinUnitLow() {
        return minUnitLow;
    }

    public void setMinUnitLow(Integer minUnitLow) {
        this.minUnitLow = minUnitLow;
    }

    public Integer getMinUnitNormal() {
        return minUnitNormal;
    }

    public void setMinUnitNormal(Integer minUnitNormal) {
        this.minUnitNormal = minUnitNormal;
    }

    public Integer getMinUnitMax() {
        return minUnitMax;
    }

    public void setMinUnitMax(Integer minUnitMax) {
        this.minUnitMax = minUnitMax;
    }

    public Integer getCaloryBoundryMin() {
        return caloryBoundryMin;
    }

    public void setCaloryBoundryMin(Integer caloryBoundryMin) {
        this.caloryBoundryMin = caloryBoundryMin;
    }

    public Integer getCaloryBoundryMax() {
        return caloryBoundryMax;
    }

    public void setCaloryBoundryMax(Integer caloryBoundryMax) {
        this.caloryBoundryMax = caloryBoundryMax;
    }

    public Integer getCaloriesPerUnit() {
        return caloriesPerUnit;
    }

    public void setCaloriesPerUnit(Integer caloriesPerUnit) {
        this.caloriesPerUnit = caloriesPerUnit;
    }

    public Integer getProteinPerUnit() {
        return proteinPerUnit;
    }

    public void setProteinPerUnit(Integer proteinPerUnit) {
        this.proteinPerUnit = proteinPerUnit;
    }

    public Integer getCarbsPerUnit() {
        return carbsPerUnit;
    }

    public void setCarbsPerUnit(Integer carbsPerUnit) {
        this.carbsPerUnit = carbsPerUnit;
    }

    public Integer getFatInUnit() {
        return fatInUnit;
    }

    public void setFatInUnit(Integer fatInUnit) {
        this.fatInUnit = fatInUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NutritionDTO)) {
            return false;
        }

        return id != null && id.equals(((NutritionDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NutritionDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", unit=" + getUnit() +
            ", minUnitLow=" + getMinUnitLow() +
            ", minUnitNormal=" + getMinUnitNormal() +
            ", minUnitMax=" + getMinUnitMax() +
            ", caloryBoundryMin=" + getCaloryBoundryMin() +
            ", caloryBoundryMax=" + getCaloryBoundryMax() +
            ", caloriesPerUnit=" + getCaloriesPerUnit() +
            ", proteinPerUnit=" + getProteinPerUnit() +
            ", carbsPerUnit=" + getCarbsPerUnit() +
            ", fatInUnit=" + getFatInUnit() +
            "}";
    }
}
