package com.LabelVie.GestionStockLbv.Repository;
import com.LabelVie.GestionStockLbv.Entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

}

