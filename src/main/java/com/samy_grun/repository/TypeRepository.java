package com.samy_grun.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.samy_grun.model.TypeEntity;

@Repository("typeRepository")
public interface TypeRepository extends JpaRepository<TypeEntity, Long> {
    public List<TypeEntity> findByLibelle(String libelle);
}