package com.adjust.api.service.mapper;


import com.adjust.api.domain.*;
import com.adjust.api.service.dto.DiseaseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Disease} and its DTO {@link DiseaseDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DiseaseMapper extends EntityMapper<DiseaseDTO, Disease> {


    @Mapping(target = "medicalProfiles", ignore = true)
    @Mapping(target = "removeMedicalProfiles", ignore = true)
    Disease toEntity(DiseaseDTO diseaseDTO);

    default Disease fromId(Long id) {
        if (id == null) {
            return null;
        }
        Disease disease = new Disease();
        disease.setId(id);
        return disease;
    }
}
