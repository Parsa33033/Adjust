package com.adjust.api.service;

import com.adjust.api.domain.MedicalProfile;
import com.adjust.api.repository.MedicalProfileRepository;
import com.adjust.api.service.dto.MedicalProfileDTO;
import com.adjust.api.service.mapper.MedicalProfileMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link MedicalProfile}.
 */
@Service
@Transactional
public class MedicalProfileService {

    private final Logger log = LoggerFactory.getLogger(MedicalProfileService.class);

    private final MedicalProfileRepository medicalProfileRepository;

    private final MedicalProfileMapper medicalProfileMapper;

    public MedicalProfileService(MedicalProfileRepository medicalProfileRepository, MedicalProfileMapper medicalProfileMapper) {
        this.medicalProfileRepository = medicalProfileRepository;
        this.medicalProfileMapper = medicalProfileMapper;
    }

    /**
     * Save a medicalProfile.
     *
     * @param medicalProfileDTO the entity to save.
     * @return the persisted entity.
     */
    public MedicalProfileDTO save(MedicalProfileDTO medicalProfileDTO) {
        log.debug("Request to save MedicalProfile : {}", medicalProfileDTO);
        MedicalProfile medicalProfile = medicalProfileMapper.toEntity(medicalProfileDTO);
        medicalProfile = medicalProfileRepository.save(medicalProfile);
        return medicalProfileMapper.toDto(medicalProfile);
    }

    /**
     * Get all the medicalProfiles.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<MedicalProfileDTO> findAll() {
        log.debug("Request to get all MedicalProfiles");
        return medicalProfileRepository.findAllWithEagerRelationships().stream()
            .map(medicalProfileMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get all the medicalProfiles with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<MedicalProfileDTO> findAllWithEagerRelationships(Pageable pageable) {
        return medicalProfileRepository.findAllWithEagerRelationships(pageable).map(medicalProfileMapper::toDto);
    }


    /**
     *  Get all the medicalProfiles where AdjustClient is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<MedicalProfileDTO> findAllWhereAdjustClientIsNull() {
        log.debug("Request to get all medicalProfiles where AdjustClient is null");
        return StreamSupport
            .stream(medicalProfileRepository.findAll().spliterator(), false)
            .filter(medicalProfile -> medicalProfile.getAdjustClient() == null)
            .map(medicalProfileMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one medicalProfile by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MedicalProfileDTO> findOne(Long id) {
        log.debug("Request to get MedicalProfile : {}", id);
        return medicalProfileRepository.findOneWithEagerRelationships(id)
            .map(medicalProfileMapper::toDto);
    }

    /**
     * Delete the medicalProfile by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MedicalProfile : {}", id);
        medicalProfileRepository.deleteById(id);
    }
}
