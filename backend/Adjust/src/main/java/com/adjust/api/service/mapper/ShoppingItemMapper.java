package com.adjust.api.service.mapper;


import com.adjust.api.domain.*;
import com.adjust.api.service.dto.ShoppingItemDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ShoppingItem} and its DTO {@link ShoppingItemDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ShoppingItemMapper extends EntityMapper<ShoppingItemDTO, ShoppingItem> {



    default ShoppingItem fromId(Long id) {
        if (id == null) {
            return null;
        }
        ShoppingItem shoppingItem = new ShoppingItem();
        shoppingItem.setId(id);
        return shoppingItem;
    }
}
