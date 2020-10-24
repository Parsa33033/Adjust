package com.adjust.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;

import com.adjust.api.domain.enumeration.MealType;

/**
 * A MealsForNutrition.
 */
@Entity
@Table(name = "meals_for_nutrition")
public class MealsForNutrition implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "meal_type")
    private MealType mealType;

    @ManyToOne
    @JsonIgnoreProperties(value = "mealTypes", allowSetters = true)
    private Nutrition nutrition;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MealType getMealType() {
        return mealType;
    }

    public MealsForNutrition mealType(MealType mealType) {
        this.mealType = mealType;
        return this;
    }

    public void setMealType(MealType mealType) {
        this.mealType = mealType;
    }

    public Nutrition getNutrition() {
        return nutrition;
    }

    public MealsForNutrition nutrition(Nutrition nutrition) {
        this.nutrition = nutrition;
        return this;
    }

    public void setNutrition(Nutrition nutrition) {
        this.nutrition = nutrition;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MealsForNutrition)) {
            return false;
        }
        return id != null && id.equals(((MealsForNutrition) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MealsForNutrition{" +
            "id=" + getId() +
            ", mealType='" + getMealType() + "'" +
            "}";
    }
}
