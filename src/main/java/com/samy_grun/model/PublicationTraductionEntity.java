package com.samy_grun.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.samy_grun.model.TraductionEntity;

@Entity
@Table(name="PublicationTraduction")
public class PublicationTraductionEntity {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToMany(cascade = CascadeType.ALL, targetEntity = TraductionEntity.class)
    @JoinColumn(name = "id", updatable = false, insertable = false)
    private Set<TraductionEntity> traductions;

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

}
