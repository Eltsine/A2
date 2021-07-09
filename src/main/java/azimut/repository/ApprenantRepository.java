package azimut.repository;

import azimut.domain.Apprenant;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Apprenant entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ApprenantRepository extends JpaRepository<Apprenant, Long> {
}
