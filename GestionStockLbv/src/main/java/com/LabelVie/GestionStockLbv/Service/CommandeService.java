package com.LabelVie.GestionStockLbv.Service;
import com.LabelVie.GestionStockLbv.Entity.Commande;
import com.LabelVie.GestionStockLbv.Repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandeService {

    private final CommandeRepository commandeRepository;

    @Autowired
    public CommandeService(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }

    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }

    public Commande getCommandeById(int id) {
        return commandeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid commande ID: " + id));
    }

    public Commande createCommande(Commande commande) {
        return commandeRepository.save(commande);
    }

    public Commande updateCommande(int id, Commande commande) {
        commande.setId(id);
        return commandeRepository.save(commande);
    }

    public void deleteCommande(int id) {
        commandeRepository.deleteById(id);
    }
}

