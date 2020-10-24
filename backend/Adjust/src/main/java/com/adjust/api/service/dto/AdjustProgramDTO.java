package com.adjust.api.service.dto;

import java.time.LocalDate;
import java.io.Serializable;

/**
 * A DTO for the {@link com.adjust.api.domain.AdjustProgram} entity.
 */
public class AdjustProgramDTO implements Serializable {
    
    private Long id;

    private LocalDate createdAt;

    private LocalDate expirationDate;

    private Boolean medicalProfileDone;

    private Boolean bodyCompositionDone;

    private Boolean nutritionDone;

    private Boolean fitnessDone;

    private Boolean paid;

    private Boolean seenByClient;

    private Boolean seenBySpecialist;

    private Boolean designed;


    private Long bodyCompositionId;

    private Long fitnessProgramId;

    private Long nutritionProgramId;

    private Long adjustClientId;

    private Long specialistId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Boolean isMedicalProfileDone() {
        return medicalProfileDone;
    }

    public void setMedicalProfileDone(Boolean medicalProfileDone) {
        this.medicalProfileDone = medicalProfileDone;
    }

    public Boolean isBodyCompositionDone() {
        return bodyCompositionDone;
    }

    public void setBodyCompositionDone(Boolean bodyCompositionDone) {
        this.bodyCompositionDone = bodyCompositionDone;
    }

    public Boolean isNutritionDone() {
        return nutritionDone;
    }

    public void setNutritionDone(Boolean nutritionDone) {
        this.nutritionDone = nutritionDone;
    }

    public Boolean isFitnessDone() {
        return fitnessDone;
    }

    public void setFitnessDone(Boolean fitnessDone) {
        this.fitnessDone = fitnessDone;
    }

    public Boolean isPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public Boolean isSeenByClient() {
        return seenByClient;
    }

    public void setSeenByClient(Boolean seenByClient) {
        this.seenByClient = seenByClient;
    }

    public Boolean isSeenBySpecialist() {
        return seenBySpecialist;
    }

    public void setSeenBySpecialist(Boolean seenBySpecialist) {
        this.seenBySpecialist = seenBySpecialist;
    }

    public Boolean isDesigned() {
        return designed;
    }

    public void setDesigned(Boolean designed) {
        this.designed = designed;
    }

    public Long getBodyCompositionId() {
        return bodyCompositionId;
    }

    public void setBodyCompositionId(Long bodyCompositionId) {
        this.bodyCompositionId = bodyCompositionId;
    }

    public Long getFitnessProgramId() {
        return fitnessProgramId;
    }

    public void setFitnessProgramId(Long fitnessProgramId) {
        this.fitnessProgramId = fitnessProgramId;
    }

    public Long getNutritionProgramId() {
        return nutritionProgramId;
    }

    public void setNutritionProgramId(Long nutritionProgramId) {
        this.nutritionProgramId = nutritionProgramId;
    }

    public Long getAdjustClientId() {
        return adjustClientId;
    }

    public void setAdjustClientId(Long adjustClientId) {
        this.adjustClientId = adjustClientId;
    }

    public Long getSpecialistId() {
        return specialistId;
    }

    public void setSpecialistId(Long specialistId) {
        this.specialistId = specialistId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdjustProgramDTO)) {
            return false;
        }

        return id != null && id.equals(((AdjustProgramDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdjustProgramDTO{" +
            "id=" + getId() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", expirationDate='" + getExpirationDate() + "'" +
            ", medicalProfileDone='" + isMedicalProfileDone() + "'" +
            ", bodyCompositionDone='" + isBodyCompositionDone() + "'" +
            ", nutritionDone='" + isNutritionDone() + "'" +
            ", fitnessDone='" + isFitnessDone() + "'" +
            ", paid='" + isPaid() + "'" +
            ", seenByClient='" + isSeenByClient() + "'" +
            ", seenBySpecialist='" + isSeenBySpecialist() + "'" +
            ", designed='" + isDesigned() + "'" +
            ", bodyCompositionId=" + getBodyCompositionId() +
            ", fitnessProgramId=" + getFitnessProgramId() +
            ", nutritionProgramId=" + getNutritionProgramId() +
            ", adjustClientId=" + getAdjustClientId() +
            ", specialistId=" + getSpecialistId() +
            "}";
    }
}
