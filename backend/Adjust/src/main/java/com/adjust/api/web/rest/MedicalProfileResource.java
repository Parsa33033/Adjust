package com.adjust.api.web.rest;

import com.adjust.api.service.MedicalProfileService;
import com.adjust.api.web.rest.errors.BadRequestAlertException;
import com.adjust.api.service.dto.MedicalProfileDTO;

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
import java.util.stream.StreamSupport;

/**
 * REST controller for managing {@link com.adjust.api.domain.MedicalProfile}.
 */
@RestController
@RequestMapping("/api")
public class MedicalProfileResource {

    private final Logger log = LoggerFactory.getLogger(MedicalProfileResource.class);

    private static final String ENTITY_NAME = "medicalProfile";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MedicalProfileService medicalProfileService;

    public MedicalProfileResource(MedicalProfileService medicalProfileService) {
        this.medicalProfileService = medicalProfileService;
    }

    /**
     * {@code POST  /medical-profiles} : Create a new medicalProfile.
     *
     * @param medicalProfileDTO the medicalProfileDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new medicalProfileDTO, or with status {@code 400 (Bad Request)} if the medicalProfile has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/medical-profiles")
    public ResponseEntity<MedicalProfileDTO> createMedicalProfile(@RequestBody MedicalProfileDTO medicalProfileDTO) throws URISyntaxException {
        log.debug("REST request to save MedicalProfile : {}", medicalProfileDTO);
        if (medicalProfileDTO.getId() != null) {
            throw new BadRequestAlertException("A new medicalProfile cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MedicalProfileDTO result = medicalProfileService.save(medicalProfileDTO);
        return ResponseEntity.created(new URI("/api/medical-profiles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /medical-profiles} : Updates an existing medicalProfile.
     *
     * @param medicalProfileDTO the medicalProfileDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated medicalProfileDTO,
     * or with status {@code 400 (Bad Request)} if the medicalProfileDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the medicalProfileDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/medical-profiles")
    public ResponseEntity<MedicalProfileDTO> updateMedicalProfile(@RequestBody MedicalProfileDTO medicalProfileDTO) throws URISyntaxException {
        log.debug("REST request to update MedicalProfile : {}", medicalProfileDTO);
        if (medicalProfileDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MedicalProfileDTO result = medicalProfileService.save(medicalProfileDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, medicalProfileDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /medical-profiles} : get all the medicalProfiles.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of medicalProfiles in body.
     */
    @GetMapping("/medical-profiles")
    public List<MedicalProfileDTO> getAllMedicalProfiles(@RequestParam(required = false) String filter,@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        if ("adjustclient-is-null".equals(filter)) {
            log.debug("REST request to get all MedicalProfiles where adjustClient is null");
            return medicalProfileService.findAllWhereAdjustClientIsNull();
        }
        log.debug("REST request to get all MedicalProfiles");
        return medicalProfileService.findAll();
    }

    /**
     * {@code GET  /medical-profiles/:id} : get the "id" medicalProfile.
     *
     * @param id the id of the medicalProfileDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the medicalProfileDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/medical-profiles/{id}")
    public ResponseEntity<MedicalProfileDTO> getMedicalProfile(@PathVariable Long id) {
        log.debug("REST request to get MedicalProfile : {}", id);
        Optional<MedicalProfileDTO> medicalProfileDTO = medicalProfileService.findOne(id);
        return ResponseUtil.wrapOrNotFound(medicalProfileDTO);
    }

    /**
     * {@code DELETE  /medical-profiles/:id} : delete the "id" medicalProfile.
     *
     * @param id the id of the medicalProfileDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/medical-profiles/{id}")
    public ResponseEntity<Void> deleteMedicalProfile(@PathVariable Long id) {
        log.debug("REST request to delete MedicalProfile : {}", id);
        medicalProfileService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
