package com.samy_grun.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.samy_grun.model.UserEntity;
import com.samy_grun.model.enums.RoleEnum;
import com.samy_grun.repository.UserRepository;

@Controller
public class UserController extends SecurityController {
	@Autowired
	private UserRepository User;

    @RequestMapping(value="/login",  method={RequestMethod.GET, RequestMethod.POST})
    public String login(
        Model model,
        HttpServletRequest request,
        @RequestParam(name="email"   , required = false, defaultValue = "") String email  ,
        @RequestParam(name="name"    , required = false, defaultValue = "") String name   ,
        @RequestParam(name="surname" , required = false, defaultValue = "") String surname,
        @RequestParam(name="password", required = false, defaultValue = "") String password
    ) {
        HttpSession session = request.getSession();
        if (isLogged(session)) {return "Publication/liste";}

        session.setAttribute("logged", false);
        session.setAttribute("update", System.currentTimeMillis());
        if (!password.isEmpty() && ((!name.isEmpty() && !surname.isEmpty()) || !email.isEmpty())) {
            password = hashPassword(password);
            List<UserEntity> Users =  User.findByEmailAndPassword(email, password);
            if (Users.size() == 0) {Users = User.findByNameAndSurnameAndPassword(name, surname, password);}
            if (Users.size() == 0 || Users.size() > 1) {
                model.addAttribute("info", "Utilisateur ou mot de passe incorrect !!");
            } else {
                model.addAttribute("info", "Connexion réussi");
                session.setAttribute("logged", true);
                session.setAttribute("userid", Users.get(0).getId());
            }
        }
        if (isLogged(session)) {return "redirect:type/afficher";}
        return "index";
    }

    @RequestMapping(value="/logout",  method={RequestMethod.GET, RequestMethod.POST})
    public String logout(Model model, HttpServletRequest request)
    {
        disconnect(request.getSession(), "");
        return "index";
    }

    @RequestMapping(value="/register",  method={RequestMethod.GET, RequestMethod.POST})
    public String register(
        Model model,
        HttpServletRequest request,
        @RequestParam(name="email"   , required = false, defaultValue = "") String email  ,
        @RequestParam(name="name"    , required = false, defaultValue = "") String name   ,
        @RequestParam(name="surname" , required = false, defaultValue = "") String surname,
        @RequestParam(name="password", required = false, defaultValue = "") String password
    ) {
        if (!password.isEmpty() && !name.isEmpty() && !surname.isEmpty() && !email.isEmpty()) {
            List<UserEntity> Users = User.findByEmail(email);
            if (Users.size() == 0){
                UserEntity usr = new UserEntity();
                usr.setEmail(email);
                usr.setName(name);
                usr.setSurname(surname);
                usr.setRole(RoleEnum.UTILISATEUR.toString());
                usr.setPassword(hashPassword(password));
                User.save(usr);
                model.addAttribute("level", "success");
                model.addAttribute("info", "Compte creer, vous pouvez vous connecter");
                return "index";
            } else {
                model.addAttribute("level", "error");
                model.addAttribute("info", "Email déjà existant");
            }
        }
        return "register";
    }

    @RequestMapping(value="/account",  method={RequestMethod.GET, RequestMethod.POST})
    public String account(
        Model model, 
        HttpServletRequest request,
        @RequestParam(name="mode"    , required = false, defaultValue = "") String mode    ,
        @RequestParam(name="password", required = false, defaultValue = "") String password,
        @RequestParam(name="passchg" , required = false, defaultValue = "") String passchg ,
        @RequestParam(name="passcnf" , required = false, defaultValue = "") String passcnf ,
        @RequestParam(name="name"    , required = false, defaultValue = "") String name    ,
        @RequestParam(name="email"   , required = false, defaultValue = "") String email   ,
        @RequestParam(name="surname" , required = false, defaultValue = "") String surname
    ){
        HttpSession session = request.getSession();
        if (!isLogged(session)) {return "index";}

        Long id = (Long) session.getAttribute("userid");
        UserEntity usr = User.findById(id).get();

        switch (mode){
            case "edit-mail":
                if (!email.isEmpty() && !email.isBlank()){
                    usr.setEmail(email);
                    User.save(usr);
                    model.addAttribute("info", "Email mis a jour");
                } else {model.addAttribute("info", "email vide");}
                break;
            case "edit-name":
                if (!name.isEmpty()    && !name.isBlank()   ) usr.setName(name);
                if (!surname.isEmpty() && !surname.isBlank()) usr.setSurname(surname);
                User.save(usr);
                model.addAttribute("info", "Compte mis a jour");
                break;
            case "edit-password":
                password = hashPassword(password);
                if (usr.getPassword().equals(password)){
                    if (passchg.equals(passcnf) && !passcnf.isEmpty() && !passcnf.isBlank()) {
                        passchg = hashPassword(passchg);
                        usr.setPassword(passchg);
                        User.save(usr);
                        model.addAttribute("info", "Mot de passe mis a jour");
                    } else {
                        model.addAttribute("info", "Le mot de passe sur les 2 champs ne sont pas pareil !");
                    }
                } else {}
                break;
            case "delete":
                if (passchg.equals("SUPPRIMER")) {
                    if (usr.getPassword().equals(hashPassword(password))){
                        User.deleteById(usr.getId());
                        disconnect(session, "Compte supprime");
                        return "index";
                    } else {
                        model.addAttribute("info", "Mot de passe incorrect. Veuillez ressayer");
                    }
                } else {
                    model.addAttribute("info", "Suppression annule");
                }
                break;
            default:
        }

        model.addAttribute("name", usr.getName());
        model.addAttribute("surname", usr.getSurname());
        model.addAttribute("email", usr.getEmail());
        return "account";
    }
}
