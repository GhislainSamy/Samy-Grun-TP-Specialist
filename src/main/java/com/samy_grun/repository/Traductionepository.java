package com.samy_grun.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.samy_grun.model.TraductionEntity;

@Repository("traductionRepository")
public interface Traductionepository extends JpaRepository<TraductionEntity, Long> {}