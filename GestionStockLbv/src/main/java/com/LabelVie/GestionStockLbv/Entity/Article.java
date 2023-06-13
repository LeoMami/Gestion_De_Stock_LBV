package com.LabelVie.GestionStockLbv.Entity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String designation;

    private double prix;

    private int stock;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateCreation;

    private Long codeEan;

    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;

    @ManyToOne
    @JoinColumn(name = "fournisseur_id")
    private Fournisseur fournisseur;

    //Constructeurs
    public Article() {
    }

    public Article(String designation, double prix, int stock,LocalDate dateCreation,Long codeEan, Categorie categorie, Fournisseur fournisseur) {
        this.designation = designation;
        this.prix = prix;
        this.stock = stock;
        this.dateCreation = dateCreation;
        this.codeEan = codeEan;
        this.categorie = categorie;
        this.fournisseur = fournisseur;

    }
    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Long getCodeEan() {
        return codeEan;
    }

    public void setCodeEan(Long codeEan) {
        this.codeEan = codeEan;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }
}
