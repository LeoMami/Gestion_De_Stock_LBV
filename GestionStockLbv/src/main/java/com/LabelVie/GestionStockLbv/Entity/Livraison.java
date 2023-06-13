package com.LabelVie.GestionStockLbv.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Livraison {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String statut;

    public List<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(List<Commande> commandes) {
        this.commandes = commandes;
    }

    @OneToMany(mappedBy = "livraison")
    private List<Commande> commandes;
    //constructeurs
    public Livraison() {
    }

    public Livraison(String statut) {
        this.statut = statut;
    }
    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}
