package com.adjust.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A AdjustProgram.
 */
@Entity
@Table(name = "adjust_program")
public class AdjustProgram implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "medical_profile_done")
    private Boolean medicalProfileDone;

    @Column(name = "body_composition_done")
    private Boolean bodyCompositionDone;

    @Column(name = "nutrition_done")
    private Boolean nutritionDone;

    @Column(name = "fitness_done")
    private Boolean fitnessDone;

    @Column(name = "paid")
    private Boolean paid;

    @Column(name = "seen_by_client")
    private Boolean seenByClient;

    @Column(name = "seen_by_specialist")
    private Boolean seenBySpecialist;

    @Column(name = "designed")
    private Boolean designed;

    @OneToOne
    @JoinColumn(unique = true)
    private BodyComposition bodyComposition;

    @OneToOne
    @JoinColumn(unique = true)
    private FitnessProgram fitnessProgram;

    @OneToOne
    @JoinColumn(unique = true)
    private NutritionProgram nutritionProgram;

    @OneToMany(mappedBy = "adjustProgram", fetch = FetchType.EAGER)
    private Set<Development> developments = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "adjustPrograms", allowSetters = true)
    private AdjustClient adjustClient;

    @ManyToOne
    @JsonIgnoreProperties(value = "adjustPrograms", allowSetters = true)
    private Specialist specialist;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public AdjustProgram createdAt(LocalDate createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public AdjustProgram expirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Boolean isMedicalProfileDone() {
        return medicalProfileDone;
    }

    public AdjustProgram medicalProfileDone(Boolean medicalProfileDone) {
        this.medicalProfileDone = medicalProfileDone;
        return this;
    }

    public void setMedicalProfileDone(Boolean medicalProfileDone) {
        this.medicalProfileDone = medicalProfileDone;
    }

    public Boolean isBodyCompositionDone() {
        return bodyCompositionDone;
    }

    public AdjustProgram bodyCompositionDone(Boolean bodyCompositionDone) {
        this.bodyCompositionDone = bodyCompositionDone;
        return this;
    }

    public void setBodyCompositionDone(Boolean bodyCompositionDone) {
        this.bodyCompositionDone = bodyCompositionDone;
    }

    public Boolean isNutritionDone() {
        return nutritionDone;
    }

    public AdjustProgram nutritionDone(Boolean nutritionDone) {
        this.nutritionDone = nutritionDone;
        return this;
    }

    public void setNutritionDone(Boolean nutritionDone) {
        this.nutritionDone = nutritionDone;
    }

    public Boolean isFitnessDone() {
        return fitnessDone;
    }

    public AdjustProgram fitnessDone(Boolean fitnessDone) {
        this.fitnessDone = fitnessDone;
        return this;
    }

    public void setFitnessDone(Boolean fitnessDone) {
        this.fitnessDone = fitnessDone;
    }

    public Boolean isPaid() {
        return paid;
    }

    public AdjustProgram paid(Boolean paid) {
        this.paid = paid;
        return this;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public Boolean isSeenByClient() {
        return seenByClient;
    }

    public AdjustProgram seenByClient(Boolean seenByClient) {
        this.seenByClient = seenByClient;
        return this;
    }

    public void setSeenByClient(Boolean seenByClient) {
        this.seenByClient = seenByClient;
    }

    public Boolean isSeenBySpecialist() {
        return seenBySpecialist;
    }

    public AdjustProgram seenBySpecialist(Boolean seenBySpecialist) {
        this.seenBySpecialist = seenBySpecialist;
        return this;
    }

    public void setSeenBySpecialist(Boolean seenBySpecialist) {
        this.seenBySpecialist = seenBySpecialist;
    }

    public Boolean isDesigned() {
        return designed;
    }

    public AdjustProgram designed(Boolean designed) {
        this.designed = designed;
        return this;
    }

    public void setDesigned(Boolean designed) {
        this.designed = designed;
    }

    public BodyComposition getBodyComposition() {
        return bodyComposition;
    }

    public AdjustProgram bodyComposition(BodyComposition bodyComposition) {
        this.bodyComposition = bodyComposition;
        return this;
    }

    public void setBodyComposition(BodyComposition bodyComposition) {
        this.bodyComposition = bodyComposition;
    }

    public FitnessProgram getFitnessProgram() {
        return fitnessProgram;
    }

    public AdjustProgram fitnessProgram(FitnessProgram fitnessProgram) {
        this.fitnessProgram = fitnessProgram;
        return this;
    }

    public void setFitnessProgram(FitnessProgram fitnessProgram) {
        this.fitnessProgram = fitnessProgram;
    }

    public NutritionProgram getNutritionProgram() {
        return nutritionProgram;
    }

    public AdjustProgram nutritionProgram(NutritionProgram nutritionProgram) {
        this.nutritionProgram = nutritionProgram;
        return this;
    }

    public void setNutritionProgram(NutritionProgram nutritionProgram) {
        this.nutritionProgram = nutritionProgram;
    }

    public Set<Development> getDevelopments() {
        return developments;
    }

    public AdjustProgram developments(Set<Development> developments) {
        this.developments = developments;
        return this;
    }

    public AdjustProgram addDevelopments(Development development) {
        this.developments.add(development);
        development.setAdjustProgram(this);
        return this;
    }

    public AdjustProgram removeDevelopments(Development development) {
        this.developments.remove(development);
        development.setAdjustProgram(null);
        return this;
    }

    public void setDevelopments(Set<Development> developments) {
        this.developments = developments;
    }

    public AdjustClient getAdjustClient() {
        return adjustClient;
    }

    public AdjustProgram adjustClient(AdjustClient adjustClient) {
        this.adjustClient = adjustClient;
        return this;
    }

    public void setAdjustClient(AdjustClient adjustClient) {
        this.adjustClient = adjustClient;
    }

    public Specialist getSpecialist() {
        return specialist;
    }

    public AdjustProgram specialist(Specialist specialist) {
        this.specialist = specialist;
        return this;
    }

    public void setSpecialist(Specialist specialist) {
        this.specialist = specialist;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdjustProgram)) {
            return false;
        }
        return id != null && id.equals(((AdjustProgram) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdjustProgram{" +
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
            "}";
    }
}
