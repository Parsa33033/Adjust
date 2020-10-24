package com.adjust.api.service.dto;

import java.time.LocalDate;
import java.io.Serializable;

/**
 * A DTO for the {@link com.adjust.api.domain.Development} entity.
 */
public class DevelopmentDTO implements Serializable {
    
    private Long id;

    private LocalDate date;

    private Double nutritionScore;

    private Double fitnessScore;

    private Double weight;

    private Double bmi;

    private Double lbm;

    private Double pbf;


    private Long adjustProgramId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getNutritionScore() {
        return nutritionScore;
    }

    public void setNutritionScore(Double nutritionScore) {
        this.nutritionScore = nutritionScore;
    }

    public Double getFitnessScore() {
        return fitnessScore;
    }

    public void setFitnessScore(Double fitnessScore) {
        this.fitnessScore = fitnessScore;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getBmi() {
        return bmi;
    }

    public void setBmi(Double bmi) {
        this.bmi = bmi;
    }

    public Double getLbm() {
        return lbm;
    }

    public void setLbm(Double lbm) {
        this.lbm = lbm;
    }

    public Double getPbf() {
        return pbf;
    }

    public void setPbf(Double pbf) {
        this.pbf = pbf;
    }

    public Long getAdjustProgramId() {
        return adjustProgramId;
    }

    public void setAdjustProgramId(Long adjustProgramId) {
        this.adjustProgramId = adjustProgramId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DevelopmentDTO)) {
            return false;
        }

        return id != null && id.equals(((DevelopmentDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DevelopmentDTO{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", nutritionScore=" + getNutritionScore() +
            ", fitnessScore=" + getFitnessScore() +
            ", weight=" + getWeight() +
            ", bmi=" + getBmi() +
            ", lbm=" + getLbm() +
            ", pbf=" + getPbf() +
            ", adjustProgramId=" + getAdjustProgramId() +
            "}";
    }
}
