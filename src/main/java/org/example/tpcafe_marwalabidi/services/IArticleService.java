package org.example.tpcafe_marwalabidi.services;

import org.example.tpcafe_marwalabidi.entities.Article;

import java.util.List;

public interface IArticleService {
    Article addArticle(Article a);

    List<Article> saveArticles(List<Article> articles);

    Article selectArticleById(long id);

    List<Article> selectAllArticles();

    void deleteArticle(Article a);

    void deleteAllArticles();

    void deleteArticleById(long id);

    long countingArticles();

    boolean verifArticleById(long id);

    Article ajouterArticleEtPromotions(Article article);
}

