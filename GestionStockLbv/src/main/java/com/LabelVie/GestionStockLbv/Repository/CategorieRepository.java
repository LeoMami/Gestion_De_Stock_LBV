package com.LabelVie.GestionStockLbv.Repository;
import com.LabelVie.GestionStockLbv.Entity.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Integer> {

}
