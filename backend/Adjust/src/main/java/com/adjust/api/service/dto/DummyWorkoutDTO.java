package com.adjust.api.service.dto;

import java.util.List;

public class DummyWorkoutDTO extends WorkoutDTO {

    private List<DummyExerciseDTO> exercises;

    public DummyWorkoutDTO() {
    }

    public DummyWorkoutDTO(WorkoutDTO workoutDTO) {
        this.setDayNumber(workoutDTO.getDayNumber());
        this.setId(workoutDTO.getId());
        this.setFitnessProgramId(workoutDTO.getFitnessProgramId());
    }

    public List<DummyExerciseDTO> getExercises() {
        return exercises;
    }

    public void setExercises(List<DummyExerciseDTO> exercises) {
        this.exercises = exercises;
    }
}
