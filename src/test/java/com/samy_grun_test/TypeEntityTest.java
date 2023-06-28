package com.samy_grun_test;

import com.samy_grun.model.PublicationTypeEntity;
import com.samy_grun.model.TypeEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TypeEntityTest {
    private TypeEntity typeEntity;

    @BeforeEach
    public void setUp() {
        typeEntity = new TypeEntity();
    }

    @Test
    public void testId() {
        Long id = 1L;
        typeEntity.setId(id);

        Assertions.assertEquals(id, typeEntity.getId());
    }

    @Test
    public void testPublicationType() {
        PublicationTypeEntity publicationType = new PublicationTypeEntity();
        typeEntity.setPublicationType(publicationType);

        Assertions.assertEquals(publicationType, typeEntity.getPublicationType());
    }

    @Test
    public void testLibelle() {
        String libelle = "Libelle1Test";
        typeEntity.setlibelle(libelle);

        Assertions.assertEquals(libelle, typeEntity.getlibelle());
    }
}
