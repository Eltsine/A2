package azimut.web.rest;

import azimut.AzimutApp;
import azimut.domain.Apprenant;
import azimut.repository.ApprenantRepository;
import azimut.service.ApprenantService;
import azimut.service.dto.ApprenantDTO;
import azimut.service.mapper.ApprenantMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ApprenantResource} REST controller.
 */
@SpringBootTest(classes = AzimutApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ApprenantResourceIT {

    private static final byte[] DEFAULT_PHOTO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_PHOTO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_PHOTO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_PHOTO_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM = "BBBBBBBBBB";

    private static final String DEFAULT_STATUT = "AAAAAAAAAA";
    private static final String UPDATED_STATUT = "BBBBBBBBBB";

    private static final String DEFAULT_NIVEAU = "AAAAAAAAAA";
    private static final String UPDATED_NIVEAU = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_ADD_PARENT = "AAAAAAAAAA";
    private static final String UPDATED_ADD_PARENT = "BBBBBBBBBB";

    @Autowired
    private ApprenantRepository apprenantRepository;

    @Autowired
    private ApprenantMapper apprenantMapper;

    @Autowired
    private ApprenantService apprenantService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restApprenantMockMvc;

    private Apprenant apprenant;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Apprenant createEntity(EntityManager em) {
        Apprenant apprenant = new Apprenant()
            .photo(DEFAULT_PHOTO)
            .photoContentType(DEFAULT_PHOTO_CONTENT_TYPE)
            .nom(DEFAULT_NOM)
            .prenom(DEFAULT_PRENOM)
            .statut(DEFAULT_STATUT)
            .niveau(DEFAULT_NIVEAU)
            .contact(DEFAULT_CONTACT)
            .email(DEFAULT_EMAIL)
            .addParent(DEFAULT_ADD_PARENT);
        return apprenant;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Apprenant createUpdatedEntity(EntityManager em) {
        Apprenant apprenant = new Apprenant()
            .photo(UPDATED_PHOTO)
            .photoContentType(UPDATED_PHOTO_CONTENT_TYPE)
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .statut(UPDATED_STATUT)
            .niveau(UPDATED_NIVEAU)
            .contact(UPDATED_CONTACT)
            .email(UPDATED_EMAIL)
            .addParent(UPDATED_ADD_PARENT);
        return apprenant;
    }

    @BeforeEach
    public void initTest() {
        apprenant = createEntity(em);
    }

    @Test
    @Transactional
    public void createApprenant() throws Exception {
        int databaseSizeBeforeCreate = apprenantRepository.findAll().size();
        // Create the Apprenant
        ApprenantDTO apprenantDTO = apprenantMapper.toDto(apprenant);
        restApprenantMockMvc.perform(post("/api/apprenants")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(apprenantDTO)))
            .andExpect(status().isCreated());

        // Validate the Apprenant in the database
        List<Apprenant> apprenantList = apprenantRepository.findAll();
        assertThat(apprenantList).hasSize(databaseSizeBeforeCreate + 1);
        Apprenant testApprenant = apprenantList.get(apprenantList.size() - 1);
        assertThat(testApprenant.getPhoto()).isEqualTo(DEFAULT_PHOTO);
        assertThat(testApprenant.getPhotoContentType()).isEqualTo(DEFAULT_PHOTO_CONTENT_TYPE);
        assertThat(testApprenant.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testApprenant.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testApprenant.getStatut()).isEqualTo(DEFAULT_STATUT);
        assertThat(testApprenant.getNiveau()).isEqualTo(DEFAULT_NIVEAU);
        assertThat(testApprenant.getContact()).isEqualTo(DEFAULT_CONTACT);
        assertThat(testApprenant.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testApprenant.getAddParent()).isEqualTo(DEFAULT_ADD_PARENT);
    }

    @Test
    @Transactional
    public void createApprenantWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = apprenantRepository.findAll().size();

        // Create the Apprenant with an existing ID
        apprenant.setId(1L);
        ApprenantDTO apprenantDTO = apprenantMapper.toDto(apprenant);

        // An entity with an existing ID cannot be created, so this API call must fail
        restApprenantMockMvc.perform(post("/api/apprenants")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(apprenantDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Apprenant in the database
        List<Apprenant> apprenantList = apprenantRepository.findAll();
        assertThat(apprenantList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNomIsRequired() throws Exception {
        int databaseSizeBeforeTest = apprenantRepository.findAll().size();
        // set the field null
        apprenant.setNom(null);

        // Create the Apprenant, which fails.
        ApprenantDTO apprenantDTO = apprenantMapper.toDto(apprenant);


        restApprenantMockMvc.perform(post("/api/apprenants")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(apprenantDTO)))
            .andExpect(status().isBadRequest());

        List<Apprenant> apprenantList = apprenantRepository.findAll();
        assertThat(apprenantList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPrenomIsRequired() throws Exception {
        int databaseSizeBeforeTest = apprenantRepository.findAll().size();
        // set the field null
        apprenant.setPrenom(null);

        // Create the Apprenant, which fails.
        ApprenantDTO apprenantDTO = apprenantMapper.toDto(apprenant);


        restApprenantMockMvc.perform(post("/api/apprenants")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(apprenantDTO)))
            .andExpect(status().isBadRequest());

        List<Apprenant> apprenantList = apprenantRepository.findAll();
        assertThat(apprenantList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatutIsRequired() throws Exception {
        int databaseSizeBeforeTest = apprenantRepository.findAll().size();
        // set the field null
        apprenant.setStatut(null);

        // Create the Apprenant, which fails.
        ApprenantDTO apprenantDTO = apprenantMapper.toDto(apprenant);


        restApprenantMockMvc.perform(post("/api/apprenants")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(apprenantDTO)))
            .andExpect(status().isBadRequest());

        List<Apprenant> apprenantList = apprenantRepository.findAll();
        assertThat(apprenantList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContactIsRequired() throws Exception {
        int databaseSizeBeforeTest = apprenantRepository.findAll().size();
        // set the field null
        apprenant.setContact(null);

        // Create the Apprenant, which fails.
        ApprenantDTO apprenantDTO = apprenantMapper.toDto(apprenant);


        restApprenantMockMvc.perform(post("/api/apprenants")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(apprenantDTO)))
            .andExpect(status().isBadRequest());

        List<Apprenant> apprenantList = apprenantRepository.findAll();
        assertThat(apprenantList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllApprenants() throws Exception {
        // Initialize the database
        apprenantRepository.saveAndFlush(apprenant);

        // Get all the apprenantList
        restApprenantMockMvc.perform(get("/api/apprenants?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(apprenant.getId().intValue())))
            .andExpect(jsonPath("$.[*].photoContentType").value(hasItem(DEFAULT_PHOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].photo").value(hasItem(Base64Utils.encodeToString(DEFAULT_PHOTO))))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM)))
            .andExpect(jsonPath("$.[*].statut").value(hasItem(DEFAULT_STATUT)))
            .andExpect(jsonPath("$.[*].niveau").value(hasItem(DEFAULT_NIVEAU)))
            .andExpect(jsonPath("$.[*].contact").value(hasItem(DEFAULT_CONTACT)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].addParent").value(hasItem(DEFAULT_ADD_PARENT)));
    }
    
    @Test
    @Transactional
    public void getApprenant() throws Exception {
        // Initialize the database
        apprenantRepository.saveAndFlush(apprenant);

        // Get the apprenant
        restApprenantMockMvc.perform(get("/api/apprenants/{id}", apprenant.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(apprenant.getId().intValue()))
            .andExpect(jsonPath("$.photoContentType").value(DEFAULT_PHOTO_CONTENT_TYPE))
            .andExpect(jsonPath("$.photo").value(Base64Utils.encodeToString(DEFAULT_PHOTO)))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.prenom").value(DEFAULT_PRENOM))
            .andExpect(jsonPath("$.statut").value(DEFAULT_STATUT))
            .andExpect(jsonPath("$.niveau").value(DEFAULT_NIVEAU))
            .andExpect(jsonPath("$.contact").value(DEFAULT_CONTACT))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.addParent").value(DEFAULT_ADD_PARENT));
    }
    @Test
    @Transactional
    public void getNonExistingApprenant() throws Exception {
        // Get the apprenant
        restApprenantMockMvc.perform(get("/api/apprenants/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateApprenant() throws Exception {
        // Initialize the database
        apprenantRepository.saveAndFlush(apprenant);

        int databaseSizeBeforeUpdate = apprenantRepository.findAll().size();

        // Update the apprenant
        Apprenant updatedApprenant = apprenantRepository.findById(apprenant.getId()).get();
        // Disconnect from session so that the updates on updatedApprenant are not directly saved in db
        em.detach(updatedApprenant);
        updatedApprenant
            .photo(UPDATED_PHOTO)
            .photoContentType(UPDATED_PHOTO_CONTENT_TYPE)
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .statut(UPDATED_STATUT)
            .niveau(UPDATED_NIVEAU)
            .contact(UPDATED_CONTACT)
            .email(UPDATED_EMAIL)
            .addParent(UPDATED_ADD_PARENT);
        ApprenantDTO apprenantDTO = apprenantMapper.toDto(updatedApprenant);

        restApprenantMockMvc.perform(put("/api/apprenants")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(apprenantDTO)))
            .andExpect(status().isOk());

        // Validate the Apprenant in the database
        List<Apprenant> apprenantList = apprenantRepository.findAll();
        assertThat(apprenantList).hasSize(databaseSizeBeforeUpdate);
        Apprenant testApprenant = apprenantList.get(apprenantList.size() - 1);
        assertThat(testApprenant.getPhoto()).isEqualTo(UPDATED_PHOTO);
        assertThat(testApprenant.getPhotoContentType()).isEqualTo(UPDATED_PHOTO_CONTENT_TYPE);
        assertThat(testApprenant.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testApprenant.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testApprenant.getStatut()).isEqualTo(UPDATED_STATUT);
        assertThat(testApprenant.getNiveau()).isEqualTo(UPDATED_NIVEAU);
        assertThat(testApprenant.getContact()).isEqualTo(UPDATED_CONTACT);
        assertThat(testApprenant.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testApprenant.getAddParent()).isEqualTo(UPDATED_ADD_PARENT);
    }

    @Test
    @Transactional
    public void updateNonExistingApprenant() throws Exception {
        int databaseSizeBeforeUpdate = apprenantRepository.findAll().size();

        // Create the Apprenant
        ApprenantDTO apprenantDTO = apprenantMapper.toDto(apprenant);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restApprenantMockMvc.perform(put("/api/apprenants")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(apprenantDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Apprenant in the database
        List<Apprenant> apprenantList = apprenantRepository.findAll();
        assertThat(apprenantList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteApprenant() throws Exception {
        // Initialize the database
        apprenantRepository.saveAndFlush(apprenant);

        int databaseSizeBeforeDelete = apprenantRepository.findAll().size();

        // Delete the apprenant
        restApprenantMockMvc.perform(delete("/api/apprenants/{id}", apprenant.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Apprenant> apprenantList = apprenantRepository.findAll();
        assertThat(apprenantList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
