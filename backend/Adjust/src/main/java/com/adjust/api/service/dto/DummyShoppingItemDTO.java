package com.adjust.api.service.dto;

public class DummyShoppingItemDTO extends ShoppingItemDTO {

    public DummyShoppingItemDTO() {
    }

    public DummyShoppingItemDTO(ShoppingItemDTO shoppingItemDTO) {
        this.setImageContentType(shoppingItemDTO.getImageContentType());
        this.setImage(shoppingItemDTO.getImage());
        this.setPrice(shoppingItemDTO.getPrice());
        this.setOrderable(shoppingItemDTO.isOrderable());
        this.setName(shoppingItemDTO.getName());
        this.setDescription(shoppingItemDTO.getDescription());
        this.setId(shoppingItemDTO.getId());
        this.setItemId(shoppingItemDTO.getItemId());
        this.setNumber(shoppingItemDTO.getNumber());
    }
}
