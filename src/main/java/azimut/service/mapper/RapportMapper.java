package azimut.service.mapper;


import azimut.domain.*;
import azimut.service.dto.RapportDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Rapport} and its DTO {@link RapportDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RapportMapper extends EntityMapper<RapportDTO, Rapport> {


    @Mapping(target = "formation", ignore = true)
    Rapport toEntity(RapportDTO rapportDTO);

    default Rapport fromId(Long id) {
        if (id == null) {
            return null;
        }
        Rapport rapport = new Rapport();
        rapport.setId(id);
        return rapport;
    }
}
