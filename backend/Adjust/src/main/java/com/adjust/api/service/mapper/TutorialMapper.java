package com.adjust.api.service.mapper;


import com.adjust.api.domain.*;
import com.adjust.api.service.dto.TutorialDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Tutorial} and its DTO {@link TutorialDTO}.
 */
@Mapper(componentModel = "spring", uses = {TutorialVideoMapper.class, CourseMapper.class})
public interface TutorialMapper extends EntityMapper<TutorialDTO, Tutorial> {

    @Mapping(source = "video.id", target = "videoId")
    @Mapping(source = "course.id", target = "courseId")
    TutorialDTO toDto(Tutorial tutorial);

    @Mapping(source = "videoId", target = "video")
    @Mapping(source = "courseId", target = "course")
    @Mapping(target = "clients", ignore = true)
    @Mapping(target = "removeClient", ignore = true)
    Tutorial toEntity(TutorialDTO tutorialDTO);

    default Tutorial fromId(Long id) {
        if (id == null) {
            return null;
        }
        Tutorial tutorial = new Tutorial();
        tutorial.setId(id);
        return tutorial;
    }
}
