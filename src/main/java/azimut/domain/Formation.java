package azimut.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import azimut.domain.enumeration.NomFormation;

/**
 * A Formation.
 */
@Entity
@Table(name = "formation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Formation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "nom_formation", nullable = false)
    private NomFormation nomFormation;

    @OneToOne
    @JoinColumn(unique = true)
    private Rapport rapport;

    @OneToMany(mappedBy = "formation")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Module> modules = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "formations", allowSetters = true)
    private Inscription inscription;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NomFormation getNomFormation() {
        return nomFormation;
    }

    public Formation nomFormation(NomFormation nomFormation) {
        this.nomFormation = nomFormation;
        return this;
    }

    public void setNomFormation(NomFormation nomFormation) {
        this.nomFormation = nomFormation;
    }

    public Rapport getRapport() {
        return rapport;
    }

    public Formation rapport(Rapport rapport) {
        this.rapport = rapport;
        return this;
    }

    public void setRapport(Rapport rapport) {
        this.rapport = rapport;
    }

    public Set<Module> getModules() {
        return modules;
    }

    public Formation modules(Set<Module> modules) {
        this.modules = modules;
        return this;
    }

    public Formation addModule(Module module) {
        this.modules.add(module);
        module.setFormation(this);
        return this;
    }

    public Formation removeModule(Module module) {
        this.modules.remove(module);
        module.setFormation(null);
        return this;
    }

    public void setModules(Set<Module> modules) {
        this.modules = modules;
    }

    public Inscription getInscription() {
        return inscription;
    }

    public Formation inscription(Inscription inscription) {
        this.inscription = inscription;
        return this;
    }

    public void setInscription(Inscription inscription) {
        this.inscription = inscription;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Formation)) {
            return false;
        }
        return id != null && id.equals(((Formation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Formation{" +
            "id=" + getId() +
            ", nomFormation='" + getNomFormation() + "'" +
            "}";
    }
}
