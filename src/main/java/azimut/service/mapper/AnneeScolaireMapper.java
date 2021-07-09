package azimut.service.mapper;


import azimut.domain.*;
import azimut.service.dto.AnneeScolaireDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AnneeScolaire} and its DTO {@link AnneeScolaireDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AnneeScolaireMapper extends EntityMapper<AnneeScolaireDTO, AnneeScolaire> {


    @Mapping(target = "inscriptions", ignore = true)
    @Mapping(target = "removeInscription", ignore = true)
    AnneeScolaire toEntity(AnneeScolaireDTO anneeScolaireDTO);

    default AnneeScolaire fromId(Long id) {
        if (id == null) {
            return null;
        }
        AnneeScolaire anneeScolaire = new AnneeScolaire();
        anneeScolaire.setId(id);
        return anneeScolaire;
    }
}
