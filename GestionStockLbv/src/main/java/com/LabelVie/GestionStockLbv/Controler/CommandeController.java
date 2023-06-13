package com.LabelVie.GestionStockLbv.Controler;

import com.LabelVie.GestionStockLbv.Entity.Client;
import com.LabelVie.GestionStockLbv.Entity.Commande;
import com.LabelVie.GestionStockLbv.Entity.Livraison;
import com.LabelVie.GestionStockLbv.Service.ClientService;
import com.LabelVie.GestionStockLbv.Service.CommandeService;
import com.LabelVie.GestionStockLbv.Service.LivraisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CommandeController {
    @Autowired
    private final CommandeService commandeService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private LivraisonService livraisonService;

    @Autowired
    public CommandeController(CommandeService commandeService) {
        this.commandeService = commandeService;
    }
    /*@GetMapping("/commande")
    public String gotoCommande(){
        return "commande";
    }*/

    @GetMapping("commandeRegister")
    public String NouvelleCommande(Model model) {
        List<Client> clientList = clientService.getAllClients();
        model.addAttribute("clientList", clientList);
        model.addAttribute("Commande", new Commande());
        List<Livraison> livraisonList = livraisonService.getAllLivraisons();
        model.addAttribute("livraisonList", livraisonList);
        model.addAttribute("Commande", new Commande());
        return "commandeRegister";
    }

    @PostMapping("/saveCommande")
    public String addCommande(@ModelAttribute Commande b) {
        commandeService.createCommande(b);
        return "redirect:/commande";
    }

    @GetMapping("/commande")
    public ModelAndView getAllCommande() {
        List<Commande> list = commandeService.getAllCommandes();
        return new ModelAndView("commande", "Commande", list);
    }

    // Endpoint pour mettre à jour un article
    @GetMapping("/commandeEdit/{id}")
    public String commandeEdit(@PathVariable(value = "id") int id, Model model) {
        System.out.println("Je suis dans la page update");
        List<Client> clientList = clientService.getAllClients();
        model.addAttribute("clientList", clientList);
        model.addAttribute("Commande", new Commande());
        List<Livraison> livraisonList = livraisonService.getAllLivraisons();
        model.addAttribute("livraisonList", livraisonList);
        model.addAttribute("Commande", new Commande());
        Commande b = commandeService.getCommandeById(id);
        model.addAttribute("Commande", b);
        return "commandeEdit";
    }

    @PostMapping("/commandeEd/{id}/update")
    public String updateCommande(@PathVariable("id") int id, @ModelAttribute("commande") Commande updatedCommande) {
        // 1. Récupérer l'article original depuis la base de données
        Commande existingCommande = commandeService.getCommandeById(id);
        // 2. Mettre à jour les attributs de la commande existant avec les valeurs de l'article modifié
        existingCommande.setListArticles(updatedCommande.getListArticles());
        existingCommande.setDate(updatedCommande.getDate());
        existingCommande.setClient(updatedCommande.getClient());
        existingCommande.setLivraison(updatedCommande.getLivraison());

        // 3. Mettre à jour l'article dans la base de données
        commandeService.createCommande(existingCommande);

        // 4. Rediriger vers la page d'affichage de l'article modifié ou vers une autre page appropriée
        return "redirect:/commande";
    }

    @RequestMapping("/commandeDelete/{id}")
    public String deleteCommande(@PathVariable("id") int id) {
        commandeService.deleteCommande(id);
        return "redirect:/commande";
    }
}
