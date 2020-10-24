package com.adjust.api.service;

import com.adjust.api.domain.Development;
import com.adjust.api.repository.DevelopmentRepository;
import com.adjust.api.service.dto.DevelopmentDTO;
import com.adjust.api.service.mapper.DevelopmentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Development}.
 */
@Service
@Transactional
public class DevelopmentService {

    private final Logger log = LoggerFactory.getLogger(DevelopmentService.class);

    private final DevelopmentRepository developmentRepository;

    private final DevelopmentMapper developmentMapper;

    public DevelopmentService(DevelopmentRepository developmentRepository, DevelopmentMapper developmentMapper) {
        this.developmentRepository = developmentRepository;
        this.developmentMapper = developmentMapper;
    }

    /**
     * Save a development.
     *
     * @param developmentDTO the entity to save.
     * @return the persisted entity.
     */
    public DevelopmentDTO save(DevelopmentDTO developmentDTO) {
        log.debug("Request to save Development : {}", developmentDTO);
        Development development = developmentMapper.toEntity(developmentDTO);
        development = developmentRepository.save(development);
        return developmentMapper.toDto(development);
    }

    /**
     * Get all the developments.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<DevelopmentDTO> findAll() {
        log.debug("Request to get all Developments");
        return developmentRepository.findAll().stream()
            .map(developmentMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one development by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DevelopmentDTO> findOne(Long id) {
        log.debug("Request to get Development : {}", id);
        return developmentRepository.findById(id)
            .map(developmentMapper::toDto);
    }

    /**
     * Delete the development by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Development : {}", id);
        developmentRepository.deleteById(id);
    }
}
