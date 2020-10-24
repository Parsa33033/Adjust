package com.adjust.api.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Lob;
import com.adjust.api.domain.enumeration.MaritalStatus;
import com.adjust.api.domain.enumeration.Education;
import com.adjust.api.domain.enumeration.Occupation;
import com.adjust.api.domain.enumeration.FinancialStatus;
import com.adjust.api.domain.enumeration.BloodType;

/**
 * A DTO for the {@link com.adjust.api.domain.MedicalProfile} entity.
 */
public class MedicalProfileDTO implements Serializable {
    
    private Long id;

    private Boolean haveLostWeight;

    private Boolean drowsiness;

    private Boolean goodNightSleep;

    private Integer hoursOfTraningPerWeek;

    private Boolean loveForSugar;

    private Boolean overEating;

    private MaritalStatus maritalStatus;

    private Integer childrenNumber;

    private Education education;

    private Occupation occupation;

    private Integer hoursOfWorkDaily;

    private FinancialStatus financialStatus;

    private String city;

    private Boolean useOfMedicine;

    @Lob
    private String medicines;

    private Boolean haveAllergy;

    private String allergy;

    private Boolean haveDisease;

    private BloodType bloodType;


    private Long bodyCompositionId;
    private Set<DiseaseDTO> diseases = new HashSet<>();
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isHaveLostWeight() {
        return haveLostWeight;
    }

    public void setHaveLostWeight(Boolean haveLostWeight) {
        this.haveLostWeight = haveLostWeight;
    }

    public Boolean isDrowsiness() {
        return drowsiness;
    }

    public void setDrowsiness(Boolean drowsiness) {
        this.drowsiness = drowsiness;
    }

    public Boolean isGoodNightSleep() {
        return goodNightSleep;
    }

    public void setGoodNightSleep(Boolean goodNightSleep) {
        this.goodNightSleep = goodNightSleep;
    }

    public Integer getHoursOfTraningPerWeek() {
        return hoursOfTraningPerWeek;
    }

    public void setHoursOfTraningPerWeek(Integer hoursOfTraningPerWeek) {
        this.hoursOfTraningPerWeek = hoursOfTraningPerWeek;
    }

    public Boolean isLoveForSugar() {
        return loveForSugar;
    }

    public void setLoveForSugar(Boolean loveForSugar) {
        this.loveForSugar = loveForSugar;
    }

    public Boolean isOverEating() {
        return overEating;
    }

    public void setOverEating(Boolean overEating) {
        this.overEating = overEating;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Integer getChildrenNumber() {
        return childrenNumber;
    }

    public void setChildrenNumber(Integer childrenNumber) {
        this.childrenNumber = childrenNumber;
    }

    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    public Occupation getOccupation() {
        return occupation;
    }

    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }

    public Integer getHoursOfWorkDaily() {
        return hoursOfWorkDaily;
    }

    public void setHoursOfWorkDaily(Integer hoursOfWorkDaily) {
        this.hoursOfWorkDaily = hoursOfWorkDaily;
    }

    public FinancialStatus getFinancialStatus() {
        return financialStatus;
    }

    public void setFinancialStatus(FinancialStatus financialStatus) {
        this.financialStatus = financialStatus;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Boolean isUseOfMedicine() {
        return useOfMedicine;
    }

    public void setUseOfMedicine(Boolean useOfMedicine) {
        this.useOfMedicine = useOfMedicine;
    }

    public String getMedicines() {
        return medicines;
    }

    public void setMedicines(String medicines) {
        this.medicines = medicines;
    }

    public Boolean isHaveAllergy() {
        return haveAllergy;
    }

    public void setHaveAllergy(Boolean haveAllergy) {
        this.haveAllergy = haveAllergy;
    }

    public String getAllergy() {
        return allergy;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }

    public Boolean isHaveDisease() {
        return haveDisease;
    }

    public void setHaveDisease(Boolean haveDisease) {
        this.haveDisease = haveDisease;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    public Long getBodyCompositionId() {
        return bodyCompositionId;
    }

    public void setBodyCompositionId(Long bodyCompositionId) {
        this.bodyCompositionId = bodyCompositionId;
    }

    public Set<DiseaseDTO> getDiseases() {
        return diseases;
    }

    public void setDiseases(Set<DiseaseDTO> diseases) {
        this.diseases = diseases;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MedicalProfileDTO)) {
            return false;
        }

        return id != null && id.equals(((MedicalProfileDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MedicalProfileDTO{" +
            "id=" + getId() +
            ", haveLostWeight='" + isHaveLostWeight() + "'" +
            ", drowsiness='" + isDrowsiness() + "'" +
            ", goodNightSleep='" + isGoodNightSleep() + "'" +
            ", hoursOfTraningPerWeek=" + getHoursOfTraningPerWeek() +
            ", loveForSugar='" + isLoveForSugar() + "'" +
            ", overEating='" + isOverEating() + "'" +
            ", maritalStatus='" + getMaritalStatus() + "'" +
            ", childrenNumber=" + getChildrenNumber() +
            ", education='" + getEducation() + "'" +
            ", occupation='" + getOccupation() + "'" +
            ", hoursOfWorkDaily=" + getHoursOfWorkDaily() +
            ", financialStatus='" + getFinancialStatus() + "'" +
            ", city='" + getCity() + "'" +
            ", useOfMedicine='" + isUseOfMedicine() + "'" +
            ", medicines='" + getMedicines() + "'" +
            ", haveAllergy='" + isHaveAllergy() + "'" +
            ", allergy='" + getAllergy() + "'" +
            ", haveDisease='" + isHaveDisease() + "'" +
            ", bloodType='" + getBloodType() + "'" +
            ", bodyCompositionId=" + getBodyCompositionId() +
            ", diseases='" + getDiseases() + "'" +
            "}";
    }
}
