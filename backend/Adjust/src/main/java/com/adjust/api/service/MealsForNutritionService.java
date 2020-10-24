package com.adjust.api.service;

import com.adjust.api.domain.MealsForNutrition;
import com.adjust.api.repository.MealsForNutritionRepository;
import com.adjust.api.service.dto.MealsForNutritionDTO;
import com.adjust.api.service.mapper.MealsForNutritionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link MealsForNutrition}.
 */
@Service
@Transactional
public class MealsForNutritionService {

    private final Logger log = LoggerFactory.getLogger(MealsForNutritionService.class);

    private final MealsForNutritionRepository mealsForNutritionRepository;

    private final MealsForNutritionMapper mealsForNutritionMapper;

    public MealsForNutritionService(MealsForNutritionRepository mealsForNutritionRepository, MealsForNutritionMapper mealsForNutritionMapper) {
        this.mealsForNutritionRepository = mealsForNutritionRepository;
        this.mealsForNutritionMapper = mealsForNutritionMapper;
    }

    /**
     * Save a mealsForNutrition.
     *
     * @param mealsForNutritionDTO the entity to save.
     * @return the persisted entity.
     */
    public MealsForNutritionDTO save(MealsForNutritionDTO mealsForNutritionDTO) {
        log.debug("Request to save MealsForNutrition : {}", mealsForNutritionDTO);
        MealsForNutrition mealsForNutrition = mealsForNutritionMapper.toEntity(mealsForNutritionDTO);
        mealsForNutrition = mealsForNutritionRepository.save(mealsForNutrition);
        return mealsForNutritionMapper.toDto(mealsForNutrition);
    }

    /**
     * Get all the mealsForNutritions.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<MealsForNutritionDTO> findAll() {
        log.debug("Request to get all MealsForNutritions");
        return mealsForNutritionRepository.findAll().stream()
            .map(mealsForNutritionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one mealsForNutrition by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MealsForNutritionDTO> findOne(Long id) {
        log.debug("Request to get MealsForNutrition : {}", id);
        return mealsForNutritionRepository.findById(id)
            .map(mealsForNutritionMapper::toDto);
    }

    /**
     * Delete the mealsForNutrition by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MealsForNutrition : {}", id);
        mealsForNutritionRepository.deleteById(id);
    }
}
