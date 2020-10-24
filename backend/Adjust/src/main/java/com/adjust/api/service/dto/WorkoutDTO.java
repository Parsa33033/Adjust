package com.adjust.api.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.adjust.api.domain.Workout} entity.
 */
public class WorkoutDTO implements Serializable {
    
    private Long id;

    private Integer dayNumber;


    private Long fitnessProgramId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(Integer dayNumber) {
        this.dayNumber = dayNumber;
    }

    public Long getFitnessProgramId() {
        return fitnessProgramId;
    }

    public void setFitnessProgramId(Long fitnessProgramId) {
        this.fitnessProgramId = fitnessProgramId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WorkoutDTO)) {
            return false;
        }

        return id != null && id.equals(((WorkoutDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WorkoutDTO{" +
            "id=" + getId() +
            ", dayNumber=" + getDayNumber() +
            ", fitnessProgramId=" + getFitnessProgramId() +
            "}";
    }
}
