package com.adjust.api.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.adjust.api.domain.OrderItem} entity.
 */
public class OrderItemDTO implements Serializable {
    
    private Long id;

    private Integer number;


    private Long shoppingItemId;

    private Long orderId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Long getShoppingItemId() {
        return shoppingItemId;
    }

    public void setShoppingItemId(Long shoppingItemId) {
        this.shoppingItemId = shoppingItemId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderItemDTO)) {
            return false;
        }

        return id != null && id.equals(((OrderItemDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrderItemDTO{" +
            "id=" + getId() +
            ", number=" + getNumber() +
            ", shoppingItemId=" + getShoppingItemId() +
            ", orderId=" + getOrderId() +
            "}";
    }
}
