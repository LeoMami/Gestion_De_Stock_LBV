package com.LabelVie.GestionStockLbv.Repository;
import com.LabelVie.GestionStockLbv.Entity.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FournisseurRepository extends JpaRepository<Fournisseur, Integer> {

}

