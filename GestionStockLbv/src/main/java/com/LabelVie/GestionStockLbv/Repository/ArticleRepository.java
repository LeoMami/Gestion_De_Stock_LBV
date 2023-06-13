package com.LabelVie.GestionStockLbv.Repository;
import com.LabelVie.GestionStockLbv.Entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
    @Query("SELECT a FROM Article a WHERE a.dateCreation = :dateCreation")
    List<Article> findByDateCreation(@Param("dateCreation") LocalDate dateCreation);
    @Query("SELECT a FROM Article a WHERE a.designation LIKE %:designation%")
    List<Article> findByDesignation(@Param("designation") String designation);
}

