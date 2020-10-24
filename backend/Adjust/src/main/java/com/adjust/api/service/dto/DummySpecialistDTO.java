package com.adjust.api.service.dto;

import java.util.List;

public class DummySpecialistDTO extends SpecialistDTO {

    private List<DummyAdjustProgramDTO> adjustPrograms;

    private List<DummyConversationDTO> conversations;

    public DummySpecialistDTO() {
    }

    public DummySpecialistDTO(SpecialistDTO specialistDTO) {
        this.setGender(specialistDTO.getGender());
        this.setImageContentType(specialistDTO.getImageContentType());
        this.setImage(specialistDTO.getImage());
        this.setUsername(specialistDTO.getUsername());
        this.setStars(specialistDTO.getStars());
        this.setResume(specialistDTO.getResume());
        this.setLastName(specialistDTO.getLastName());
        this.setFirstName(specialistDTO.getFirstName());
        this.setField(specialistDTO.getField());
        this.setDegree(specialistDTO.getDegree());
        this.setBusy(specialistDTO.isBusy());
        this.setId(specialistDTO.getId());
        this.setBirthday(specialistDTO.getBirthday());
    }

    public List<DummyAdjustProgramDTO> getAdjustPrograms() {
        return adjustPrograms;
    }

    public void setAdjustPrograms(List<DummyAdjustProgramDTO> adjustPrograms) {
        this.adjustPrograms = adjustPrograms;
    }

    public List<DummyConversationDTO> getConversations() {
        return conversations;
    }

    public void setConversations(List<DummyConversationDTO> conversations) {
        this.conversations = conversations;
    }
}
