package azimut.service.mapper;


import azimut.domain.*;
import azimut.service.dto.SpecialiteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Specialite} and its DTO {@link SpecialiteDTO}.
 */
@Mapper(componentModel = "spring", uses = {FormateurMapper.class})
public interface SpecialiteMapper extends EntityMapper<SpecialiteDTO, Specialite> {

    @Mapping(source = "formateur.id", target = "formateurId")
    SpecialiteDTO toDto(Specialite specialite);

    @Mapping(source = "formateurId", target = "formateur")
    Specialite toEntity(SpecialiteDTO specialiteDTO);

    default Specialite fromId(Long id) {
        if (id == null) {
            return null;
        }
        Specialite specialite = new Specialite();
        specialite.setId(id);
        return specialite;
    }
}
