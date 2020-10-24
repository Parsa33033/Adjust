package com.adjust.api.web.rest;

import com.adjust.api.AdjustApp;
import com.adjust.api.domain.MealsForNutrition;
import com.adjust.api.repository.MealsForNutritionRepository;
import com.adjust.api.service.MealsForNutritionService;
import com.adjust.api.service.dto.MealsForNutritionDTO;
import com.adjust.api.service.mapper.MealsForNutritionMapper;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.adjust.api.domain.enumeration.MealType;
/**
 * Integration tests for the {@link MealsForNutritionResource} REST controller.
 */
@SpringBootTest(classes = AdjustApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MealsForNutritionResourceIT {

    private static final MealType DEFAULT_MEAL_TYPE = MealType.BREAKFAST;
    private static final MealType UPDATED_MEAL_TYPE = MealType.LUNCH;

    @Autowired
    private MealsForNutritionRepository mealsForNutritionRepository;

    @Autowired
    private MealsForNutritionMapper mealsForNutritionMapper;

    @Autowired
    private MealsForNutritionService mealsForNutritionService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMealsForNutritionMockMvc;

    private MealsForNutrition mealsForNutrition;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MealsForNutrition createEntity(EntityManager em) {
        MealsForNutrition mealsForNutrition = new MealsForNutrition()
            .mealType(DEFAULT_MEAL_TYPE);
        return mealsForNutrition;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MealsForNutrition createUpdatedEntity(EntityManager em) {
        MealsForNutrition mealsForNutrition = new MealsForNutrition()
            .mealType(UPDATED_MEAL_TYPE);
        return mealsForNutrition;
    }

    @BeforeEach
    public void initTest() {
        mealsForNutrition = createEntity(em);
    }

    @Test
    @Transactional
    public void createMealsForNutrition() throws Exception {
        int databaseSizeBeforeCreate = mealsForNutritionRepository.findAll().size();
        // Create the MealsForNutrition
        MealsForNutritionDTO mealsForNutritionDTO = mealsForNutritionMapper.toDto(mealsForNutrition);
        restMealsForNutritionMockMvc.perform(post("/api/meals-for-nutritions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mealsForNutritionDTO)))
            .andExpect(status().isCreated());

        // Validate the MealsForNutrition in the database
        List<MealsForNutrition> mealsForNutritionList = mealsForNutritionRepository.findAll();
        assertThat(mealsForNutritionList).hasSize(databaseSizeBeforeCreate + 1);
        MealsForNutrition testMealsForNutrition = mealsForNutritionList.get(mealsForNutritionList.size() - 1);
        assertThat(testMealsForNutrition.getMealType()).isEqualTo(DEFAULT_MEAL_TYPE);
    }

    @Test
    @Transactional
    public void createMealsForNutritionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mealsForNutritionRepository.findAll().size();

        // Create the MealsForNutrition with an existing ID
        mealsForNutrition.setId(1L);
        MealsForNutritionDTO mealsForNutritionDTO = mealsForNutritionMapper.toDto(mealsForNutrition);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMealsForNutritionMockMvc.perform(post("/api/meals-for-nutritions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mealsForNutritionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MealsForNutrition in the database
        List<MealsForNutrition> mealsForNutritionList = mealsForNutritionRepository.findAll();
        assertThat(mealsForNutritionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMealsForNutritions() throws Exception {
        // Initialize the database
        mealsForNutritionRepository.saveAndFlush(mealsForNutrition);

        // Get all the mealsForNutritionList
        restMealsForNutritionMockMvc.perform(get("/api/meals-for-nutritions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mealsForNutrition.getId().intValue())))
            .andExpect(jsonPath("$.[*].mealType").value(hasItem(DEFAULT_MEAL_TYPE.toString())));
    }
    
    @Test
    @Transactional
    public void getMealsForNutrition() throws Exception {
        // Initialize the database
        mealsForNutritionRepository.saveAndFlush(mealsForNutrition);

        // Get the mealsForNutrition
        restMealsForNutritionMockMvc.perform(get("/api/meals-for-nutritions/{id}", mealsForNutrition.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(mealsForNutrition.getId().intValue()))
            .andExpect(jsonPath("$.mealType").value(DEFAULT_MEAL_TYPE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingMealsForNutrition() throws Exception {
        // Get the mealsForNutrition
        restMealsForNutritionMockMvc.perform(get("/api/meals-for-nutritions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMealsForNutrition() throws Exception {
        // Initialize the database
        mealsForNutritionRepository.saveAndFlush(mealsForNutrition);

        int databaseSizeBeforeUpdate = mealsForNutritionRepository.findAll().size();

        // Update the mealsForNutrition
        MealsForNutrition updatedMealsForNutrition = mealsForNutritionRepository.findById(mealsForNutrition.getId()).get();
        // Disconnect from session so that the updates on updatedMealsForNutrition are not directly saved in db
        em.detach(updatedMealsForNutrition);
        updatedMealsForNutrition
            .mealType(UPDATED_MEAL_TYPE);
        MealsForNutritionDTO mealsForNutritionDTO = mealsForNutritionMapper.toDto(updatedMealsForNutrition);

        restMealsForNutritionMockMvc.perform(put("/api/meals-for-nutritions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mealsForNutritionDTO)))
            .andExpect(status().isOk());

        // Validate the MealsForNutrition in the database
        List<MealsForNutrition> mealsForNutritionList = mealsForNutritionRepository.findAll();
        assertThat(mealsForNutritionList).hasSize(databaseSizeBeforeUpdate);
        MealsForNutrition testMealsForNutrition = mealsForNutritionList.get(mealsForNutritionList.size() - 1);
        assertThat(testMealsForNutrition.getMealType()).isEqualTo(UPDATED_MEAL_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingMealsForNutrition() throws Exception {
        int databaseSizeBeforeUpdate = mealsForNutritionRepository.findAll().size();

        // Create the MealsForNutrition
        MealsForNutritionDTO mealsForNutritionDTO = mealsForNutritionMapper.toDto(mealsForNutrition);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMealsForNutritionMockMvc.perform(put("/api/meals-for-nutritions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mealsForNutritionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MealsForNutrition in the database
        List<MealsForNutrition> mealsForNutritionList = mealsForNutritionRepository.findAll();
        assertThat(mealsForNutritionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMealsForNutrition() throws Exception {
        // Initialize the database
        mealsForNutritionRepository.saveAndFlush(mealsForNutrition);

        int databaseSizeBeforeDelete = mealsForNutritionRepository.findAll().size();

        // Delete the mealsForNutrition
        restMealsForNutritionMockMvc.perform(delete("/api/meals-for-nutritions/{id}", mealsForNutrition.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MealsForNutrition> mealsForNutritionList = mealsForNutritionRepository.findAll();
        assertThat(mealsForNutritionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
