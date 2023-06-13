package com.LabelVie.GestionStockLbv.Entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String listArticles;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "idClient")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "idLivraison")
    private Livraison livraison;
    //Constructeurs
    public Commande() {
    }

    public Commande(LocalDate date, String listArticles, Client client, Livraison livraison) {
        this.date = date;
        this.listArticles = listArticles;
        this.client = client;
        this.livraison = livraison;
    }
    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getListArticles() {
        return listArticles;
    }

    public void setListArticles(String listArticles) {
        this.listArticles = listArticles;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Livraison getLivraison() {
        return livraison;
    }

    public void setLivraison(Livraison livraison) {
        this.livraison = livraison;
    }
}
