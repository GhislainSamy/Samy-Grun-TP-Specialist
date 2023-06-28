package com.samy_grun.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.samy_grun.model.UserEntity;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    public List<UserEntity> findByEmail(String email);
    public List<UserEntity> findByEmailAndPassword(String email, String password);
    public List<UserEntity> findByName(String name);
    public List<UserEntity> findByNameAndSurname(String name, String surname);
    public List<UserEntity> findByNameAndSurnameAndPassword(String name, String surname, String password);
    public List<UserEntity> findByRole(String role);
}