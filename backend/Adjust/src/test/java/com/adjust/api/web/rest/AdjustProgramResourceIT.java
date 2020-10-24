package com.adjust.api.web.rest;

import com.adjust.api.AdjustApp;
import com.adjust.api.domain.AdjustProgram;
import com.adjust.api.repository.AdjustProgramRepository;
import com.adjust.api.service.AdjustProgramService;
import com.adjust.api.service.dto.AdjustProgramDTO;
import com.adjust.api.service.mapper.AdjustProgramMapper;

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
 * Integration tests for the {@link AdjustProgramResource} REST controller.
 */
@SpringBootTest(classes = AdjustApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AdjustProgramResourceIT {

    private static final LocalDate DEFAULT_CREATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_AT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_EXPIRATION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_EXPIRATION_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Boolean DEFAULT_MEDICAL_PROFILE_DONE = false;
    private static final Boolean UPDATED_MEDICAL_PROFILE_DONE = true;

    private static final Boolean DEFAULT_BODY_COMPOSITION_DONE = false;
    private static final Boolean UPDATED_BODY_COMPOSITION_DONE = true;

    private static final Boolean DEFAULT_NUTRITION_DONE = false;
    private static final Boolean UPDATED_NUTRITION_DONE = true;

    private static final Boolean DEFAULT_FITNESS_DONE = false;
    private static final Boolean UPDATED_FITNESS_DONE = true;

    private static final Boolean DEFAULT_PAID = false;
    private static final Boolean UPDATED_PAID = true;

    private static final Boolean DEFAULT_SEEN_BY_CLIENT = false;
    private static final Boolean UPDATED_SEEN_BY_CLIENT = true;

    private static final Boolean DEFAULT_SEEN_BY_SPECIALIST = false;
    private static final Boolean UPDATED_SEEN_BY_SPECIALIST = true;

    private static final Boolean DEFAULT_DESIGNED = false;
    private static final Boolean UPDATED_DESIGNED = true;

    @Autowired
    private AdjustProgramRepository adjustProgramRepository;

    @Autowired
    private AdjustProgramMapper adjustProgramMapper;

    @Autowired
    private AdjustProgramService adjustProgramService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAdjustProgramMockMvc;

    private AdjustProgram adjustProgram;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdjustProgram createEntity(EntityManager em) {
        AdjustProgram adjustProgram = new AdjustProgram()
            .createdAt(DEFAULT_CREATED_AT)
            .expirationDate(DEFAULT_EXPIRATION_DATE)
            .medicalProfileDone(DEFAULT_MEDICAL_PROFILE_DONE)
            .bodyCompositionDone(DEFAULT_BODY_COMPOSITION_DONE)
            .nutritionDone(DEFAULT_NUTRITION_DONE)
            .fitnessDone(DEFAULT_FITNESS_DONE)
            .paid(DEFAULT_PAID)
            .seenByClient(DEFAULT_SEEN_BY_CLIENT)
            .seenBySpecialist(DEFAULT_SEEN_BY_SPECIALIST)
            .designed(DEFAULT_DESIGNED);
        return adjustProgram;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdjustProgram createUpdatedEntity(EntityManager em) {
        AdjustProgram adjustProgram = new AdjustProgram()
            .createdAt(UPDATED_CREATED_AT)
            .expirationDate(UPDATED_EXPIRATION_DATE)
            .medicalProfileDone(UPDATED_MEDICAL_PROFILE_DONE)
            .bodyCompositionDone(UPDATED_BODY_COMPOSITION_DONE)
            .nutritionDone(UPDATED_NUTRITION_DONE)
            .fitnessDone(UPDATED_FITNESS_DONE)
            .paid(UPDATED_PAID)
            .seenByClient(UPDATED_SEEN_BY_CLIENT)
            .seenBySpecialist(UPDATED_SEEN_BY_SPECIALIST)
            .designed(UPDATED_DESIGNED);
        return adjustProgram;
    }

    @BeforeEach
    public void initTest() {
        adjustProgram = createEntity(em);
    }

    @Test
    @Transactional
    public void createAdjustProgram() throws Exception {
        int databaseSizeBeforeCreate = adjustProgramRepository.findAll().size();
        // Create the AdjustProgram
        AdjustProgramDTO adjustProgramDTO = adjustProgramMapper.toDto(adjustProgram);
        restAdjustProgramMockMvc.perform(post("/api/adjust-programs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adjustProgramDTO)))
            .andExpect(status().isCreated());

        // Validate the AdjustProgram in the database
        List<AdjustProgram> adjustProgramList = adjustProgramRepository.findAll();
        assertThat(adjustProgramList).hasSize(databaseSizeBeforeCreate + 1);
        AdjustProgram testAdjustProgram = adjustProgramList.get(adjustProgramList.size() - 1);
        assertThat(testAdjustProgram.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testAdjustProgram.getExpirationDate()).isEqualTo(DEFAULT_EXPIRATION_DATE);
        assertThat(testAdjustProgram.isMedicalProfileDone()).isEqualTo(DEFAULT_MEDICAL_PROFILE_DONE);
        assertThat(testAdjustProgram.isBodyCompositionDone()).isEqualTo(DEFAULT_BODY_COMPOSITION_DONE);
        assertThat(testAdjustProgram.isNutritionDone()).isEqualTo(DEFAULT_NUTRITION_DONE);
        assertThat(testAdjustProgram.isFitnessDone()).isEqualTo(DEFAULT_FITNESS_DONE);
        assertThat(testAdjustProgram.isPaid()).isEqualTo(DEFAULT_PAID);
        assertThat(testAdjustProgram.isSeenByClient()).isEqualTo(DEFAULT_SEEN_BY_CLIENT);
        assertThat(testAdjustProgram.isSeenBySpecialist()).isEqualTo(DEFAULT_SEEN_BY_SPECIALIST);
        assertThat(testAdjustProgram.isDesigned()).isEqualTo(DEFAULT_DESIGNED);
    }

    @Test
    @Transactional
    public void createAdjustProgramWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = adjustProgramRepository.findAll().size();

        // Create the AdjustProgram with an existing ID
        adjustProgram.setId(1L);
        AdjustProgramDTO adjustProgramDTO = adjustProgramMapper.toDto(adjustProgram);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdjustProgramMockMvc.perform(post("/api/adjust-programs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adjustProgramDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdjustProgram in the database
        List<AdjustProgram> adjustProgramList = adjustProgramRepository.findAll();
        assertThat(adjustProgramList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAdjustPrograms() throws Exception {
        // Initialize the database
        adjustProgramRepository.saveAndFlush(adjustProgram);

        // Get all the adjustProgramList
        restAdjustProgramMockMvc.perform(get("/api/adjust-programs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(adjustProgram.getId().intValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].expirationDate").value(hasItem(DEFAULT_EXPIRATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].medicalProfileDone").value(hasItem(DEFAULT_MEDICAL_PROFILE_DONE.booleanValue())))
            .andExpect(jsonPath("$.[*].bodyCompositionDone").value(hasItem(DEFAULT_BODY_COMPOSITION_DONE.booleanValue())))
            .andExpect(jsonPath("$.[*].nutritionDone").value(hasItem(DEFAULT_NUTRITION_DONE.booleanValue())))
            .andExpect(jsonPath("$.[*].fitnessDone").value(hasItem(DEFAULT_FITNESS_DONE.booleanValue())))
            .andExpect(jsonPath("$.[*].paid").value(hasItem(DEFAULT_PAID.booleanValue())))
            .andExpect(jsonPath("$.[*].seenByClient").value(hasItem(DEFAULT_SEEN_BY_CLIENT.booleanValue())))
            .andExpect(jsonPath("$.[*].seenBySpecialist").value(hasItem(DEFAULT_SEEN_BY_SPECIALIST.booleanValue())))
            .andExpect(jsonPath("$.[*].designed").value(hasItem(DEFAULT_DESIGNED.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getAdjustProgram() throws Exception {
        // Initialize the database
        adjustProgramRepository.saveAndFlush(adjustProgram);

        // Get the adjustProgram
        restAdjustProgramMockMvc.perform(get("/api/adjust-programs/{id}", adjustProgram.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(adjustProgram.getId().intValue()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.expirationDate").value(DEFAULT_EXPIRATION_DATE.toString()))
            .andExpect(jsonPath("$.medicalProfileDone").value(DEFAULT_MEDICAL_PROFILE_DONE.booleanValue()))
            .andExpect(jsonPath("$.bodyCompositionDone").value(DEFAULT_BODY_COMPOSITION_DONE.booleanValue()))
            .andExpect(jsonPath("$.nutritionDone").value(DEFAULT_NUTRITION_DONE.booleanValue()))
            .andExpect(jsonPath("$.fitnessDone").value(DEFAULT_FITNESS_DONE.booleanValue()))
            .andExpect(jsonPath("$.paid").value(DEFAULT_PAID.booleanValue()))
            .andExpect(jsonPath("$.seenByClient").value(DEFAULT_SEEN_BY_CLIENT.booleanValue()))
            .andExpect(jsonPath("$.seenBySpecialist").value(DEFAULT_SEEN_BY_SPECIALIST.booleanValue()))
            .andExpect(jsonPath("$.designed").value(DEFAULT_DESIGNED.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingAdjustProgram() throws Exception {
        // Get the adjustProgram
        restAdjustProgramMockMvc.perform(get("/api/adjust-programs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAdjustProgram() throws Exception {
        // Initialize the database
        adjustProgramRepository.saveAndFlush(adjustProgram);

        int databaseSizeBeforeUpdate = adjustProgramRepository.findAll().size();

        // Update the adjustProgram
        AdjustProgram updatedAdjustProgram = adjustProgramRepository.findById(adjustProgram.getId()).get();
        // Disconnect from session so that the updates on updatedAdjustProgram are not directly saved in db
        em.detach(updatedAdjustProgram);
        updatedAdjustProgram
            .createdAt(UPDATED_CREATED_AT)
            .expirationDate(UPDATED_EXPIRATION_DATE)
            .medicalProfileDone(UPDATED_MEDICAL_PROFILE_DONE)
            .bodyCompositionDone(UPDATED_BODY_COMPOSITION_DONE)
            .nutritionDone(UPDATED_NUTRITION_DONE)
            .fitnessDone(UPDATED_FITNESS_DONE)
            .paid(UPDATED_PAID)
            .seenByClient(UPDATED_SEEN_BY_CLIENT)
            .seenBySpecialist(UPDATED_SEEN_BY_SPECIALIST)
            .designed(UPDATED_DESIGNED);
        AdjustProgramDTO adjustProgramDTO = adjustProgramMapper.toDto(updatedAdjustProgram);

        restAdjustProgramMockMvc.perform(put("/api/adjust-programs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adjustProgramDTO)))
            .andExpect(status().isOk());

        // Validate the AdjustProgram in the database
        List<AdjustProgram> adjustProgramList = adjustProgramRepository.findAll();
        assertThat(adjustProgramList).hasSize(databaseSizeBeforeUpdate);
        AdjustProgram testAdjustProgram = adjustProgramList.get(adjustProgramList.size() - 1);
        assertThat(testAdjustProgram.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testAdjustProgram.getExpirationDate()).isEqualTo(UPDATED_EXPIRATION_DATE);
        assertThat(testAdjustProgram.isMedicalProfileDone()).isEqualTo(UPDATED_MEDICAL_PROFILE_DONE);
        assertThat(testAdjustProgram.isBodyCompositionDone()).isEqualTo(UPDATED_BODY_COMPOSITION_DONE);
        assertThat(testAdjustProgram.isNutritionDone()).isEqualTo(UPDATED_NUTRITION_DONE);
        assertThat(testAdjustProgram.isFitnessDone()).isEqualTo(UPDATED_FITNESS_DONE);
        assertThat(testAdjustProgram.isPaid()).isEqualTo(UPDATED_PAID);
        assertThat(testAdjustProgram.isSeenByClient()).isEqualTo(UPDATED_SEEN_BY_CLIENT);
        assertThat(testAdjustProgram.isSeenBySpecialist()).isEqualTo(UPDATED_SEEN_BY_SPECIALIST);
        assertThat(testAdjustProgram.isDesigned()).isEqualTo(UPDATED_DESIGNED);
    }

    @Test
    @Transactional
    public void updateNonExistingAdjustProgram() throws Exception {
        int databaseSizeBeforeUpdate = adjustProgramRepository.findAll().size();

        // Create the AdjustProgram
        AdjustProgramDTO adjustProgramDTO = adjustProgramMapper.toDto(adjustProgram);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdjustProgramMockMvc.perform(put("/api/adjust-programs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adjustProgramDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdjustProgram in the database
        List<AdjustProgram> adjustProgramList = adjustProgramRepository.findAll();
        assertThat(adjustProgramList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAdjustProgram() throws Exception {
        // Initialize the database
        adjustProgramRepository.saveAndFlush(adjustProgram);

        int databaseSizeBeforeDelete = adjustProgramRepository.findAll().size();

        // Delete the adjustProgram
        restAdjustProgramMockMvc.perform(delete("/api/adjust-programs/{id}", adjustProgram.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdjustProgram> adjustProgramList = adjustProgramRepository.findAll();
        assertThat(adjustProgramList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
