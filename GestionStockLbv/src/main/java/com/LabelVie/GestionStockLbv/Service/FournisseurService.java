package com.LabelVie.GestionStockLbv.Service;
import com.LabelVie.GestionStockLbv.Entity.Fournisseur;
import com.LabelVie.GestionStockLbv.Repository.FournisseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FournisseurService {

    private final FournisseurRepository fournisseurRepository;

    @Autowired
    public FournisseurService(FournisseurRepository fournisseurRepository) {
        this.fournisseurRepository = fournisseurRepository;
    }

    public List<Fournisseur> getAllFournisseurs() {
        return fournisseurRepository.findAll();
    }

    public Fournisseur getFournisseurById(int id) {
        return fournisseurRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid fournisseur ID: " + id));
    }

    public Fournisseur createFournisseur(Fournisseur fournisseur) {
        return fournisseurRepository.save(fournisseur);
    }

    public Fournisseur updateFournisseur(int id, Fournisseur fournisseur) {
        fournisseur.setId(id);
        return fournisseurRepository.save(fournisseur);
    }

    public void deleteFournisseur(int id) {
        fournisseurRepository.deleteById(id);
    }
}

