package com.adjust.api.service.mapper;


import com.adjust.api.domain.*;
import com.adjust.api.service.dto.DevelopmentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Development} and its DTO {@link DevelopmentDTO}.
 */
@Mapper(componentModel = "spring", uses = {AdjustProgramMapper.class})
public interface DevelopmentMapper extends EntityMapper<DevelopmentDTO, Development> {

    @Mapping(source = "adjustProgram.id", target = "adjustProgramId")
    DevelopmentDTO toDto(Development development);

    @Mapping(source = "adjustProgramId", target = "adjustProgram")
    Development toEntity(DevelopmentDTO developmentDTO);

    default Development fromId(Long id) {
        if (id == null) {
            return null;
        }
        Development development = new Development();
        development.setId(id);
        return development;
    }
}
