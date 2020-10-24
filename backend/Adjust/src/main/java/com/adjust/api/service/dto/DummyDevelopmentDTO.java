package com.adjust.api.service.dto;

public class DummyDevelopmentDTO extends DevelopmentDTO {

    public DummyDevelopmentDTO() {
    }

    public DummyDevelopmentDTO(DevelopmentDTO developmentDTO) {
        this.setId(developmentDTO.getId());
        this.setAdjustProgramId(developmentDTO.getAdjustProgramId());
        this.setBmi(developmentDTO.getBmi());
        this.setDate(developmentDTO.getDate());
        this.setFitnessScore(developmentDTO.getFitnessScore());
        this.setLbm(developmentDTO.getLbm());
        this.setNutritionScore(developmentDTO.getNutritionScore());
        this.setPbf(developmentDTO.getPbf());
        this.setWeight(developmentDTO.getWeight());
    }
}
