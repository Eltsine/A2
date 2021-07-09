package azimut.web.rest;

import azimut.AzimutApp;
import azimut.domain.Formateur;
import azimut.repository.FormateurRepository;
import azimut.service.FormateurService;
import azimut.service.dto.FormateurDTO;
import azimut.service.mapper.FormateurMapper;

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
 * Integration tests for the {@link FormateurResource} REST controller.
 */
@SpringBootTest(classes = AzimutApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FormateurResourceIT {

    private static final byte[] DEFAULT_PHOTO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_PHOTO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_PHOTO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_PHOTO_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_CNIB = "AAAAAAAAAA";
    private static final String UPDATED_CNIB = "BBBBBBBBBB";

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM = "BBBBBBBBBB";

    private static final String DEFAULT_STATUT = "AAAAAAAAAA";
    private static final String UPDATED_STATUT = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final Long DEFAULT_SALAIRE_HORAIRE = 1L;
    private static final Long UPDATED_SALAIRE_HORAIRE = 2L;

    private static final Long DEFAULT_SALAIRE_MENSUEL = 1L;
    private static final Long UPDATED_SALAIRE_MENSUEL = 2L;

    @Autowired
    private FormateurRepository formateurRepository;

    @Autowired
    private FormateurMapper formateurMapper;

    @Autowired
    private FormateurService formateurService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFormateurMockMvc;

    private Formateur formateur;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Formateur createEntity(EntityManager em) {
        Formateur formateur = new Formateur()
            .photo(DEFAULT_PHOTO)
            .photoContentType(DEFAULT_PHOTO_CONTENT_TYPE)
            .cnib(DEFAULT_CNIB)
            .nom(DEFAULT_NOM)
            .prenom(DEFAULT_PRENOM)
            .statut(DEFAULT_STATUT)
            .contact(DEFAULT_CONTACT)
            .email(DEFAULT_EMAIL)
            .salaireHoraire(DEFAULT_SALAIRE_HORAIRE)
            .salaireMensuel(DEFAULT_SALAIRE_MENSUEL);
        return formateur;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Formateur createUpdatedEntity(EntityManager em) {
        Formateur formateur = new Formateur()
            .photo(UPDATED_PHOTO)
            .photoContentType(UPDATED_PHOTO_CONTENT_TYPE)
            .cnib(UPDATED_CNIB)
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .statut(UPDATED_STATUT)
            .contact(UPDATED_CONTACT)
            .email(UPDATED_EMAIL)
            .salaireHoraire(UPDATED_SALAIRE_HORAIRE)
            .salaireMensuel(UPDATED_SALAIRE_MENSUEL);
        return formateur;
    }

    @BeforeEach
    public void initTest() {
        formateur = createEntity(em);
    }

    @Test
    @Transactional
    public void createFormateur() throws Exception {
        int databaseSizeBeforeCreate = formateurRepository.findAll().size();
        // Create the Formateur
        FormateurDTO formateurDTO = formateurMapper.toDto(formateur);
        restFormateurMockMvc.perform(post("/api/formateurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(formateurDTO)))
            .andExpect(status().isCreated());

        // Validate the Formateur in the database
        List<Formateur> formateurList = formateurRepository.findAll();
        assertThat(formateurList).hasSize(databaseSizeBeforeCreate + 1);
        Formateur testFormateur = formateurList.get(formateurList.size() - 1);
        assertThat(testFormateur.getPhoto()).isEqualTo(DEFAULT_PHOTO);
        assertThat(testFormateur.getPhotoContentType()).isEqualTo(DEFAULT_PHOTO_CONTENT_TYPE);
        assertThat(testFormateur.getCnib()).isEqualTo(DEFAULT_CNIB);
        assertThat(testFormateur.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testFormateur.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testFormateur.getStatut()).isEqualTo(DEFAULT_STATUT);
        assertThat(testFormateur.getContact()).isEqualTo(DEFAULT_CONTACT);
        assertThat(testFormateur.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testFormateur.getSalaireHoraire()).isEqualTo(DEFAULT_SALAIRE_HORAIRE);
        assertThat(testFormateur.getSalaireMensuel()).isEqualTo(DEFAULT_SALAIRE_MENSUEL);
    }

    @Test
    @Transactional
    public void createFormateurWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = formateurRepository.findAll().size();

        // Create the Formateur with an existing ID
        formateur.setId(1L);
        FormateurDTO formateurDTO = formateurMapper.toDto(formateur);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFormateurMockMvc.perform(post("/api/formateurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(formateurDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Formateur in the database
        List<Formateur> formateurList = formateurRepository.findAll();
        assertThat(formateurList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCnibIsRequired() throws Exception {
        int databaseSizeBeforeTest = formateurRepository.findAll().size();
        // set the field null
        formateur.setCnib(null);

        // Create the Formateur, which fails.
        FormateurDTO formateurDTO = formateurMapper.toDto(formateur);


        restFormateurMockMvc.perform(post("/api/formateurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(formateurDTO)))
            .andExpect(status().isBadRequest());

        List<Formateur> formateurList = formateurRepository.findAll();
        assertThat(formateurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNomIsRequired() throws Exception {
        int databaseSizeBeforeTest = formateurRepository.findAll().size();
        // set the field null
        formateur.setNom(null);

        // Create the Formateur, which fails.
        FormateurDTO formateurDTO = formateurMapper.toDto(formateur);


        restFormateurMockMvc.perform(post("/api/formateurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(formateurDTO)))
            .andExpect(status().isBadRequest());

        List<Formateur> formateurList = formateurRepository.findAll();
        assertThat(formateurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPrenomIsRequired() throws Exception {
        int databaseSizeBeforeTest = formateurRepository.findAll().size();
        // set the field null
        formateur.setPrenom(null);

        // Create the Formateur, which fails.
        FormateurDTO formateurDTO = formateurMapper.toDto(formateur);


        restFormateurMockMvc.perform(post("/api/formateurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(formateurDTO)))
            .andExpect(status().isBadRequest());

        List<Formateur> formateurList = formateurRepository.findAll();
        assertThat(formateurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContactIsRequired() throws Exception {
        int databaseSizeBeforeTest = formateurRepository.findAll().size();
        // set the field null
        formateur.setContact(null);

        // Create the Formateur, which fails.
        FormateurDTO formateurDTO = formateurMapper.toDto(formateur);


        restFormateurMockMvc.perform(post("/api/formateurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(formateurDTO)))
            .andExpect(status().isBadRequest());

        List<Formateur> formateurList = formateurRepository.findAll();
        assertThat(formateurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFormateurs() throws Exception {
        // Initialize the database
        formateurRepository.saveAndFlush(formateur);

        // Get all the formateurList
        restFormateurMockMvc.perform(get("/api/formateurs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(formateur.getId().intValue())))
            .andExpect(jsonPath("$.[*].photoContentType").value(hasItem(DEFAULT_PHOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].photo").value(hasItem(Base64Utils.encodeToString(DEFAULT_PHOTO))))
            .andExpect(jsonPath("$.[*].cnib").value(hasItem(DEFAULT_CNIB)))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM)))
            .andExpect(jsonPath("$.[*].statut").value(hasItem(DEFAULT_STATUT)))
            .andExpect(jsonPath("$.[*].contact").value(hasItem(DEFAULT_CONTACT)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].salaireHoraire").value(hasItem(DEFAULT_SALAIRE_HORAIRE.intValue())))
            .andExpect(jsonPath("$.[*].salaireMensuel").value(hasItem(DEFAULT_SALAIRE_MENSUEL.intValue())));
    }
    
    @Test
    @Transactional
    public void getFormateur() throws Exception {
        // Initialize the database
        formateurRepository.saveAndFlush(formateur);

        // Get the formateur
        restFormateurMockMvc.perform(get("/api/formateurs/{id}", formateur.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(formateur.getId().intValue()))
            .andExpect(jsonPath("$.photoContentType").value(DEFAULT_PHOTO_CONTENT_TYPE))
            .andExpect(jsonPath("$.photo").value(Base64Utils.encodeToString(DEFAULT_PHOTO)))
            .andExpect(jsonPath("$.cnib").value(DEFAULT_CNIB))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.prenom").value(DEFAULT_PRENOM))
            .andExpect(jsonPath("$.statut").value(DEFAULT_STATUT))
            .andExpect(jsonPath("$.contact").value(DEFAULT_CONTACT))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.salaireHoraire").value(DEFAULT_SALAIRE_HORAIRE.intValue()))
            .andExpect(jsonPath("$.salaireMensuel").value(DEFAULT_SALAIRE_MENSUEL.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingFormateur() throws Exception {
        // Get the formateur
        restFormateurMockMvc.perform(get("/api/formateurs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFormateur() throws Exception {
        // Initialize the database
        formateurRepository.saveAndFlush(formateur);

        int databaseSizeBeforeUpdate = formateurRepository.findAll().size();

        // Update the formateur
        Formateur updatedFormateur = formateurRepository.findById(formateur.getId()).get();
        // Disconnect from session so that the updates on updatedFormateur are not directly saved in db
        em.detach(updatedFormateur);
        updatedFormateur
            .photo(UPDATED_PHOTO)
            .photoContentType(UPDATED_PHOTO_CONTENT_TYPE)
            .cnib(UPDATED_CNIB)
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .statut(UPDATED_STATUT)
            .contact(UPDATED_CONTACT)
            .email(UPDATED_EMAIL)
            .salaireHoraire(UPDATED_SALAIRE_HORAIRE)
            .salaireMensuel(UPDATED_SALAIRE_MENSUEL);
        FormateurDTO formateurDTO = formateurMapper.toDto(updatedFormateur);

        restFormateurMockMvc.perform(put("/api/formateurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(formateurDTO)))
            .andExpect(status().isOk());

        // Validate the Formateur in the database
        List<Formateur> formateurList = formateurRepository.findAll();
        assertThat(formateurList).hasSize(databaseSizeBeforeUpdate);
        Formateur testFormateur = formateurList.get(formateurList.size() - 1);
        assertThat(testFormateur.getPhoto()).isEqualTo(UPDATED_PHOTO);
        assertThat(testFormateur.getPhotoContentType()).isEqualTo(UPDATED_PHOTO_CONTENT_TYPE);
        assertThat(testFormateur.getCnib()).isEqualTo(UPDATED_CNIB);
        assertThat(testFormateur.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testFormateur.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testFormateur.getStatut()).isEqualTo(UPDATED_STATUT);
        assertThat(testFormateur.getContact()).isEqualTo(UPDATED_CONTACT);
        assertThat(testFormateur.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testFormateur.getSalaireHoraire()).isEqualTo(UPDATED_SALAIRE_HORAIRE);
        assertThat(testFormateur.getSalaireMensuel()).isEqualTo(UPDATED_SALAIRE_MENSUEL);
    }

    @Test
    @Transactional
    public void updateNonExistingFormateur() throws Exception {
        int databaseSizeBeforeUpdate = formateurRepository.findAll().size();

        // Create the Formateur
        FormateurDTO formateurDTO = formateurMapper.toDto(formateur);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFormateurMockMvc.perform(put("/api/formateurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(formateurDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Formateur in the database
        List<Formateur> formateurList = formateurRepository.findAll();
        assertThat(formateurList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFormateur() throws Exception {
        // Initialize the database
        formateurRepository.saveAndFlush(formateur);

        int databaseSizeBeforeDelete = formateurRepository.findAll().size();

        // Delete the formateur
        restFormateurMockMvc.perform(delete("/api/formateurs/{id}", formateur.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Formateur> formateurList = formateurRepository.findAll();
        assertThat(formateurList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
