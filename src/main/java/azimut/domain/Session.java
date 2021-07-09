package azimut.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Session.
 */
@Entity
@Table(name = "session")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Session implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "date_debut")
    private LocalDate dateDebut;

    @Column(name = "date_fin")
    private LocalDate dateFin;

    @NotNull
    @Column(name = "nbre_participant", nullable = false)
    private Integer nbreParticipant;

    @Column(name = "nbre_heure")
    private Integer nbreHeure;

    @Column(name = "heure_debut")
    private Integer heureDebut;

    @Column(name = "heure_fin")
    private Integer heureFin;

    @NotNull
    @Column(name = "contenu_formation", nullable = false)
    private String contenuFormation;

    @ManyToOne
    @JsonIgnoreProperties(value = "sessions", allowSetters = true)
    private Module module;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public Session dateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
        return this;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public Session dateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
        return this;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public Integer getNbreParticipant() {
        return nbreParticipant;
    }

    public Session nbreParticipant(Integer nbreParticipant) {
        this.nbreParticipant = nbreParticipant;
        return this;
    }

    public void setNbreParticipant(Integer nbreParticipant) {
        this.nbreParticipant = nbreParticipant;
    }

    public Integer getNbreHeure() {
        return nbreHeure;
    }

    public Session nbreHeure(Integer nbreHeure) {
        this.nbreHeure = nbreHeure;
        return this;
    }

    public void setNbreHeure(Integer nbreHeure) {
        this.nbreHeure = nbreHeure;
    }

    public Integer getHeureDebut() {
        return heureDebut;
    }

    public Session heureDebut(Integer heureDebut) {
        this.heureDebut = heureDebut;
        return this;
    }

    public void setHeureDebut(Integer heureDebut) {
        this.heureDebut = heureDebut;
    }

    public Integer getHeureFin() {
        return heureFin;
    }

    public Session heureFin(Integer heureFin) {
        this.heureFin = heureFin;
        return this;
    }

    public void setHeureFin(Integer heureFin) {
        this.heureFin = heureFin;
    }

    public String getContenuFormation() {
        return contenuFormation;
    }

    public Session contenuFormation(String contenuFormation) {
        this.contenuFormation = contenuFormation;
        return this;
    }

    public void setContenuFormation(String contenuFormation) {
        this.contenuFormation = contenuFormation;
    }

    public Module getModule() {
        return module;
    }

    public Session module(Module module) {
        this.module = module;
        return this;
    }

    public void setModule(Module module) {
        this.module = module;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Session)) {
            return false;
        }
        return id != null && id.equals(((Session) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Session{" +
            "id=" + getId() +
            ", dateDebut='" + getDateDebut() + "'" +
            ", dateFin='" + getDateFin() + "'" +
            ", nbreParticipant=" + getNbreParticipant() +
            ", nbreHeure=" + getNbreHeure() +
            ", heureDebut=" + getHeureDebut() +
            ", heureFin=" + getHeureFin() +
            ", contenuFormation='" + getContenuFormation() + "'" +
            "}";
    }
}
