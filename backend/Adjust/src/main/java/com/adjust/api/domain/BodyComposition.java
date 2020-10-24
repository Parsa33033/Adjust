package com.adjust.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

import com.adjust.api.domain.enumeration.Gender;

import com.adjust.api.domain.enumeration.BMICategory;

import com.adjust.api.domain.enumeration.BodyFatCategory;

import com.adjust.api.domain.enumeration.BodyType;

import com.adjust.api.domain.enumeration.BodyFrameSize;

import com.adjust.api.domain.enumeration.WorkoutLocation;

import com.adjust.api.domain.enumeration.Goal;

import com.adjust.api.domain.enumeration.ActivityType;

import com.adjust.api.domain.enumeration.WorkoutType;

/**
 * A BodyComposition.
 */
@Entity
@Table(name = "body_composition")
public class BodyComposition implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "age")
    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "height")
    private Double height;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "wrist")
    private Double wrist;

    @Column(name = "waist")
    private Double waist;

    @Column(name = "hip")
    private Double hip;

    @Column(name = "abdomen")
    private Double abdomen;

    @Column(name = "neck")
    private Double neck;

    @Column(name = "waist_hip_ratio")
    private Double waistHipRatio;

    @Column(name = "ideal_weight_min")
    private Double idealWeightMin;

    @Column(name = "ideal_weight_max")
    private Double idealWeightMax;

    @Column(name = "best_weight")
    private Double bestWeight;

    @Column(name = "bmi")
    private Double bmi;

    @Enumerated(EnumType.STRING)
    @Column(name = "bmi_category")
    private BMICategory bmiCategory;

    @Column(name = "bmr")
    private Double bmr;

    @Column(name = "lbm")
    private Double lbm;

    @Column(name = "pbf")
    private Double pbf;

    @Enumerated(EnumType.STRING)
    @Column(name = "body_fat_category")
    private BodyFatCategory bodyFatCategory;

    @Column(name = "smm")
    private Double smm;

    @Column(name = "body_type_number")
    private Double bodyTypeNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "body_type")
    private BodyType bodyType;

    @Column(name = "frame_size")
    private Double frameSize;

    @Enumerated(EnumType.STRING)
    @Column(name = "body_frame_size")
    private BodyFrameSize bodyFrameSize;

    @Column(name = "visceral_fat_level")
    private Double visceralFatLevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "workout_location")
    private WorkoutLocation workoutLocation;

    @Enumerated(EnumType.STRING)
    @Column(name = "goal")
    private Goal goal;

    @Enumerated(EnumType.STRING)
    @Column(name = "activity_type")
    private ActivityType activityType;

    @Enumerated(EnumType.STRING)
    @Column(name = "workout_type")
    private WorkoutType workoutType;

    @OneToOne(mappedBy = "bodyComposition")
    @JsonIgnore
    private MedicalProfile medicalProfile;

    @OneToOne(mappedBy = "bodyComposition")
    @JsonIgnore
    private AdjustProgram adjustProgram;

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

    public BodyComposition createdAt(LocalDate createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getAge() {
        return age;
    }

    public BodyComposition age(Integer age) {
        this.age = age;
        return this;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public BodyComposition gender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Double getHeight() {
        return height;
    }

    public BodyComposition height(Double height) {
        this.height = height;
        return this;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public BodyComposition weight(Double weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getWrist() {
        return wrist;
    }

    public BodyComposition wrist(Double wrist) {
        this.wrist = wrist;
        return this;
    }

    public void setWrist(Double wrist) {
        this.wrist = wrist;
    }

    public Double getWaist() {
        return waist;
    }

    public BodyComposition waist(Double waist) {
        this.waist = waist;
        return this;
    }

    public void setWaist(Double waist) {
        this.waist = waist;
    }

    public Double getHip() {
        return hip;
    }

    public BodyComposition hip(Double hip) {
        this.hip = hip;
        return this;
    }

    public void setHip(Double hip) {
        this.hip = hip;
    }

    public Double getAbdomen() {
        return abdomen;
    }

    public BodyComposition abdomen(Double abdomen) {
        this.abdomen = abdomen;
        return this;
    }

    public void setAbdomen(Double abdomen) {
        this.abdomen = abdomen;
    }

    public Double getNeck() {
        return neck;
    }

    public BodyComposition neck(Double neck) {
        this.neck = neck;
        return this;
    }

    public void setNeck(Double neck) {
        this.neck = neck;
    }

    public Double getWaistHipRatio() {
        return waistHipRatio;
    }

    public BodyComposition waistHipRatio(Double waistHipRatio) {
        this.waistHipRatio = waistHipRatio;
        return this;
    }

    public void setWaistHipRatio(Double waistHipRatio) {
        this.waistHipRatio = waistHipRatio;
    }

    public Double getIdealWeightMin() {
        return idealWeightMin;
    }

    public BodyComposition idealWeightMin(Double idealWeightMin) {
        this.idealWeightMin = idealWeightMin;
        return this;
    }

    public void setIdealWeightMin(Double idealWeightMin) {
        this.idealWeightMin = idealWeightMin;
    }

    public Double getIdealWeightMax() {
        return idealWeightMax;
    }

    public BodyComposition idealWeightMax(Double idealWeightMax) {
        this.idealWeightMax = idealWeightMax;
        return this;
    }

    public void setIdealWeightMax(Double idealWeightMax) {
        this.idealWeightMax = idealWeightMax;
    }

    public Double getBestWeight() {
        return bestWeight;
    }

    public BodyComposition bestWeight(Double bestWeight) {
        this.bestWeight = bestWeight;
        return this;
    }

    public void setBestWeight(Double bestWeight) {
        this.bestWeight = bestWeight;
    }

    public Double getBmi() {
        return bmi;
    }

    public BodyComposition bmi(Double bmi) {
        this.bmi = bmi;
        return this;
    }

    public void setBmi(Double bmi) {
        this.bmi = bmi;
    }

    public BMICategory getBmiCategory() {
        return bmiCategory;
    }

    public BodyComposition bmiCategory(BMICategory bmiCategory) {
        this.bmiCategory = bmiCategory;
        return this;
    }

    public void setBmiCategory(BMICategory bmiCategory) {
        this.bmiCategory = bmiCategory;
    }

    public Double getBmr() {
        return bmr;
    }

    public BodyComposition bmr(Double bmr) {
        this.bmr = bmr;
        return this;
    }

    public void setBmr(Double bmr) {
        this.bmr = bmr;
    }

    public Double getLbm() {
        return lbm;
    }

    public BodyComposition lbm(Double lbm) {
        this.lbm = lbm;
        return this;
    }

    public void setLbm(Double lbm) {
        this.lbm = lbm;
    }

    public Double getPbf() {
        return pbf;
    }

    public BodyComposition pbf(Double pbf) {
        this.pbf = pbf;
        return this;
    }

    public void setPbf(Double pbf) {
        this.pbf = pbf;
    }

    public BodyFatCategory getBodyFatCategory() {
        return bodyFatCategory;
    }

    public BodyComposition bodyFatCategory(BodyFatCategory bodyFatCategory) {
        this.bodyFatCategory = bodyFatCategory;
        return this;
    }

    public void setBodyFatCategory(BodyFatCategory bodyFatCategory) {
        this.bodyFatCategory = bodyFatCategory;
    }

    public Double getSmm() {
        return smm;
    }

    public BodyComposition smm(Double smm) {
        this.smm = smm;
        return this;
    }

    public void setSmm(Double smm) {
        this.smm = smm;
    }

    public Double getBodyTypeNumber() {
        return bodyTypeNumber;
    }

    public BodyComposition bodyTypeNumber(Double bodyTypeNumber) {
        this.bodyTypeNumber = bodyTypeNumber;
        return this;
    }

    public void setBodyTypeNumber(Double bodyTypeNumber) {
        this.bodyTypeNumber = bodyTypeNumber;
    }

    public BodyType getBodyType() {
        return bodyType;
    }

    public BodyComposition bodyType(BodyType bodyType) {
        this.bodyType = bodyType;
        return this;
    }

    public void setBodyType(BodyType bodyType) {
        this.bodyType = bodyType;
    }

    public Double getFrameSize() {
        return frameSize;
    }

    public BodyComposition frameSize(Double frameSize) {
        this.frameSize = frameSize;
        return this;
    }

    public void setFrameSize(Double frameSize) {
        this.frameSize = frameSize;
    }

    public BodyFrameSize getBodyFrameSize() {
        return bodyFrameSize;
    }

    public BodyComposition bodyFrameSize(BodyFrameSize bodyFrameSize) {
        this.bodyFrameSize = bodyFrameSize;
        return this;
    }

    public void setBodyFrameSize(BodyFrameSize bodyFrameSize) {
        this.bodyFrameSize = bodyFrameSize;
    }

    public Double getVisceralFatLevel() {
        return visceralFatLevel;
    }

    public BodyComposition visceralFatLevel(Double visceralFatLevel) {
        this.visceralFatLevel = visceralFatLevel;
        return this;
    }

    public void setVisceralFatLevel(Double visceralFatLevel) {
        this.visceralFatLevel = visceralFatLevel;
    }

    public WorkoutLocation getWorkoutLocation() {
        return workoutLocation;
    }

    public BodyComposition workoutLocation(WorkoutLocation workoutLocation) {
        this.workoutLocation = workoutLocation;
        return this;
    }

    public void setWorkoutLocation(WorkoutLocation workoutLocation) {
        this.workoutLocation = workoutLocation;
    }

    public Goal getGoal() {
        return goal;
    }

    public BodyComposition goal(Goal goal) {
        this.goal = goal;
        return this;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public BodyComposition activityType(ActivityType activityType) {
        this.activityType = activityType;
        return this;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    public WorkoutType getWorkoutType() {
        return workoutType;
    }

    public BodyComposition workoutType(WorkoutType workoutType) {
        this.workoutType = workoutType;
        return this;
    }

    public void setWorkoutType(WorkoutType workoutType) {
        this.workoutType = workoutType;
    }

    public MedicalProfile getMedicalProfile() {
        return medicalProfile;
    }

    public BodyComposition medicalProfile(MedicalProfile medicalProfile) {
        this.medicalProfile = medicalProfile;
        return this;
    }

    public void setMedicalProfile(MedicalProfile medicalProfile) {
        this.medicalProfile = medicalProfile;
    }

    public AdjustProgram getAdjustProgram() {
        return adjustProgram;
    }

    public BodyComposition adjustProgram(AdjustProgram adjustProgram) {
        this.adjustProgram = adjustProgram;
        return this;
    }

    public void setAdjustProgram(AdjustProgram adjustProgram) {
        this.adjustProgram = adjustProgram;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BodyComposition)) {
            return false;
        }
        return id != null && id.equals(((BodyComposition) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BodyComposition{" +
            "id=" + getId() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", age=" + getAge() +
            ", gender='" + getGender() + "'" +
            ", height=" + getHeight() +
            ", weight=" + getWeight() +
            ", wrist=" + getWrist() +
            ", waist=" + getWaist() +
            ", hip=" + getHip() +
            ", abdomen=" + getAbdomen() +
            ", neck=" + getNeck() +
            ", waistHipRatio=" + getWaistHipRatio() +
            ", idealWeightMin=" + getIdealWeightMin() +
            ", idealWeightMax=" + getIdealWeightMax() +
            ", bestWeight=" + getBestWeight() +
            ", bmi=" + getBmi() +
            ", bmiCategory='" + getBmiCategory() + "'" +
            ", bmr=" + getBmr() +
            ", lbm=" + getLbm() +
            ", pbf=" + getPbf() +
            ", bodyFatCategory='" + getBodyFatCategory() + "'" +
            ", smm=" + getSmm() +
            ", bodyTypeNumber=" + getBodyTypeNumber() +
            ", bodyType='" + getBodyType() + "'" +
            ", frameSize=" + getFrameSize() +
            ", bodyFrameSize='" + getBodyFrameSize() + "'" +
            ", visceralFatLevel=" + getVisceralFatLevel() +
            ", workoutLocation='" + getWorkoutLocation() + "'" +
            ", goal='" + getGoal() + "'" +
            ", activityType='" + getActivityType() + "'" +
            ", workoutType='" + getWorkoutType() + "'" +
            "}";
    }
}
