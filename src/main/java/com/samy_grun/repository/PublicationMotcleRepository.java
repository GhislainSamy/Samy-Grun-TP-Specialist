package com.samy_grun.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.samy_grun.model.PublicationMotCleEntity;

@Repository("publicationMotcleRepository")
public interface PublicationMotcleRepository extends JpaRepository<PublicationMotCleEntity, Long> {}