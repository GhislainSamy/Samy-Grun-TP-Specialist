package com.samy_grun_test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import com.samy_grun.controller.UserController;
import com.samy_grun.model.UserEntity;
import com.samy_grun.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class UserControllerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Mock
    private Model model;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLogin_WhenAlreadyLogged_ReturnsPublicationListe() {
        // Arrange
        when(request.getSession()).thenReturn(session);
        when(isLogged(session)).thenReturn(true);

        // Act
        String result = userController.login(model, request, "", "", "", "");

        // Assert
        assertEquals("Publication/liste", result);
    }

    @Test
    public void testLogin_WhenFieldsAreEmpty_ReturnsIndex() {
        // Arrange
        when(request.getSession()).thenReturn(session);
        when(isLogged(session)).thenReturn(false);

        // Act
        String result = userController.login(model, request, "", "", "", "");

        // Assert
        assertEquals("index", result);
        verify(model, never()).addAttribute(eq("info"), anyString());
    }

//    @Test
//    public void testLogin_WhenFieldsAreNotEmpty_AndUserWithEmailAndPasswordWrong_ReturnsRedirectToIndex() {
//        // Arrange
//        String email = "ghislain.samy@specialist.itis";
//        String password = "1234";
//        List<UserEntity> userList = new ArrayList<>();
//        userList.add(new UserEntity());
//        when(request.getSession()).thenReturn(session);
//        when(isLogged(session)).thenReturn(false);
//        when(userRepository.findByEmailAndPassword(email, password)).thenReturn(userList);
//
//        // Act
//        String result = userController.login(model, request, email, "", "", password);
//
//        // Assert
//    //    assertEquals("redirect:type/afficher", result);
//        assertEquals("index", result);
//        verify(model).addAttribute("info", "Utilisateur ou mot de passe incorrect !!");
//        verify(session).setAttribute("logged", true);
//        verify(session).setAttribute("userid", userList.get(0).getId());
//    }

//   @Test
//    public void testLogout_ReturnsIndex() {
//        // Act
//        String result = userController.logout(model, request);
//
//        // Assert
//        assertEquals("index", result);
//    }

    @Test
    public void testRegister_WhenFieldsAreEmpty_ReturnsRegister() {
        // Act
        String result = userController.register(model, request, "", "", "", "");

        // Assert
        assertEquals("register", result);
        verify(userRepository, never()).findByEmail(anyString());
        verify(userRepository, never()).save(any(UserEntity.class));
        verify(model, never()).addAttribute(eq("info"), anyString());
    }

    @Test
    public void testRegister_WhenEmailAlreadyExists_ReturnsRegisterWithError() {
        // Arrange
        String email = "test@example.com";
        List<UserEntity> userList = new ArrayList<>();
        userList.add(new UserEntity());
        when(userRepository.findByEmail(email)).thenReturn(userList);

        // Act
        String result = userController.register(model, request, email, "John", "Doe", "password");

        // Assert
        assertEquals("register", result);
        verify(userRepository).findByEmail(email);
        verify(userRepository, never()).save(any(UserEntity.class));
        verify(model).addAttribute("level", "error");
        verify(model).addAttribute("info", "Email déjà existant");
    }

    @Test
    public void testRegister_WhenFieldsAreNotEmpty_AndEmailDoesNotExist_ReturnsIndexWithSuccessMessage() {
        // Arrange
        String email = "test@example.com";
        String name = "John";
        String surname = "Doe";
        String password = "password";
        when(userRepository.findByEmail(email)).thenReturn(new ArrayList<>());

        // Act
        String result = userController.register(model, request, email, name, surname, password);

        // Assert
        assertEquals("index", result);
        verify(userRepository).findByEmail(email);
        verify(userRepository).save(any(UserEntity.class));
        verify(model).addAttribute("level", "success");
        verify(model).addAttribute("info", "Compte creer, vous pouvez vous connecter");
    }

    @Test
    public void testAccount_WhenNotLogged_ReturnsIndex() {
        // Arrange
        when(request.getSession()).thenReturn(session);
        when(isLogged(session)).thenReturn(false);

        // Act
        String result = userController.account(model, request, "", "", "", "", "", "", "");

        // Assert
        assertEquals("index", result);
        verify(model, never()).addAttribute(eq("info"), anyString());
    }

//    @Test
//    public void testAccount_WhenEditModeIsEditMail_AndEmailIsNotEmpty_UpdatesEmailAndReturnsAccount() {
        // Arrange
//        Long userId = 1L;
//        String email = "test@example.com";
//        UserEntity user = new UserEntity();
//        user.setId(userId);
//        user.setEmail("old@example.com");
//        when(request.getSession()).thenReturn(session);
//        when(isLogged(session)).thenReturn(true);
//        when(session.getAttribute("userid")).thenReturn(userId);
//        when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(user));

 //       // Act
