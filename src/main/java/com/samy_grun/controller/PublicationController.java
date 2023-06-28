package com.samy_grun.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.samy_grun.model.PublicationEntity;
import com.samy_grun.model.PublicationTypeEntity;
import com.samy_grun.model.UserEntity;
import com.samy_grun.repository.PublicationRepository;
import com.samy_grun.repository.PublicationTypeRepository;
import com.samy_grun.repository.TypeRepository;
import com.samy_grun.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PublicationController extends SecurityController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PublicationRepository publicationRepository;

    @Autowired
    private PublicationTypeRepository publicationTypeRepository;

    @Autowired
    private TypeRepository typeRepository;

    @RequestMapping(value = "/Publication/afficher", method = { RequestMethod.GET, RequestMethod.POST })
    public String liste(Model model, HttpServletRequest request,
            @RequestParam(name = "npublication", required = false, defaultValue = "") String npublication) {
        HttpSession session = request.getSession();
        if (!isLogged(session)) {
            return "index";
        }
        UserEntity user = userRepository.getById((Long) session.getAttribute("userid"));
        List<PublicationEntity> publicationList = publicationRepository.findAll();
        model.addAttribute("publicationList", publicationList);
        model.addAttribute("user", user);
        return "Publication/afficher";
    }

    @RequestMapping(value = "/Publication/sauvegarder", method = { RequestMethod.GET, RequestMethod.POST })
    public String append(Model model, HttpServletRequest request,
            @RequestParam(name = "title", required = false, defaultValue = "") String title,
            @RequestParam(name = "texte", required = false, defaultValue = "") String texte,
            @RequestParam(name = "auteur", required = false, defaultValue = "") String author,
            @RequestParam(name = "resume", required = false, defaultValue = "") String summary,
            @RequestParam(name = "source", required = false, defaultValue = "") String source,
            @RequestParam(name = "sens", required = false, defaultValue = "") String sens,
            @RequestParam(name = "document", required = false, defaultValue = "") String document,
            @RequestParam(name = "type", required = false) Long type) {
        HttpSession session = request.getSession();
        if (!isLogged(session)) {
            return "index";
        }
        if (!title.isEmpty() && !texte.isEmpty() && !author.isEmpty() && !summary.isEmpty() && type != null) {
            PublicationEntity publisave = new PublicationEntity();
            publisave.setAuteur(author);
            publisave.setResume(summary);
            publisave.setTexte(texte);
            publisave.setTitle(title);
            publisave.setDocument(document);
            publisave.setSens(sens);
            publisave.setSource(source);
            publisave.setStatuts(PublicationEntity.STATUS.DISPO);

            PublicationTypeEntity publicationType = publicationTypeRepository.getById(type);
            publisave.setType(publicationType);

            publicationRepository.save(publisave);
            model.addAttribute("info", "Publication bien enregistré");
        }

        model.addAttribute("types", typeRepository.findAll());
        return "Publication/sauvegarder";
    }
}
