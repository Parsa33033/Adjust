package com.adjust.api.service.dto;

import java.io.Serializable;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.adjust.api.domain.ShoppingItem} entity.
 */
public class ShoppingItemDTO implements Serializable {
    
    private Long id;

    private String name;

    private Long itemId;

    @Lob
    private String description;

    private Double price;

    @Lob
    private byte[] image;

    private String imageContentType;
    private Boolean orderable;

    private Integer number;

    
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

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public Boolean isOrderable() {
        return orderable;
    }

    public void setOrderable(Boolean orderable) {
        this.orderable = orderable;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ShoppingItemDTO)) {
            return false;
        }

        return id != null && id.equals(((ShoppingItemDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ShoppingItemDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", itemId=" + getItemId() +
            ", description='" + getDescription() + "'" +
            ", price=" + getPrice() +
            ", image='" + getImage() + "'" +
            ", orderable='" + isOrderable() + "'" +
            ", number=" + getNumber() +
            "}";
    }
}
