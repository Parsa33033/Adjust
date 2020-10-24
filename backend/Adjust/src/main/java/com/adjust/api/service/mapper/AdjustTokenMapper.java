package com.adjust.api.service.mapper;


import com.adjust.api.domain.*;
import com.adjust.api.service.dto.AdjustTokenDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AdjustToken} and its DTO {@link AdjustTokenDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AdjustTokenMapper extends EntityMapper<AdjustTokenDTO, AdjustToken> {



    default AdjustToken fromId(Long id) {
        if (id == null) {
            return null;
        }
        AdjustToken adjustToken = new AdjustToken();
        adjustToken.setId(id);
        return adjustToken;
    }
}
