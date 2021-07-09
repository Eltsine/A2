package azimut.service;

import azimut.domain.AnneeScolaire;
import azimut.repository.AnneeScolaireRepository;
import azimut.service.dto.AnneeScolaireDTO;
import azimut.service.mapper.AnneeScolaireMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link AnneeScolaire}.
 */
@Service
@Transactional
public class AnneeScolaireService {

    private final Logger log = LoggerFactory.getLogger(AnneeScolaireService.class);

    private final AnneeScolaireRepository anneeScolaireRepository;

    private final AnneeScolaireMapper anneeScolaireMapper;

    public AnneeScolaireService(AnneeScolaireRepository anneeScolaireRepository, AnneeScolaireMapper anneeScolaireMapper) {
        this.anneeScolaireRepository = anneeScolaireRepository;
        this.anneeScolaireMapper = anneeScolaireMapper;
    }

    /**
     * Save a anneeScolaire.
     *
     * @param anneeScolaireDTO the entity to save.
     * @return the persisted entity.
     */
    public AnneeScolaireDTO save(AnneeScolaireDTO anneeScolaireDTO) {
        log.debug("Request to save AnneeScolaire : {}", anneeScolaireDTO);
        AnneeScolaire anneeScolaire = anneeScolaireMapper.toEntity(anneeScolaireDTO);
        anneeScolaire = anneeScolaireRepository.save(anneeScolaire);
        return anneeScolaireMapper.toDto(anneeScolaire);
    }

    /**
     * Get all the anneeScolaires.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<AnneeScolaireDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AnneeScolaires");
        return anneeScolaireRepository.findAll(pageable)
            .map(anneeScolaireMapper::toDto);
    }


    /**
     * Get one anneeScolaire by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AnneeScolaireDTO> findOne(Long id) {
        log.debug("Request to get AnneeScolaire : {}", id);
        return anneeScolaireRepository.findById(id)
            .map(anneeScolaireMapper::toDto);
    }

    /**
     * Delete the anneeScolaire by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AnneeScolaire : {}", id);
        anneeScolaireRepository.deleteById(id);
    }
}
