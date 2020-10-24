package com.adjust.api.service.dto;

import java.util.List;

public class DummyCourseDTO extends CourseDTO {

    private List<DummyTutorialDTO> tutorials;

    public DummyCourseDTO() {
    }

    public DummyCourseDTO(CourseDTO courseDTO) {
        this.setId(courseDTO.getId());
        this.setDescription(courseDTO.getDescription());
        this.setTitle(courseDTO.getTitle());
        this.setThumbnail(courseDTO.getThumbnail());
        this.setThumbnailContentType(courseDTO.getThumbnailContentType());
    }

    public List<DummyTutorialDTO> getTutorials() {
        return tutorials;
    }

    public void setTutorials(List<DummyTutorialDTO> tutorials) {
        this.tutorials = tutorials;
    }
}
