package com.adjust.api.web.rest;

import com.adjust.api.service.ShoppingItemService;
import com.adjust.api.web.rest.errors.BadRequestAlertException;
import com.adjust.api.service.dto.ShoppingItemDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.adjust.api.domain.ShoppingItem}.
 */
@RestController
@RequestMapping("/api")
public class ShoppingItemResource {

    private final Logger log = LoggerFactory.getLogger(ShoppingItemResource.class);

    private static final String ENTITY_NAME = "shoppingItem";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ShoppingItemService shoppingItemService;

    public ShoppingItemResource(ShoppingItemService shoppingItemService) {
        this.shoppingItemService = shoppingItemService;
    }

    /**
     * {@code POST  /shopping-items} : Create a new shoppingItem.
     *
     * @param shoppingItemDTO the shoppingItemDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new shoppingItemDTO, or with status {@code 400 (Bad Request)} if the shoppingItem has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/shopping-items")
    public ResponseEntity<ShoppingItemDTO> createShoppingItem(@RequestBody ShoppingItemDTO shoppingItemDTO) throws URISyntaxException {
        log.debug("REST request to save ShoppingItem : {}", shoppingItemDTO);
        if (shoppingItemDTO.getId() != null) {
            throw new BadRequestAlertException("A new shoppingItem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ShoppingItemDTO result = shoppingItemService.save(shoppingItemDTO);
        return ResponseEntity.created(new URI("/api/shopping-items/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /shopping-items} : Updates an existing shoppingItem.
     *
     * @param shoppingItemDTO the shoppingItemDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated shoppingItemDTO,
     * or with status {@code 400 (Bad Request)} if the shoppingItemDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the shoppingItemDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/shopping-items")
    public ResponseEntity<ShoppingItemDTO> updateShoppingItem(@RequestBody ShoppingItemDTO shoppingItemDTO) throws URISyntaxException {
        log.debug("REST request to update ShoppingItem : {}", shoppingItemDTO);
        if (shoppingItemDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ShoppingItemDTO result = shoppingItemService.save(shoppingItemDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, shoppingItemDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /shopping-items} : get all the shoppingItems.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of shoppingItems in body.
     */
    @GetMapping("/shopping-items")
    public List<ShoppingItemDTO> getAllShoppingItems() {
        log.debug("REST request to get all ShoppingItems");
        return shoppingItemService.findAll();
    }

    /**
     * {@code GET  /shopping-items/:id} : get the "id" shoppingItem.
     *
     * @param id the id of the shoppingItemDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the shoppingItemDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/shopping-items/{id}")
    public ResponseEntity<ShoppingItemDTO> getShoppingItem(@PathVariable Long id) {
        log.debug("REST request to get ShoppingItem : {}", id);
        Optional<ShoppingItemDTO> shoppingItemDTO = shoppingItemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(shoppingItemDTO);
    }

    /**
     * {@code DELETE  /shopping-items/:id} : delete the "id" shoppingItem.
     *
     * @param id the id of the shoppingItemDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/shopping-items/{id}")
    public ResponseEntity<Void> deleteShoppingItem(@PathVariable Long id) {
        log.debug("REST request to delete ShoppingItem : {}", id);
        shoppingItemService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
