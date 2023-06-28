package com.samy_grun.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "publication")
public class PublicationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public enum STATUS {
        ENCOUR, DISPO, NONDISPO
    };

    private STATUS statuts;

    @NotBlank(message = "Title est obligatoire")
    private String title;

    @NotBlank(message = "Texte est obligatoire")
    private String texte;

    @NotBlank(message = "Auteur est obligatoire")
    private String auteur;

    @NotBlank(message = "Sens est obligatoire")
    private String sens;

    @NotBlank(message = "Source est obligatoire")
    private String source;

    @NotBlank(message = "Document est obligatoire")
    private String document;

    @NotBlank(message = "Résumé est obligatoire")
    private String resume;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private PublicationTypeEntity type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public STATUS getStatuts() {
        return statuts;
    }

    public void setStatuts(STATUS statuts) {
        this.statuts = statuts;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getSens() {
        return sens;
    }

    public void setSens(String sens) {
        this.sens = sens;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public PublicationTypeEntity getType() {
        return type;
    }

    public void setType(PublicationTypeEntity type) {
        this.type = type;
    }
}
