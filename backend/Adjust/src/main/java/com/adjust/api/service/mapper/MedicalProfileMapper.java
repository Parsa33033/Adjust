package com.adjust.api.service.mapper;


import com.adjust.api.domain.*;
import com.adjust.api.service.dto.MedicalProfileDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MedicalProfile} and its DTO {@link MedicalProfileDTO}.
 */
@Mapper(componentModel = "spring", uses = {BodyCompositionMapper.class, DiseaseMapper.class})
public interface MedicalProfileMapper extends EntityMapper<MedicalProfileDTO, MedicalProfile> {

    @Mapping(source = "bodyComposition.id", target = "bodyCompositionId")
    MedicalProfileDTO toDto(MedicalProfile medicalProfile);

    @Mapping(source = "bodyCompositionId", target = "bodyComposition")
    @Mapping(target = "removeDiseases", ignore = true)
    @Mapping(target = "adjustClient", ignore = true)
    MedicalProfile toEntity(MedicalProfileDTO medicalProfileDTO);

    default MedicalProfile fromId(Long id) {
        if (id == null) {
            return null;
        }
        MedicalProfile medicalProfile = new MedicalProfile();
        medicalProfile.setId(id);
        return medicalProfile;
    }
}
