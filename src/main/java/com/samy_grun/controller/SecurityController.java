package com.samy_grun.controller;

import java.nio.charset.StandardCharsets;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

@Controller
public class SecurityController {

    protected Boolean isLogged(HttpSession session) {
        Long timestamp = (session.getAttribute("update") == null) ? 0L: (Long) session.getAttribute("update");
        if (timestamp > (System.currentTimeMillis() + 210000)) disconnect(session, "Connexion Timeout");
        else session.setAttribute("update", System.currentTimeMillis());
        return (session.getAttribute("logged") == null) ? false: (Boolean) session.getAttribute("logged");
    }

    protected Void disconnect(HttpSession session, String message) {
        session.setAttribute("logged", false);
        session.setAttribute("userid",    0L);
        session.setAttribute("update", System.currentTimeMillis());
        session.setAttribute("info", message);
        return null;
    }

    protected String hashPassword(String password) {
        try {
            MessageDigest digest= MessageDigest.getInstance("SHA3-256");
            byte[] rawhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hash = new StringBuilder(2 * rawhash.length);
            for (int i=0; i<rawhash.length; i++) {
                String hex = Integer.toHexString(0xff & rawhash[i]);
                if(hex.length() == 1) {hash.append('0');}
                hash.append(hex);
            }
            return hash.toString();
        } catch (NoSuchAlgorithmException e) {}
        return "*!";
    }
}
