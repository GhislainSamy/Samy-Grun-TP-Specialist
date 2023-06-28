package com.samy_grun.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.samy_grun.model.PublicationTypeEntity;

@Repository("PublicationTypeRepository")
public interface PublicationTypeRepository extends JpaRepository<PublicationTypeEntity, Long> {}