package azimut.service.mapper;


import azimut.domain.*;
import azimut.service.dto.InscriptionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Inscription} and its DTO {@link InscriptionDTO}.
 */
@Mapper(componentModel = "spring", uses = {PeriodeMapper.class, EtablissementMapper.class, AnneeScolaireMapper.class})
public interface InscriptionMapper extends EntityMapper<InscriptionDTO, Inscription> {

    @Mapping(source = "periode.id", target = "periodeId")
    @Mapping(source = "etablissement.id", target = "etablissementId")
    @Mapping(source = "anneeScolaire.id", target = "anneeScolaireId")
    InscriptionDTO toDto(Inscription inscription);

    @Mapping(source = "periodeId", target = "periode")
    @Mapping(target = "formations", ignore = true)
    @Mapping(target = "removeFormation", ignore = true)
    @Mapping(source = "etablissementId", target = "etablissement")
    @Mapping(source = "anneeScolaireId", target = "anneeScolaire")
    Inscription toEntity(InscriptionDTO inscriptionDTO);

    default Inscription fromId(Long id) {
        if (id == null) {
            return null;
        }
        Inscription inscription = new Inscription();
        inscription.setId(id);
        return inscription;
    }
}
