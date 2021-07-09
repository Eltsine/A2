package azimut.web.rest;

import azimut.service.SpecialiteService;
import azimut.web.rest.errors.BadRequestAlertException;
import azimut.service.dto.SpecialiteDTO;

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

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link azimut.domain.Specialite}.
 */
@RestController
@RequestMapping("/api")
public class SpecialiteResource {

    private final Logger log = LoggerFactory.getLogger(SpecialiteResource.class);

    private static final String ENTITY_NAME = "specialite";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SpecialiteService specialiteService;

    public SpecialiteResource(SpecialiteService specialiteService) {
        this.specialiteService = specialiteService;
    }

    /**
     * {@code POST  /specialites} : Create a new specialite.
     *
     * @param specialiteDTO the specialiteDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new specialiteDTO, or with status {@code 400 (Bad Request)} if the specialite has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/specialites")
    public ResponseEntity<SpecialiteDTO> createSpecialite(@Valid @RequestBody SpecialiteDTO specialiteDTO) throws URISyntaxException {
        log.debug("REST request to save Specialite : {}", specialiteDTO);
        if (specialiteDTO.getId() != null) {
            throw new BadRequestAlertException("A new specialite cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SpecialiteDTO result = specialiteService.save(specialiteDTO);
        return ResponseEntity.created(new URI("/api/specialites/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /specialites} : Updates an existing specialite.
     *
     * @param specialiteDTO the specialiteDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated specialiteDTO,
     * or with status {@code 400 (Bad Request)} if the specialiteDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the specialiteDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/specialites")
    public ResponseEntity<SpecialiteDTO> updateSpecialite(@Valid @RequestBody SpecialiteDTO specialiteDTO) throws URISyntaxException {
        log.debug("REST request to update Specialite : {}", specialiteDTO);
        if (specialiteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SpecialiteDTO result = specialiteService.save(specialiteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, specialiteDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /specialites} : get all the specialites.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of specialites in body.
     */
    @GetMapping("/specialites")
    public ResponseEntity<List<SpecialiteDTO>> getAllSpecialites(Pageable pageable) {
        log.debug("REST request to get a page of Specialites");
        Page<SpecialiteDTO> page = specialiteService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /specialites/:id} : get the "id" specialite.
     *
     * @param id the id of the specialiteDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the specialiteDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/specialites/{id}")
    public ResponseEntity<SpecialiteDTO> getSpecialite(@PathVariable Long id) {
        log.debug("REST request to get Specialite : {}", id);
        Optional<SpecialiteDTO> specialiteDTO = specialiteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(specialiteDTO);
    }

    /**
     * {@code DELETE  /specialites/:id} : delete the "id" specialite.
     *
     * @param id the id of the specialiteDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/specialites/{id}")
    public ResponseEntity<Void> deleteSpecialite(@PathVariable Long id) {
        log.debug("REST request to delete Specialite : {}", id);
        specialiteService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
