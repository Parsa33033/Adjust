package com.adjust.api.service.dto;

public class DummyDiseaseDTO extends DiseaseDTO {

    public DummyDiseaseDTO() {
    }

    public DummyDiseaseDTO(DiseaseDTO diseaseDTO) {
        this.setId(diseaseDTO.getId());
        this.setCarbsPercentage(diseaseDTO.getCarbsPercentage());
        this.setFatPercentage(diseaseDTO.getFatPercentage());
        this.setName(diseaseDTO.getName());
        this.setProteinPercentage(diseaseDTO.getProteinPercentage());
    }
}
