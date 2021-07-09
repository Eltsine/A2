package azimut.service.mapper;


import azimut.domain.*;
import azimut.service.dto.ModuleDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Module} and its DTO {@link ModuleDTO}.
 */
@Mapper(componentModel = "spring", uses = {FormationMapper.class})
public interface ModuleMapper extends EntityMapper<ModuleDTO, Module> {

    @Mapping(source = "formation.id", target = "formationId")
    ModuleDTO toDto(Module module);

    @Mapping(target = "sessions", ignore = true)
    @Mapping(target = "removeSession", ignore = true)
    @Mapping(source = "formationId", target = "formation")
    Module toEntity(ModuleDTO moduleDTO);

    default Module fromId(Long id) {
        if (id == null) {
            return null;
        }
        Module module = new Module();
        module.setId(id);
        return module;
    }
}
