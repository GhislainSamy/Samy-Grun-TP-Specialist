package com.samy_grun.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.samy_grun.model.PublicationEntity;
import com.samy_grun.model.PublicationTypeEntity;

@Repository("PublicationRepository")
public interface PublicationRepository extends JpaRepository<PublicationEntity, Long> {
    List<PublicationEntity> findByTitle(String title);
    List<PublicationEntity> findByType(PublicationTypeEntity type);
}