//        String result = userController.account(model, request, "edit-mail", "", "", "", "", "", email);
//
 //       // Assert
 //       assertEquals("account", result);
 //       assertEquals(email, user.getEmail());
 //       verify(userRepository).save(user);
 //       verify(model).addAttribute("info", "Email mis a jour");
 //   }

    @Test
    public void testAccount_WhenEditModeIsEditMail_AndEmailIsEmpty_DoesNotUpdateEmailAndReturnsAccount() {
        // Arrange
        Long userId = 1L;
        String email = "";
        UserEntity user = new UserEntity();
        user.setId(userId);
        user.setEmail("old@example.com");
        when(request.getSession()).thenReturn(session);
        when(isLogged(session)).thenReturn(true);
        when(session.getAttribute("userid")).thenReturn(userId);
        when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(user));

        // Act
        String result = userController.account(model, request, "edit-mail", "", "", "", "", "", email);

        // Assert
        assertEquals("account", result);
        assertEquals("old@example.com", user.getEmail());
        verify(userRepository, never()).save(user);
        verify(model).addAttribute("info", "email vide");
    }

    @Test
    public void testAccount_WhenEditModeIsEditName_AndNameAndSurnameAreNotEmpty_UpdatesNameAndSurnameAndReturnsAccount() {
        // Arrange
        Long userId = 1L;
        String name = "John";
        String surname = "Doe";
        UserEntity user = new UserEntity();
        user.setId(userId);
        user.setName("Old");
        user.setSurname("User");
        when(request.getSession()).thenReturn(session);
        when(isLogged(session)).thenReturn(true);
        when(session.getAttribute("userid")).thenReturn(userId);
        when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(user));

        // Act
        String result = userController.account(model, request, "edit-name", "", "", "", name, "", surname);

        // Assert
        assertEquals("account", result);
        assertEquals(name, user.getName());
        assertEquals(surname, user.getSurname());
        verify(userRepository).save(user);
        verify(model).addAttribute("info", "Compte mis a jour");
    }

//    @Test
//    public void testAccount_WhenEditModeIsEditPassword_AndPasswordMatchesConfirmation_AndPasswordAndConfirmationAreNotEmpty_UpdatesPasswordAndReturnsAccount() {
        // Arrange
//        Long userId = 1L;
//        String password = "password";
//        String confirmation = "password";
//        UserEntity user = new UserEntity();
//        user.setId(userId);
//        user.setPassword("oldpassword");
//        when(request.getSession()).thenReturn(session);
//        when(isLogged(session)).thenReturn(true);
//        when(session.getAttribute("userid")).thenReturn(userId);
//        when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(user));
//        when(hashPassword(password)).thenReturn("hashedpassword");

        // Act
//        String result = userController.account(model, request, "edit-password", password, confirmation, "", "", "", "");

        // Assert
//        assertEquals("account", result);
//        assertEquals("hashedpassword", user.getPassword());
//        verify(userRepository).save(user);
//        verify(model).addAttribute("info", "Mot de passe mis a jour");
//    }


//    @Test
//    public void testAccount_WhenEditModeIsDelete_AndConfirmationMatchesDeleteString_DeletesUserAndReturnsIndex() {
        // Arrange
//        Long userId = 1L;
//        String password = "password";
//        UserEntity user = new UserEntity();
//        user.setId(userId);
//        user.setPassword("password");
//        when(request.getSession()).thenReturn(session);
//        when(isLogged(session)).thenReturn(true);
//        when(session.getAttribute("userid")).thenReturn(userId);
//        when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(user));

        // Act
//        String result = userController.account(model, request, "delete", password, "SUPPRIMER", "", "", "", "");

        // Assert
//        assertEquals("index", result);
//        verify(userRepository).deleteById(userId);
//        verify(userController).disconnect(session, "Compte supprime");
//    }

//    @Test
//    public void testAccount_WhenEditModeIsDelete_AndConfirmationDoesNotMatchDeleteString_DoesNotDeleteUserAndReturnsAccount() {
        // Arrange
//        Long userId = 1L;
//        String password = "password";
//        UserEntity user = new UserEntity();
//        user.setId(userId);
//        user.setPassword("password");
//        when(request.getSession()).thenReturn(session);
//        when(isLogged(session)).thenReturn(true);
//        when(session.getAttribute("userid")).thenReturn(userId);
//        when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(user));
//
        // Act
//        String result = userController.account(model, request, "delete", password, "WRONG", "", "", "", "");
//
//        // Assert
//        assertEquals("account", result);
//        verify(userRepository, never()).deleteById(userId);
//        verify(userController, never()).disconnect(session, "Compte supprime");
//        verify(model).addAttribute("info", "Suppression annule");
//    }
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
