package com.adjust.api.web.rest;

import com.adjust.api.service.FoodService;
import com.adjust.api.web.rest.errors.BadRequestAlertException;
import com.adjust.api.service.dto.FoodDTO;

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
 * REST controller for managing {@link com.adjust.api.domain.Food}.
 */
@RestController
@RequestMapping("/api")
public class FoodResource {

    private final Logger log = LoggerFactory.getLogger(FoodResource.class);

    private static final String ENTITY_NAME = "food";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FoodService foodService;

    public FoodResource(FoodService foodService) {
        this.foodService = foodService;
    }

    /**
     * {@code POST  /foods} : Create a new food.
     *
     * @param foodDTO the foodDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new foodDTO, or with status {@code 400 (Bad Request)} if the food has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/foods")
    public ResponseEntity<FoodDTO> createFood(@RequestBody FoodDTO foodDTO) throws URISyntaxException {
        log.debug("REST request to save Food : {}", foodDTO);
        if (foodDTO.getId() != null) {
            throw new BadRequestAlertException("A new food cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FoodDTO result = foodService.save(foodDTO);
        return ResponseEntity.created(new URI("/api/foods/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /foods} : Updates an existing food.
     *
     * @param foodDTO the foodDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated foodDTO,
     * or with status {@code 400 (Bad Request)} if the foodDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the foodDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/foods")
    public ResponseEntity<FoodDTO> updateFood(@RequestBody FoodDTO foodDTO) throws URISyntaxException {
        log.debug("REST request to update Food : {}", foodDTO);
        if (foodDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FoodDTO result = foodService.save(foodDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, foodDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /foods} : get all the foods.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of foods in body.
     */
    @GetMapping("/foods")
    public List<FoodDTO> getAllFoods() {
        log.debug("REST request to get all Foods");
        return foodService.findAll();
    }

    /**
     * {@code GET  /foods/:id} : get the "id" food.
     *
     * @param id the id of the foodDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the foodDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/foods/{id}")
    public ResponseEntity<FoodDTO> getFood(@PathVariable Long id) {
        log.debug("REST request to get Food : {}", id);
        Optional<FoodDTO> foodDTO = foodService.findOne(id);
        return ResponseUtil.wrapOrNotFound(foodDTO);
    }

    /**
     * {@code DELETE  /foods/:id} : delete the "id" food.
     *
     * @param id the id of the foodDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/foods/{id}")
    public ResponseEntity<Void> deleteFood(@PathVariable Long id) {
        log.debug("REST request to delete Food : {}", id);
        foodService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
