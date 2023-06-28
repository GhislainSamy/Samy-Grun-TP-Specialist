package com.samy_grun_test;

import com.samy_grun.model.PublicationTypeEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PublicationTypeEntityTest {
    private PublicationTypeEntity publicationTypeEntity;

    @BeforeEach
    public void setUp() {
        publicationTypeEntity = new PublicationTypeEntity();
    }

    @Test
    public void testId() {
        Long id = 1L;
        publicationTypeEntity.setId(id);

        Assertions.assertEquals(id, publicationTypeEntity.getId());
    }

    @Test
    public void testLibelle() {
        String libelle = "LibelleTes";
        publicationTypeEntity.setLibelle(libelle);

        Assertions.assertEquals(libelle, publicationTypeEntity.getLibelle());
    }
}
