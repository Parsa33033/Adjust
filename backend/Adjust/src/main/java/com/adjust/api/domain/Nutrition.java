package com.adjust.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Nutrition.
 */
@Entity
@Table(name = "nutrition")
public class Nutrition implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "unit")
    private Integer unit;

    @Column(name = "min_unit_low")
    private Integer minUnitLow;

    @Column(name = "min_unit_normal")
    private Integer minUnitNormal;

    @Column(name = "min_unit_max")
    private Integer minUnitMax;

    @Column(name = "calory_boundry_min")
    private Integer caloryBoundryMin;

    @Column(name = "calory_boundry_max")
    private Integer caloryBoundryMax;

    @Column(name = "calories_per_unit")
    private Integer caloriesPerUnit;

    @Column(name = "protein_per_unit")
    private Integer proteinPerUnit;

    @Column(name = "carbs_per_unit")
    private Integer carbsPerUnit;

    @Column(name = "fat_in_unit")
    private Integer fatInUnit;

    @OneToMany(mappedBy = "nutrition", fetch = FetchType.EAGER)
    private Set<MealsForNutrition> mealTypes = new HashSet<>();

    @OneToMany(mappedBy = "nutrition", fetch = FetchType.EAGER)
    private Set<Food> foods = new HashSet<>();

    @ManyToMany(mappedBy = "nutritions", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Meal> meals = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Nutrition name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Nutrition description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getUnit() {
        return unit;
    }

    public Nutrition unit(Integer unit) {
        this.unit = unit;
        return this;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Integer getMinUnitLow() {
        return minUnitLow;
    }

    public Nutrition minUnitLow(Integer minUnitLow) {
        this.minUnitLow = minUnitLow;
        return this;
    }

    public void setMinUnitLow(Integer minUnitLow) {
        this.minUnitLow = minUnitLow;
    }

    public Integer getMinUnitNormal() {
        return minUnitNormal;
    }

    public Nutrition minUnitNormal(Integer minUnitNormal) {
        this.minUnitNormal = minUnitNormal;
        return this;
    }

    public void setMinUnitNormal(Integer minUnitNormal) {
        this.minUnitNormal = minUnitNormal;
    }

    public Integer getMinUnitMax() {
        return minUnitMax;
    }

    public Nutrition minUnitMax(Integer minUnitMax) {
        this.minUnitMax = minUnitMax;
        return this;
    }

    public void setMinUnitMax(Integer minUnitMax) {
        this.minUnitMax = minUnitMax;
    }

    public Integer getCaloryBoundryMin() {
        return caloryBoundryMin;
    }

    public Nutrition caloryBoundryMin(Integer caloryBoundryMin) {
        this.caloryBoundryMin = caloryBoundryMin;
        return this;
    }

    public void setCaloryBoundryMin(Integer caloryBoundryMin) {
        this.caloryBoundryMin = caloryBoundryMin;
    }

    public Integer getCaloryBoundryMax() {
        return caloryBoundryMax;
    }

    public Nutrition caloryBoundryMax(Integer caloryBoundryMax) {
        this.caloryBoundryMax = caloryBoundryMax;
        return this;
    }

    public void setCaloryBoundryMax(Integer caloryBoundryMax) {
        this.caloryBoundryMax = caloryBoundryMax;
    }

    public Integer getCaloriesPerUnit() {
        return caloriesPerUnit;
    }

    public Nutrition caloriesPerUnit(Integer caloriesPerUnit) {
        this.caloriesPerUnit = caloriesPerUnit;
        return this;
    }

    public void setCaloriesPerUnit(Integer caloriesPerUnit) {
        this.caloriesPerUnit = caloriesPerUnit;
    }

    public Integer getProteinPerUnit() {
        return proteinPerUnit;
    }

    public Nutrition proteinPerUnit(Integer proteinPerUnit) {
        this.proteinPerUnit = proteinPerUnit;
        return this;
    }

    public void setProteinPerUnit(Integer proteinPerUnit) {
        this.proteinPerUnit = proteinPerUnit;
    }

    public Integer getCarbsPerUnit() {
        return carbsPerUnit;
    }

    public Nutrition carbsPerUnit(Integer carbsPerUnit) {
        this.carbsPerUnit = carbsPerUnit;
        return this;
    }

    public void setCarbsPerUnit(Integer carbsPerUnit) {
        this.carbsPerUnit = carbsPerUnit;
    }

    public Integer getFatInUnit() {
        return fatInUnit;
    }

    public Nutrition fatInUnit(Integer fatInUnit) {
        this.fatInUnit = fatInUnit;
        return this;
    }

    public void setFatInUnit(Integer fatInUnit) {
        this.fatInUnit = fatInUnit;
    }

    public Set<MealsForNutrition> getMealTypes() {
        return mealTypes;
    }

    public Nutrition mealTypes(Set<MealsForNutrition> mealsForNutritions) {
        this.mealTypes = mealsForNutritions;
        return this;
    }

    public Nutrition addMealTypes(MealsForNutrition mealsForNutrition) {
        this.mealTypes.add(mealsForNutrition);
        mealsForNutrition.setNutrition(this);
        return this;
    }

    public Nutrition removeMealTypes(MealsForNutrition mealsForNutrition) {
        this.mealTypes.remove(mealsForNutrition);
        mealsForNutrition.setNutrition(null);
        return this;
    }

    public void setMealTypes(Set<MealsForNutrition> mealsForNutritions) {
        this.mealTypes = mealsForNutritions;
    }

    public Set<Food> getFoods() {
        return foods;
    }

    public Nutrition foods(Set<Food> foods) {
        this.foods = foods;
        return this;
    }

    public Nutrition addFoods(Food food) {
        this.foods.add(food);
        food.setNutrition(this);
        return this;
    }

    public Nutrition removeFoods(Food food) {
        this.foods.remove(food);
        food.setNutrition(null);
        return this;
    }

    public void setFoods(Set<Food> foods) {
        this.foods = foods;
    }

    public Set<Meal> getMeals() {
        return meals;
    }

    public Nutrition meals(Set<Meal> meals) {
        this.meals = meals;
        return this;
    }

    public Nutrition addMeals(Meal meal) {
        this.meals.add(meal);
        meal.getNutritions().add(this);
        return this;
    }

    public Nutrition removeMeals(Meal meal) {
        this.meals.remove(meal);
        meal.getNutritions().remove(this);
        return this;
    }

    public void setMeals(Set<Meal> meals) {
        this.meals = meals;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Nutrition)) {
            return false;
        }
        return id != null && id.equals(((Nutrition) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Nutrition{" +
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
