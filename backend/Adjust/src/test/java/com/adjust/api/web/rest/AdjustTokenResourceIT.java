package com.adjust.api.web.rest;

import com.adjust.api.AdjustApp;
import com.adjust.api.domain.AdjustToken;
import com.adjust.api.repository.AdjustTokenRepository;
import com.adjust.api.service.AdjustTokenService;
import com.adjust.api.service.dto.AdjustTokenDTO;
import com.adjust.api.service.mapper.AdjustTokenMapper;

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

/**
 * Integration tests for the {@link AdjustTokenResource} REST controller.
 */
@SpringBootTest(classes = AdjustApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AdjustTokenResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Double DEFAULT_TOKEN = 1D;
    private static final Double UPDATED_TOKEN = 2D;

    private static final Double DEFAULT_PRICE = 1D;
    private static final Double UPDATED_PRICE = 2D;

    private static final byte[] DEFAULT_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGE_CONTENT_TYPE = "image/png";

    @Autowired
    private AdjustTokenRepository adjustTokenRepository;

    @Autowired
    private AdjustTokenMapper adjustTokenMapper;

    @Autowired
    private AdjustTokenService adjustTokenService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAdjustTokenMockMvc;

    private AdjustToken adjustToken;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdjustToken createEntity(EntityManager em) {
        AdjustToken adjustToken = new AdjustToken()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .token(DEFAULT_TOKEN)
            .price(DEFAULT_PRICE)
            .image(DEFAULT_IMAGE)
            .imageContentType(DEFAULT_IMAGE_CONTENT_TYPE);
        return adjustToken;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdjustToken createUpdatedEntity(EntityManager em) {
        AdjustToken adjustToken = new AdjustToken()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .token(UPDATED_TOKEN)
            .price(UPDATED_PRICE)
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE);
        return adjustToken;
    }

    @BeforeEach
    public void initTest() {
        adjustToken = createEntity(em);
    }

    @Test
    @Transactional
    public void createAdjustToken() throws Exception {
        int databaseSizeBeforeCreate = adjustTokenRepository.findAll().size();
        // Create the AdjustToken
        AdjustTokenDTO adjustTokenDTO = adjustTokenMapper.toDto(adjustToken);
        restAdjustTokenMockMvc.perform(post("/api/adjust-tokens")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adjustTokenDTO)))
            .andExpect(status().isCreated());

        // Validate the AdjustToken in the database
        List<AdjustToken> adjustTokenList = adjustTokenRepository.findAll();
        assertThat(adjustTokenList).hasSize(databaseSizeBeforeCreate + 1);
        AdjustToken testAdjustToken = adjustTokenList.get(adjustTokenList.size() - 1);
        assertThat(testAdjustToken.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testAdjustToken.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testAdjustToken.getToken()).isEqualTo(DEFAULT_TOKEN);
        assertThat(testAdjustToken.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testAdjustToken.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testAdjustToken.getImageContentType()).isEqualTo(DEFAULT_IMAGE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createAdjustTokenWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = adjustTokenRepository.findAll().size();

        // Create the AdjustToken with an existing ID
        adjustToken.setId(1L);
        AdjustTokenDTO adjustTokenDTO = adjustTokenMapper.toDto(adjustToken);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdjustTokenMockMvc.perform(post("/api/adjust-tokens")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adjustTokenDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdjustToken in the database
        List<AdjustToken> adjustTokenList = adjustTokenRepository.findAll();
        assertThat(adjustTokenList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAdjustTokens() throws Exception {
        // Initialize the database
        adjustTokenRepository.saveAndFlush(adjustToken);

        // Get all the adjustTokenList
        restAdjustTokenMockMvc.perform(get("/api/adjust-tokens?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(adjustToken.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].token").value(hasItem(DEFAULT_TOKEN.doubleValue())))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].imageContentType").value(hasItem(DEFAULT_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE))));
    }
    
    @Test
    @Transactional
    public void getAdjustToken() throws Exception {
        // Initialize the database
        adjustTokenRepository.saveAndFlush(adjustToken);

        // Get the adjustToken
        restAdjustTokenMockMvc.perform(get("/api/adjust-tokens/{id}", adjustToken.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(adjustToken.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.token").value(DEFAULT_TOKEN.doubleValue()))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.doubleValue()))
            .andExpect(jsonPath("$.imageContentType").value(DEFAULT_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.image").value(Base64Utils.encodeToString(DEFAULT_IMAGE)));
    }
    @Test
    @Transactional
    public void getNonExistingAdjustToken() throws Exception {
        // Get the adjustToken
        restAdjustTokenMockMvc.perform(get("/api/adjust-tokens/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAdjustToken() throws Exception {
        // Initialize the database
        adjustTokenRepository.saveAndFlush(adjustToken);

        int databaseSizeBeforeUpdate = adjustTokenRepository.findAll().size();

        // Update the adjustToken
        AdjustToken updatedAdjustToken = adjustTokenRepository.findById(adjustToken.getId()).get();
        // Disconnect from session so that the updates on updatedAdjustToken are not directly saved in db
        em.detach(updatedAdjustToken);
        updatedAdjustToken
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .token(UPDATED_TOKEN)
            .price(UPDATED_PRICE)
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE);
        AdjustTokenDTO adjustTokenDTO = adjustTokenMapper.toDto(updatedAdjustToken);

        restAdjustTokenMockMvc.perform(put("/api/adjust-tokens")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adjustTokenDTO)))
            .andExpect(status().isOk());

        // Validate the AdjustToken in the database
        List<AdjustToken> adjustTokenList = adjustTokenRepository.findAll();
        assertThat(adjustTokenList).hasSize(databaseSizeBeforeUpdate);
        AdjustToken testAdjustToken = adjustTokenList.get(adjustTokenList.size() - 1);
        assertThat(testAdjustToken.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAdjustToken.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testAdjustToken.getToken()).isEqualTo(UPDATED_TOKEN);
        assertThat(testAdjustToken.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testAdjustToken.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testAdjustToken.getImageContentType()).isEqualTo(UPDATED_IMAGE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingAdjustToken() throws Exception {
        int databaseSizeBeforeUpdate = adjustTokenRepository.findAll().size();

        // Create the AdjustToken
        AdjustTokenDTO adjustTokenDTO = adjustTokenMapper.toDto(adjustToken);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdjustTokenMockMvc.perform(put("/api/adjust-tokens")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adjustTokenDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdjustToken in the database
        List<AdjustToken> adjustTokenList = adjustTokenRepository.findAll();
        assertThat(adjustTokenList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAdjustToken() throws Exception {
        // Initialize the database
        adjustTokenRepository.saveAndFlush(adjustToken);

        int databaseSizeBeforeDelete = adjustTokenRepository.findAll().size();

        // Delete the adjustToken
        restAdjustTokenMockMvc.perform(delete("/api/adjust-tokens/{id}", adjustToken.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdjustToken> adjustTokenList = adjustTokenRepository.findAll();
        assertThat(adjustTokenList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
