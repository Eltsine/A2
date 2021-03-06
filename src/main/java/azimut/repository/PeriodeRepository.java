package azimut.repository;

import azimut.domain.Periode;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Periode entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PeriodeRepository extends JpaRepository<Periode, Long> {
}
