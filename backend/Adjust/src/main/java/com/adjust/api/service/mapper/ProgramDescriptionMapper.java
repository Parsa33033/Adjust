package com.adjust.api.service.mapper;


import com.adjust.api.domain.*;
import com.adjust.api.service.dto.ProgramDescriptionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProgramDescription} and its DTO {@link ProgramDescriptionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProgramDescriptionMapper extends EntityMapper<ProgramDescriptionDTO, ProgramDescription> {



    default ProgramDescription fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProgramDescription programDescription = new ProgramDescription();
        programDescription.setId(id);
        return programDescription;
    }
}
