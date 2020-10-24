package com.adjust.api.service;

import com.adjust.api.domain.Disease;
import com.adjust.api.repository.DiseaseRepository;
import com.adjust.api.service.dto.DiseaseDTO;
import com.adjust.api.service.mapper.DiseaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Disease}.
 */
@Service
@Transactional
public class DiseaseService {

    private final Logger log = LoggerFactory.getLogger(DiseaseService.class);

    private final DiseaseRepository diseaseRepository;

    private final DiseaseMapper diseaseMapper;

    public DiseaseService(DiseaseRepository diseaseRepository, DiseaseMapper diseaseMapper) {
        this.diseaseRepository = diseaseRepository;
        this.diseaseMapper = diseaseMapper;
    }

    /**
     * Save a disease.
     *
     * @param diseaseDTO the entity to save.
     * @return the persisted entity.
     */
    public DiseaseDTO save(DiseaseDTO diseaseDTO) {
        log.debug("Request to save Disease : {}", diseaseDTO);
        Disease disease = diseaseMapper.toEntity(diseaseDTO);
        disease = diseaseRepository.save(disease);
        return diseaseMapper.toDto(disease);
    }

    /**
     * Get all the diseases.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<DiseaseDTO> findAll() {
        log.debug("Request to get all Diseases");
        return diseaseRepository.findAll().stream()
            .map(diseaseMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one disease by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DiseaseDTO> findOne(Long id) {
        log.debug("Request to get Disease : {}", id);
        return diseaseRepository.findById(id)
            .map(diseaseMapper::toDto);
    }

    /**
     * Delete the disease by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Disease : {}", id);
        diseaseRepository.deleteById(id);
    }
}
