package org.example.tpcafe_marwalabidi.restController;

import lombok.RequiredArgsConstructor;
import org.example.tpcafe_marwalabidi.entities.Article;
import org.example.tpcafe_marwalabidi.services.IArticleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleRestController {


    private final IArticleService articleService;

    // GET all
    @GetMapping
    public List<Article> getAllArticles() {
        return articleService.selectAllArticles();
    }

    // POST one
    @PostMapping
    public Article addArticle(@RequestBody Article article) {
        return articleService.addArticle(article);
    }

    // POST list
    @PostMapping("/addlist")
    public List<Article> addArticles(@RequestBody List<Article> articles) {
        return articleService.saveArticles(articles);
    }

    @PostMapping("/with-promotions")
    public Article addArticleWithPromotions(@RequestBody Article article) {
        return articleService.ajouterArticleEtPromotions(article);
    }

    // GET by id (path)
    @GetMapping("{id}")
    public Article getArticleById(@PathVariable long id) {
        return articleService.selectArticleById(id);
    }

    // GET by id (query)
    @GetMapping("/selectById")
    public Article getArticleByIdReq(@RequestParam long id) {
        return articleService.selectArticleById(id);
    }

    // DELETE by id
    @DeleteMapping("{id}")
    public void deleteArticleById(@PathVariable long id) {
        articleService.deleteArticleById(id);
    }

    // DELETE all
    @DeleteMapping("/all")
    public void deleteAllArticles() {
        articleService.deleteAllArticles();
    }

    // DELETE by body (optionnel)
    @DeleteMapping("/del")
    public void deleteArticle(@RequestBody Article article) {
        articleService.deleteArticle(article);
    }

    // COUNT
    @GetMapping("/count")
    public long countArticles() {
        return articleService.countingArticles();
    }

    // EXISTS
    @GetMapping("/exists/{id}")
    public boolean existsById(@PathVariable long id) {
        return articleService.verifArticleById(id);
    }

}
