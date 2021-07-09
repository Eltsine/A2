package azimut.service.mapper;


import azimut.domain.*;
import azimut.service.dto.FormateurDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Formateur} and its DTO {@link FormateurDTO}.
 */
@Mapper(componentModel = "spring", uses = {EtablissementMapper.class})
public interface FormateurMapper extends EntityMapper<FormateurDTO, Formateur> {

    @Mapping(source = "etablissement.id", target = "etablissementId")
    FormateurDTO toDto(Formateur formateur);

    @Mapping(target = "specialites", ignore = true)
    @Mapping(target = "removeSpecialite", ignore = true)
    @Mapping(target = "personnes", ignore = true)
    @Mapping(target = "removePersonne", ignore = true)
    @Mapping(source = "etablissementId", target = "etablissement")
    Formateur toEntity(FormateurDTO formateurDTO);

    default Formateur fromId(Long id) {
        if (id == null) {
            return null;
        }
        Formateur formateur = new Formateur();
        formateur.setId(id);
        return formateur;
    }
}
