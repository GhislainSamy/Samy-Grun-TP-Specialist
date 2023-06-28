package com.samy_grun.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.persistence.Id;

@Entity
@Table(name="Traduction")
public class TraductionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

  //  @ManyToOne(targetEntity = PublicationEntity.class)
  //  @JoinColumn(name="id", updatable = false, insertable = false)
 //   private PublicationEntity publication;

    @NotEmpty(message = "")
    private String texte;

    @NotEmpty(message = "")
    private String image;

 //   @ManyToOne(cascade = CascadeType.ALL, targetEntity = UserEntity.class)
 //   @JoinColumn(name = "id", updatable = false, insertable = false)
 //   private UserEntity auteur;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = LangueEntity.class)
    @JoinColumn(name = "id", updatable = false, insertable = false)
    private Set<LangueEntity> langues;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

 //   public PublicationEntity getPublication() {
 //       return publication;
 //   }

  //  public void setPublication(PublicationEntity publication) {
 //       this.publication = publication;
//    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

//    public UserEntity getAuteur() {
//        return auteur;
//    }

 //   public void setAuteur(UserEntity auteur) {
 //       this.auteur = auteur;
 //   }

    public Set<LangueEntity> getLangues() {
        return langues;
    }

    public void setLangues(Set<LangueEntity> langues) {
        this.langues = langues;
    }
}
