package com.adjust.api.service.dto;

public class DummyExerciseDTO extends ExerciseDTO {

    private DummyMoveDTO move;

    public DummyExerciseDTO() {
    }

    public DummyExerciseDTO(ExerciseDTO exerciseDTO) {
        this.setMoveId(exerciseDTO.getMoveId());
        this.setWorkoutId(exerciseDTO.getWorkoutId());
        this.setSets(exerciseDTO.getSets());
        this.setRepsMin(exerciseDTO.getRepsMin());
        this.setRepsMax(exerciseDTO.getRepsMax());
        this.setNumber(exerciseDTO.getNumber());
        this.setId(exerciseDTO.getId());
    }

    public DummyMoveDTO getMove() {
        return move;
    }

    public void setMove(DummyMoveDTO move) {
        this.move = move;
    }
}
