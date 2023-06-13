package com.LabelVie.GestionStockLbv.Service;
import com.LabelVie.GestionStockLbv.Entity.Livraison;
import com.LabelVie.GestionStockLbv.Repository.LivraisonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivraisonService {

    private final LivraisonRepository livraisonRepository;

    @Autowired
    public LivraisonService(LivraisonRepository livraisonRepository) {
        this.livraisonRepository = livraisonRepository;
    }

    public List<Livraison> getAllLivraisons() {
        return livraisonRepository.findAll();
    }

    public Livraison getLivraisonById(int id) {
        return livraisonRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid livraison ID: " + id));
    }

    public Livraison createLivraison(Livraison livraison) {
        return livraisonRepository.save(livraison);
    }

    public Livraison updateLivraison(int id, Livraison livraison) {
        livraison.setId(id);
        return livraisonRepository.save(livraison);
    }

    public void deleteLivraison(int id) {
        livraisonRepository.deleteById(id);
    }
}
