package com.adjust.api.web.rest;

import com.adjust.api.service.AdjustTokenService;
import com.adjust.api.web.rest.errors.BadRequestAlertException;
import com.adjust.api.service.dto.AdjustTokenDTO;

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
 * REST controller for managing {@link com.adjust.api.domain.AdjustToken}.
 */
@RestController
@RequestMapping("/api")
public class AdjustTokenResource {

    private final Logger log = LoggerFactory.getLogger(AdjustTokenResource.class);

    private static final String ENTITY_NAME = "adjustToken";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdjustTokenService adjustTokenService;

    public AdjustTokenResource(AdjustTokenService adjustTokenService) {
        this.adjustTokenService = adjustTokenService;
    }

    /**
     * {@code POST  /adjust-tokens} : Create a new adjustToken.
     *
     * @param adjustTokenDTO the adjustTokenDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new adjustTokenDTO, or with status {@code 400 (Bad Request)} if the adjustToken has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adjust-tokens")
    public ResponseEntity<AdjustTokenDTO> createAdjustToken(@RequestBody AdjustTokenDTO adjustTokenDTO) throws URISyntaxException {
        log.debug("REST request to save AdjustToken : {}", adjustTokenDTO);
        if (adjustTokenDTO.getId() != null) {
            throw new BadRequestAlertException("A new adjustToken cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdjustTokenDTO result = adjustTokenService.save(adjustTokenDTO);
        return ResponseEntity.created(new URI("/api/adjust-tokens/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /adjust-tokens} : Updates an existing adjustToken.
     *
     * @param adjustTokenDTO the adjustTokenDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated adjustTokenDTO,
     * or with status {@code 400 (Bad Request)} if the adjustTokenDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the adjustTokenDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adjust-tokens")
    public ResponseEntity<AdjustTokenDTO> updateAdjustToken(@RequestBody AdjustTokenDTO adjustTokenDTO) throws URISyntaxException {
        log.debug("REST request to update AdjustToken : {}", adjustTokenDTO);
        if (adjustTokenDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AdjustTokenDTO result = adjustTokenService.save(adjustTokenDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, adjustTokenDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /adjust-tokens} : get all the adjustTokens.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of adjustTokens in body.
     */
    @GetMapping("/adjust-tokens")
    public List<AdjustTokenDTO> getAllAdjustTokens() {
        log.debug("REST request to get all AdjustTokens");
        return adjustTokenService.findAll();
    }

    /**
     * {@code GET  /adjust-tokens/:id} : get the "id" adjustToken.
     *
     * @param id the id of the adjustTokenDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the adjustTokenDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adjust-tokens/{id}")
    public ResponseEntity<AdjustTokenDTO> getAdjustToken(@PathVariable Long id) {
        log.debug("REST request to get AdjustToken : {}", id);
        Optional<AdjustTokenDTO> adjustTokenDTO = adjustTokenService.findOne(id);
        return ResponseUtil.wrapOrNotFound(adjustTokenDTO);
    }

    /**
     * {@code DELETE  /adjust-tokens/:id} : delete the "id" adjustToken.
     *
     * @param id the id of the adjustTokenDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/adjust-tokens/{id}")
    public ResponseEntity<Void> deleteAdjustToken(@PathVariable Long id) {
        log.debug("REST request to delete AdjustToken : {}", id);
        adjustTokenService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
