package com.adjust.api.service;

import com.adjust.api.domain.ProgramDescription;
import com.adjust.api.repository.ProgramDescriptionRepository;
import com.adjust.api.service.dto.ProgramDescriptionDTO;
import com.adjust.api.service.mapper.ProgramDescriptionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ProgramDescription}.
 */
@Service
@Transactional
public class ProgramDescriptionService {

    private final Logger log = LoggerFactory.getLogger(ProgramDescriptionService.class);

    private final ProgramDescriptionRepository programDescriptionRepository;

    private final ProgramDescriptionMapper programDescriptionMapper;

    public ProgramDescriptionService(ProgramDescriptionRepository programDescriptionRepository, ProgramDescriptionMapper programDescriptionMapper) {
        this.programDescriptionRepository = programDescriptionRepository;
        this.programDescriptionMapper = programDescriptionMapper;
    }

    /**
     * Save a programDescription.
     *
     * @param programDescriptionDTO the entity to save.
     * @return the persisted entity.
     */
    public ProgramDescriptionDTO save(ProgramDescriptionDTO programDescriptionDTO) {
        log.debug("Request to save ProgramDescription : {}", programDescriptionDTO);
        ProgramDescription programDescription = programDescriptionMapper.toEntity(programDescriptionDTO);
        programDescription = programDescriptionRepository.save(programDescription);
        return programDescriptionMapper.toDto(programDescription);
    }

    /**
     * Get all the programDescriptions.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ProgramDescriptionDTO> findAll() {
        log.debug("Request to get all ProgramDescriptions");
        return programDescriptionRepository.findAll().stream()
            .map(programDescriptionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one programDescription by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ProgramDescriptionDTO> findOne(Long id) {
        log.debug("Request to get ProgramDescription : {}", id);
        return programDescriptionRepository.findById(id)
            .map(programDescriptionMapper::toDto);
    }

    /**
     * Delete the programDescription by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ProgramDescription : {}", id);
        programDescriptionRepository.deleteById(id);
    }
}
