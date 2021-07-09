package azimut.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import javax.persistence.Lob;

/**
 * A DTO for the {@link azimut.domain.Formateur} entity.
 */
public class FormateurDTO implements Serializable {
    
    private Long id;

    @Lob
    private byte[] photo;

    private String photoContentType;
    @NotNull
    private String cnib;

    @NotNull
    private String nom;

    @NotNull
    private String prenom;

    private String statut;

    @NotNull
    private String contact;

    private String email;

    private Long salaireHoraire;

    private Long salaireMensuel;


    private Long etablissementId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getPhotoContentType() {
        return photoContentType;
    }

    public void setPhotoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
    }

    public String getCnib() {
        return cnib;
    }

    public void setCnib(String cnib) {
        this.cnib = cnib;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getSalaireHoraire() {
        return salaireHoraire;
    }

    public void setSalaireHoraire(Long salaireHoraire) {
        this.salaireHoraire = salaireHoraire;
    }

    public Long getSalaireMensuel() {
        return salaireMensuel;
    }

    public void setSalaireMensuel(Long salaireMensuel) {
        this.salaireMensuel = salaireMensuel;
    }

    public Long getEtablissementId() {
        return etablissementId;
    }

    public void setEtablissementId(Long etablissementId) {
        this.etablissementId = etablissementId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FormateurDTO)) {
            return false;
        }

        return id != null && id.equals(((FormateurDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FormateurDTO{" +
            "id=" + getId() +
            ", photo='" + getPhoto() + "'" +
            ", cnib='" + getCnib() + "'" +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", statut='" + getStatut() + "'" +
            ", contact='" + getContact() + "'" +
            ", email='" + getEmail() + "'" +
            ", salaireHoraire=" + getSalaireHoraire() +
            ", salaireMensuel=" + getSalaireMensuel() +
            ", etablissementId=" + getEtablissementId() +
            "}";
    }
}
