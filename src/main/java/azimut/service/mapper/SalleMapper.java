package azimut.service.mapper;


import azimut.domain.*;
import azimut.service.dto.SalleDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Salle} and its DTO {@link SalleDTO}.
 */
@Mapper(componentModel = "spring", uses = {SessionMapper.class})
public interface SalleMapper extends EntityMapper<SalleDTO, Salle> {

    @Mapping(source = "session.id", target = "sessionId")
    SalleDTO toDto(Salle salle);

    @Mapping(source = "sessionId", target = "session")
    Salle toEntity(SalleDTO salleDTO);

    default Salle fromId(Long id) {
        if (id == null) {
            return null;
        }
        Salle salle = new Salle();
        salle.setId(id);
        return salle;
    }
}
