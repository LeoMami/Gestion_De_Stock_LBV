package com.LabelVie.GestionStockLbv.Controler;

import com.LabelVie.GestionStockLbv.Entity.User;
import com.LabelVie.GestionStockLbv.Repository.UserRepository;
import com.LabelVie.GestionStockLbv.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class UserController {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private UserService userService;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void userDetails(@ModelAttribute Model model, Principal principal) {
        System.out.println("Je Suiiiiis Iciiii , Detail Utilisateur - UserController!! ");
        String email = principal.getName();
        System.out.println(email);
        User user = userService.findByEmail(email);
        System.out.println(user);
        model.addAttribute("user", user);
    }

    @RequestMapping(value = "/createUtilisateur", method = RequestMethod.POST)
    public String createUserString(@ModelAttribute User user, HttpSession session) {
        boolean f = userService.checkEmail(user.getEmail());
        if (f) {
            session.setAttribute("msg", "Attention, Email Existe Déja");
        } else if (user == null) {
            session.setAttribute("msg", "");
        } else {
            User user1 = userService.creatUser(user);
            if (user1 != null) {

                session.setAttribute("msg", "Bien Enregistré !");
            } else {
                session.setAttribute("msg", "Attention , Erreur !!!");
            }
        }
        return "redirect:/register";
    }
}

