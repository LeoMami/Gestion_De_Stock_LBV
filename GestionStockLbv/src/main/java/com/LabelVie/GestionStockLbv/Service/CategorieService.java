package com.LabelVie.GestionStockLbv.Service;
import com.LabelVie.GestionStockLbv.Entity.Categorie;
import com.LabelVie.GestionStockLbv.Repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieService {

    private final CategorieRepository categorieRepository;

    @Autowired
    public CategorieService(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }

    public Categorie getCategorieById(int id) {
        return categorieRepository.findById(id).get();
    }

    public Categorie createCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    public Categorie updateCategorie(int id, Categorie categorie) {
        categorie.setId(id);
        return categorieRepository.save(categorie);
    }

    public void deleteCategorie(int id) {
        categorieRepository.deleteById(id);
    }
}
