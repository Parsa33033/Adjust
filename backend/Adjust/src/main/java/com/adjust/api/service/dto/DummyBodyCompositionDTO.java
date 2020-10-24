package com.adjust.api.service.dto;

public class DummyBodyCompositionDTO extends BodyCompositionDTO {

    public DummyBodyCompositionDTO() {
    }

    public DummyBodyCompositionDTO(BodyCompositionDTO bodyCompositionDTO) {
        this.setWrist(bodyCompositionDTO.getWrist());
        this.setWaist(bodyCompositionDTO.getWaist());
        this.setGender(bodyCompositionDTO.getGender());
        this.setAge(bodyCompositionDTO.getAge());
        this.setCreatedAt(bodyCompositionDTO.getCreatedAt());
        this.setBmi(bodyCompositionDTO.getBmi());
        this.setWeight(bodyCompositionDTO.getWeight());
        this.setHeight(bodyCompositionDTO.getHeight());
        this.setId(bodyCompositionDTO.getId());
        this.setAbdomen(bodyCompositionDTO.getAbdomen());
        this.setActivityType(bodyCompositionDTO.getActivityType());
        this.setBestWeight(bodyCompositionDTO.getBestWeight());
        this.setBmiCategory(bodyCompositionDTO.getBmiCategory());
        this.setBmr(bodyCompositionDTO.getBmr());
        this.setBodyFatCategory(bodyCompositionDTO.getBodyFatCategory());
        this.setBodyFrameSize(bodyCompositionDTO.getBodyFrameSize());
        this.setBodyType(bodyCompositionDTO.getBodyType());
        this.setFrameSize(bodyCompositionDTO.getFrameSize());
        this.setBodyTypeNumber(bodyCompositionDTO.getBodyTypeNumber());
        this.setGoal(bodyCompositionDTO.getGoal());
        this.setHip(bodyCompositionDTO.getHip());
        this.setIdealWeightMax(bodyCompositionDTO.getIdealWeightMax());
        this.setIdealWeightMin(bodyCompositionDTO.getIdealWeightMin());
        this.setLbm(bodyCompositionDTO.getLbm());
        this.setNeck(bodyCompositionDTO.getNeck());
        this.setPbf(bodyCompositionDTO.getPbf());
        this.setSmm(bodyCompositionDTO.getSmm());
        this.setVisceralFatLevel(bodyCompositionDTO.getVisceralFatLevel());
        this.setWaistHipRatio(bodyCompositionDTO.getWaistHipRatio());
        this.setWorkoutLocation(bodyCompositionDTO.getWorkoutLocation());
        this.setWorkoutType(bodyCompositionDTO.getWorkoutType());
    }
}
