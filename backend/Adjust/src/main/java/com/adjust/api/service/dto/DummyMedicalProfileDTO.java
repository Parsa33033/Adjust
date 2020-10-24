package com.adjust.api.service.dto;
import java.util.List;

public class DummyMedicalProfileDTO extends MedicalProfileDTO {

    private List<DummyDiseaseDTO> diseaseList;
    private DummyBodyCompositionDTO bodyComposition;

    public DummyMedicalProfileDTO() {
    }

    public DummyMedicalProfileDTO(MedicalProfileDTO medicalProfileDTO) {
        this.setId(medicalProfileDTO.getId());
        this.setAllergy(medicalProfileDTO.getAllergy());
        this.setBloodType(medicalProfileDTO.getBloodType());
        this.setBodyCompositionId(medicalProfileDTO.getBodyCompositionId());
        this.setChildrenNumber(medicalProfileDTO.getChildrenNumber());
        this.setCity(medicalProfileDTO.getCity());
        this.setDrowsiness(medicalProfileDTO.isDrowsiness());
        this.setEducation(medicalProfileDTO.getEducation());
        this.setFinancialStatus(medicalProfileDTO.getFinancialStatus());
        this.setGoodNightSleep(medicalProfileDTO.isGoodNightSleep());
        this.setHaveAllergy(medicalProfileDTO.isHaveAllergy());
        this.setHaveDisease(medicalProfileDTO.isHaveDisease());
        this.setHaveLostWeight(medicalProfileDTO.isHaveLostWeight());
        this.setHoursOfTraningPerWeek(medicalProfileDTO.getHoursOfTraningPerWeek());
        this.setHoursOfWorkDaily(medicalProfileDTO.getHoursOfWorkDaily());
        this.setLoveForSugar(medicalProfileDTO.isLoveForSugar());
        this.setMaritalStatus(medicalProfileDTO.getMaritalStatus());
        this.setMedicines(medicalProfileDTO.getMedicines());
        this.setOccupation(medicalProfileDTO.getOccupation());
        this.setOverEating(medicalProfileDTO.isOverEating());
        this.setUseOfMedicine(medicalProfileDTO.isUseOfMedicine());
    }

    public List<DummyDiseaseDTO> getDiseaseList() {
        return diseaseList;
    }

    public void setDiseaseList(List<DummyDiseaseDTO> diseaseList) {
        this.diseaseList = diseaseList;
    }

    public DummyBodyCompositionDTO getBodyComposition() {
        return bodyComposition;
    }

    public void setBodyComposition(DummyBodyCompositionDTO bodyComposition) {
        this.bodyComposition = bodyComposition;
    }
}
