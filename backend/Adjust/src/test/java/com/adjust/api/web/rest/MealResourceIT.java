package com.adjust.api.web.rest;

import com.adjust.api.AdjustApp;
import com.adjust.api.domain.Meal;
import com.adjust.api.repository.MealRepository;
import com.adjust.api.service.MealService;
import com.adjust.api.service.dto.MealDTO;
import com.adjust.api.service.mapper.MealMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.adjust.api.domain.enumeration.MealType;
/**
 * Integration tests for the {@link MealResource} REST controller.
 */
@SpringBootTest(classes = AdjustApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class MealResourceIT {

    private static final MealType DEFAULT_MEAL_TYPE = MealType.BREAKFAST;
    private static final MealType UPDATED_MEAL_TYPE = MealType.LUNCH;

    private static final Integer DEFAULT_NUMBER = 1;
    private static final Integer UPDATED_NUMBER = 2;

    @Autowired
    private MealRepository mealRepository;

    @Mock
    private MealRepository mealRepositoryMock;

    @Autowired
    private MealMapper mealMapper;

    @Mock
    private MealService mealServiceMock;

    @Autowired
    private MealService mealService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMealMockMvc;

    private Meal meal;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Meal createEntity(EntityManager em) {
        Meal meal = new Meal()
            .mealType(DEFAULT_MEAL_TYPE)
            .number(DEFAULT_NUMBER);
        return meal;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Meal createUpdatedEntity(EntityManager em) {
        Meal meal = new Meal()
            .mealType(UPDATED_MEAL_TYPE)
            .number(UPDATED_NUMBER);
        return meal;
    }

    @BeforeEach
    public void initTest() {
        meal = createEntity(em);
    }

    @Test
    @Transactional
    public void createMeal() throws Exception {
        int databaseSizeBeforeCreate = mealRepository.findAll().size();
        // Create the Meal
        MealDTO mealDTO = mealMapper.toDto(meal);
        restMealMockMvc.perform(post("/api/meals")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mealDTO)))
            .andExpect(status().isCreated());

        // Validate the Meal in the database
        List<Meal> mealList = mealRepository.findAll();
        assertThat(mealList).hasSize(databaseSizeBeforeCreate + 1);
        Meal testMeal = mealList.get(mealList.size() - 1);
        assertThat(testMeal.getMealType()).isEqualTo(DEFAULT_MEAL_TYPE);
        assertThat(testMeal.getNumber()).isEqualTo(DEFAULT_NUMBER);
    }

    @Test
    @Transactional
    public void createMealWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mealRepository.findAll().size();

        // Create the Meal with an existing ID
        meal.setId(1L);
        MealDTO mealDTO = mealMapper.toDto(meal);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMealMockMvc.perform(post("/api/meals")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mealDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Meal in the database
        List<Meal> mealList = mealRepository.findAll();
        assertThat(mealList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMeals() throws Exception {
        // Initialize the database
        mealRepository.saveAndFlush(meal);

        // Get all the mealList
        restMealMockMvc.perform(get("/api/meals?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(meal.getId().intValue())))
            .andExpect(jsonPath("$.[*].mealType").value(hasItem(DEFAULT_MEAL_TYPE.toString())))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER)));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllMealsWithEagerRelationshipsIsEnabled() throws Exception {
        when(mealServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restMealMockMvc.perform(get("/api/meals?eagerload=true"))
            .andExpect(status().isOk());

        verify(mealServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllMealsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(mealServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restMealMockMvc.perform(get("/api/meals?eagerload=true"))
            .andExpect(status().isOk());

        verify(mealServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getMeal() throws Exception {
        // Initialize the database
        mealRepository.saveAndFlush(meal);

        // Get the meal
        restMealMockMvc.perform(get("/api/meals/{id}", meal.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(meal.getId().intValue()))
            .andExpect(jsonPath("$.mealType").value(DEFAULT_MEAL_TYPE.toString()))
            .andExpect(jsonPath("$.number").value(DEFAULT_NUMBER));
    }
    @Test
    @Transactional
    public void getNonExistingMeal() throws Exception {
        // Get the meal
        restMealMockMvc.perform(get("/api/meals/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMeal() throws Exception {
        // Initialize the database
        mealRepository.saveAndFlush(meal);

        int databaseSizeBeforeUpdate = mealRepository.findAll().size();

        // Update the meal
        Meal updatedMeal = mealRepository.findById(meal.getId()).get();
        // Disconnect from session so that the updates on updatedMeal are not directly saved in db
        em.detach(updatedMeal);
        updatedMeal
            .mealType(UPDATED_MEAL_TYPE)
            .number(UPDATED_NUMBER);
        MealDTO mealDTO = mealMapper.toDto(updatedMeal);

        restMealMockMvc.perform(put("/api/meals")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mealDTO)))
            .andExpect(status().isOk());

        // Validate the Meal in the database
        List<Meal> mealList = mealRepository.findAll();
        assertThat(mealList).hasSize(databaseSizeBeforeUpdate);
        Meal testMeal = mealList.get(mealList.size() - 1);
        assertThat(testMeal.getMealType()).isEqualTo(UPDATED_MEAL_TYPE);
        assertThat(testMeal.getNumber()).isEqualTo(UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void updateNonExistingMeal() throws Exception {
        int databaseSizeBeforeUpdate = mealRepository.findAll().size();

        // Create the Meal
        MealDTO mealDTO = mealMapper.toDto(meal);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMealMockMvc.perform(put("/api/meals")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mealDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Meal in the database
        List<Meal> mealList = mealRepository.findAll();
        assertThat(mealList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMeal() throws Exception {
        // Initialize the database
        mealRepository.saveAndFlush(meal);

        int databaseSizeBeforeDelete = mealRepository.findAll().size();

        // Delete the meal
        restMealMockMvc.perform(delete("/api/meals/{id}", meal.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Meal> mealList = mealRepository.findAll();
        assertThat(mealList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
