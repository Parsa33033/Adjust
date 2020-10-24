package com.adjust.api.web.rest;

import com.adjust.api.AdjustApp;
import com.adjust.api.domain.Development;
import com.adjust.api.repository.DevelopmentRepository;
import com.adjust.api.service.DevelopmentService;
import com.adjust.api.service.dto.DevelopmentDTO;
import com.adjust.api.service.mapper.DevelopmentMapper;

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

/**
 * Integration tests for the {@link DevelopmentResource} REST controller.
 */
@SpringBootTest(classes = AdjustApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DevelopmentResourceIT {

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_NUTRITION_SCORE = 1D;
    private static final Double UPDATED_NUTRITION_SCORE = 2D;

    private static final Double DEFAULT_FITNESS_SCORE = 1D;
    private static final Double UPDATED_FITNESS_SCORE = 2D;

    private static final Double DEFAULT_WEIGHT = 1D;
    private static final Double UPDATED_WEIGHT = 2D;

    private static final Double DEFAULT_BMI = 1D;
    private static final Double UPDATED_BMI = 2D;

    private static final Double DEFAULT_LBM = 1D;
    private static final Double UPDATED_LBM = 2D;

    private static final Double DEFAULT_PBF = 1D;
    private static final Double UPDATED_PBF = 2D;

    @Autowired
    private DevelopmentRepository developmentRepository;

    @Autowired
    private DevelopmentMapper developmentMapper;

    @Autowired
    private DevelopmentService developmentService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDevelopmentMockMvc;

    private Development development;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Development createEntity(EntityManager em) {
        Development development = new Development()
            .date(DEFAULT_DATE)
            .nutritionScore(DEFAULT_NUTRITION_SCORE)
            .fitnessScore(DEFAULT_FITNESS_SCORE)
            .weight(DEFAULT_WEIGHT)
            .bmi(DEFAULT_BMI)
            .lbm(DEFAULT_LBM)
            .pbf(DEFAULT_PBF);
        return development;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Development createUpdatedEntity(EntityManager em) {
        Development development = new Development()
            .date(UPDATED_DATE)
            .nutritionScore(UPDATED_NUTRITION_SCORE)
            .fitnessScore(UPDATED_FITNESS_SCORE)
            .weight(UPDATED_WEIGHT)
            .bmi(UPDATED_BMI)
            .lbm(UPDATED_LBM)
            .pbf(UPDATED_PBF);
        return development;
    }

    @BeforeEach
    public void initTest() {
        development = createEntity(em);
    }

    @Test
    @Transactional
    public void createDevelopment() throws Exception {
        int databaseSizeBeforeCreate = developmentRepository.findAll().size();
        // Create the Development
        DevelopmentDTO developmentDTO = developmentMapper.toDto(development);
        restDevelopmentMockMvc.perform(post("/api/developments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(developmentDTO)))
            .andExpect(status().isCreated());

        // Validate the Development in the database
        List<Development> developmentList = developmentRepository.findAll();
        assertThat(developmentList).hasSize(databaseSizeBeforeCreate + 1);
        Development testDevelopment = developmentList.get(developmentList.size() - 1);
        assertThat(testDevelopment.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testDevelopment.getNutritionScore()).isEqualTo(DEFAULT_NUTRITION_SCORE);
        assertThat(testDevelopment.getFitnessScore()).isEqualTo(DEFAULT_FITNESS_SCORE);
        assertThat(testDevelopment.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testDevelopment.getBmi()).isEqualTo(DEFAULT_BMI);
        assertThat(testDevelopment.getLbm()).isEqualTo(DEFAULT_LBM);
        assertThat(testDevelopment.getPbf()).isEqualTo(DEFAULT_PBF);
    }

    @Test
    @Transactional
    public void createDevelopmentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = developmentRepository.findAll().size();

        // Create the Development with an existing ID
        development.setId(1L);
        DevelopmentDTO developmentDTO = developmentMapper.toDto(development);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDevelopmentMockMvc.perform(post("/api/developments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(developmentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Development in the database
        List<Development> developmentList = developmentRepository.findAll();
        assertThat(developmentList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDevelopments() throws Exception {
        // Initialize the database
        developmentRepository.saveAndFlush(development);

        // Get all the developmentList
        restDevelopmentMockMvc.perform(get("/api/developments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(development.getId().intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].nutritionScore").value(hasItem(DEFAULT_NUTRITION_SCORE.doubleValue())))
            .andExpect(jsonPath("$.[*].fitnessScore").value(hasItem(DEFAULT_FITNESS_SCORE.doubleValue())))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT.doubleValue())))
            .andExpect(jsonPath("$.[*].bmi").value(hasItem(DEFAULT_BMI.doubleValue())))
            .andExpect(jsonPath("$.[*].lbm").value(hasItem(DEFAULT_LBM.doubleValue())))
            .andExpect(jsonPath("$.[*].pbf").value(hasItem(DEFAULT_PBF.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getDevelopment() throws Exception {
        // Initialize the database
        developmentRepository.saveAndFlush(development);

        // Get the development
        restDevelopmentMockMvc.perform(get("/api/developments/{id}", development.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(development.getId().intValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.nutritionScore").value(DEFAULT_NUTRITION_SCORE.doubleValue()))
            .andExpect(jsonPath("$.fitnessScore").value(DEFAULT_FITNESS_SCORE.doubleValue()))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT.doubleValue()))
            .andExpect(jsonPath("$.bmi").value(DEFAULT_BMI.doubleValue()))
            .andExpect(jsonPath("$.lbm").value(DEFAULT_LBM.doubleValue()))
            .andExpect(jsonPath("$.pbf").value(DEFAULT_PBF.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingDevelopment() throws Exception {
        // Get the development
        restDevelopmentMockMvc.perform(get("/api/developments/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDevelopment() throws Exception {
        // Initialize the database
        developmentRepository.saveAndFlush(development);

        int databaseSizeBeforeUpdate = developmentRepository.findAll().size();

        // Update the development
        Development updatedDevelopment = developmentRepository.findById(development.getId()).get();
        // Disconnect from session so that the updates on updatedDevelopment are not directly saved in db
        em.detach(updatedDevelopment);
        updatedDevelopment
            .date(UPDATED_DATE)
            .nutritionScore(UPDATED_NUTRITION_SCORE)
            .fitnessScore(UPDATED_FITNESS_SCORE)
            .weight(UPDATED_WEIGHT)
            .bmi(UPDATED_BMI)
            .lbm(UPDATED_LBM)
            .pbf(UPDATED_PBF);
        DevelopmentDTO developmentDTO = developmentMapper.toDto(updatedDevelopment);

        restDevelopmentMockMvc.perform(put("/api/developments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(developmentDTO)))
            .andExpect(status().isOk());

        // Validate the Development in the database
        List<Development> developmentList = developmentRepository.findAll();
        assertThat(developmentList).hasSize(databaseSizeBeforeUpdate);
        Development testDevelopment = developmentList.get(developmentList.size() - 1);
        assertThat(testDevelopment.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testDevelopment.getNutritionScore()).isEqualTo(UPDATED_NUTRITION_SCORE);
        assertThat(testDevelopment.getFitnessScore()).isEqualTo(UPDATED_FITNESS_SCORE);
        assertThat(testDevelopment.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testDevelopment.getBmi()).isEqualTo(UPDATED_BMI);
        assertThat(testDevelopment.getLbm()).isEqualTo(UPDATED_LBM);
        assertThat(testDevelopment.getPbf()).isEqualTo(UPDATED_PBF);
    }

    @Test
    @Transactional
    public void updateNonExistingDevelopment() throws Exception {
        int databaseSizeBeforeUpdate = developmentRepository.findAll().size();

        // Create the Development
        DevelopmentDTO developmentDTO = developmentMapper.toDto(development);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDevelopmentMockMvc.perform(put("/api/developments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(developmentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Development in the database
        List<Development> developmentList = developmentRepository.findAll();
        assertThat(developmentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDevelopment() throws Exception {
        // Initialize the database
        developmentRepository.saveAndFlush(development);

        int databaseSizeBeforeDelete = developmentRepository.findAll().size();

        // Delete the development
        restDevelopmentMockMvc.perform(delete("/api/developments/{id}", development.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Development> developmentList = developmentRepository.findAll();
        assertThat(developmentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
