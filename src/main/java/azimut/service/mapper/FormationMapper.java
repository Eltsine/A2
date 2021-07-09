package azimut.service.mapper;


import azimut.domain.*;
import azimut.service.dto.FormationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Formation} and its DTO {@link FormationDTO}.
 */
@Mapper(componentModel = "spring", uses = {RapportMapper.class, InscriptionMapper.class})
public interface FormationMapper extends EntityMapper<FormationDTO, Formation> {

    @Mapping(source = "rapport.id", target = "rapportId")
    @Mapping(source = "inscription.id", target = "inscriptionId")
    FormationDTO toDto(Formation formation);

    @Mapping(source = "rapportId", target = "rapport")
    @Mapping(target = "modules", ignore = true)
    @Mapping(target = "removeModule", ignore = true)
    @Mapping(source = "inscriptionId", target = "inscription")
    Formation toEntity(FormationDTO formationDTO);

    default Formation fromId(Long id) {
        if (id == null) {
            return null;
        }
        Formation formation = new Formation();
        formation.setId(id);
        return formation;
    }
}
