package azimut.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link azimut.domain.Specialite} entity.
 */
public class SpecialiteDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String libSpecialite;


    private Long formateurId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibSpecialite() {
        return libSpecialite;
    }

    public void setLibSpecialite(String libSpecialite) {
        this.libSpecialite = libSpecialite;
    }

    public Long getFormateurId() {
        return formateurId;
    }

    public void setFormateurId(Long formateurId) {
        this.formateurId = formateurId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SpecialiteDTO)) {
            return false;
        }

        return id != null && id.equals(((SpecialiteDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SpecialiteDTO{" +
            "id=" + getId() +
            ", libSpecialite='" + getLibSpecialite() + "'" +
            ", formateurId=" + getFormateurId() +
            "}";
    }
}
