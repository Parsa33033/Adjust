package com.adjust.api.service;

import com.adjust.api.domain.AdjustToken;
import com.adjust.api.repository.AdjustTokenRepository;
import com.adjust.api.service.dto.AdjustTokenDTO;
import com.adjust.api.service.mapper.AdjustTokenMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link AdjustToken}.
 */
@Service
@Transactional
public class AdjustTokenService {

    private final Logger log = LoggerFactory.getLogger(AdjustTokenService.class);

    private final AdjustTokenRepository adjustTokenRepository;

    private final AdjustTokenMapper adjustTokenMapper;

    public AdjustTokenService(AdjustTokenRepository adjustTokenRepository, AdjustTokenMapper adjustTokenMapper) {
        this.adjustTokenRepository = adjustTokenRepository;
        this.adjustTokenMapper = adjustTokenMapper;
    }

    /**
     * Save a adjustToken.
     *
     * @param adjustTokenDTO the entity to save.
     * @return the persisted entity.
     */
    public AdjustTokenDTO save(AdjustTokenDTO adjustTokenDTO) {
        log.debug("Request to save AdjustToken : {}", adjustTokenDTO);
        AdjustToken adjustToken = adjustTokenMapper.toEntity(adjustTokenDTO);
        adjustToken = adjustTokenRepository.save(adjustToken);
        return adjustTokenMapper.toDto(adjustToken);
    }

    /**
     * Get all the adjustTokens.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AdjustTokenDTO> findAll() {
        log.debug("Request to get all AdjustTokens");
        return adjustTokenRepository.findAll().stream()
            .map(adjustTokenMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one adjustToken by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AdjustTokenDTO> findOne(Long id) {
        log.debug("Request to get AdjustToken : {}", id);
        return adjustTokenRepository.findById(id)
            .map(adjustTokenMapper::toDto);
    }

    /**
     * Delete the adjustToken by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AdjustToken : {}", id);
        adjustTokenRepository.deleteById(id);
    }
}
