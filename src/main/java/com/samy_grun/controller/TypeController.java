package com.samy_grun.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.samy_grun.model.TypeEntity;
import com.samy_grun.model.PublicationTypeEntity;
import com.samy_grun.repository.PublicationTypeRepository;
import com.samy_grun.repository.TypeRepository;

@Controller
public class TypeController extends SecurityController {
    @Autowired
    private TypeRepository typeRepository;
    
    @Autowired
    private PublicationTypeRepository publicationTypeRepository;

    @RequestMapping(value = "/type/sauvegarder", method = { RequestMethod.GET, RequestMethod.POST })
    public String append(Model model, HttpServletRequest request,
            @RequestParam(name = "libelle", required = false, defaultValue = "") String libelle) {
        HttpSession session = request.getSession();
        if (!isLogged(session)) {
            return "index";
        }
        if (!libelle.isEmpty()) {
            if (typeRepository.findByLibelle(libelle).size() > 0) {
                model.addAttribute("info", "Type déjà créé");
            } else {
                PublicationTypeEntity publicationType = new PublicationTypeEntity();
                publicationType.setLibelle(libelle);
                publicationTypeRepository.save(publicationType);

                TypeEntity type = new TypeEntity();
                type.setlibelle(libelle);
                type.setPublicationType(publicationType);
                typeRepository.save(type);
                model.addAttribute("info", "Type enregistré");
            }
            return "redirect:afficher";
        }
        model.addAttribute("list", typeRepository.findAll());
        return "type/sauvegarder";
    }

    @RequestMapping(value = "/type/afficher", method = { RequestMethod.GET, RequestMethod.POST })
    public String list(Model model, HttpServletRequest request,
            @RequestParam(name = "libelle", required = false, defaultValue = "") String libelle) {
        HttpSession session = request.getSession();
        if (!isLogged(session)) {
            return "index";
        }
        model.addAttribute("types", typeRepository.findAll());
        return "type/afficher";
    }
}
