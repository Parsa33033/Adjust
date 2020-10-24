package com.adjust.api.web.rest;

import com.adjust.api.service.ProgramDescriptionService;
import com.adjust.api.web.rest.errors.BadRequestAlertException;
import com.adjust.api.service.dto.ProgramDescriptionDTO;

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
 * REST controller for managing {@link com.adjust.api.domain.ProgramDescription}.
 */
@RestController
@RequestMapping("/api")
public class ProgramDescriptionResource {

    private final Logger log = LoggerFactory.getLogger(ProgramDescriptionResource.class);

    private static final String ENTITY_NAME = "programDescription";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProgramDescriptionService programDescriptionService;

    public ProgramDescriptionResource(ProgramDescriptionService programDescriptionService) {
        this.programDescriptionService = programDescriptionService;
    }

    /**
     * {@code POST  /program-descriptions} : Create a new programDescription.
     *
     * @param programDescriptionDTO the programDescriptionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new programDescriptionDTO, or with status {@code 400 (Bad Request)} if the programDescription has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/program-descriptions")
    public ResponseEntity<ProgramDescriptionDTO> createProgramDescription(@RequestBody ProgramDescriptionDTO programDescriptionDTO) throws URISyntaxException {
        log.debug("REST request to save ProgramDescription : {}", programDescriptionDTO);
        if (programDescriptionDTO.getId() != null) {
            throw new BadRequestAlertException("A new programDescription cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProgramDescriptionDTO result = programDescriptionService.save(programDescriptionDTO);
        return ResponseEntity.created(new URI("/api/program-descriptions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /program-descriptions} : Updates an existing programDescription.
     *
     * @param programDescriptionDTO the programDescriptionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated programDescriptionDTO,
     * or with status {@code 400 (Bad Request)} if the programDescriptionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the programDescriptionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/program-descriptions")
    public ResponseEntity<ProgramDescriptionDTO> updateProgramDescription(@RequestBody ProgramDescriptionDTO programDescriptionDTO) throws URISyntaxException {
        log.debug("REST request to update ProgramDescription : {}", programDescriptionDTO);
        if (programDescriptionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProgramDescriptionDTO result = programDescriptionService.save(programDescriptionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, programDescriptionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /program-descriptions} : get all the programDescriptions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of programDescriptions in body.
     */
    @GetMapping("/program-descriptions")
    public List<ProgramDescriptionDTO> getAllProgramDescriptions() {
        log.debug("REST request to get all ProgramDescriptions");
        return programDescriptionService.findAll();
    }

    /**
     * {@code GET  /program-descriptions/:id} : get the "id" programDescription.
     *
     * @param id the id of the programDescriptionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the programDescriptionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/program-descriptions/{id}")
    public ResponseEntity<ProgramDescriptionDTO> getProgramDescription(@PathVariable Long id) {
        log.debug("REST request to get ProgramDescription : {}", id);
        Optional<ProgramDescriptionDTO> programDescriptionDTO = programDescriptionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(programDescriptionDTO);
    }

    /**
     * {@code DELETE  /program-descriptions/:id} : delete the "id" programDescription.
     *
     * @param id the id of the programDescriptionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/program-descriptions/{id}")
    public ResponseEntity<Void> deleteProgramDescription(@PathVariable Long id) {
        log.debug("REST request to delete ProgramDescription : {}", id);
        programDescriptionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
