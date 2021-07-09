package azimut.service.mapper;


import azimut.domain.*;
import azimut.service.dto.EtablissementDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Etablissement} and its DTO {@link EtablissementDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EtablissementMapper extends EntityMapper<EtablissementDTO, Etablissement> {


    @Mapping(target = "inscriptions", ignore = true)
    @Mapping(target = "removeInscription", ignore = true)
    @Mapping(target = "formateurs", ignore = true)
    @Mapping(target = "removeFormateur", ignore = true)
    Etablissement toEntity(EtablissementDTO etablissementDTO);

    default Etablissement fromId(Long id) {
        if (id == null) {
            return null;
        }
        Etablissement etablissement = new Etablissement();
        etablissement.setId(id);
        return etablissement;
    }
}
