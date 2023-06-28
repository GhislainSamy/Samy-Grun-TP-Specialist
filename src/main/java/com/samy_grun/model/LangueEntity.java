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
@Table(name="Langue")
public class LangueEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "")
    private String libelle;

    @ManyToOne(targetEntity = TraductionEntity.class)
    @JoinColumn(name = "Id", updatable = false, insertable = false)
    private TraductionEntity traduction;

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

    public TraductionEntity getTraduction() {
        return traduction;
    }

    public void setTraduction(TraductionEntity traduction) {
        this.traduction = traduction;
    }
}
