package com.adjust.api.web.rest;

import com.adjust.api.service.MealsForNutritionService;
import com.adjust.api.web.rest.errors.BadRequestAlertException;
import com.adjust.api.service.dto.MealsForNutritionDTO;

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
 * REST controller for managing {@link com.adjust.api.domain.MealsForNutrition}.
 */
@RestController
@RequestMapping("/api")
public class MealsForNutritionResource {

    private final Logger log = LoggerFactory.getLogger(MealsForNutritionResource.class);

    private static final String ENTITY_NAME = "mealsForNutrition";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MealsForNutritionService mealsForNutritionService;

    public MealsForNutritionResource(MealsForNutritionService mealsForNutritionService) {
        this.mealsForNutritionService = mealsForNutritionService;
    }

    /**
     * {@code POST  /meals-for-nutritions} : Create a new mealsForNutrition.
     *
     * @param mealsForNutritionDTO the mealsForNutritionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mealsForNutritionDTO, or with status {@code 400 (Bad Request)} if the mealsForNutrition has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/meals-for-nutritions")
    public ResponseEntity<MealsForNutritionDTO> createMealsForNutrition(@RequestBody MealsForNutritionDTO mealsForNutritionDTO) throws URISyntaxException {
        log.debug("REST request to save MealsForNutrition : {}", mealsForNutritionDTO);
        if (mealsForNutritionDTO.getId() != null) {
            throw new BadRequestAlertException("A new mealsForNutrition cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MealsForNutritionDTO result = mealsForNutritionService.save(mealsForNutritionDTO);
        return ResponseEntity.created(new URI("/api/meals-for-nutritions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /meals-for-nutritions} : Updates an existing mealsForNutrition.
     *
     * @param mealsForNutritionDTO the mealsForNutritionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mealsForNutritionDTO,
     * or with status {@code 400 (Bad Request)} if the mealsForNutritionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mealsForNutritionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/meals-for-nutritions")
    public ResponseEntity<MealsForNutritionDTO> updateMealsForNutrition(@RequestBody MealsForNutritionDTO mealsForNutritionDTO) throws URISyntaxException {
        log.debug("REST request to update MealsForNutrition : {}", mealsForNutritionDTO);
        if (mealsForNutritionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MealsForNutritionDTO result = mealsForNutritionService.save(mealsForNutritionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, mealsForNutritionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /meals-for-nutritions} : get all the mealsForNutritions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mealsForNutritions in body.
     */
    @GetMapping("/meals-for-nutritions")
    public List<MealsForNutritionDTO> getAllMealsForNutritions() {
        log.debug("REST request to get all MealsForNutritions");
        return mealsForNutritionService.findAll();
    }

    /**
     * {@code GET  /meals-for-nutritions/:id} : get the "id" mealsForNutrition.
     *
     * @param id the id of the mealsForNutritionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mealsForNutritionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/meals-for-nutritions/{id}")
    public ResponseEntity<MealsForNutritionDTO> getMealsForNutrition(@PathVariable Long id) {
        log.debug("REST request to get MealsForNutrition : {}", id);
        Optional<MealsForNutritionDTO> mealsForNutritionDTO = mealsForNutritionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mealsForNutritionDTO);
    }

    /**
     * {@code DELETE  /meals-for-nutritions/:id} : delete the "id" mealsForNutrition.
     *
     * @param id the id of the mealsForNutritionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/meals-for-nutritions/{id}")
    public ResponseEntity<Void> deleteMealsForNutrition(@PathVariable Long id) {
        log.debug("REST request to delete MealsForNutrition : {}", id);
        mealsForNutritionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
