package com.samy_grun.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Validation")
public class ValidationEntity {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
 //   @ManyToOne(targetEntity = UserEntity.class)
 //   @JoinColumn(name = "id", updatable = false, insertable = false)
//    private UserEntity valideur;

 //   @OneToMany(targetEntity = PublicationEntity.class)
 //   @JoinColumn(name = "id", updatable = false, insertable = false)
 //   private Set<PublicationEntity> publications;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

 //   public UserEntity getValideur() {
//        return valideur;
//    }

 //   public void setValideur(UserEntity valideur) {
//        this.valideur = valideur;
//    }

//    public Set<PublicationEntity> getPublications() {
//        return publications;
//    }

//    public void setPublications(Set<PublicationEntity> publications) {
//        this.publications = publications;
//    }
}
