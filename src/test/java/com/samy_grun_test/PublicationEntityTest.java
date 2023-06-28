package com.samy_grun_test;

import com.samy_grun.model.PublicationEntity;
import com.samy_grun.model.PublicationTypeEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PublicationEntityTest {
    private PublicationEntity publicationEntity;

    @BeforeEach
    public void setUp() {
        publicationEntity = new PublicationEntity();
    }

    @Test
    public void testId() {
        Long id = 1L;
        publicationEntity.setId(id);

        Assertions.assertEquals(id, publicationEntity.getId());
    }

    @Test
    public void testTitle() {
        String title = "Harry Potter";
        publicationEntity.setTitle(title);

        Assertions.assertEquals(title, publicationEntity.getTitle());
    }

    @Test
    public void testStatuts() {
        PublicationEntity.STATUS statuts = PublicationEntity.STATUS.ENCOUR;
        publicationEntity.setStatuts(statuts);

        Assertions.assertEquals(statuts, publicationEntity.getStatuts());
    }

    @Test
    public void testTexte() {
        String texte = "Monde créer par JK..";
        publicationEntity.setTexte(texte);

        Assertions.assertEquals(texte, publicationEntity.getTexte());
    }

    @Test
    public void testAuteur() {
        String auteur = "Daniel Radcliffe";
        publicationEntity.setAuteur(auteur);

        Assertions.assertEquals(auteur, publicationEntity.getAuteur());
    }

    @Test
    public void testSens() {
        String sens = "Le sens";
        publicationEntity.setSens(sens);

        Assertions.assertEquals(sens, publicationEntity.getSens());
    }

    @Test
    public void testSource() {
        String source = "wikipedia";
        publicationEntity.setSource(source);

        Assertions.assertEquals(source, publicationEntity.getSource());
    }

    @Test
    public void testDocument() {
        String document = "le document";
        publicationEntity.setDocument(document);

        Assertions.assertEquals(document, publicationEntity.getDocument());
    }

    @Test
    public void testResume() {
        String resume = "un sorcier .....";
        publicationEntity.setResume(resume);

        Assertions.assertEquals(resume, publicationEntity.getResume());
    }

    @Test
    public void testType() {
        PublicationTypeEntity type = new PublicationTypeEntity();
        type.setId(1L);
        type.setLibelle("Fantastique");
        publicationEntity.setType(type);

        Assertions.assertEquals(type, publicationEntity.getType());
    }
}
