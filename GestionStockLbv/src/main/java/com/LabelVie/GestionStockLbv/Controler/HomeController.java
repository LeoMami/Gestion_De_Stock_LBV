package com.LabelVie.GestionStockLbv.Controler;

import com.LabelVie.GestionStockLbv.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @GetMapping("/signin")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String home() {
        return "home"; // Nom de la vue (template) à afficher pour la page d'accueil
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }
}