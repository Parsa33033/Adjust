package com.adjust.api.service.dto;

public class DummyMoveDTO extends MoveDTO {

    public DummyMoveDTO() {
    }

    public DummyMoveDTO(MoveDTO moveDTO) {
        this.setPictureContentType(moveDTO.getPictureContentType());
        this.setPicture(moveDTO.getPicture());
        this.setName(moveDTO.getName());
        this.setMuscleName(moveDTO.getMuscleName());
        this.setEquipment(moveDTO.getEquipment());
        this.setId(moveDTO.getId());
        this.setDescription(moveDTO.getDescription());
        this.setMoveType(moveDTO.getMoveType());
        this.setPlace(moveDTO.getPlace());
    }
}
