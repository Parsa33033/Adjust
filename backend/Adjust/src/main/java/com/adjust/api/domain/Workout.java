package com.adjust.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Workout.
 */
@Entity
@Table(name = "workout")
public class Workout implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "day_number")
    private Integer dayNumber;

    @OneToMany(mappedBy = "workout", fetch = FetchType.EAGER)
    private Set<Exercise> exercises = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "workouts", allowSetters = true)
    private FitnessProgram fitnessProgram;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDayNumber() {
        return dayNumber;
    }

    public Workout dayNumber(Integer dayNumber) {
        this.dayNumber = dayNumber;
        return this;
    }

    public void setDayNumber(Integer dayNumber) {
        this.dayNumber = dayNumber;
    }

    public Set<Exercise> getExercises() {
        return exercises;
    }

    public Workout exercises(Set<Exercise> exercises) {
        this.exercises = exercises;
        return this;
    }

    public Workout addExercises(Exercise exercise) {
        this.exercises.add(exercise);
        exercise.setWorkout(this);
        return this;
    }

    public Workout removeExercises(Exercise exercise) {
        this.exercises.remove(exercise);
        exercise.setWorkout(null);
        return this;
    }

    public void setExercises(Set<Exercise> exercises) {
        this.exercises = exercises;
    }

    public FitnessProgram getFitnessProgram() {
        return fitnessProgram;
    }

    public Workout fitnessProgram(FitnessProgram fitnessProgram) {
        this.fitnessProgram = fitnessProgram;
        return this;
    }

    public void setFitnessProgram(FitnessProgram fitnessProgram) {
        this.fitnessProgram = fitnessProgram;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Workout)) {
            return false;
        }
        return id != null && id.equals(((Workout) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Workout{" +
            "id=" + getId() +
            ", dayNumber=" + getDayNumber() +
            "}";
    }
}
