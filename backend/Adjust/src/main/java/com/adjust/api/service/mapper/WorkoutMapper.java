package com.adjust.api.service.mapper;


import com.adjust.api.domain.*;
import com.adjust.api.service.dto.WorkoutDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Workout} and its DTO {@link WorkoutDTO}.
 */
@Mapper(componentModel = "spring", uses = {FitnessProgramMapper.class})
public interface WorkoutMapper extends EntityMapper<WorkoutDTO, Workout> {

    @Mapping(source = "fitnessProgram.id", target = "fitnessProgramId")
    WorkoutDTO toDto(Workout workout);

    @Mapping(target = "exercises", ignore = true)
    @Mapping(target = "removeExercises", ignore = true)
    @Mapping(source = "fitnessProgramId", target = "fitnessProgram")
    Workout toEntity(WorkoutDTO workoutDTO);

    default Workout fromId(Long id) {
        if (id == null) {
            return null;
        }
        Workout workout = new Workout();
        workout.setId(id);
        return workout;
    }
}
