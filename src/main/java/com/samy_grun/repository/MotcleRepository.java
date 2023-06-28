package com.samy_grun.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.samy_grun.model.MotcleEntity;

@Repository("motcleRepository")
public interface MotcleRepository extends JpaRepository<MotcleEntity, Long> {}