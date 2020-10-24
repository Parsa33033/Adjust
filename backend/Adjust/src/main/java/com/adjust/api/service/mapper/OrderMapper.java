package com.adjust.api.service.mapper;


import com.adjust.api.domain.*;
import com.adjust.api.service.dto.OrderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Order} and its DTO {@link OrderDTO}.
 */
@Mapper(componentModel = "spring", uses = {AdjustClientMapper.class})
public interface OrderMapper extends EntityMapper<OrderDTO, Order> {

    @Mapping(source = "adjustClient.id", target = "adjustClientId")
    OrderDTO toDto(Order order);

    @Mapping(target = "orderItems", ignore = true)
    @Mapping(target = "removeOrderItems", ignore = true)
    @Mapping(source = "adjustClientId", target = "adjustClient")
    Order toEntity(OrderDTO orderDTO);

    default Order fromId(Long id) {
        if (id == null) {
            return null;
        }
        Order order = new Order();
        order.setId(id);
        return order;
    }
}
