package com.adjust.api.service.dto;

public class DummyOrderItemDTO extends OrderItemDTO {

    DummyShoppingItemDTO shoppingItem;

    public DummyOrderItemDTO() {
    }

    public DummyOrderItemDTO(OrderItemDTO orderItemDTO) {
        this.setId(orderItemDTO.getId());
        this.setNumber(orderItemDTO.getNumber());
        this.setShoppingItemId(orderItemDTO.getShoppingItemId());
        this.setOrderId(orderItemDTO.getOrderId());
    }

    public DummyShoppingItemDTO getShoppingItem() {
        return shoppingItem;
    }

    public void setShoppingItem(DummyShoppingItemDTO shoppingItem) {
        this.shoppingItem = shoppingItem;
    }
}
