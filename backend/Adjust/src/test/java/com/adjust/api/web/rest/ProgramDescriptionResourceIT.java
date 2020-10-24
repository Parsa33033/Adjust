package com.adjust.api.web.rest;

import com.adjust.api.AdjustApp;
import com.adjust.api.domain.ProgramDescription;
import com.adjust.api.repository.ProgramDescriptionRepository;
import com.adjust.api.service.ProgramDescriptionService;
import com.adjust.api.service.dto.ProgramDescriptionDTO;
import com.adjust.api.service.mapper.ProgramDescriptionMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.adjust.api.domain.enumeration.ProgramType;
/**
 * Integration tests for the {@link ProgramDescriptionResource} REST controller.
 */
@SpringBootTest(classes = AdjustApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ProgramDescriptionResourceIT {

    private static final String DEFAULT_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_TEXT = "BBBBBBBBBB";

    private static final ProgramType DEFAULT_TYPE = ProgramType.NUTRITION;
    private static final ProgramType UPDATED_TYPE = ProgramType.FITNESS;

    @Autowired
    private ProgramDescriptionRepository programDescriptionRepository;

    @Autowired
    private ProgramDescriptionMapper programDescriptionMapper;

    @Autowired
    private ProgramDescriptionService programDescriptionService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProgramDescriptionMockMvc;

    private ProgramDescription programDescription;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProgramDescription createEntity(EntityManager em) {
        ProgramDescription programDescription = new ProgramDescription()
            .text(DEFAULT_TEXT)
            .type(DEFAULT_TYPE);
        return programDescription;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProgramDescription createUpdatedEntity(EntityManager em) {
        ProgramDescription programDescription = new ProgramDescription()
            .text(UPDATED_TEXT)
            .type(UPDATED_TYPE);
        return programDescription;
    }

    @BeforeEach
    public void initTest() {
        programDescription = createEntity(em);
    }

    @Test
    @Transactional
    public void createProgramDescription() throws Exception {
        int databaseSizeBeforeCreate = programDescriptionRepository.findAll().size();
        // Create the ProgramDescription
        ProgramDescriptionDTO programDescriptionDTO = programDescriptionMapper.toDto(programDescription);
        restProgramDescriptionMockMvc.perform(post("/api/program-descriptions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(programDescriptionDTO)))
            .andExpect(status().isCreated());

        // Validate the ProgramDescription in the database
        List<ProgramDescription> programDescriptionList = programDescriptionRepository.findAll();
        assertThat(programDescriptionList).hasSize(databaseSizeBeforeCreate + 1);
        ProgramDescription testProgramDescription = programDescriptionList.get(programDescriptionList.size() - 1);
        assertThat(testProgramDescription.getText()).isEqualTo(DEFAULT_TEXT);
        assertThat(testProgramDescription.getType()).isEqualTo(DEFAULT_TYPE);
    }

    @Test
    @Transactional
    public void createProgramDescriptionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = programDescriptionRepository.findAll().size();

        // Create the ProgramDescription with an existing ID
        programDescription.setId(1L);
        ProgramDescriptionDTO programDescriptionDTO = programDescriptionMapper.toDto(programDescription);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProgramDescriptionMockMvc.perform(post("/api/program-descriptions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(programDescriptionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProgramDescription in the database
        List<ProgramDescription> programDescriptionList = programDescriptionRepository.findAll();
        assertThat(programDescriptionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProgramDescriptions() throws Exception {
        // Initialize the database
        programDescriptionRepository.saveAndFlush(programDescription);

        // Get all the programDescriptionList
        restProgramDescriptionMockMvc.perform(get("/api/program-descriptions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(programDescription.getId().intValue())))
            .andExpect(jsonPath("$.[*].text").value(hasItem(DEFAULT_TEXT.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())));
    }
    
    @Test
    @Transactional
    public void getProgramDescription() throws Exception {
        // Initialize the database
        programDescriptionRepository.saveAndFlush(programDescription);

        // Get the programDescription
        restProgramDescriptionMockMvc.perform(get("/api/program-descriptions/{id}", programDescription.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(programDescription.getId().intValue()))
            .andExpect(jsonPath("$.text").value(DEFAULT_TEXT.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingProgramDescription() throws Exception {
        // Get the programDescription
        restProgramDescriptionMockMvc.perform(get("/api/program-descriptions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProgramDescription() throws Exception {
        // Initialize the database
        programDescriptionRepository.saveAndFlush(programDescription);

        int databaseSizeBeforeUpdate = programDescriptionRepository.findAll().size();

        // Update the programDescription
        ProgramDescription updatedProgramDescription = programDescriptionRepository.findById(programDescription.getId()).get();
        // Disconnect from session so that the updates on updatedProgramDescription are not directly saved in db
        em.detach(updatedProgramDescription);
        updatedProgramDescription
            .text(UPDATED_TEXT)
            .type(UPDATED_TYPE);
        ProgramDescriptionDTO programDescriptionDTO = programDescriptionMapper.toDto(updatedProgramDescription);

        restProgramDescriptionMockMvc.perform(put("/api/program-descriptions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(programDescriptionDTO)))
            .andExpect(status().isOk());

        // Validate the ProgramDescription in the database
        List<ProgramDescription> programDescriptionList = programDescriptionRepository.findAll();
        assertThat(programDescriptionList).hasSize(databaseSizeBeforeUpdate);
        ProgramDescription testProgramDescription = programDescriptionList.get(programDescriptionList.size() - 1);
        assertThat(testProgramDescription.getText()).isEqualTo(UPDATED_TEXT);
        assertThat(testProgramDescription.getType()).isEqualTo(UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingProgramDescription() throws Exception {
        int databaseSizeBeforeUpdate = programDescriptionRepository.findAll().size();

        // Create the ProgramDescription
        ProgramDescriptionDTO programDescriptionDTO = programDescriptionMapper.toDto(programDescription);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProgramDescriptionMockMvc.perform(put("/api/program-descriptions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(programDescriptionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProgramDescription in the database
        List<ProgramDescription> programDescriptionList = programDescriptionRepository.findAll();
        assertThat(programDescriptionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProgramDescription() throws Exception {
        // Initialize the database
        programDescriptionRepository.saveAndFlush(programDescription);

        int databaseSizeBeforeDelete = programDescriptionRepository.findAll().size();

        // Delete the programDescription
        restProgramDescriptionMockMvc.perform(delete("/api/program-descriptions/{id}", programDescription.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProgramDescription> programDescriptionList = programDescriptionRepository.findAll();
        assertThat(programDescriptionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
