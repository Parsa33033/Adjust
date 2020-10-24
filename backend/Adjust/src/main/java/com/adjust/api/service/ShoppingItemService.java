package com.adjust.api.service;

import com.adjust.api.domain.ShoppingItem;
import com.adjust.api.repository.ShoppingItemRepository;
import com.adjust.api.service.dto.ShoppingItemDTO;
import com.adjust.api.service.mapper.ShoppingItemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ShoppingItem}.
 */
@Service
@Transactional
public class ShoppingItemService {

    private final Logger log = LoggerFactory.getLogger(ShoppingItemService.class);

    private final ShoppingItemRepository shoppingItemRepository;

    private final ShoppingItemMapper shoppingItemMapper;

    public ShoppingItemService(ShoppingItemRepository shoppingItemRepository, ShoppingItemMapper shoppingItemMapper) {
        this.shoppingItemRepository = shoppingItemRepository;
        this.shoppingItemMapper = shoppingItemMapper;
    }

    /**
     * Save a shoppingItem.
     *
     * @param shoppingItemDTO the entity to save.
     * @return the persisted entity.
     */
    public ShoppingItemDTO save(ShoppingItemDTO shoppingItemDTO) {
        log.debug("Request to save ShoppingItem : {}", shoppingItemDTO);
        ShoppingItem shoppingItem = shoppingItemMapper.toEntity(shoppingItemDTO);
        shoppingItem = shoppingItemRepository.save(shoppingItem);
        return shoppingItemMapper.toDto(shoppingItem);
    }

    /**
     * Get all the shoppingItems.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ShoppingItemDTO> findAll() {
        log.debug("Request to get all ShoppingItems");
        return shoppingItemRepository.findAll().stream()
            .map(shoppingItemMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one shoppingItem by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ShoppingItemDTO> findOne(Long id) {
        log.debug("Request to get ShoppingItem : {}", id);
        return shoppingItemRepository.findById(id)
            .map(shoppingItemMapper::toDto);
    }

    /**
     * Delete the shoppingItem by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ShoppingItem : {}", id);
        shoppingItemRepository.deleteById(id);
    }
}
