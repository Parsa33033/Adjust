package com.adjust.api.web.rest;

import com.adjust.api.AdjustApp;
import com.adjust.api.domain.MedicalProfile;
import com.adjust.api.repository.MedicalProfileRepository;
import com.adjust.api.service.MedicalProfileService;
import com.adjust.api.service.dto.MedicalProfileDTO;
import com.adjust.api.service.mapper.MedicalProfileMapper;

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
import org.springframework.util.Base64Utils;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.adjust.api.domain.enumeration.MaritalStatus;
import com.adjust.api.domain.enumeration.Education;
import com.adjust.api.domain.enumeration.Occupation;
import com.adjust.api.domain.enumeration.FinancialStatus;
import com.adjust.api.domain.enumeration.BloodType;
/**
 * Integration tests for the {@link MedicalProfileResource} REST controller.
 */
@SpringBootTest(classes = AdjustApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class MedicalProfileResourceIT {

    private static final Boolean DEFAULT_HAVE_LOST_WEIGHT = false;
    private static final Boolean UPDATED_HAVE_LOST_WEIGHT = true;

    private static final Boolean DEFAULT_DROWSINESS = false;
    private static final Boolean UPDATED_DROWSINESS = true;

    private static final Boolean DEFAULT_GOOD_NIGHT_SLEEP = false;
    private static final Boolean UPDATED_GOOD_NIGHT_SLEEP = true;

    private static final Integer DEFAULT_HOURS_OF_TRANING_PER_WEEK = 1;
    private static final Integer UPDATED_HOURS_OF_TRANING_PER_WEEK = 2;

    private static final Boolean DEFAULT_LOVE_FOR_SUGAR = false;
    private static final Boolean UPDATED_LOVE_FOR_SUGAR = true;

    private static final Boolean DEFAULT_OVER_EATING = false;
    private static final Boolean UPDATED_OVER_EATING = true;

    private static final MaritalStatus DEFAULT_MARITAL_STATUS = MaritalStatus.MARRIED;
    private static final MaritalStatus UPDATED_MARITAL_STATUS = MaritalStatus.SINGLE;

    private static final Integer DEFAULT_CHILDREN_NUMBER = 1;
    private static final Integer UPDATED_CHILDREN_NUMBER = 2;

    private static final Education DEFAULT_EDUCATION = Education.NONE;
    private static final Education UPDATED_EDUCATION = Education.LOW;

    private static final Occupation DEFAULT_OCCUPATION = Occupation.NONE;
    private static final Occupation UPDATED_OCCUPATION = Occupation.STUDENT;

    private static final Integer DEFAULT_HOURS_OF_WORK_DAILY = 1;
    private static final Integer UPDATED_HOURS_OF_WORK_DAILY = 2;

    private static final FinancialStatus DEFAULT_FINANCIAL_STATUS = FinancialStatus.BAD;
    private static final FinancialStatus UPDATED_FINANCIAL_STATUS = FinancialStatus.NORMAL;

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final Boolean DEFAULT_USE_OF_MEDICINE = false;
    private static final Boolean UPDATED_USE_OF_MEDICINE = true;

    private static final String DEFAULT_MEDICINES = "AAAAAAAAAA";
    private static final String UPDATED_MEDICINES = "BBBBBBBBBB";

    private static final Boolean DEFAULT_HAVE_ALLERGY = false;
    private static final Boolean UPDATED_HAVE_ALLERGY = true;

    private static final String DEFAULT_ALLERGY = "AAAAAAAAAA";
    private static final String UPDATED_ALLERGY = "BBBBBBBBBB";

    private static final Boolean DEFAULT_HAVE_DISEASE = false;
    private static final Boolean UPDATED_HAVE_DISEASE = true;

    private static final BloodType DEFAULT_BLOOD_TYPE = BloodType.A;
    private static final BloodType UPDATED_BLOOD_TYPE = BloodType.B;

    @Autowired
    private MedicalProfileRepository medicalProfileRepository;

    @Mock
    private MedicalProfileRepository medicalProfileRepositoryMock;

    @Autowired
    private MedicalProfileMapper medicalProfileMapper;

    @Mock
    private MedicalProfileService medicalProfileServiceMock;

    @Autowired
    private MedicalProfileService medicalProfileService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMedicalProfileMockMvc;

    private MedicalProfile medicalProfile;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MedicalProfile createEntity(EntityManager em) {
        MedicalProfile medicalProfile = new MedicalProfile()
            .haveLostWeight(DEFAULT_HAVE_LOST_WEIGHT)
            .drowsiness(DEFAULT_DROWSINESS)
            .goodNightSleep(DEFAULT_GOOD_NIGHT_SLEEP)
            .hoursOfTraningPerWeek(DEFAULT_HOURS_OF_TRANING_PER_WEEK)
            .loveForSugar(DEFAULT_LOVE_FOR_SUGAR)
            .overEating(DEFAULT_OVER_EATING)
            .maritalStatus(DEFAULT_MARITAL_STATUS)
            .childrenNumber(DEFAULT_CHILDREN_NUMBER)
            .education(DEFAULT_EDUCATION)
            .occupation(DEFAULT_OCCUPATION)
            .hoursOfWorkDaily(DEFAULT_HOURS_OF_WORK_DAILY)
            .financialStatus(DEFAULT_FINANCIAL_STATUS)
            .city(DEFAULT_CITY)
            .useOfMedicine(DEFAULT_USE_OF_MEDICINE)
            .medicines(DEFAULT_MEDICINES)
            .haveAllergy(DEFAULT_HAVE_ALLERGY)
            .allergy(DEFAULT_ALLERGY)
            .haveDisease(DEFAULT_HAVE_DISEASE)
            .bloodType(DEFAULT_BLOOD_TYPE);
        return medicalProfile;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MedicalProfile createUpdatedEntity(EntityManager em) {
        MedicalProfile medicalProfile = new MedicalProfile()
            .haveLostWeight(UPDATED_HAVE_LOST_WEIGHT)
            .drowsiness(UPDATED_DROWSINESS)
            .goodNightSleep(UPDATED_GOOD_NIGHT_SLEEP)
            .hoursOfTraningPerWeek(UPDATED_HOURS_OF_TRANING_PER_WEEK)
            .loveForSugar(UPDATED_LOVE_FOR_SUGAR)
            .overEating(UPDATED_OVER_EATING)
            .maritalStatus(UPDATED_MARITAL_STATUS)
            .childrenNumber(UPDATED_CHILDREN_NUMBER)
            .education(UPDATED_EDUCATION)
            .occupation(UPDATED_OCCUPATION)
            .hoursOfWorkDaily(UPDATED_HOURS_OF_WORK_DAILY)
            .financialStatus(UPDATED_FINANCIAL_STATUS)
            .city(UPDATED_CITY)
            .useOfMedicine(UPDATED_USE_OF_MEDICINE)
            .medicines(UPDATED_MEDICINES)
            .haveAllergy(UPDATED_HAVE_ALLERGY)
            .allergy(UPDATED_ALLERGY)
            .haveDisease(UPDATED_HAVE_DISEASE)
            .bloodType(UPDATED_BLOOD_TYPE);
        return medicalProfile;
    }

    @BeforeEach
    public void initTest() {
        medicalProfile = createEntity(em);
    }

    @Test
    @Transactional
    public void createMedicalProfile() throws Exception {
        int databaseSizeBeforeCreate = medicalProfileRepository.findAll().size();
        // Create the MedicalProfile
        MedicalProfileDTO medicalProfileDTO = medicalProfileMapper.toDto(medicalProfile);
        restMedicalProfileMockMvc.perform(post("/api/medical-profiles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalProfileDTO)))
            .andExpect(status().isCreated());

        // Validate the MedicalProfile in the database
        List<MedicalProfile> medicalProfileList = medicalProfileRepository.findAll();
        assertThat(medicalProfileList).hasSize(databaseSizeBeforeCreate + 1);
        MedicalProfile testMedicalProfile = medicalProfileList.get(medicalProfileList.size() - 1);
        assertThat(testMedicalProfile.isHaveLostWeight()).isEqualTo(DEFAULT_HAVE_LOST_WEIGHT);
        assertThat(testMedicalProfile.isDrowsiness()).isEqualTo(DEFAULT_DROWSINESS);
        assertThat(testMedicalProfile.isGoodNightSleep()).isEqualTo(DEFAULT_GOOD_NIGHT_SLEEP);
        assertThat(testMedicalProfile.getHoursOfTraningPerWeek()).isEqualTo(DEFAULT_HOURS_OF_TRANING_PER_WEEK);
        assertThat(testMedicalProfile.isLoveForSugar()).isEqualTo(DEFAULT_LOVE_FOR_SUGAR);
        assertThat(testMedicalProfile.isOverEating()).isEqualTo(DEFAULT_OVER_EATING);
        assertThat(testMedicalProfile.getMaritalStatus()).isEqualTo(DEFAULT_MARITAL_STATUS);
        assertThat(testMedicalProfile.getChildrenNumber()).isEqualTo(DEFAULT_CHILDREN_NUMBER);
        assertThat(testMedicalProfile.getEducation()).isEqualTo(DEFAULT_EDUCATION);
        assertThat(testMedicalProfile.getOccupation()).isEqualTo(DEFAULT_OCCUPATION);
        assertThat(testMedicalProfile.getHoursOfWorkDaily()).isEqualTo(DEFAULT_HOURS_OF_WORK_DAILY);
        assertThat(testMedicalProfile.getFinancialStatus()).isEqualTo(DEFAULT_FINANCIAL_STATUS);
        assertThat(testMedicalProfile.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testMedicalProfile.isUseOfMedicine()).isEqualTo(DEFAULT_USE_OF_MEDICINE);
        assertThat(testMedicalProfile.getMedicines()).isEqualTo(DEFAULT_MEDICINES);
        assertThat(testMedicalProfile.isHaveAllergy()).isEqualTo(DEFAULT_HAVE_ALLERGY);
        assertThat(testMedicalProfile.getAllergy()).isEqualTo(DEFAULT_ALLERGY);
        assertThat(testMedicalProfile.isHaveDisease()).isEqualTo(DEFAULT_HAVE_DISEASE);
        assertThat(testMedicalProfile.getBloodType()).isEqualTo(DEFAULT_BLOOD_TYPE);
    }

    @Test
    @Transactional
    public void createMedicalProfileWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = medicalProfileRepository.findAll().size();

        // Create the MedicalProfile with an existing ID
        medicalProfile.setId(1L);
        MedicalProfileDTO medicalProfileDTO = medicalProfileMapper.toDto(medicalProfile);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMedicalProfileMockMvc.perform(post("/api/medical-profiles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalProfileDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MedicalProfile in the database
        List<MedicalProfile> medicalProfileList = medicalProfileRepository.findAll();
        assertThat(medicalProfileList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMedicalProfiles() throws Exception {
        // Initialize the database
        medicalProfileRepository.saveAndFlush(medicalProfile);

        // Get all the medicalProfileList
        restMedicalProfileMockMvc.perform(get("/api/medical-profiles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(medicalProfile.getId().intValue())))
            .andExpect(jsonPath("$.[*].haveLostWeight").value(hasItem(DEFAULT_HAVE_LOST_WEIGHT.booleanValue())))
            .andExpect(jsonPath("$.[*].drowsiness").value(hasItem(DEFAULT_DROWSINESS.booleanValue())))
            .andExpect(jsonPath("$.[*].goodNightSleep").value(hasItem(DEFAULT_GOOD_NIGHT_SLEEP.booleanValue())))
            .andExpect(jsonPath("$.[*].hoursOfTraningPerWeek").value(hasItem(DEFAULT_HOURS_OF_TRANING_PER_WEEK)))
            .andExpect(jsonPath("$.[*].loveForSugar").value(hasItem(DEFAULT_LOVE_FOR_SUGAR.booleanValue())))
            .andExpect(jsonPath("$.[*].overEating").value(hasItem(DEFAULT_OVER_EATING.booleanValue())))
            .andExpect(jsonPath("$.[*].maritalStatus").value(hasItem(DEFAULT_MARITAL_STATUS.toString())))
            .andExpect(jsonPath("$.[*].childrenNumber").value(hasItem(DEFAULT_CHILDREN_NUMBER)))
            .andExpect(jsonPath("$.[*].education").value(hasItem(DEFAULT_EDUCATION.toString())))
            .andExpect(jsonPath("$.[*].occupation").value(hasItem(DEFAULT_OCCUPATION.toString())))
            .andExpect(jsonPath("$.[*].hoursOfWorkDaily").value(hasItem(DEFAULT_HOURS_OF_WORK_DAILY)))
            .andExpect(jsonPath("$.[*].financialStatus").value(hasItem(DEFAULT_FINANCIAL_STATUS.toString())))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY)))
            .andExpect(jsonPath("$.[*].useOfMedicine").value(hasItem(DEFAULT_USE_OF_MEDICINE.booleanValue())))
            .andExpect(jsonPath("$.[*].medicines").value(hasItem(DEFAULT_MEDICINES.toString())))
            .andExpect(jsonPath("$.[*].haveAllergy").value(hasItem(DEFAULT_HAVE_ALLERGY.booleanValue())))
            .andExpect(jsonPath("$.[*].allergy").value(hasItem(DEFAULT_ALLERGY)))
            .andExpect(jsonPath("$.[*].haveDisease").value(hasItem(DEFAULT_HAVE_DISEASE.booleanValue())))
            .andExpect(jsonPath("$.[*].bloodType").value(hasItem(DEFAULT_BLOOD_TYPE.toString())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllMedicalProfilesWithEagerRelationshipsIsEnabled() throws Exception {
        when(medicalProfileServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restMedicalProfileMockMvc.perform(get("/api/medical-profiles?eagerload=true"))
            .andExpect(status().isOk());

        verify(medicalProfileServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllMedicalProfilesWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(medicalProfileServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restMedicalProfileMockMvc.perform(get("/api/medical-profiles?eagerload=true"))
            .andExpect(status().isOk());

        verify(medicalProfileServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getMedicalProfile() throws Exception {
        // Initialize the database
        medicalProfileRepository.saveAndFlush(medicalProfile);

        // Get the medicalProfile
        restMedicalProfileMockMvc.perform(get("/api/medical-profiles/{id}", medicalProfile.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(medicalProfile.getId().intValue()))
            .andExpect(jsonPath("$.haveLostWeight").value(DEFAULT_HAVE_LOST_WEIGHT.booleanValue()))
            .andExpect(jsonPath("$.drowsiness").value(DEFAULT_DROWSINESS.booleanValue()))
            .andExpect(jsonPath("$.goodNightSleep").value(DEFAULT_GOOD_NIGHT_SLEEP.booleanValue()))
            .andExpect(jsonPath("$.hoursOfTraningPerWeek").value(DEFAULT_HOURS_OF_TRANING_PER_WEEK))
            .andExpect(jsonPath("$.loveForSugar").value(DEFAULT_LOVE_FOR_SUGAR.booleanValue()))
            .andExpect(jsonPath("$.overEating").value(DEFAULT_OVER_EATING.booleanValue()))
            .andExpect(jsonPath("$.maritalStatus").value(DEFAULT_MARITAL_STATUS.toString()))
            .andExpect(jsonPath("$.childrenNumber").value(DEFAULT_CHILDREN_NUMBER))
            .andExpect(jsonPath("$.education").value(DEFAULT_EDUCATION.toString()))
            .andExpect(jsonPath("$.occupation").value(DEFAULT_OCCUPATION.toString()))
            .andExpect(jsonPath("$.hoursOfWorkDaily").value(DEFAULT_HOURS_OF_WORK_DAILY))
            .andExpect(jsonPath("$.financialStatus").value(DEFAULT_FINANCIAL_STATUS.toString()))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY))
            .andExpect(jsonPath("$.useOfMedicine").value(DEFAULT_USE_OF_MEDICINE.booleanValue()))
            .andExpect(jsonPath("$.medicines").value(DEFAULT_MEDICINES.toString()))
            .andExpect(jsonPath("$.haveAllergy").value(DEFAULT_HAVE_ALLERGY.booleanValue()))
            .andExpect(jsonPath("$.allergy").value(DEFAULT_ALLERGY))
            .andExpect(jsonPath("$.haveDisease").value(DEFAULT_HAVE_DISEASE.booleanValue()))
            .andExpect(jsonPath("$.bloodType").value(DEFAULT_BLOOD_TYPE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingMedicalProfile() throws Exception {
        // Get the medicalProfile
        restMedicalProfileMockMvc.perform(get("/api/medical-profiles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMedicalProfile() throws Exception {
        // Initialize the database
        medicalProfileRepository.saveAndFlush(medicalProfile);

        int databaseSizeBeforeUpdate = medicalProfileRepository.findAll().size();

        // Update the medicalProfile
        MedicalProfile updatedMedicalProfile = medicalProfileRepository.findById(medicalProfile.getId()).get();
        // Disconnect from session so that the updates on updatedMedicalProfile are not directly saved in db
        em.detach(updatedMedicalProfile);
        updatedMedicalProfile
            .haveLostWeight(UPDATED_HAVE_LOST_WEIGHT)
            .drowsiness(UPDATED_DROWSINESS)
            .goodNightSleep(UPDATED_GOOD_NIGHT_SLEEP)
            .hoursOfTraningPerWeek(UPDATED_HOURS_OF_TRANING_PER_WEEK)
            .loveForSugar(UPDATED_LOVE_FOR_SUGAR)
            .overEating(UPDATED_OVER_EATING)
            .maritalStatus(UPDATED_MARITAL_STATUS)
            .childrenNumber(UPDATED_CHILDREN_NUMBER)
            .education(UPDATED_EDUCATION)
            .occupation(UPDATED_OCCUPATION)
            .hoursOfWorkDaily(UPDATED_HOURS_OF_WORK_DAILY)
            .financialStatus(UPDATED_FINANCIAL_STATUS)
            .city(UPDATED_CITY)
            .useOfMedicine(UPDATED_USE_OF_MEDICINE)
            .medicines(UPDATED_MEDICINES)
            .haveAllergy(UPDATED_HAVE_ALLERGY)
            .allergy(UPDATED_ALLERGY)
            .haveDisease(UPDATED_HAVE_DISEASE)
            .bloodType(UPDATED_BLOOD_TYPE);
        MedicalProfileDTO medicalProfileDTO = medicalProfileMapper.toDto(updatedMedicalProfile);

        restMedicalProfileMockMvc.perform(put("/api/medical-profiles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalProfileDTO)))
            .andExpect(status().isOk());

        // Validate the MedicalProfile in the database
        List<MedicalProfile> medicalProfileList = medicalProfileRepository.findAll();
        assertThat(medicalProfileList).hasSize(databaseSizeBeforeUpdate);
        MedicalProfile testMedicalProfile = medicalProfileList.get(medicalProfileList.size() - 1);
        assertThat(testMedicalProfile.isHaveLostWeight()).isEqualTo(UPDATED_HAVE_LOST_WEIGHT);
        assertThat(testMedicalProfile.isDrowsiness()).isEqualTo(UPDATED_DROWSINESS);
        assertThat(testMedicalProfile.isGoodNightSleep()).isEqualTo(UPDATED_GOOD_NIGHT_SLEEP);
        assertThat(testMedicalProfile.getHoursOfTraningPerWeek()).isEqualTo(UPDATED_HOURS_OF_TRANING_PER_WEEK);
        assertThat(testMedicalProfile.isLoveForSugar()).isEqualTo(UPDATED_LOVE_FOR_SUGAR);
        assertThat(testMedicalProfile.isOverEating()).isEqualTo(UPDATED_OVER_EATING);
        assertThat(testMedicalProfile.getMaritalStatus()).isEqualTo(UPDATED_MARITAL_STATUS);
        assertThat(testMedicalProfile.getChildrenNumber()).isEqualTo(UPDATED_CHILDREN_NUMBER);
        assertThat(testMedicalProfile.getEducation()).isEqualTo(UPDATED_EDUCATION);
        assertThat(testMedicalProfile.getOccupation()).isEqualTo(UPDATED_OCCUPATION);
        assertThat(testMedicalProfile.getHoursOfWorkDaily()).isEqualTo(UPDATED_HOURS_OF_WORK_DAILY);
        assertThat(testMedicalProfile.getFinancialStatus()).isEqualTo(UPDATED_FINANCIAL_STATUS);
        assertThat(testMedicalProfile.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testMedicalProfile.isUseOfMedicine()).isEqualTo(UPDATED_USE_OF_MEDICINE);
        assertThat(testMedicalProfile.getMedicines()).isEqualTo(UPDATED_MEDICINES);
        assertThat(testMedicalProfile.isHaveAllergy()).isEqualTo(UPDATED_HAVE_ALLERGY);
        assertThat(testMedicalProfile.getAllergy()).isEqualTo(UPDATED_ALLERGY);
        assertThat(testMedicalProfile.isHaveDisease()).isEqualTo(UPDATED_HAVE_DISEASE);
        assertThat(testMedicalProfile.getBloodType()).isEqualTo(UPDATED_BLOOD_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingMedicalProfile() throws Exception {
        int databaseSizeBeforeUpdate = medicalProfileRepository.findAll().size();

        // Create the MedicalProfile
        MedicalProfileDTO medicalProfileDTO = medicalProfileMapper.toDto(medicalProfile);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMedicalProfileMockMvc.perform(put("/api/medical-profiles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalProfileDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MedicalProfile in the database
        List<MedicalProfile> medicalProfileList = medicalProfileRepository.findAll();
        assertThat(medicalProfileList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMedicalProfile() throws Exception {
        // Initialize the database
        medicalProfileRepository.saveAndFlush(medicalProfile);

        int databaseSizeBeforeDelete = medicalProfileRepository.findAll().size();

        // Delete the medicalProfile
        restMedicalProfileMockMvc.perform(delete("/api/medical-profiles/{id}", medicalProfile.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MedicalProfile> medicalProfileList = medicalProfileRepository.findAll();
        assertThat(medicalProfileList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
