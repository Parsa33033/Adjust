package com.adjust.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.adjust.api.domain.enumeration.MealType;

/**
 * A Meal.
 */
@Entity
@Table(name = "meal")
public class Meal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "meal_type")
    private MealType mealType;

    @Column(name = "number")
    private Integer number;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "meal_nutritions",
               joinColumns = @JoinColumn(name = "meal_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "nutritions_id", referencedColumnName = "id"))
    private Set<Nutrition> nutritions = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "meals", allowSetters = true)
    private NutritionProgram nutritionProgram;

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

    public Meal mealType(MealType mealType) {
        this.mealType = mealType;
        return this;
    }

    public void setMealType(MealType mealType) {
        this.mealType = mealType;
    }

    public Integer getNumber() {
        return number;
    }

    public Meal number(Integer number) {
        this.number = number;
        return this;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Set<Nutrition> getNutritions() {
        return nutritions;
    }

    public Meal nutritions(Set<Nutrition> nutritions) {
        this.nutritions = nutritions;
        return this;
    }

    public Meal addNutritions(Nutrition nutrition) {
        this.nutritions.add(nutrition);
        nutrition.getMeals().add(this);
        return this;
    }

    public Meal removeNutritions(Nutrition nutrition) {
        this.nutritions.remove(nutrition);
        nutrition.getMeals().remove(this);
        return this;
    }

    public void setNutritions(Set<Nutrition> nutritions) {
        this.nutritions = nutritions;
    }

    public NutritionProgram getNutritionProgram() {
        return nutritionProgram;
    }

    public Meal nutritionProgram(NutritionProgram nutritionProgram) {
        this.nutritionProgram = nutritionProgram;
        return this;
    }

    public void setNutritionProgram(NutritionProgram nutritionProgram) {
        this.nutritionProgram = nutritionProgram;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Meal)) {
            return false;
        }
        return id != null && id.equals(((Meal) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Meal{" +
            "id=" + getId() +
            ", mealType='" + getMealType() + "'" +
            ", number=" + getNumber() +
            "}";
    }
}
