package azimut.service.mapper;


import azimut.domain.*;
import azimut.service.dto.PeriodeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Periode} and its DTO {@link PeriodeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PeriodeMapper extends EntityMapper<PeriodeDTO, Periode> {



    default Periode fromId(Long id) {
        if (id == null) {
            return null;
        }
        Periode periode = new Periode();
        periode.setId(id);
        return periode;
    }
}
