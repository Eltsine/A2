package azimut.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link azimut.domain.Session} entity.
 */
public class SessionDTO implements Serializable {
    
    private Long id;

    private LocalDate dateDebut;

    private LocalDate dateFin;

    @NotNull
    private Integer nbreParticipant;

    private Integer nbreHeure;

    private Integer heureDebut;

    private Integer heureFin;

    @NotNull
    private String contenuFormation;


    private Long moduleId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public Integer getNbreParticipant() {
        return nbreParticipant;
    }

    public void setNbreParticipant(Integer nbreParticipant) {
        this.nbreParticipant = nbreParticipant;
    }

    public Integer getNbreHeure() {
        return nbreHeure;
    }

    public void setNbreHeure(Integer nbreHeure) {
        this.nbreHeure = nbreHeure;
    }

    public Integer getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(Integer heureDebut) {
        this.heureDebut = heureDebut;
    }

    public Integer getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(Integer heureFin) {
        this.heureFin = heureFin;
    }

    public String getContenuFormation() {
        return contenuFormation;
    }

    public void setContenuFormation(String contenuFormation) {
        this.contenuFormation = contenuFormation;
    }

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SessionDTO)) {
            return false;
        }

        return id != null && id.equals(((SessionDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SessionDTO{" +
            "id=" + getId() +
            ", dateDebut='" + getDateDebut() + "'" +
            ", dateFin='" + getDateFin() + "'" +
            ", nbreParticipant=" + getNbreParticipant() +
            ", nbreHeure=" + getNbreHeure() +
            ", heureDebut=" + getHeureDebut() +
            ", heureFin=" + getHeureFin() +
            ", contenuFormation='" + getContenuFormation() + "'" +
            ", moduleId=" + getModuleId() +
            "}";
    }
}
