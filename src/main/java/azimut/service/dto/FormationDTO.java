package azimut.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import azimut.domain.enumeration.NomFormation;

/**
 * A DTO for the {@link azimut.domain.Formation} entity.
 */
public class FormationDTO implements Serializable {
    
    private Long id;

    @NotNull
    private NomFormation nomFormation;


    private Long rapportId;

    private Long inscriptionId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NomFormation getNomFormation() {
        return nomFormation;
    }

    public void setNomFormation(NomFormation nomFormation) {
        this.nomFormation = nomFormation;
    }

    public Long getRapportId() {
        return rapportId;
    }

    public void setRapportId(Long rapportId) {
        this.rapportId = rapportId;
    }

    public Long getInscriptionId() {
        return inscriptionId;
    }

    public void setInscriptionId(Long inscriptionId) {
        this.inscriptionId = inscriptionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FormationDTO)) {
            return false;
        }

        return id != null && id.equals(((FormationDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FormationDTO{" +
            "id=" + getId() +
            ", nomFormation='" + getNomFormation() + "'" +
            ", rapportId=" + getRapportId() +
            ", inscriptionId=" + getInscriptionId() +
            "}";
    }
}
