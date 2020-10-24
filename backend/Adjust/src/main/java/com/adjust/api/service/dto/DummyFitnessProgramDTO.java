package com.adjust.api.service.dto;

import java.util.List;

public class DummyFitnessProgramDTO extends FitnessProgramDTO {

    private List<DummyWorkoutDTO> workouts;

    public DummyFitnessProgramDTO() {
    }

    public DummyFitnessProgramDTO(FitnessProgramDTO fitnessProgramDTO) {
        this.setType(fitnessProgramDTO.getType());
        this.setDescription(fitnessProgramDTO.getDescription());
        this.setId(fitnessProgramDTO.getId());
    }

    public List<DummyWorkoutDTO> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<DummyWorkoutDTO> workouts) {
        this.workouts = workouts;
    }
}
