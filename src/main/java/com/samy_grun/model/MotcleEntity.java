package com.samy_grun.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.samy_grun.model.PublicationMotCleEntity;

@Entity
@Table(name="Motcle")
public class MotcleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "")
    private String libelle;

    @ManyToOne(targetEntity = PublicationMotCleEntity.class)
    @JoinColumn(name = "id", updatable = false, insertable = false)
    private PublicationMotCleEntity publicationMotCle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public PublicationMotCleEntity getPublicationMotCle() {
        return publicationMotCle;
    }

    public void setPublicationMotCle(PublicationMotCleEntity publicationMotCle) {
        this.publicationMotCle = publicationMotCle;
    }

    
}
