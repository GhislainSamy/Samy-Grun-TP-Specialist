package com.samy_grun.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.samy_grun.model.UserEntity;
import com.samy_grun.model.enums.RoleEnum;
import com.samy_grun.repository.UserRepository;

@Service
public class DbInit implements CommandLineRunner {

    private final UserRepository userRepository;

    @Autowired
    public DbInit(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Vérifier si le premier utilisateur existe déjà
        if (userRepository.findByEmail("ghislain.samy@specialist.itis").isEmpty()) {
            // Insérer le premier utilisateur
            UserEntity user1 = new UserEntity();
            user1.setEmail("ghislain.samy@specialist.itis");
            user1.setName("samy");
            user1.setSurname("ghislain");
            user1.setPassword(hashPasswordtest("1234"));
            user1.setRole(RoleEnum.UTILISATEUR.toString());
            userRepository.save(user1);
        } else {
            System.out.println("Le premier utilisateur existe déjà en base !");
        }

        // Vérifier si le deuxième utilisateur existe déjà
        if (userRepository.findByEmail("alexis.grun@specialist.itis").isEmpty()) {
            // Insérer le deuxième utilisateur
            UserEntity user2 = new UserEntity();
            user2.setEmail("alexis.grun@specialist.itis");
            user2.setName("grun");
            user2.setSurname("alexis");
            user2.setPassword(hashPasswordtest("5678"));
            user2.setRole(RoleEnum.UTILISATEUR.toString());
            userRepository.save(user2);
        } else {
            System.out.println("Le deuxième utilisateur existe déjà en base !");
        }
    }

    protected String hashPasswordtest(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA3-256");
            byte[] rawhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hash = new StringBuilder(2 * rawhash.length);
            for (int i = 0; i < rawhash.length; i++) {
                String hex = Integer.toHexString(0xff & rawhash[i]);
                if (hex.length() == 1) {
                    hash.append('0');
                }
                hash.append(hex);
            }
            return hash.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "*!";
    }
}
