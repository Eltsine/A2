package azimut.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A AnneeScolaire.
 */
@Entity
@Table(name = "annee_scolaire")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AnneeScolaire implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "libelle_annee")
    private String libelleAnnee;

    @OneToMany(mappedBy = "anneeScolaire")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Inscription> inscriptions = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelleAnnee() {
        return libelleAnnee;
    }

    public AnneeScolaire libelleAnnee(String libelleAnnee) {
        this.libelleAnnee = libelleAnnee;
        return this;
    }

    public void setLibelleAnnee(String libelleAnnee) {
        this.libelleAnnee = libelleAnnee;
    }

    public Set<Inscription> getInscriptions() {
        return inscriptions;
    }

    public AnneeScolaire inscriptions(Set<Inscription> inscriptions) {
        this.inscriptions = inscriptions;
        return this;
    }

    public AnneeScolaire addInscription(Inscription inscription) {
        this.inscriptions.add(inscription);
        inscription.setAnneeScolaire(this);
        return this;
    }

    public AnneeScolaire removeInscription(Inscription inscription) {
        this.inscriptions.remove(inscription);
        inscription.setAnneeScolaire(null);
        return this;
    }

    public void setInscriptions(Set<Inscription> inscriptions) {
        this.inscriptions = inscriptions;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AnneeScolaire)) {
            return false;
        }
        return id != null && id.equals(((AnneeScolaire) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AnneeScolaire{" +
            "id=" + getId() +
            ", libelleAnnee='" + getLibelleAnnee() + "'" +
            "}";
    }
}
