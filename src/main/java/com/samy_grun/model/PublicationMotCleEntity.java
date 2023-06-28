package com.samy_grun.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.samy_grun.model.LangueEntity;
import com.samy_grun.model.MotcleEntity;

@Entity
@Table(name="PublicationMotCle")
public class PublicationMotCleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "")
    private String traduction;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = MotcleEntity.class)
    @JoinColumn(name = "id", updatable = false, insertable = false)
    private Set<MotcleEntity> motcle;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = LangueEntity.class)
    @JoinColumn(name = "id", updatable = false, insertable = false)
    private Set<LangueEntity> langue = new HashSet<LangueEntity>();

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getTraduction() {return traduction;}
    public void setTraduction(String traduction) {this.traduction = traduction;}

    public Set<MotcleEntity> getMotcle() {return motcle;}
    public void setMotcle(Set<MotcleEntity> motcle) {this.motcle = motcle;}

    public Set<LangueEntity> getLangue() {return langue;}
    public void setLangue(Set<LangueEntity> langue) {this.langue = langue;}
}