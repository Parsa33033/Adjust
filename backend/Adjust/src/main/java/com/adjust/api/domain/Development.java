package com.adjust.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Development.
 */
@Entity
@Table(name = "development")
public class Development implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "nutrition_score")
    private Double nutritionScore;

    @Column(name = "fitness_score")
    private Double fitnessScore;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "bmi")
    private Double bmi;

    @Column(name = "lbm")
    private Double lbm;

    @Column(name = "pbf")
    private Double pbf;

    @ManyToOne
    @JsonIgnoreProperties(value = "developments", allowSetters = true)
    private AdjustProgram adjustProgram;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Development date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getNutritionScore() {
        return nutritionScore;
    }

    public Development nutritionScore(Double nutritionScore) {
        this.nutritionScore = nutritionScore;
        return this;
    }

    public void setNutritionScore(Double nutritionScore) {
        this.nutritionScore = nutritionScore;
    }

    public Double getFitnessScore() {
        return fitnessScore;
    }

    public Development fitnessScore(Double fitnessScore) {
        this.fitnessScore = fitnessScore;
        return this;
    }

    public void setFitnessScore(Double fitnessScore) {
        this.fitnessScore = fitnessScore;
    }

    public Double getWeight() {
        return weight;
    }

    public Development weight(Double weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getBmi() {
        return bmi;
    }

    public Development bmi(Double bmi) {
        this.bmi = bmi;
        return this;
    }

    public void setBmi(Double bmi) {
        this.bmi = bmi;
    }

    public Double getLbm() {
        return lbm;
    }

    public Development lbm(Double lbm) {
        this.lbm = lbm;
        return this;
    }

    public void setLbm(Double lbm) {
        this.lbm = lbm;
    }

    public Double getPbf() {
        return pbf;
    }

    public Development pbf(Double pbf) {
        this.pbf = pbf;
        return this;
    }

    public void setPbf(Double pbf) {
        this.pbf = pbf;
    }

    public AdjustProgram getAdjustProgram() {
        return adjustProgram;
    }

    public Development adjustProgram(AdjustProgram adjustProgram) {
        this.adjustProgram = adjustProgram;
        return this;
    }

    public void setAdjustProgram(AdjustProgram adjustProgram) {
        this.adjustProgram = adjustProgram;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Development)) {
            return false;
        }
        return id != null && id.equals(((Development) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Development{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", nutritionScore=" + getNutritionScore() +
            ", fitnessScore=" + getFitnessScore() +
            ", weight=" + getWeight() +
            ", bmi=" + getBmi() +
            ", lbm=" + getLbm() +
            ", pbf=" + getPbf() +
            "}";
    }
}
