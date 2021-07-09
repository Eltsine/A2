package azimut.web.rest;

import azimut.AzimutApp;
import azimut.domain.Periode;
import azimut.repository.PeriodeRepository;
import azimut.service.PeriodeService;
import azimut.service.dto.PeriodeDTO;
import azimut.service.mapper.PeriodeMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link PeriodeResource} REST controller.
 */
@SpringBootTest(classes = AzimutApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PeriodeResourceIT {

    private static final String DEFAULT_DUREE = "AAAAAAAAAA";
    private static final String UPDATED_DUREE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_DEBUT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_DEBUT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_FIN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_FIN = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private PeriodeRepository periodeRepository;

    @Autowired
    private PeriodeMapper periodeMapper;

    @Autowired
    private PeriodeService periodeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPeriodeMockMvc;

    private Periode periode;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Periode createEntity(EntityManager em) {
        Periode periode = new Periode()
            .duree(DEFAULT_DUREE)
            .dateDebut(DEFAULT_DATE_DEBUT)
            .dateFin(DEFAULT_DATE_FIN);
        return periode;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Periode createUpdatedEntity(EntityManager em) {
        Periode periode = new Periode()
            .duree(UPDATED_DUREE)
            .dateDebut(UPDATED_DATE_DEBUT)
            .dateFin(UPDATED_DATE_FIN);
        return periode;
    }

    @BeforeEach
    public void initTest() {
        periode = createEntity(em);
    }

    @Test
    @Transactional
    public void createPeriode() throws Exception {
        int databaseSizeBeforeCreate = periodeRepository.findAll().size();
        // Create the Periode
        PeriodeDTO periodeDTO = periodeMapper.toDto(periode);
        restPeriodeMockMvc.perform(post("/api/periodes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(periodeDTO)))
            .andExpect(status().isCreated());

        // Validate the Periode in the database
        List<Periode> periodeList = periodeRepository.findAll();
        assertThat(periodeList).hasSize(databaseSizeBeforeCreate + 1);
        Periode testPeriode = periodeList.get(periodeList.size() - 1);
        assertThat(testPeriode.getDuree()).isEqualTo(DEFAULT_DUREE);
        assertThat(testPeriode.getDateDebut()).isEqualTo(DEFAULT_DATE_DEBUT);
        assertThat(testPeriode.getDateFin()).isEqualTo(DEFAULT_DATE_FIN);
    }

    @Test
    @Transactional
    public void createPeriodeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = periodeRepository.findAll().size();

        // Create the Periode with an existing ID
        periode.setId(1L);
        PeriodeDTO periodeDTO = periodeMapper.toDto(periode);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPeriodeMockMvc.perform(post("/api/periodes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(periodeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Periode in the database
        List<Periode> periodeList = periodeRepository.findAll();
        assertThat(periodeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDateDebutIsRequired() throws Exception {
        int databaseSizeBeforeTest = periodeRepository.findAll().size();
        // set the field null
        periode.setDateDebut(null);

        // Create the Periode, which fails.
        PeriodeDTO periodeDTO = periodeMapper.toDto(periode);


        restPeriodeMockMvc.perform(post("/api/periodes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(periodeDTO)))
            .andExpect(status().isBadRequest());

        List<Periode> periodeList = periodeRepository.findAll();
        assertThat(periodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateFinIsRequired() throws Exception {
        int databaseSizeBeforeTest = periodeRepository.findAll().size();
        // set the field null
        periode.setDateFin(null);

        // Create the Periode, which fails.
        PeriodeDTO periodeDTO = periodeMapper.toDto(periode);


        restPeriodeMockMvc.perform(post("/api/periodes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(periodeDTO)))
            .andExpect(status().isBadRequest());

        List<Periode> periodeList = periodeRepository.findAll();
        assertThat(periodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPeriodes() throws Exception {
        // Initialize the database
        periodeRepository.saveAndFlush(periode);

        // Get all the periodeList
        restPeriodeMockMvc.perform(get("/api/periodes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(periode.getId().intValue())))
            .andExpect(jsonPath("$.[*].duree").value(hasItem(DEFAULT_DUREE)))
            .andExpect(jsonPath("$.[*].dateDebut").value(hasItem(DEFAULT_DATE_DEBUT.toString())))
            .andExpect(jsonPath("$.[*].dateFin").value(hasItem(DEFAULT_DATE_FIN.toString())));
    }
    
    @Test
    @Transactional
    public void getPeriode() throws Exception {
        // Initialize the database
        periodeRepository.saveAndFlush(periode);

        // Get the periode
        restPeriodeMockMvc.perform(get("/api/periodes/{id}", periode.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(periode.getId().intValue()))
            .andExpect(jsonPath("$.duree").value(DEFAULT_DUREE))
            .andExpect(jsonPath("$.dateDebut").value(DEFAULT_DATE_DEBUT.toString()))
            .andExpect(jsonPath("$.dateFin").value(DEFAULT_DATE_FIN.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingPeriode() throws Exception {
        // Get the periode
        restPeriodeMockMvc.perform(get("/api/periodes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePeriode() throws Exception {
        // Initialize the database
        periodeRepository.saveAndFlush(periode);

        int databaseSizeBeforeUpdate = periodeRepository.findAll().size();

        // Update the periode
        Periode updatedPeriode = periodeRepository.findById(periode.getId()).get();
        // Disconnect from session so that the updates on updatedPeriode are not directly saved in db
        em.detach(updatedPeriode);
        updatedPeriode
            .duree(UPDATED_DUREE)
            .dateDebut(UPDATED_DATE_DEBUT)
            .dateFin(UPDATED_DATE_FIN);
        PeriodeDTO periodeDTO = periodeMapper.toDto(updatedPeriode);

        restPeriodeMockMvc.perform(put("/api/periodes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(periodeDTO)))
            .andExpect(status().isOk());

        // Validate the Periode in the database
        List<Periode> periodeList = periodeRepository.findAll();
        assertThat(periodeList).hasSize(databaseSizeBeforeUpdate);
        Periode testPeriode = periodeList.get(periodeList.size() - 1);
        assertThat(testPeriode.getDuree()).isEqualTo(UPDATED_DUREE);
        assertThat(testPeriode.getDateDebut()).isEqualTo(UPDATED_DATE_DEBUT);
        assertThat(testPeriode.getDateFin()).isEqualTo(UPDATED_DATE_FIN);
    }

    @Test
    @Transactional
    public void updateNonExistingPeriode() throws Exception {
        int databaseSizeBeforeUpdate = periodeRepository.findAll().size();

        // Create the Periode
        PeriodeDTO periodeDTO = periodeMapper.toDto(periode);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPeriodeMockMvc.perform(put("/api/periodes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(periodeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Periode in the database
        List<Periode> periodeList = periodeRepository.findAll();
        assertThat(periodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePeriode() throws Exception {
        // Initialize the database
        periodeRepository.saveAndFlush(periode);

        int databaseSizeBeforeDelete = periodeRepository.findAll().size();

        // Delete the periode
        restPeriodeMockMvc.perform(delete("/api/periodes/{id}", periode.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Periode> periodeList = periodeRepository.findAll();
        assertThat(periodeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
