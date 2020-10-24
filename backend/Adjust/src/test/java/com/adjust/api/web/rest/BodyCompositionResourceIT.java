package com.adjust.api.web.rest;

import com.adjust.api.AdjustApp;
import com.adjust.api.domain.BodyComposition;
import com.adjust.api.repository.BodyCompositionRepository;
import com.adjust.api.service.BodyCompositionService;
import com.adjust.api.service.dto.BodyCompositionDTO;
import com.adjust.api.service.mapper.BodyCompositionMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
 * Integration tests for the {@link BodyCompositionResource} REST controller.
 */
@SpringBootTest(classes = AdjustApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BodyCompositionResourceIT {

    private static final LocalDate DEFAULT_CREATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_AT = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_AGE = 1;
    private static final Integer UPDATED_AGE = 2;

    private static final Gender DEFAULT_GENDER = Gender.MALE;
    private static final Gender UPDATED_GENDER = Gender.FEMALE;

    private static final Double DEFAULT_HEIGHT = 1D;
    private static final Double UPDATED_HEIGHT = 2D;

    private static final Double DEFAULT_WEIGHT = 1D;
    private static final Double UPDATED_WEIGHT = 2D;

    private static final Double DEFAULT_WRIST = 1D;
    private static final Double UPDATED_WRIST = 2D;

    private static final Double DEFAULT_WAIST = 1D;
    private static final Double UPDATED_WAIST = 2D;

    private static final Double DEFAULT_HIP = 1D;
    private static final Double UPDATED_HIP = 2D;

    private static final Double DEFAULT_ABDOMEN = 1D;
    private static final Double UPDATED_ABDOMEN = 2D;

    private static final Double DEFAULT_NECK = 1D;
    private static final Double UPDATED_NECK = 2D;

    private static final Double DEFAULT_WAIST_HIP_RATIO = 1D;
    private static final Double UPDATED_WAIST_HIP_RATIO = 2D;

    private static final Double DEFAULT_IDEAL_WEIGHT_MIN = 1D;
    private static final Double UPDATED_IDEAL_WEIGHT_MIN = 2D;

    private static final Double DEFAULT_IDEAL_WEIGHT_MAX = 1D;
    private static final Double UPDATED_IDEAL_WEIGHT_MAX = 2D;

    private static final Double DEFAULT_BEST_WEIGHT = 1D;
    private static final Double UPDATED_BEST_WEIGHT = 2D;

    private static final Double DEFAULT_BMI = 1D;
    private static final Double UPDATED_BMI = 2D;

    private static final BMICategory DEFAULT_BMI_CATEGORY = BMICategory.UNDERWEIGHT;
    private static final BMICategory UPDATED_BMI_CATEGORY = BMICategory.NORMAL;

    private static final Double DEFAULT_BMR = 1D;
    private static final Double UPDATED_BMR = 2D;

    private static final Double DEFAULT_LBM = 1D;
    private static final Double UPDATED_LBM = 2D;

    private static final Double DEFAULT_PBF = 1D;
    private static final Double UPDATED_PBF = 2D;

    private static final BodyFatCategory DEFAULT_BODY_FAT_CATEGORY = BodyFatCategory.ESSENTIAL;
    private static final BodyFatCategory UPDATED_BODY_FAT_CATEGORY = BodyFatCategory.ATHLETE;

    private static final Double DEFAULT_SMM = 1D;
    private static final Double UPDATED_SMM = 2D;

    private static final Double DEFAULT_BODY_TYPE_NUMBER = 1D;
    private static final Double UPDATED_BODY_TYPE_NUMBER = 2D;

    private static final BodyType DEFAULT_BODY_TYPE = BodyType.MESOMORPH;
    private static final BodyType UPDATED_BODY_TYPE = BodyType.ECTOMORPH;

    private static final Double DEFAULT_FRAME_SIZE = 1D;
    private static final Double UPDATED_FRAME_SIZE = 2D;

    private static final BodyFrameSize DEFAULT_BODY_FRAME_SIZE = BodyFrameSize.SMALL;
    private static final BodyFrameSize UPDATED_BODY_FRAME_SIZE = BodyFrameSize.MEDIUM;

    private static final Double DEFAULT_VISCERAL_FAT_LEVEL = 1D;
    private static final Double UPDATED_VISCERAL_FAT_LEVEL = 2D;

    private static final WorkoutLocation DEFAULT_WORKOUT_LOCATION = WorkoutLocation.HOME;
    private static final WorkoutLocation UPDATED_WORKOUT_LOCATION = WorkoutLocation.GYM;

    private static final Goal DEFAULT_GOAL = Goal.DECREASEWEIGHT;
    private static final Goal UPDATED_GOAL = Goal.INCREASEWEIGHT;

    private static final ActivityType DEFAULT_ACTIVITY_TYPE = ActivityType.SEDENTARY;
    private static final ActivityType UPDATED_ACTIVITY_TYPE = ActivityType.LIGHT;

    private static final WorkoutType DEFAULT_WORKOUT_TYPE = WorkoutType.POWERLIFTING;
    private static final WorkoutType UPDATED_WORKOUT_TYPE = WorkoutType.BODYBUILDING;

    @Autowired
    private BodyCompositionRepository bodyCompositionRepository;

    @Autowired
    private BodyCompositionMapper bodyCompositionMapper;

    @Autowired
    private BodyCompositionService bodyCompositionService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBodyCompositionMockMvc;

    private BodyComposition bodyComposition;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BodyComposition createEntity(EntityManager em) {
        BodyComposition bodyComposition = new BodyComposition()
            .createdAt(DEFAULT_CREATED_AT)
            .age(DEFAULT_AGE)
            .gender(DEFAULT_GENDER)
            .height(DEFAULT_HEIGHT)
            .weight(DEFAULT_WEIGHT)
            .wrist(DEFAULT_WRIST)
            .waist(DEFAULT_WAIST)
            .hip(DEFAULT_HIP)
            .abdomen(DEFAULT_ABDOMEN)
            .neck(DEFAULT_NECK)
            .waistHipRatio(DEFAULT_WAIST_HIP_RATIO)
            .idealWeightMin(DEFAULT_IDEAL_WEIGHT_MIN)
            .idealWeightMax(DEFAULT_IDEAL_WEIGHT_MAX)
            .bestWeight(DEFAULT_BEST_WEIGHT)
            .bmi(DEFAULT_BMI)
            .bmiCategory(DEFAULT_BMI_CATEGORY)
            .bmr(DEFAULT_BMR)
            .lbm(DEFAULT_LBM)
            .pbf(DEFAULT_PBF)
            .bodyFatCategory(DEFAULT_BODY_FAT_CATEGORY)
            .smm(DEFAULT_SMM)
            .bodyTypeNumber(DEFAULT_BODY_TYPE_NUMBER)
            .bodyType(DEFAULT_BODY_TYPE)
            .frameSize(DEFAULT_FRAME_SIZE)
            .bodyFrameSize(DEFAULT_BODY_FRAME_SIZE)
            .visceralFatLevel(DEFAULT_VISCERAL_FAT_LEVEL)
            .workoutLocation(DEFAULT_WORKOUT_LOCATION)
            .goal(DEFAULT_GOAL)
            .activityType(DEFAULT_ACTIVITY_TYPE)
            .workoutType(DEFAULT_WORKOUT_TYPE);
        return bodyComposition;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BodyComposition createUpdatedEntity(EntityManager em) {
        BodyComposition bodyComposition = new BodyComposition()
            .createdAt(UPDATED_CREATED_AT)
            .age(UPDATED_AGE)
            .gender(UPDATED_GENDER)
            .height(UPDATED_HEIGHT)
            .weight(UPDATED_WEIGHT)
            .wrist(UPDATED_WRIST)
            .waist(UPDATED_WAIST)
            .hip(UPDATED_HIP)
            .abdomen(UPDATED_ABDOMEN)
            .neck(UPDATED_NECK)
            .waistHipRatio(UPDATED_WAIST_HIP_RATIO)
            .idealWeightMin(UPDATED_IDEAL_WEIGHT_MIN)
            .idealWeightMax(UPDATED_IDEAL_WEIGHT_MAX)
            .bestWeight(UPDATED_BEST_WEIGHT)
            .bmi(UPDATED_BMI)
            .bmiCategory(UPDATED_BMI_CATEGORY)
            .bmr(UPDATED_BMR)
            .lbm(UPDATED_LBM)
            .pbf(UPDATED_PBF)
            .bodyFatCategory(UPDATED_BODY_FAT_CATEGORY)
            .smm(UPDATED_SMM)
            .bodyTypeNumber(UPDATED_BODY_TYPE_NUMBER)
            .bodyType(UPDATED_BODY_TYPE)
            .frameSize(UPDATED_FRAME_SIZE)
            .bodyFrameSize(UPDATED_BODY_FRAME_SIZE)
            .visceralFatLevel(UPDATED_VISCERAL_FAT_LEVEL)
            .workoutLocation(UPDATED_WORKOUT_LOCATION)
            .goal(UPDATED_GOAL)
            .activityType(UPDATED_ACTIVITY_TYPE)
            .workoutType(UPDATED_WORKOUT_TYPE);
        return bodyComposition;
    }

    @BeforeEach
    public void initTest() {
        bodyComposition = createEntity(em);
    }

    @Test
    @Transactional
    public void createBodyComposition() throws Exception {
        int databaseSizeBeforeCreate = bodyCompositionRepository.findAll().size();
        // Create the BodyComposition
        BodyCompositionDTO bodyCompositionDTO = bodyCompositionMapper.toDto(bodyComposition);
        restBodyCompositionMockMvc.perform(post("/api/body-compositions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bodyCompositionDTO)))
            .andExpect(status().isCreated());

        // Validate the BodyComposition in the database
        List<BodyComposition> bodyCompositionList = bodyCompositionRepository.findAll();
        assertThat(bodyCompositionList).hasSize(databaseSizeBeforeCreate + 1);
        BodyComposition testBodyComposition = bodyCompositionList.get(bodyCompositionList.size() - 1);
        assertThat(testBodyComposition.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testBodyComposition.getAge()).isEqualTo(DEFAULT_AGE);
        assertThat(testBodyComposition.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testBodyComposition.getHeight()).isEqualTo(DEFAULT_HEIGHT);
        assertThat(testBodyComposition.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testBodyComposition.getWrist()).isEqualTo(DEFAULT_WRIST);
        assertThat(testBodyComposition.getWaist()).isEqualTo(DEFAULT_WAIST);
        assertThat(testBodyComposition.getHip()).isEqualTo(DEFAULT_HIP);
        assertThat(testBodyComposition.getAbdomen()).isEqualTo(DEFAULT_ABDOMEN);
        assertThat(testBodyComposition.getNeck()).isEqualTo(DEFAULT_NECK);
        assertThat(testBodyComposition.getWaistHipRatio()).isEqualTo(DEFAULT_WAIST_HIP_RATIO);
        assertThat(testBodyComposition.getIdealWeightMin()).isEqualTo(DEFAULT_IDEAL_WEIGHT_MIN);
        assertThat(testBodyComposition.getIdealWeightMax()).isEqualTo(DEFAULT_IDEAL_WEIGHT_MAX);
        assertThat(testBodyComposition.getBestWeight()).isEqualTo(DEFAULT_BEST_WEIGHT);
        assertThat(testBodyComposition.getBmi()).isEqualTo(DEFAULT_BMI);
        assertThat(testBodyComposition.getBmiCategory()).isEqualTo(DEFAULT_BMI_CATEGORY);
        assertThat(testBodyComposition.getBmr()).isEqualTo(DEFAULT_BMR);
        assertThat(testBodyComposition.getLbm()).isEqualTo(DEFAULT_LBM);
        assertThat(testBodyComposition.getPbf()).isEqualTo(DEFAULT_PBF);
        assertThat(testBodyComposition.getBodyFatCategory()).isEqualTo(DEFAULT_BODY_FAT_CATEGORY);
        assertThat(testBodyComposition.getSmm()).isEqualTo(DEFAULT_SMM);
        assertThat(testBodyComposition.getBodyTypeNumber()).isEqualTo(DEFAULT_BODY_TYPE_NUMBER);
        assertThat(testBodyComposition.getBodyType()).isEqualTo(DEFAULT_BODY_TYPE);
        assertThat(testBodyComposition.getFrameSize()).isEqualTo(DEFAULT_FRAME_SIZE);
        assertThat(testBodyComposition.getBodyFrameSize()).isEqualTo(DEFAULT_BODY_FRAME_SIZE);
        assertThat(testBodyComposition.getVisceralFatLevel()).isEqualTo(DEFAULT_VISCERAL_FAT_LEVEL);
        assertThat(testBodyComposition.getWorkoutLocation()).isEqualTo(DEFAULT_WORKOUT_LOCATION);
        assertThat(testBodyComposition.getGoal()).isEqualTo(DEFAULT_GOAL);
        assertThat(testBodyComposition.getActivityType()).isEqualTo(DEFAULT_ACTIVITY_TYPE);
        assertThat(testBodyComposition.getWorkoutType()).isEqualTo(DEFAULT_WORKOUT_TYPE);
    }

    @Test
    @Transactional
    public void createBodyCompositionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bodyCompositionRepository.findAll().size();

        // Create the BodyComposition with an existing ID
        bodyComposition.setId(1L);
        BodyCompositionDTO bodyCompositionDTO = bodyCompositionMapper.toDto(bodyComposition);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBodyCompositionMockMvc.perform(post("/api/body-compositions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bodyCompositionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BodyComposition in the database
        List<BodyComposition> bodyCompositionList = bodyCompositionRepository.findAll();
        assertThat(bodyCompositionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBodyCompositions() throws Exception {
        // Initialize the database
        bodyCompositionRepository.saveAndFlush(bodyComposition);

        // Get all the bodyCompositionList
        restBodyCompositionMockMvc.perform(get("/api/body-compositions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bodyComposition.getId().intValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].age").value(hasItem(DEFAULT_AGE)))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.toString())))
            .andExpect(jsonPath("$.[*].height").value(hasItem(DEFAULT_HEIGHT.doubleValue())))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT.doubleValue())))
            .andExpect(jsonPath("$.[*].wrist").value(hasItem(DEFAULT_WRIST.doubleValue())))
            .andExpect(jsonPath("$.[*].waist").value(hasItem(DEFAULT_WAIST.doubleValue())))
            .andExpect(jsonPath("$.[*].hip").value(hasItem(DEFAULT_HIP.doubleValue())))
            .andExpect(jsonPath("$.[*].abdomen").value(hasItem(DEFAULT_ABDOMEN.doubleValue())))
            .andExpect(jsonPath("$.[*].neck").value(hasItem(DEFAULT_NECK.doubleValue())))
            .andExpect(jsonPath("$.[*].waistHipRatio").value(hasItem(DEFAULT_WAIST_HIP_RATIO.doubleValue())))
            .andExpect(jsonPath("$.[*].idealWeightMin").value(hasItem(DEFAULT_IDEAL_WEIGHT_MIN.doubleValue())))
            .andExpect(jsonPath("$.[*].idealWeightMax").value(hasItem(DEFAULT_IDEAL_WEIGHT_MAX.doubleValue())))
            .andExpect(jsonPath("$.[*].bestWeight").value(hasItem(DEFAULT_BEST_WEIGHT.doubleValue())))
            .andExpect(jsonPath("$.[*].bmi").value(hasItem(DEFAULT_BMI.doubleValue())))
            .andExpect(jsonPath("$.[*].bmiCategory").value(hasItem(DEFAULT_BMI_CATEGORY.toString())))
            .andExpect(jsonPath("$.[*].bmr").value(hasItem(DEFAULT_BMR.doubleValue())))
            .andExpect(jsonPath("$.[*].lbm").value(hasItem(DEFAULT_LBM.doubleValue())))
            .andExpect(jsonPath("$.[*].pbf").value(hasItem(DEFAULT_PBF.doubleValue())))
            .andExpect(jsonPath("$.[*].bodyFatCategory").value(hasItem(DEFAULT_BODY_FAT_CATEGORY.toString())))
            .andExpect(jsonPath("$.[*].smm").value(hasItem(DEFAULT_SMM.doubleValue())))
            .andExpect(jsonPath("$.[*].bodyTypeNumber").value(hasItem(DEFAULT_BODY_TYPE_NUMBER.doubleValue())))
            .andExpect(jsonPath("$.[*].bodyType").value(hasItem(DEFAULT_BODY_TYPE.toString())))
            .andExpect(jsonPath("$.[*].frameSize").value(hasItem(DEFAULT_FRAME_SIZE.doubleValue())))
            .andExpect(jsonPath("$.[*].bodyFrameSize").value(hasItem(DEFAULT_BODY_FRAME_SIZE.toString())))
            .andExpect(jsonPath("$.[*].visceralFatLevel").value(hasItem(DEFAULT_VISCERAL_FAT_LEVEL.doubleValue())))
            .andExpect(jsonPath("$.[*].workoutLocation").value(hasItem(DEFAULT_WORKOUT_LOCATION.toString())))
            .andExpect(jsonPath("$.[*].goal").value(hasItem(DEFAULT_GOAL.toString())))
            .andExpect(jsonPath("$.[*].activityType").value(hasItem(DEFAULT_ACTIVITY_TYPE.toString())))
            .andExpect(jsonPath("$.[*].workoutType").value(hasItem(DEFAULT_WORKOUT_TYPE.toString())));
    }
    
    @Test
    @Transactional
    public void getBodyComposition() throws Exception {
        // Initialize the database
        bodyCompositionRepository.saveAndFlush(bodyComposition);

        // Get the bodyComposition
        restBodyCompositionMockMvc.perform(get("/api/body-compositions/{id}", bodyComposition.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bodyComposition.getId().intValue()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.age").value(DEFAULT_AGE))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER.toString()))
            .andExpect(jsonPath("$.height").value(DEFAULT_HEIGHT.doubleValue()))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT.doubleValue()))
            .andExpect(jsonPath("$.wrist").value(DEFAULT_WRIST.doubleValue()))
            .andExpect(jsonPath("$.waist").value(DEFAULT_WAIST.doubleValue()))
            .andExpect(jsonPath("$.hip").value(DEFAULT_HIP.doubleValue()))
            .andExpect(jsonPath("$.abdomen").value(DEFAULT_ABDOMEN.doubleValue()))
            .andExpect(jsonPath("$.neck").value(DEFAULT_NECK.doubleValue()))
            .andExpect(jsonPath("$.waistHipRatio").value(DEFAULT_WAIST_HIP_RATIO.doubleValue()))
            .andExpect(jsonPath("$.idealWeightMin").value(DEFAULT_IDEAL_WEIGHT_MIN.doubleValue()))
            .andExpect(jsonPath("$.idealWeightMax").value(DEFAULT_IDEAL_WEIGHT_MAX.doubleValue()))
            .andExpect(jsonPath("$.bestWeight").value(DEFAULT_BEST_WEIGHT.doubleValue()))
            .andExpect(jsonPath("$.bmi").value(DEFAULT_BMI.doubleValue()))
            .andExpect(jsonPath("$.bmiCategory").value(DEFAULT_BMI_CATEGORY.toString()))
            .andExpect(jsonPath("$.bmr").value(DEFAULT_BMR.doubleValue()))
            .andExpect(jsonPath("$.lbm").value(DEFAULT_LBM.doubleValue()))
            .andExpect(jsonPath("$.pbf").value(DEFAULT_PBF.doubleValue()))
            .andExpect(jsonPath("$.bodyFatCategory").value(DEFAULT_BODY_FAT_CATEGORY.toString()))
            .andExpect(jsonPath("$.smm").value(DEFAULT_SMM.doubleValue()))
            .andExpect(jsonPath("$.bodyTypeNumber").value(DEFAULT_BODY_TYPE_NUMBER.doubleValue()))
            .andExpect(jsonPath("$.bodyType").value(DEFAULT_BODY_TYPE.toString()))
            .andExpect(jsonPath("$.frameSize").value(DEFAULT_FRAME_SIZE.doubleValue()))
            .andExpect(jsonPath("$.bodyFrameSize").value(DEFAULT_BODY_FRAME_SIZE.toString()))
            .andExpect(jsonPath("$.visceralFatLevel").value(DEFAULT_VISCERAL_FAT_LEVEL.doubleValue()))
            .andExpect(jsonPath("$.workoutLocation").value(DEFAULT_WORKOUT_LOCATION.toString()))
            .andExpect(jsonPath("$.goal").value(DEFAULT_GOAL.toString()))
            .andExpect(jsonPath("$.activityType").value(DEFAULT_ACTIVITY_TYPE.toString()))
            .andExpect(jsonPath("$.workoutType").value(DEFAULT_WORKOUT_TYPE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingBodyComposition() throws Exception {
        // Get the bodyComposition
        restBodyCompositionMockMvc.perform(get("/api/body-compositions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBodyComposition() throws Exception {
        // Initialize the database
        bodyCompositionRepository.saveAndFlush(bodyComposition);

        int databaseSizeBeforeUpdate = bodyCompositionRepository.findAll().size();

        // Update the bodyComposition
        BodyComposition updatedBodyComposition = bodyCompositionRepository.findById(bodyComposition.getId()).get();
        // Disconnect from session so that the updates on updatedBodyComposition are not directly saved in db
        em.detach(updatedBodyComposition);
        updatedBodyComposition
            .createdAt(UPDATED_CREATED_AT)
            .age(UPDATED_AGE)
            .gender(UPDATED_GENDER)
            .height(UPDATED_HEIGHT)
            .weight(UPDATED_WEIGHT)
            .wrist(UPDATED_WRIST)
            .waist(UPDATED_WAIST)
            .hip(UPDATED_HIP)
            .abdomen(UPDATED_ABDOMEN)
            .neck(UPDATED_NECK)
            .waistHipRatio(UPDATED_WAIST_HIP_RATIO)
            .idealWeightMin(UPDATED_IDEAL_WEIGHT_MIN)
            .idealWeightMax(UPDATED_IDEAL_WEIGHT_MAX)
            .bestWeight(UPDATED_BEST_WEIGHT)
            .bmi(UPDATED_BMI)
            .bmiCategory(UPDATED_BMI_CATEGORY)
            .bmr(UPDATED_BMR)
            .lbm(UPDATED_LBM)
            .pbf(UPDATED_PBF)
            .bodyFatCategory(UPDATED_BODY_FAT_CATEGORY)
            .smm(UPDATED_SMM)
            .bodyTypeNumber(UPDATED_BODY_TYPE_NUMBER)
            .bodyType(UPDATED_BODY_TYPE)
            .frameSize(UPDATED_FRAME_SIZE)
            .bodyFrameSize(UPDATED_BODY_FRAME_SIZE)
            .visceralFatLevel(UPDATED_VISCERAL_FAT_LEVEL)
            .workoutLocation(UPDATED_WORKOUT_LOCATION)
            .goal(UPDATED_GOAL)
            .activityType(UPDATED_ACTIVITY_TYPE)
            .workoutType(UPDATED_WORKOUT_TYPE);
        BodyCompositionDTO bodyCompositionDTO = bodyCompositionMapper.toDto(updatedBodyComposition);

        restBodyCompositionMockMvc.perform(put("/api/body-compositions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bodyCompositionDTO)))
            .andExpect(status().isOk());

        // Validate the BodyComposition in the database
        List<BodyComposition> bodyCompositionList = bodyCompositionRepository.findAll();
        assertThat(bodyCompositionList).hasSize(databaseSizeBeforeUpdate);
        BodyComposition testBodyComposition = bodyCompositionList.get(bodyCompositionList.size() - 1);
        assertThat(testBodyComposition.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testBodyComposition.getAge()).isEqualTo(UPDATED_AGE);
        assertThat(testBodyComposition.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testBodyComposition.getHeight()).isEqualTo(UPDATED_HEIGHT);
        assertThat(testBodyComposition.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testBodyComposition.getWrist()).isEqualTo(UPDATED_WRIST);
        assertThat(testBodyComposition.getWaist()).isEqualTo(UPDATED_WAIST);
        assertThat(testBodyComposition.getHip()).isEqualTo(UPDATED_HIP);
        assertThat(testBodyComposition.getAbdomen()).isEqualTo(UPDATED_ABDOMEN);
        assertThat(testBodyComposition.getNeck()).isEqualTo(UPDATED_NECK);
        assertThat(testBodyComposition.getWaistHipRatio()).isEqualTo(UPDATED_WAIST_HIP_RATIO);
        assertThat(testBodyComposition.getIdealWeightMin()).isEqualTo(UPDATED_IDEAL_WEIGHT_MIN);
        assertThat(testBodyComposition.getIdealWeightMax()).isEqualTo(UPDATED_IDEAL_WEIGHT_MAX);
        assertThat(testBodyComposition.getBestWeight()).isEqualTo(UPDATED_BEST_WEIGHT);
        assertThat(testBodyComposition.getBmi()).isEqualTo(UPDATED_BMI);
        assertThat(testBodyComposition.getBmiCategory()).isEqualTo(UPDATED_BMI_CATEGORY);
        assertThat(testBodyComposition.getBmr()).isEqualTo(UPDATED_BMR);
        assertThat(testBodyComposition.getLbm()).isEqualTo(UPDATED_LBM);
        assertThat(testBodyComposition.getPbf()).isEqualTo(UPDATED_PBF);
        assertThat(testBodyComposition.getBodyFatCategory()).isEqualTo(UPDATED_BODY_FAT_CATEGORY);
        assertThat(testBodyComposition.getSmm()).isEqualTo(UPDATED_SMM);
        assertThat(testBodyComposition.getBodyTypeNumber()).isEqualTo(UPDATED_BODY_TYPE_NUMBER);
        assertThat(testBodyComposition.getBodyType()).isEqualTo(UPDATED_BODY_TYPE);
        assertThat(testBodyComposition.getFrameSize()).isEqualTo(UPDATED_FRAME_SIZE);
        assertThat(testBodyComposition.getBodyFrameSize()).isEqualTo(UPDATED_BODY_FRAME_SIZE);
        assertThat(testBodyComposition.getVisceralFatLevel()).isEqualTo(UPDATED_VISCERAL_FAT_LEVEL);
        assertThat(testBodyComposition.getWorkoutLocation()).isEqualTo(UPDATED_WORKOUT_LOCATION);
        assertThat(testBodyComposition.getGoal()).isEqualTo(UPDATED_GOAL);
        assertThat(testBodyComposition.getActivityType()).isEqualTo(UPDATED_ACTIVITY_TYPE);
        assertThat(testBodyComposition.getWorkoutType()).isEqualTo(UPDATED_WORKOUT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingBodyComposition() throws Exception {
        int databaseSizeBeforeUpdate = bodyCompositionRepository.findAll().size();

        // Create the BodyComposition
        BodyCompositionDTO bodyCompositionDTO = bodyCompositionMapper.toDto(bodyComposition);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBodyCompositionMockMvc.perform(put("/api/body-compositions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bodyCompositionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BodyComposition in the database
        List<BodyComposition> bodyCompositionList = bodyCompositionRepository.findAll();
        assertThat(bodyCompositionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBodyComposition() throws Exception {
        // Initialize the database
        bodyCompositionRepository.saveAndFlush(bodyComposition);

        int databaseSizeBeforeDelete = bodyCompositionRepository.findAll().size();

        // Delete the bodyComposition
        restBodyCompositionMockMvc.perform(delete("/api/body-compositions/{id}", bodyComposition.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BodyComposition> bodyCompositionList = bodyCompositionRepository.findAll();
        assertThat(bodyCompositionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
