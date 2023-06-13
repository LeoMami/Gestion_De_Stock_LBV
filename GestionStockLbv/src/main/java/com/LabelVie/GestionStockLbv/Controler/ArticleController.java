package com.LabelVie.GestionStockLbv.Controler;

import com.LabelVie.GestionStockLbv.Entity.Article;
import com.LabelVie.GestionStockLbv.Entity.Categorie;
import com.LabelVie.GestionStockLbv.Entity.Fournisseur;
import com.LabelVie.GestionStockLbv.Service.ArticleService;
import com.LabelVie.GestionStockLbv.Service.CategorieService;
import com.LabelVie.GestionStockLbv.Service.FournisseurService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.List;

@Controller
public class ArticleController {

    @Autowired
    private final ArticleService articleService;
    @Autowired
    private CategorieService categorieService;
    @Autowired
    private FournisseurService fournisseurService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    // Endpoint pour récupérer et afficher tous les articles
    @GetMapping("/articleList")
    public ModelAndView getAllArticleList(@RequestParam(name = "designation", required = false) String designation, @RequestParam(name = "dateCreation", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateCreation) {
        List<Article> list = null;
        if (dateCreation == null && (designation == null || designation == "")) {
            list = articleService.getAllArticles();
        } else if (dateCreation != null) {
            list = articleService.searchArticlesByDateCreation(dateCreation);
        } else if (designation != null || designation != "") {
            list = articleService.searchArticlesByDesignation(designation);
        }
        return new ModelAndView("articleList", "Article", list);
    }

    @GetMapping("/exportArticles")
    public void exportArticleList(@RequestParam(name = "designation", required = false) String designation, @RequestParam(name = "dateCreation", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateCreation, HttpServletResponse response ) {
        ModelAndView modelAndView = getAllArticleList(designation, dateCreation);
        List<Article> list = (List<Article>) modelAndView.getModel().get("Article");
        // Exporter la liste des articles en fichier Excel
        XSSFWorkbook workbook = createExcelWorkbook(list);
        // Définir les en-têtes de la réponse HTTP
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=articles.xlsx");
        // Écrire le contenu du fichier Excel dans le flux de sortie de la réponse
        try (OutputStream outputStream = response.getOutputStream()) {
            workbook.write(outputStream);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public XSSFWorkbook createExcelWorkbook(List<Article> articles) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Articles");

        // Créez les en-têtes de colonne
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Désignation");
        headerRow.createCell(1).setCellValue("Prix Unitaire");
        headerRow.createCell(2).setCellValue("Quantité");
        headerRow.createCell(3).setCellValue("Date Creation");
        headerRow.createCell(4).setCellValue("Code EAN");
        headerRow.createCell(5).setCellValue("Catégorie");
        headerRow.createCell(6).setCellValue("Fournisseur");
        // Ajoutez d'autres en-têtes de colonne selon vos besoins

        // Remplissez les données des articles
        int rowNum = 1;
        for (Article article : articles) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(article.getDesignation());
            row.createCell(1).setCellValue(article.getPrix());
            row.createCell(2).setCellValue(article.getStock());
            row.createCell(3).setCellValue(article.getDateCreation().toString());
            row.createCell(4).setCellValue(article.getCodeEan().toString());
            row.createCell(5).setCellValue(article.getCategorie().getNom());
            row.createCell(6).setCellValue(article.getFournisseur().getNom());
        }

        return workbook;
    }

    //Afficher la page pour Ajouter un Article
    @GetMapping("articleRegister")
    public String NouveauArticle(Model model) {
        List<Categorie> categorieList = categorieService.getAllCategories();
        model.addAttribute("categorieList", categorieList);
        model.addAttribute("Article", new Article());
        List<Fournisseur> fournisseurList = fournisseurService.getAllFournisseurs();
        model.addAttribute("fournisseurList", fournisseurList);
        model.addAttribute("Article", new Article());
        return "articleRegister";
    }

    // Endpoint pour créer un nouvel article
    @PostMapping("/save")
    public String addArticle(@ModelAttribute Article b) {
        articleService.createArticle(b);
        return "redirect:/articleList";
    }

    // Endpoint pour mettre à jour un article
    @GetMapping("/edit/{id}")
    public String articleEdit(@PathVariable(value = "id") int id, Model model) {

        List<Categorie> categorieList = categorieService.getAllCategories();
        model.addAttribute("categorieList", categorieList);
        model.addAttribute("Article", new Article());
        List<Fournisseur> fournisseurList = fournisseurService.getAllFournisseurs();
        model.addAttribute("fournisseurList", fournisseurList);
        model.addAttribute("Article", new Article());
        Article b = articleService.getArticleById(id);
        model.addAttribute("Article", b);
        return "articleEdit";
    }

    @PostMapping("/articles/{id}/update")
    public String updateArticle(@PathVariable("id") int id, @ModelAttribute("article") Article updatedArticle) {
        // 1. Récupérer l'article original depuis la base de données
        Article existingArticle = articleService.getArticleById(id);
        // 2. Mettre à jour les attributs de l'article existant avec les valeurs de l'article modifié
        existingArticle.setDesignation(updatedArticle.getDesignation());
        existingArticle.setPrix(updatedArticle.getPrix());
        existingArticle.setStock(updatedArticle.getStock());
        existingArticle.setDateCreation(updatedArticle.getDateCreation());
        existingArticle.setCodeEan(updatedArticle.getCodeEan());
        existingArticle.setCategorie(updatedArticle.getCategorie());
        existingArticle.setFournisseur(updatedArticle.getFournisseur());

        // 3. Mettre à jour l'article dans la base de données
        articleService.createArticle(existingArticle);

        // 4. Rediriger vers la page d'affichage de l'article modifié ou vers une autre page appropriée
        return "redirect:/articleList";
    }

    // Endpoint pour supprimer un article
    @RequestMapping("/delete/{id}")
    public String deleteArticle(@PathVariable("id") int id) {
        articleService.deleteArticle(id);
        return "redirect:/articleList";
    }
}

