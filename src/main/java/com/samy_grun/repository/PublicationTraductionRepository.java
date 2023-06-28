package com.samy_grun.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.samy_grun.model.PublicationTraductionEntity;

@Repository("publicationTraductionRepository")
public interface PublicationTraductionRepository extends JpaRepository<PublicationTraductionEntity, Long> {}