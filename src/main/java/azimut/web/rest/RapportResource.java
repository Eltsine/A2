package azimut.web.rest;

import azimut.service.RapportService;
import azimut.web.rest.errors.BadRequestAlertException;
import azimut.service.dto.RapportDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

/**
 * REST controller for managing {@link azimut.domain.Rapport}.
 */
@RestController
@RequestMapping("/api")
public class RapportResource {

    private final Logger log = LoggerFactory.getLogger(RapportResource.class);

    private static final String ENTITY_NAME = "rapport";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RapportService rapportService;

    public RapportResource(RapportService rapportService) {
        this.rapportService = rapportService;
    }

    /**
     * {@code POST  /rapports} : Create a new rapport.
     *
     * @param rapportDTO the rapportDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new rapportDTO, or with status {@code 400 (Bad Request)} if the rapport has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/rapports")
    public ResponseEntity<RapportDTO> createRapport(@RequestBody RapportDTO rapportDTO) throws URISyntaxException {
        log.debug("REST request to save Rapport : {}", rapportDTO);
        if (rapportDTO.getId() != null) {
            throw new BadRequestAlertException("A new rapport cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RapportDTO result = rapportService.save(rapportDTO);
        return ResponseEntity.created(new URI("/api/rapports/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /rapports} : Updates an existing rapport.
     *
     * @param rapportDTO the rapportDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rapportDTO,
     * or with status {@code 400 (Bad Request)} if the rapportDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the rapportDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/rapports")
    public ResponseEntity<RapportDTO> updateRapport(@RequestBody RapportDTO rapportDTO) throws URISyntaxException {
        log.debug("REST request to update Rapport : {}", rapportDTO);
        if (rapportDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RapportDTO result = rapportService.save(rapportDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rapportDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /rapports} : get all the rapports.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of rapports in body.
     */
    @GetMapping("/rapports")
    public ResponseEntity<List<RapportDTO>> getAllRapports(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("formation-is-null".equals(filter)) {
            log.debug("REST request to get all Rapports where formation is null");
            return new ResponseEntity<>(rapportService.findAllWhereFormationIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of Rapports");
        Page<RapportDTO> page = rapportService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /rapports/:id} : get the "id" rapport.
     *
     * @param id the id of the rapportDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the rapportDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/rapports/{id}")
    public ResponseEntity<RapportDTO> getRapport(@PathVariable Long id) {
        log.debug("REST request to get Rapport : {}", id);
        Optional<RapportDTO> rapportDTO = rapportService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rapportDTO);
    }

    /**
     * {@code DELETE  /rapports/:id} : delete the "id" rapport.
     *
     * @param id the id of the rapportDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/rapports/{id}")
    public ResponseEntity<Void> deleteRapport(@PathVariable Long id) {
        log.debug("REST request to delete Rapport : {}", id);
        rapportService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
