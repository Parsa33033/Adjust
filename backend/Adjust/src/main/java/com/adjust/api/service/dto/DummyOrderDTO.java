package com.adjust.api.service.dto;

import java.util.List;

public class DummyOrderDTO extends OrderDTO {

    private List<DummyOrderItemDTO> orderItems;

    public DummyOrderDTO() {
    }

    public DummyOrderDTO(OrderDTO orderDTO) {
        this.setReceived(orderDTO.isReceived());
        this.setDateOfOrder(orderDTO.getDateOfOrder());
        this.setState(orderDTO.getState());
        this.setEmail(orderDTO.getEmail());
        this.setPhoneNumber(orderDTO.getPhoneNumber());
        this.setLastName(orderDTO.getLastName());
        this.setFirstName(orderDTO.getFirstName());
        this.setUsername(orderDTO.getUsername());
        this.setDone(orderDTO.isDone());
        this.setCountry(orderDTO.getCountry());
        this.setCity(orderDTO.getCity());
        this.setAddress2(orderDTO.getAddress2());
        this.setAddress1(orderDTO.getAddress1());
        this.setId(orderDTO.getId());
        this.setAdjustClientId(orderDTO.getAdjustClientId());
        this.setPaid(orderDTO.isPaid());
    }

    public List<DummyOrderItemDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<DummyOrderItemDTO> orderItems) {
        this.orderItems = orderItems;
    }
}
