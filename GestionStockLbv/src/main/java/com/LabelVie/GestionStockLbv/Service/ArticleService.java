package com.LabelVie.GestionStockLbv.Service;
import com.LabelVie.GestionStockLbv.Entity.Article;
import com.LabelVie.GestionStockLbv.Repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public Article getArticleById(int id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid article ID: " + id));
    }

    public Article createArticle(Article article) {
        return articleRepository.save(article);
    }

    public Article updateArticle(int id, Article article) {
        article.setId(id);
        return articleRepository.save(article);
    }

    public void deleteArticle(int id) {
        articleRepository.deleteById(id);
    }

    public List<Article> searchArticlesByDesignation(String designation) {
        return articleRepository.findByDesignation(designation);
    }
    public List<Article> searchArticlesByDateCreation(LocalDate date) {
        return articleRepository.findByDateCreation(date);
    }
}

