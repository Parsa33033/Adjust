package com.adjust.api.service.dto;

public class DummyTutorialDTO extends TutorialDTO {

    public DummyTutorialDTO() {
    }

    public DummyTutorialDTO(TutorialDTO tutorialDTO) {
        this.setId(tutorialDTO.getId());
        this.setCourseId(tutorialDTO.getCourseId());
        this.setDescription(tutorialDTO.getDescription());
        this.setText(tutorialDTO.getText());
        this.setThumbnail(tutorialDTO.getThumbnail());
        this.setThumbnailContentType(tutorialDTO.getThumbnailContentType());
        this.setTitle(tutorialDTO.getTitle());
        this.setTokenPrice(tutorialDTO.getTokenPrice());
        this.setVideoId(tutorialDTO.getVideoId());
    }
}
