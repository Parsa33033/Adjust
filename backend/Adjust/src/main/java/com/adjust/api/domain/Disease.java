package com.adjust.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Disease.
 */
@Entity
@Table(name = "disease")
public class Disease implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "protein_percentage")
    private Integer proteinPercentage;

    @Column(name = "carbs_percentage")
    private Integer carbsPercentage;

    @Column(name = "fat_percentage")
    private Integer fatPercentage;

    @ManyToMany(mappedBy = "diseases", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<MedicalProfile> medicalProfiles = new HashSet<>();

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

    public Disease name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProteinPercentage() {
        return proteinPercentage;
    }

    public Disease proteinPercentage(Integer proteinPercentage) {
        this.proteinPercentage = proteinPercentage;
        return this;
    }

    public void setProteinPercentage(Integer proteinPercentage) {
        this.proteinPercentage = proteinPercentage;
    }

    public Integer getCarbsPercentage() {
        return carbsPercentage;
    }

    public Disease carbsPercentage(Integer carbsPercentage) {
        this.carbsPercentage = carbsPercentage;
        return this;
    }

    public void setCarbsPercentage(Integer carbsPercentage) {
        this.carbsPercentage = carbsPercentage;
    }

    public Integer getFatPercentage() {
        return fatPercentage;
    }

    public Disease fatPercentage(Integer fatPercentage) {
        this.fatPercentage = fatPercentage;
        return this;
    }

    public void setFatPercentage(Integer fatPercentage) {
        this.fatPercentage = fatPercentage;
    }

    public Set<MedicalProfile> getMedicalProfiles() {
        return medicalProfiles;
    }

    public Disease medicalProfiles(Set<MedicalProfile> medicalProfiles) {
        this.medicalProfiles = medicalProfiles;
        return this;
    }

    public Disease addMedicalProfiles(MedicalProfile medicalProfile) {
        this.medicalProfiles.add(medicalProfile);
        medicalProfile.getDiseases().add(this);
        return this;
    }

    public Disease removeMedicalProfiles(MedicalProfile medicalProfile) {
        this.medicalProfiles.remove(medicalProfile);
        medicalProfile.getDiseases().remove(this);
        return this;
    }

    public void setMedicalProfiles(Set<MedicalProfile> medicalProfiles) {
        this.medicalProfiles = medicalProfiles;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Disease)) {
            return false;
        }
        return id != null && id.equals(((Disease) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Disease{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", proteinPercentage=" + getProteinPercentage() +
            ", carbsPercentage=" + getCarbsPercentage() +
            ", fatPercentage=" + getFatPercentage() +
            "}";
    }
}
