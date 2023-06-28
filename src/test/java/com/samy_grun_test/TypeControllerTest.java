package com.samy_grun_test;

import com.samy_grun.controller.TypeController;
import com.samy_grun.model.TypeEntity;
import com.samy_grun.model.PublicationTypeEntity;
import com.samy_grun.repository.TypeRepository;
import com.samy_grun.repository.PublicationTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TypeControllerTest {
    @Mock
    private TypeRepository typeRepository;

    @Mock
    private PublicationTypeRepository publicationTypeRepository;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Mock
    private Model model;

    @InjectMocks
    private TypeController typeController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAppend_WhenNotLogged_ReturnsIndex() {
        // Arrange
        when(request.getSession()).thenReturn(session);
        when(isLogged(session)).thenReturn(false);

        // Act
        String result = typeController.append(model, request, "");

        // Assert
        assertEquals("index", result);
    }

    @Test
    public void testAppend_WhenLibelleIsEmpty_ReturnsTypeSauvegarder() {
        // Arrange
        when(request.getSession()).thenReturn(session);
        when(isLogged(session)).thenReturn(true);

        // Act
        String result = typeController.append(model, request, "");

        // Assert
        assertEquals("type/sauvegarder", result);
        verify(model).addAttribute(eq("list"), any());
    }

    @Test
    public void testAppend_WhenLibelleIsNotEmpty_AndTypeAlreadyExists_ReturnsRedirectToAfficher() {
        // Arrange
        String libelle = "Type1";
        List<TypeEntity> typeList = new ArrayList<>();
        typeList.add(new TypeEntity());
        when(request.getSession()).thenReturn(session);
        when(isLogged(session)).thenReturn(true);
        when(typeRepository.findByLibelle(libelle)).thenReturn(typeList);

        // Act
        String result = typeController.append(model, request, libelle);

        // Assert
        assertEquals("redirect:afficher", result);
        verify(model).addAttribute("info", "Type déjà créé");
    }

    @Test
    public void testAppend_WhenLibelleIsNotEmpty_AndTypeDoesNotExist_ReturnsRedirectToAfficher() {
        // Arrange
        String libelle = "Type1";
        when(request.getSession()).thenReturn(session);
        when(isLogged(session)).thenReturn(true);
        when(typeRepository.findByLibelle(libelle)).thenReturn(new ArrayList<>());
        when(publicationTypeRepository.save(any())).thenReturn(new PublicationTypeEntity());
        when(typeRepository.save(any())).thenReturn(new TypeEntity());

        // Act
        String result = typeController.append(model, request, libelle);

        // Assert
        assertEquals("redirect:afficher", result);
        verify(model).addAttribute("info", "Type enregistré");
    }

    @Test
    public void testList_WhenNotLogged_ReturnsIndex() {
        // Arrange
        when(request.getSession()).thenReturn(session);
        when(isLogged(session)).thenReturn(false);

        // Act
        String result = typeController.list(model, request, "");

        // Assert
        assertEquals("index", result);
    }

    @Test
    public void testList_WhenLogged_ReturnsTypeAfficher() {
        // Arrange
        when(request.getSession()).thenReturn(session);
        when(isLogged(session)).thenReturn(true);
        List<TypeEntity> typeList = new ArrayList<>();
        when(typeRepository.findAll()).thenReturn(typeList);

        // Act
        String result = typeController.list(model, request, "");

        // Assert
        assertEquals("type/afficher", result);
        verify(model).addAttribute("types", typeList);
    }
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

}
