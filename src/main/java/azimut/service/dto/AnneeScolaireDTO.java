package azimut.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link azimut.domain.AnneeScolaire} entity.
 */
public class AnneeScolaireDTO implements Serializable {
    
    private Long id;

    private String libelleAnnee;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelleAnnee() {
        return libelleAnnee;
    }

    public void setLibelleAnnee(String libelleAnnee) {
        this.libelleAnnee = libelleAnnee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AnneeScolaireDTO)) {
            return false;
        }

        return id != null && id.equals(((AnneeScolaireDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AnneeScolaireDTO{" +
            "id=" + getId() +
            ", libelleAnnee='" + getLibelleAnnee() + "'" +
            "}";
    }
}
