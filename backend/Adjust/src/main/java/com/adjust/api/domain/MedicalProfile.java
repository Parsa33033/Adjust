package com.adjust.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.adjust.api.domain.enumeration.MaritalStatus;

import com.adjust.api.domain.enumeration.Education;

import com.adjust.api.domain.enumeration.Occupation;

import com.adjust.api.domain.enumeration.FinancialStatus;

import com.adjust.api.domain.enumeration.BloodType;

/**
 * A MedicalProfile.
 */
@Entity
@Table(name = "medical_profile")
public class MedicalProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "have_lost_weight")
    private Boolean haveLostWeight;

    @Column(name = "drowsiness")
    private Boolean drowsiness;

    @Column(name = "good_night_sleep")
    private Boolean goodNightSleep;

    @Column(name = "hours_of_traning_per_week")
    private Integer hoursOfTraningPerWeek;

    @Column(name = "love_for_sugar")
    private Boolean loveForSugar;

    @Column(name = "over_eating")
    private Boolean overEating;

    @Enumerated(EnumType.STRING)
    @Column(name = "marital_status")
    private MaritalStatus maritalStatus;

    @Column(name = "children_number")
    private Integer childrenNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "education")
    private Education education;

    @Enumerated(EnumType.STRING)
    @Column(name = "occupation")
    private Occupation occupation;

    @Column(name = "hours_of_work_daily")
    private Integer hoursOfWorkDaily;

    @Enumerated(EnumType.STRING)
    @Column(name = "financial_status")
    private FinancialStatus financialStatus;

    @Column(name = "city")
    private String city;

    @Column(name = "use_of_medicine")
    private Boolean useOfMedicine;

    @Lob
    @Column(name = "medicines")
    private String medicines;

    @Column(name = "have_allergy")
    private Boolean haveAllergy;

    @Column(name = "allergy")
    private String allergy;

    @Column(name = "have_disease")
    private Boolean haveDisease;

    @Enumerated(EnumType.STRING)
    @Column(name = "blood_type")
    private BloodType bloodType;

    @OneToOne
    @JoinColumn(unique = true)
    private BodyComposition bodyComposition;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "medical_profile_diseases",
               joinColumns = @JoinColumn(name = "medical_profile_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "diseases_id", referencedColumnName = "id"))
    private Set<Disease> diseases = new HashSet<>();

    @OneToOne(mappedBy = "medicalProfile")
    @JsonIgnore
    private AdjustClient adjustClient;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isHaveLostWeight() {
        return haveLostWeight;
    }

    public MedicalProfile haveLostWeight(Boolean haveLostWeight) {
        this.haveLostWeight = haveLostWeight;
        return this;
    }

    public void setHaveLostWeight(Boolean haveLostWeight) {
        this.haveLostWeight = haveLostWeight;
    }

    public Boolean isDrowsiness() {
        return drowsiness;
    }

    public MedicalProfile drowsiness(Boolean drowsiness) {
        this.drowsiness = drowsiness;
        return this;
    }

    public void setDrowsiness(Boolean drowsiness) {
        this.drowsiness = drowsiness;
    }

    public Boolean isGoodNightSleep() {
        return goodNightSleep;
    }

    public MedicalProfile goodNightSleep(Boolean goodNightSleep) {
        this.goodNightSleep = goodNightSleep;
        return this;
    }

    public void setGoodNightSleep(Boolean goodNightSleep) {
        this.goodNightSleep = goodNightSleep;
    }

    public Integer getHoursOfTraningPerWeek() {
        return hoursOfTraningPerWeek;
    }

    public MedicalProfile hoursOfTraningPerWeek(Integer hoursOfTraningPerWeek) {
        this.hoursOfTraningPerWeek = hoursOfTraningPerWeek;
        return this;
    }

    public void setHoursOfTraningPerWeek(Integer hoursOfTraningPerWeek) {
        this.hoursOfTraningPerWeek = hoursOfTraningPerWeek;
    }

    public Boolean isLoveForSugar() {
        return loveForSugar;
    }

    public MedicalProfile loveForSugar(Boolean loveForSugar) {
        this.loveForSugar = loveForSugar;
        return this;
    }

    public void setLoveForSugar(Boolean loveForSugar) {
        this.loveForSugar = loveForSugar;
    }

    public Boolean isOverEating() {
        return overEating;
    }

    public MedicalProfile overEating(Boolean overEating) {
        this.overEating = overEating;
        return this;
    }

    public void setOverEating(Boolean overEating) {
        this.overEating = overEating;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public MedicalProfile maritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
        return this;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Integer getChildrenNumber() {
        return childrenNumber;
    }

    public MedicalProfile childrenNumber(Integer childrenNumber) {
        this.childrenNumber = childrenNumber;
        return this;
    }

    public void setChildrenNumber(Integer childrenNumber) {
        this.childrenNumber = childrenNumber;
    }

    public Education getEducation() {
        return education;
    }

    public MedicalProfile education(Education education) {
        this.education = education;
        return this;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    public Occupation getOccupation() {
        return occupation;
    }

    public MedicalProfile occupation(Occupation occupation) {
        this.occupation = occupation;
        return this;
    }

    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }

    public Integer getHoursOfWorkDaily() {
        return hoursOfWorkDaily;
    }

    public MedicalProfile hoursOfWorkDaily(Integer hoursOfWorkDaily) {
        this.hoursOfWorkDaily = hoursOfWorkDaily;
        return this;
    }

    public void setHoursOfWorkDaily(Integer hoursOfWorkDaily) {
        this.hoursOfWorkDaily = hoursOfWorkDaily;
    }

    public FinancialStatus getFinancialStatus() {
        return financialStatus;
    }

    public MedicalProfile financialStatus(FinancialStatus financialStatus) {
        this.financialStatus = financialStatus;
        return this;
    }

    public void setFinancialStatus(FinancialStatus financialStatus) {
        this.financialStatus = financialStatus;
    }

    public String getCity() {
        return city;
    }

    public MedicalProfile city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Boolean isUseOfMedicine() {
        return useOfMedicine;
    }

    public MedicalProfile useOfMedicine(Boolean useOfMedicine) {
        this.useOfMedicine = useOfMedicine;
        return this;
    }

    public void setUseOfMedicine(Boolean useOfMedicine) {
        this.useOfMedicine = useOfMedicine;
    }

    public String getMedicines() {
        return medicines;
    }

    public MedicalProfile medicines(String medicines) {
        this.medicines = medicines;
        return this;
    }

    public void setMedicines(String medicines) {
        this.medicines = medicines;
    }

    public Boolean isHaveAllergy() {
        return haveAllergy;
    }

    public MedicalProfile haveAllergy(Boolean haveAllergy) {
        this.haveAllergy = haveAllergy;
        return this;
    }

    public void setHaveAllergy(Boolean haveAllergy) {
        this.haveAllergy = haveAllergy;
    }

    public String getAllergy() {
        return allergy;
    }

    public MedicalProfile allergy(String allergy) {
        this.allergy = allergy;
        return this;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }

    public Boolean isHaveDisease() {
        return haveDisease;
    }

    public MedicalProfile haveDisease(Boolean haveDisease) {
        this.haveDisease = haveDisease;
        return this;
    }

    public void setHaveDisease(Boolean haveDisease) {
        this.haveDisease = haveDisease;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public MedicalProfile bloodType(BloodType bloodType) {
        this.bloodType = bloodType;
        return this;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    public BodyComposition getBodyComposition() {
        return bodyComposition;
    }

    public MedicalProfile bodyComposition(BodyComposition bodyComposition) {
        this.bodyComposition = bodyComposition;
        return this;
    }

    public void setBodyComposition(BodyComposition bodyComposition) {
        this.bodyComposition = bodyComposition;
    }

    public Set<Disease> getDiseases() {
        return diseases;
    }

    public MedicalProfile diseases(Set<Disease> diseases) {
        this.diseases = diseases;
        return this;
    }

    public MedicalProfile addDiseases(Disease disease) {
        this.diseases.add(disease);
        disease.getMedicalProfiles().add(this);
        return this;
    }

    public MedicalProfile removeDiseases(Disease disease) {
        this.diseases.remove(disease);
        disease.getMedicalProfiles().remove(this);
        return this;
    }

    public void setDiseases(Set<Disease> diseases) {
        this.diseases = diseases;
    }

    public AdjustClient getAdjustClient() {
        return adjustClient;
    }

    public MedicalProfile adjustClient(AdjustClient adjustClient) {
        this.adjustClient = adjustClient;
        return this;
    }

    public void setAdjustClient(AdjustClient adjustClient) {
        this.adjustClient = adjustClient;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MedicalProfile)) {
            return false;
        }
        return id != null && id.equals(((MedicalProfile) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MedicalProfile{" +
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
            "}";
    }
}
