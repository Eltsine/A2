package azimut.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import azimut.domain.enumeration.NomModule;

/**
 * A Module.
 */
@Entity
@Table(name = "module")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Module implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "nom_module", nullable = false)
    private NomModule nomModule;

    @Column(name = "prix", precision = 21, scale = 2)
    private BigDecimal prix;

    @OneToMany(mappedBy = "module")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Session> sessions = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "modules", allowSetters = true)
    private Formation formation;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NomModule getNomModule() {
        return nomModule;
    }

    public Module nomModule(NomModule nomModule) {
        this.nomModule = nomModule;
        return this;
    }

    public void setNomModule(NomModule nomModule) {
        this.nomModule = nomModule;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public Module prix(BigDecimal prix) {
        this.prix = prix;
        return this;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public Set<Session> getSessions() {
        return sessions;
    }

    public Module sessions(Set<Session> sessions) {
        this.sessions = sessions;
        return this;
    }

    public Module addSession(Session session) {
        this.sessions.add(session);
        session.setModule(this);
        return this;
    }

    public Module removeSession(Session session) {
        this.sessions.remove(session);
        session.setModule(null);
        return this;
    }

    public void setSessions(Set<Session> sessions) {
        this.sessions = sessions;
    }

    public Formation getFormation() {
        return formation;
    }

    public Module formation(Formation formation) {
        this.formation = formation;
        return this;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Module)) {
            return false;
        }
        return id != null && id.equals(((Module) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Module{" +
            "id=" + getId() +
            ", nomModule='" + getNomModule() + "'" +
            ", prix=" + getPrix() +
            "}";
    }
}
