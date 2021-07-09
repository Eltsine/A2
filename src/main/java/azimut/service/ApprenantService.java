package azimut.service;

import azimut.domain.Apprenant;
import azimut.repository.ApprenantRepository;
import azimut.service.dto.ApprenantDTO;
import azimut.service.mapper.ApprenantMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Apprenant}.
 */
@Service
@Transactional
public class ApprenantService {

    private final Logger log = LoggerFactory.getLogger(ApprenantService.class);

    private final ApprenantRepository apprenantRepository;

    private final ApprenantMapper apprenantMapper;

    public ApprenantService(ApprenantRepository apprenantRepository, ApprenantMapper apprenantMapper) {
        this.apprenantRepository = apprenantRepository;
        this.apprenantMapper = apprenantMapper;
    }

    /**
     * Save a apprenant.
     *
     * @param apprenantDTO the entity to save.
     * @return the persisted entity.
     */
    public ApprenantDTO save(ApprenantDTO apprenantDTO) {
        log.debug("Request to save Apprenant : {}", apprenantDTO);
        Apprenant apprenant = apprenantMapper.toEntity(apprenantDTO);
        apprenant = apprenantRepository.save(apprenant);
        return apprenantMapper.toDto(apprenant);
    }

    /**
     * Get all the apprenants.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ApprenantDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Apprenants");
        return apprenantRepository.findAll(pageable)
            .map(apprenantMapper::toDto);
    }


    /**
     * Get one apprenant by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ApprenantDTO> findOne(Long id) {
        log.debug("Request to get Apprenant : {}", id);
        return apprenantRepository.findById(id)
            .map(apprenantMapper::toDto);
    }

    /**
     * Delete the apprenant by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Apprenant : {}", id);
        apprenantRepository.deleteById(id);
    }
}
