package com.adjust.api.web.rest;

import com.adjust.api.service.DevelopmentService;
import com.adjust.api.web.rest.errors.BadRequestAlertException;
import com.adjust.api.service.dto.DevelopmentDTO;

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
 * REST controller for managing {@link com.adjust.api.domain.Development}.
 */
@RestController
@RequestMapping("/api")
public class DevelopmentResource {

    private final Logger log = LoggerFactory.getLogger(DevelopmentResource.class);

    private static final String ENTITY_NAME = "development";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DevelopmentService developmentService;

    public DevelopmentResource(DevelopmentService developmentService) {
        this.developmentService = developmentService;
    }

    /**
     * {@code POST  /developments} : Create a new development.
     *
     * @param developmentDTO the developmentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new developmentDTO, or with status {@code 400 (Bad Request)} if the development has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/developments")
    public ResponseEntity<DevelopmentDTO> createDevelopment(@RequestBody DevelopmentDTO developmentDTO) throws URISyntaxException {
        log.debug("REST request to save Development : {}", developmentDTO);
        if (developmentDTO.getId() != null) {
            throw new BadRequestAlertException("A new development cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DevelopmentDTO result = developmentService.save(developmentDTO);
        return ResponseEntity.created(new URI("/api/developments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /developments} : Updates an existing development.
     *
     * @param developmentDTO the developmentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated developmentDTO,
     * or with status {@code 400 (Bad Request)} if the developmentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the developmentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/developments")
    public ResponseEntity<DevelopmentDTO> updateDevelopment(@RequestBody DevelopmentDTO developmentDTO) throws URISyntaxException {
        log.debug("REST request to update Development : {}", developmentDTO);
        if (developmentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DevelopmentDTO result = developmentService.save(developmentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, developmentDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /developments} : get all the developments.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of developments in body.
     */
    @GetMapping("/developments")
    public List<DevelopmentDTO> getAllDevelopments() {
        log.debug("REST request to get all Developments");
        return developmentService.findAll();
    }

    /**
     * {@code GET  /developments/:id} : get the "id" development.
     *
     * @param id the id of the developmentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the developmentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/developments/{id}")
    public ResponseEntity<DevelopmentDTO> getDevelopment(@PathVariable Long id) {
        log.debug("REST request to get Development : {}", id);
        Optional<DevelopmentDTO> developmentDTO = developmentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(developmentDTO);
    }

    /**
     * {@code DELETE  /developments/:id} : delete the "id" development.
     *
     * @param id the id of the developmentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/developments/{id}")
    public ResponseEntity<Void> deleteDevelopment(@PathVariable Long id) {
        log.debug("REST request to delete Development : {}", id);
        developmentService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
