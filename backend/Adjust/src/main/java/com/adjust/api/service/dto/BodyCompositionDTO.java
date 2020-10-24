package com.adjust.api.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
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
 * A DTO for the {@link com.adjust.api.domain.BodyComposition} entity.
 */
public class BodyCompositionDTO implements Serializable {
    
    private Long id;

    private LocalDate createdAt;

    private Integer age;

    private Gender gender;

    private Double height;

    private Double weight;

    private Double wrist;

    private Double waist;

    private Double hip;

    private Double abdomen;

    private Double neck;

    private Double waistHipRatio;

    private Double idealWeightMin;

    private Double idealWeightMax;

    private Double bestWeight;

    private Double bmi;

    private BMICategory bmiCategory;

    private Double bmr;

    private Double lbm;

    private Double pbf;

    private BodyFatCategory bodyFatCategory;

    private Double smm;

    private Double bodyTypeNumber;

    private BodyType bodyType;

    private Double frameSize;

    private BodyFrameSize bodyFrameSize;

    private Double visceralFatLevel;

    private WorkoutLocation workoutLocation;

    private Goal goal;

    private ActivityType activityType;

    private WorkoutType workoutType;

    
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getWrist() {
        return wrist;
    }

    public void setWrist(Double wrist) {
        this.wrist = wrist;
    }

    public Double getWaist() {
        return waist;
    }

    public void setWaist(Double waist) {
        this.waist = waist;
    }

    public Double getHip() {
        return hip;
    }

    public void setHip(Double hip) {
        this.hip = hip;
    }

    public Double getAbdomen() {
        return abdomen;
    }

    public void setAbdomen(Double abdomen) {
        this.abdomen = abdomen;
    }

    public Double getNeck() {
        return neck;
    }

    public void setNeck(Double neck) {
        this.neck = neck;
    }

    public Double getWaistHipRatio() {
        return waistHipRatio;
    }

    public void setWaistHipRatio(Double waistHipRatio) {
        this.waistHipRatio = waistHipRatio;
    }

    public Double getIdealWeightMin() {
        return idealWeightMin;
    }

    public void setIdealWeightMin(Double idealWeightMin) {
        this.idealWeightMin = idealWeightMin;
    }

    public Double getIdealWeightMax() {
        return idealWeightMax;
    }

    public void setIdealWeightMax(Double idealWeightMax) {
        this.idealWeightMax = idealWeightMax;
    }

    public Double getBestWeight() {
        return bestWeight;
    }

    public void setBestWeight(Double bestWeight) {
        this.bestWeight = bestWeight;
    }

    public Double getBmi() {
        return bmi;
    }

    public void setBmi(Double bmi) {
        this.bmi = bmi;
    }

    public BMICategory getBmiCategory() {
        return bmiCategory;
    }

    public void setBmiCategory(BMICategory bmiCategory) {
        this.bmiCategory = bmiCategory;
    }

    public Double getBmr() {
        return bmr;
    }

    public void setBmr(Double bmr) {
        this.bmr = bmr;
    }

    public Double getLbm() {
        return lbm;
    }

    public void setLbm(Double lbm) {
        this.lbm = lbm;
    }

    public Double getPbf() {
        return pbf;
    }

    public void setPbf(Double pbf) {
        this.pbf = pbf;
    }

    public BodyFatCategory getBodyFatCategory() {
        return bodyFatCategory;
    }

    public void setBodyFatCategory(BodyFatCategory bodyFatCategory) {
        this.bodyFatCategory = bodyFatCategory;
    }

    public Double getSmm() {
        return smm;
    }

    public void setSmm(Double smm) {
        this.smm = smm;
    }

    public Double getBodyTypeNumber() {
        return bodyTypeNumber;
    }

    public void setBodyTypeNumber(Double bodyTypeNumber) {
        this.bodyTypeNumber = bodyTypeNumber;
    }

    public BodyType getBodyType() {
        return bodyType;
    }

    public void setBodyType(BodyType bodyType) {
        this.bodyType = bodyType;
    }

    public Double getFrameSize() {
        return frameSize;
    }

    public void setFrameSize(Double frameSize) {
        this.frameSize = frameSize;
    }

    public BodyFrameSize getBodyFrameSize() {
        return bodyFrameSize;
    }

    public void setBodyFrameSize(BodyFrameSize bodyFrameSize) {
        this.bodyFrameSize = bodyFrameSize;
    }

    public Double getVisceralFatLevel() {
        return visceralFatLevel;
    }

    public void setVisceralFatLevel(Double visceralFatLevel) {
        this.visceralFatLevel = visceralFatLevel;
    }

    public WorkoutLocation getWorkoutLocation() {
        return workoutLocation;
    }

    public void setWorkoutLocation(WorkoutLocation workoutLocation) {
        this.workoutLocation = workoutLocation;
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    public WorkoutType getWorkoutType() {
        return workoutType;
    }

    public void setWorkoutType(WorkoutType workoutType) {
        this.workoutType = workoutType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BodyCompositionDTO)) {
            return false;
        }

        return id != null && id.equals(((BodyCompositionDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BodyCompositionDTO{" +
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
