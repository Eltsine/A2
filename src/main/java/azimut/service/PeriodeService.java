package azimut.service;

import azimut.domain.Periode;
import azimut.repository.PeriodeRepository;
import azimut.service.dto.PeriodeDTO;
import azimut.service.mapper.PeriodeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Periode}.
 */
@Service
@Transactional
public class PeriodeService {

    private final Logger log = LoggerFactory.getLogger(PeriodeService.class);

    private final PeriodeRepository periodeRepository;

    private final PeriodeMapper periodeMapper;

    public PeriodeService(PeriodeRepository periodeRepository, PeriodeMapper periodeMapper) {
        this.periodeRepository = periodeRepository;
        this.periodeMapper = periodeMapper;
    }

    /**
     * Save a periode.
     *
     * @param periodeDTO the entity to save.
     * @return the persisted entity.
     */
    public PeriodeDTO save(PeriodeDTO periodeDTO) {
        log.debug("Request to save Periode : {}", periodeDTO);
        Periode periode = periodeMapper.toEntity(periodeDTO);
        periode = periodeRepository.save(periode);
        return periodeMapper.toDto(periode);
    }

    /**
     * Get all the periodes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<PeriodeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Periodes");
        return periodeRepository.findAll(pageable)
            .map(periodeMapper::toDto);
    }


    /**
     * Get one periode by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PeriodeDTO> findOne(Long id) {
        log.debug("Request to get Periode : {}", id);
        return periodeRepository.findById(id)
            .map(periodeMapper::toDto);
    }

    /**
     * Delete the periode by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Periode : {}", id);
        periodeRepository.deleteById(id);
    }
}
