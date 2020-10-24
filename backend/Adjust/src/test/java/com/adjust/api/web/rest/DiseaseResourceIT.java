package com.adjust.api.web.rest;

import com.adjust.api.AdjustApp;
import com.adjust.api.domain.Disease;
import com.adjust.api.repository.DiseaseRepository;
import com.adjust.api.service.DiseaseService;
import com.adjust.api.service.dto.DiseaseDTO;
import com.adjust.api.service.mapper.DiseaseMapper;

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

/**
 * Integration tests for the {@link DiseaseResource} REST controller.
 */
@SpringBootTest(classes = AdjustApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DiseaseResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_PROTEIN_PERCENTAGE = 1;
    private static final Integer UPDATED_PROTEIN_PERCENTAGE = 2;

    private static final Integer DEFAULT_CARBS_PERCENTAGE = 1;
    private static final Integer UPDATED_CARBS_PERCENTAGE = 2;

    private static final Integer DEFAULT_FAT_PERCENTAGE = 1;
    private static final Integer UPDATED_FAT_PERCENTAGE = 2;

    @Autowired
    private DiseaseRepository diseaseRepository;

    @Autowired
    private DiseaseMapper diseaseMapper;

    @Autowired
    private DiseaseService diseaseService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDiseaseMockMvc;

    private Disease disease;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Disease createEntity(EntityManager em) {
        Disease disease = new Disease()
            .name(DEFAULT_NAME)
            .proteinPercentage(DEFAULT_PROTEIN_PERCENTAGE)
            .carbsPercentage(DEFAULT_CARBS_PERCENTAGE)
            .fatPercentage(DEFAULT_FAT_PERCENTAGE);
        return disease;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Disease createUpdatedEntity(EntityManager em) {
        Disease disease = new Disease()
            .name(UPDATED_NAME)
            .proteinPercentage(UPDATED_PROTEIN_PERCENTAGE)
            .carbsPercentage(UPDATED_CARBS_PERCENTAGE)
            .fatPercentage(UPDATED_FAT_PERCENTAGE);
        return disease;
    }

    @BeforeEach
    public void initTest() {
        disease = createEntity(em);
    }

    @Test
    @Transactional
    public void createDisease() throws Exception {
        int databaseSizeBeforeCreate = diseaseRepository.findAll().size();
        // Create the Disease
        DiseaseDTO diseaseDTO = diseaseMapper.toDto(disease);
        restDiseaseMockMvc.perform(post("/api/diseases")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(diseaseDTO)))
            .andExpect(status().isCreated());

        // Validate the Disease in the database
        List<Disease> diseaseList = diseaseRepository.findAll();
        assertThat(diseaseList).hasSize(databaseSizeBeforeCreate + 1);
        Disease testDisease = diseaseList.get(diseaseList.size() - 1);
        assertThat(testDisease.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testDisease.getProteinPercentage()).isEqualTo(DEFAULT_PROTEIN_PERCENTAGE);
        assertThat(testDisease.getCarbsPercentage()).isEqualTo(DEFAULT_CARBS_PERCENTAGE);
        assertThat(testDisease.getFatPercentage()).isEqualTo(DEFAULT_FAT_PERCENTAGE);
    }

    @Test
    @Transactional
    public void createDiseaseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = diseaseRepository.findAll().size();

        // Create the Disease with an existing ID
        disease.setId(1L);
        DiseaseDTO diseaseDTO = diseaseMapper.toDto(disease);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDiseaseMockMvc.perform(post("/api/diseases")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(diseaseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Disease in the database
        List<Disease> diseaseList = diseaseRepository.findAll();
        assertThat(diseaseList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDiseases() throws Exception {
        // Initialize the database
        diseaseRepository.saveAndFlush(disease);

        // Get all the diseaseList
        restDiseaseMockMvc.perform(get("/api/diseases?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(disease.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].proteinPercentage").value(hasItem(DEFAULT_PROTEIN_PERCENTAGE)))
            .andExpect(jsonPath("$.[*].carbsPercentage").value(hasItem(DEFAULT_CARBS_PERCENTAGE)))
            .andExpect(jsonPath("$.[*].fatPercentage").value(hasItem(DEFAULT_FAT_PERCENTAGE)));
    }
    
    @Test
    @Transactional
    public void getDisease() throws Exception {
        // Initialize the database
        diseaseRepository.saveAndFlush(disease);

        // Get the disease
        restDiseaseMockMvc.perform(get("/api/diseases/{id}", disease.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(disease.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.proteinPercentage").value(DEFAULT_PROTEIN_PERCENTAGE))
            .andExpect(jsonPath("$.carbsPercentage").value(DEFAULT_CARBS_PERCENTAGE))
            .andExpect(jsonPath("$.fatPercentage").value(DEFAULT_FAT_PERCENTAGE));
    }
    @Test
    @Transactional
    public void getNonExistingDisease() throws Exception {
        // Get the disease
        restDiseaseMockMvc.perform(get("/api/diseases/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDisease() throws Exception {
        // Initialize the database
        diseaseRepository.saveAndFlush(disease);

        int databaseSizeBeforeUpdate = diseaseRepository.findAll().size();

        // Update the disease
        Disease updatedDisease = diseaseRepository.findById(disease.getId()).get();
        // Disconnect from session so that the updates on updatedDisease are not directly saved in db
        em.detach(updatedDisease);
        updatedDisease
            .name(UPDATED_NAME)
            .proteinPercentage(UPDATED_PROTEIN_PERCENTAGE)
            .carbsPercentage(UPDATED_CARBS_PERCENTAGE)
            .fatPercentage(UPDATED_FAT_PERCENTAGE);
        DiseaseDTO diseaseDTO = diseaseMapper.toDto(updatedDisease);

        restDiseaseMockMvc.perform(put("/api/diseases")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(diseaseDTO)))
            .andExpect(status().isOk());

        // Validate the Disease in the database
        List<Disease> diseaseList = diseaseRepository.findAll();
        assertThat(diseaseList).hasSize(databaseSizeBeforeUpdate);
        Disease testDisease = diseaseList.get(diseaseList.size() - 1);
        assertThat(testDisease.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDisease.getProteinPercentage()).isEqualTo(UPDATED_PROTEIN_PERCENTAGE);
        assertThat(testDisease.getCarbsPercentage()).isEqualTo(UPDATED_CARBS_PERCENTAGE);
        assertThat(testDisease.getFatPercentage()).isEqualTo(UPDATED_FAT_PERCENTAGE);
    }

    @Test
    @Transactional
    public void updateNonExistingDisease() throws Exception {
        int databaseSizeBeforeUpdate = diseaseRepository.findAll().size();

        // Create the Disease
        DiseaseDTO diseaseDTO = diseaseMapper.toDto(disease);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDiseaseMockMvc.perform(put("/api/diseases")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(diseaseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Disease in the database
        List<Disease> diseaseList = diseaseRepository.findAll();
        assertThat(diseaseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDisease() throws Exception {
        // Initialize the database
        diseaseRepository.saveAndFlush(disease);

        int databaseSizeBeforeDelete = diseaseRepository.findAll().size();

        // Delete the disease
        restDiseaseMockMvc.perform(delete("/api/diseases/{id}", disease.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Disease> diseaseList = diseaseRepository.findAll();
        assertThat(diseaseList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
