package org.example.tpcafe_marwalabidi.services;

import lombok.AllArgsConstructor;
import org.example.tpcafe_marwalabidi.entities.Article;
import org.example.tpcafe_marwalabidi.repositories.ArticleRepository;
import org.example.tpcafe_marwalabidi.repositories.PromotionRepository;
import org.example.tpcafe_marwalabidi.entities.Promotion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ArticleService implements IArticleService {

    private final ArticleRepository articleRepository;
    private final PromotionRepository promotionRepository;

    @Override
    public Article addArticle(Article a) {
        return articleRepository.save(a);
    }

    @Override
    public List<Article> saveArticles(List<Article> articles) {
        return articleRepository.saveAll(articles);
    }

    @Override
    public Article selectArticleById(long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found with id: " + id));
    }

    @Override
    public List<Article> selectAllArticles() {
        return articleRepository.findAll();
    }

    @Override
    public void deleteArticle(Article a) {
        articleRepository.delete(a);
    }

    @Override
    public void deleteAllArticles() {
        articleRepository.deleteAll();
    }

    @Override
    public void deleteArticleById(long id) {
        articleRepository.deleteById(id);
    }

    @Override
    public long countingArticles() {
        return articleRepository.count();
    }

    @Override
    public boolean verifArticleById(long id) {
        return articleRepository.existsById(id);
    }

    @Override
    public Article ajouterArticleEtPromotions(Article article) {
        if (article.getPromotions() != null) {
            // Attacher l'article aux promotions existantes
            article.getPromotions().forEach(promo -> {
                Promotion managed = promotionRepository.findById(promo.getIdPromotion())
                        .orElseThrow(() -> new RuntimeException("Promotion introuvable : " + promo.getIdPromotion()));
                // lier côté promotion
                managed.getArticles().add(article);
            });
        }
        return articleRepository.save(article);
    }
}

