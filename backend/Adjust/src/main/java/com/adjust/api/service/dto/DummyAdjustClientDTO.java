package com.adjust.api.service.dto;

import java.util.List;

public class DummyAdjustClientDTO extends AdjustClientDTO {
    private DummyMedicalProfileDTO medicalProfile;
    private List<DummyOrderDTO> orders;
    private List<DummyTutorialDTO> tutorialList;

    public DummyAdjustClientDTO() {
    }

    public DummyAdjustClientDTO(AdjustClientDTO adjustClientDTO) {
        this.setGender(adjustClientDTO.getGender());
        this.setImageContentType(adjustClientDTO.getImageContentType());
        this.setImage(adjustClientDTO.getImage());
        this.setScore(adjustClientDTO.getScore());
        this.setToken(adjustClientDTO.getToken());
        this.setAge(adjustClientDTO.getAge());
        this.setBirthDate(adjustClientDTO.getBirthDate());
        this.setLastName(adjustClientDTO.getLastName());
        this.setFirstName(adjustClientDTO.getFirstName());
        this.setUsername(adjustClientDTO.getUsername());
        this.setId(adjustClientDTO.getId());
        this.setMedicalProfileId(adjustClientDTO.getMedicalProfileId());
        this.setTutorials(adjustClientDTO.getTutorials());
    }

    public DummyMedicalProfileDTO getMedicalProfile() {
        return medicalProfile;
    }

    public void setMedicalProfile(DummyMedicalProfileDTO medicalProfile) {
        this.medicalProfile = medicalProfile;
    }

    public List<DummyOrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<DummyOrderDTO> orders) {
        this.orders = orders;
    }

    public List<DummyTutorialDTO> getTutorialList() {
        return tutorialList;
    }

    public void setTutorialList(List<DummyTutorialDTO> tutorialList) {
        this.tutorialList = tutorialList;
    }
}
