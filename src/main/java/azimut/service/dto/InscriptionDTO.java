package azimut.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link azimut.domain.Inscription} entity.
 */
public class InscriptionDTO implements Serializable {
    
    private Long id;

    @NotNull
    private LocalDate dateInscription;

    @NotNull
    private BigDecimal montantApayer;

    @NotNull
    private BigDecimal montantVerse;

    @NotNull
    private BigDecimal resteApayer;

    @NotNull
    private String etatEtudiant;


    private Long periodeId;

    private Long etablissementId;

    private Long anneeScolaireId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(LocalDate dateInscription) {
        this.dateInscription = dateInscription;
    }

    public BigDecimal getMontantApayer() {
        return montantApayer;
    }

    public void setMontantApayer(BigDecimal montantApayer) {
        this.montantApayer = montantApayer;
    }

    public BigDecimal getMontantVerse() {
        return montantVerse;
    }

    public void setMontantVerse(BigDecimal montantVerse) {
        this.montantVerse = montantVerse;
    }

    public BigDecimal getResteApayer() {
        return resteApayer;
    }

    public void setResteApayer(BigDecimal resteApayer) {
        this.resteApayer = resteApayer;
    }

    public String getEtatEtudiant() {
        return etatEtudiant;
    }

    public void setEtatEtudiant(String etatEtudiant) {
        this.etatEtudiant = etatEtudiant;
    }

    public Long getPeriodeId() {
        return periodeId;
    }

    public void setPeriodeId(Long periodeId) {
        this.periodeId = periodeId;
    }

    public Long getEtablissementId() {
        return etablissementId;
    }

    public void setEtablissementId(Long etablissementId) {
        this.etablissementId = etablissementId;
    }

    public Long getAnneeScolaireId() {
        return anneeScolaireId;
    }

    public void setAnneeScolaireId(Long anneeScolaireId) {
        this.anneeScolaireId = anneeScolaireId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InscriptionDTO)) {
            return false;
        }

        return id != null && id.equals(((InscriptionDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InscriptionDTO{" +
            "id=" + getId() +
            ", dateInscription='" + getDateInscription() + "'" +
            ", montantApayer=" + getMontantApayer() +
            ", montantVerse=" + getMontantVerse() +
            ", resteApayer=" + getResteApayer() +
            ", etatEtudiant='" + getEtatEtudiant() + "'" +
            ", periodeId=" + getPeriodeId() +
            ", etablissementId=" + getEtablissementId() +
            ", anneeScolaireId=" + getAnneeScolaireId() +
            "}";
    }
}
