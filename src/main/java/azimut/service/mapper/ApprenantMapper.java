package azimut.service.mapper;


import azimut.domain.*;
import azimut.service.dto.ApprenantDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Apprenant} and its DTO {@link ApprenantDTO}.
 */
@Mapper(componentModel = "spring", uses = {InscriptionMapper.class, FormateurMapper.class})
public interface ApprenantMapper extends EntityMapper<ApprenantDTO, Apprenant> {

    @Mapping(source = "inscription.id", target = "inscriptionId")
    @Mapping(source = "formateur.id", target = "formateurId")
    ApprenantDTO toDto(Apprenant apprenant);

    @Mapping(source = "inscriptionId", target = "inscription")
    @Mapping(source = "formateurId", target = "formateur")
    Apprenant toEntity(ApprenantDTO apprenantDTO);

    default Apprenant fromId(Long id) {
        if (id == null) {
            return null;
        }
        Apprenant apprenant = new Apprenant();
        apprenant.setId(id);
        return apprenant;
    }
}
