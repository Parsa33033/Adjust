package com.adjust.api.service.dto;

import java.util.List;

public class DummyAdjustProgramDTO extends AdjustProgramDTO {

    private DummyBodyCompositionDTO bodyComposition;

    private DummyFitnessProgramDTO fitnessProgram;

    private DummyNutritionProgramDTO nutritionProgram;

    private List<DummyDevelopmentDTO> developments;

    private DummyAdjustClientDTO adjustClient;

    private DummySpecialistDTO specialist;

    public DummyAdjustProgramDTO() {
    }

    public DummyAdjustProgramDTO(AdjustProgramDTO adjustProgramDTO) {
        this.setFitnessDone(adjustProgramDTO.isFitnessDone());
        this.setNutritionDone(adjustProgramDTO.isNutritionDone());
        this.setSpecialistId(adjustProgramDTO.getSpecialistId());
        this.setExpirationDate(adjustProgramDTO.getExpirationDate());
        this.setPaid(adjustProgramDTO.isPaid());
        this.setDesigned(adjustProgramDTO.isDesigned());
        this.setCreatedAt(adjustProgramDTO.getCreatedAt());
        this.setFitnessProgramId(adjustProgramDTO.getFitnessProgramId());
        this.setNutritionProgramId(adjustProgramDTO.getNutritionProgramId());
        this.setId(adjustProgramDTO.getId());
        this.setAdjustClientId(adjustProgramDTO.getAdjustClientId());
        this.setBodyCompositionDone(adjustProgramDTO.isBodyCompositionDone());
        this.setBodyCompositionId(adjustProgramDTO.getBodyCompositionId());
        this.setMedicalProfileDone(adjustProgramDTO.isMedicalProfileDone());
        this.setSeenByClient(adjustProgramDTO.isSeenByClient());
        this.setSeenBySpecialist(adjustProgramDTO.isSeenBySpecialist());
    }

    public DummyBodyCompositionDTO getBodyComposition() {
        return bodyComposition;
    }

    public void setBodyComposition(DummyBodyCompositionDTO bodyComposition) {
        this.bodyComposition = bodyComposition;
    }

    public DummyFitnessProgramDTO getFitnessProgram() {
        return fitnessProgram;
    }

    public void setFitnessProgram(DummyFitnessProgramDTO fitnessProgram) {
        this.fitnessProgram = fitnessProgram;
    }

    public DummyNutritionProgramDTO getNutritionProgram() {
        return nutritionProgram;
    }

    public void setNutritionProgram(DummyNutritionProgramDTO nutritionProgram) {
        this.nutritionProgram = nutritionProgram;
    }

    public List<DummyDevelopmentDTO> getDevelopments() {
        return developments;
    }

    public void setDevelopments(List<DummyDevelopmentDTO> developments) {
        this.developments = developments;
    }

    public DummyAdjustClientDTO getAdjustClient() {
        return adjustClient;
    }

    public void setAdjustClient(DummyAdjustClientDTO adjustClient) {
        this.adjustClient = adjustClient;
    }

    public DummySpecialistDTO getSpecialist() {
        return specialist;
    }

    public void setSpecialist(DummySpecialistDTO specialist) {
        this.specialist = specialist;
    }
}
