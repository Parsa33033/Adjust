package com.adjust.api.service.dto;

import java.io.Serializable;
import javax.persistence.Lob;
import com.adjust.api.domain.enumeration.MoveType;
import com.adjust.api.domain.enumeration.WorkoutLocation;

/**
 * A DTO for the {@link com.adjust.api.domain.Move} entity.
 */
public class MoveDTO implements Serializable {
    
    private Long id;

    private String name;

    @Lob
    private String description;

    private String muscleName;

    private MoveType moveType;

    private WorkoutLocation place;

    private String equipment;

    @Lob
    private byte[] picture;

    private String pictureContentType;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMuscleName() {
        return muscleName;
    }

    public void setMuscleName(String muscleName) {
        this.muscleName = muscleName;
    }

    public MoveType getMoveType() {
        return moveType;
    }

    public void setMoveType(MoveType moveType) {
        this.moveType = moveType;
    }

    public WorkoutLocation getPlace() {
        return place;
    }

    public void setPlace(WorkoutLocation place) {
        this.place = place;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getPictureContentType() {
        return pictureContentType;
    }

    public void setPictureContentType(String pictureContentType) {
        this.pictureContentType = pictureContentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MoveDTO)) {
            return false;
        }

        return id != null && id.equals(((MoveDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MoveDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", muscleName='" + getMuscleName() + "'" +
            ", moveType='" + getMoveType() + "'" +
            ", place='" + getPlace() + "'" +
            ", equipment='" + getEquipment() + "'" +
            ", picture='" + getPicture() + "'" +
            "}";
    }
}
