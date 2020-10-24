package com.adjust.api.service.mapper;


import com.adjust.api.domain.*;
import com.adjust.api.service.dto.AdjustClientDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AdjustClient} and its DTO {@link AdjustClientDTO}.
 */
@Mapper(componentModel = "spring", uses = {MedicalProfileMapper.class, TutorialMapper.class})
public interface AdjustClientMapper extends EntityMapper<AdjustClientDTO, AdjustClient> {

    @Mapping(source = "medicalProfile.id", target = "medicalProfileId")
    AdjustClientDTO toDto(AdjustClient adjustClient);

    @Mapping(source = "medicalProfileId", target = "medicalProfile")
    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "removeOrders", ignore = true)
    @Mapping(target = "adjustPrograms", ignore = true)
    @Mapping(target = "removeAdjustPrograms", ignore = true)
    @Mapping(target = "conversations", ignore = true)
    @Mapping(target = "removeConversations", ignore = true)
    @Mapping(target = "removeTutorials", ignore = true)
    AdjustClient toEntity(AdjustClientDTO adjustClientDTO);

    default AdjustClient fromId(Long id) {
        if (id == null) {
            return null;
        }
        AdjustClient adjustClient = new AdjustClient();
        adjustClient.setId(id);
        return adjustClient;
    }
}
