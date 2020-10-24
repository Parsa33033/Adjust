package com.adjust.api.domain;


import javax.persistence.*;

import java.io.Serializable;

/**
 * A ShoppingItem.
 */
@Entity
@Table(name = "shopping_item")
public class ShoppingItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "item_id")
    private Long itemId;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @Column(name = "image_content_type")
    private String imageContentType;

    @Column(name = "orderable")
    private Boolean orderable;

    @Column(name = "number")
    private Integer number;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public ShoppingItem name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getItemId() {
        return itemId;
    }

    public ShoppingItem itemId(Long itemId) {
        this.itemId = itemId;
        return this;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getDescription() {
        return description;
    }

    public ShoppingItem description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public ShoppingItem price(Double price) {
        this.price = price;
        return this;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public byte[] getImage() {
        return image;
    }

    public ShoppingItem image(byte[] image) {
        this.image = image;
        return this;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public ShoppingItem imageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
        return this;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public Boolean isOrderable() {
        return orderable;
    }

    public ShoppingItem orderable(Boolean orderable) {
        this.orderable = orderable;
        return this;
    }

    public void setOrderable(Boolean orderable) {
        this.orderable = orderable;
    }

    public Integer getNumber() {
        return number;
    }

    public ShoppingItem number(Integer number) {
        this.number = number;
        return this;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ShoppingItem)) {
            return false;
        }
        return id != null && id.equals(((ShoppingItem) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ShoppingItem{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", itemId=" + getItemId() +
            ", description='" + getDescription() + "'" +
            ", price=" + getPrice() +
            ", image='" + getImage() + "'" +
            ", imageContentType='" + getImageContentType() + "'" +
            ", orderable='" + isOrderable() + "'" +
            ", number=" + getNumber() +
            "}";
    }
}
