package azimut.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import azimut.domain.enumeration.NomModule;

/**
 * A DTO for the {@link azimut.domain.Module} entity.
 */
public class ModuleDTO implements Serializable {
    
    private Long id;

    @NotNull
    private NomModule nomModule;

    private BigDecimal prix;


    private Long formationId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NomModule getNomModule() {
        return nomModule;
    }

    public void setNomModule(NomModule nomModule) {
        this.nomModule = nomModule;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public Long getFormationId() {
        return formationId;
    }

    public void setFormationId(Long formationId) {
        this.formationId = formationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ModuleDTO)) {
            return false;
        }

        return id != null && id.equals(((ModuleDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ModuleDTO{" +
            "id=" + getId() +
            ", nomModule='" + getNomModule() + "'" +
            ", prix=" + getPrix() +
            ", formationId=" + getFormationId() +
            "}";
    }
}
