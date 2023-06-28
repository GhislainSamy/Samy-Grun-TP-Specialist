package com.samy_grun.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.samy_grun.model.LangueEntity;

@Repository("langueRepository")
public interface LangueRepository extends JpaRepository<LangueEntity, Long> {}