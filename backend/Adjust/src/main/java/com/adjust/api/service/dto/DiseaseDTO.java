package com.adjust.api.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.adjust.api.domain.Disease} entity.
 */
public class DiseaseDTO implements Serializable {
    
    private Long id;

    private String name;

    private Integer proteinPercentage;

    private Integer carbsPercentage;

    private Integer fatPercentage;

    
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

    public Integer getProteinPercentage() {
        return proteinPercentage;
    }

    public void setProteinPercentage(Integer proteinPercentage) {
        this.proteinPercentage = proteinPercentage;
    }

    public Integer getCarbsPercentage() {
        return carbsPercentage;
    }

    public void setCarbsPercentage(Integer carbsPercentage) {
        this.carbsPercentage = carbsPercentage;
    }

    public Integer getFatPercentage() {
        return fatPercentage;
    }

    public void setFatPercentage(Integer fatPercentage) {
        this.fatPercentage = fatPercentage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DiseaseDTO)) {
            return false;
        }

        return id != null && id.equals(((DiseaseDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DiseaseDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", proteinPercentage=" + getProteinPercentage() +
            ", carbsPercentage=" + getCarbsPercentage() +
            ", fatPercentage=" + getFatPercentage() +
            "}";
    }
}
