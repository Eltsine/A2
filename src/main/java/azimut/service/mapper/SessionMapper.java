package azimut.service.mapper;


import azimut.domain.*;
import azimut.service.dto.SessionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Session} and its DTO {@link SessionDTO}.
 */
@Mapper(componentModel = "spring", uses = {ModuleMapper.class})
public interface SessionMapper extends EntityMapper<SessionDTO, Session> {

    @Mapping(source = "module.id", target = "moduleId")
    SessionDTO toDto(Session session);

    @Mapping(source = "moduleId", target = "module")
    Session toEntity(SessionDTO sessionDTO);

    default Session fromId(Long id) {
        if (id == null) {
            return null;
        }
        Session session = new Session();
        session.setId(id);
        return session;
    }
}
