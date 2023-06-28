package com.samy_grun.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="Type")
public class TypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = PublicationTypeEntity.class)
    @JoinColumn(name = "id", updatable = false, insertable = false)
    private PublicationTypeEntity PublicationType;

    @NotEmpty(message = "")
    private String libelle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PublicationTypeEntity getPublicationType() {
        return PublicationType;
    }

    public void setPublicationType(PublicationTypeEntity PublicationType) {
        this.PublicationType = PublicationType;
    }

    public String getlibelle() {
        return libelle;
    }

    public void setlibelle(String libelle) {
        this.libelle = libelle;
    }
}
