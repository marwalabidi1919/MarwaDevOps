package org.example.tpcafe_marwalabidi.repositories;

import org.example.tpcafe_marwalabidi.entities.Details_Commande;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DetailCommandeRepository extends JpaRepository<Details_Commande, Long> {

    List<Details_Commande> findByQuantiteArticle(Integer quantiteArticle);

    List<Details_Commande> findBySousTotalDetailArticle(Double sousTotalDetailArticle);

    long countByQuantiteArticleGreaterThan(Integer quantiteArticle);

    boolean existsBySousTotalDetailArticleGreaterThan(Double sousTotalDetailArticle);

    List<Details_Commande> findByQuantiteArticleBetweenAndSousTotalDetailArticleGreaterThanEqual(Integer quantiteMin, Integer quantiteMax, Double sousTotalMin);

    List<Details_Commande> findBySousTotalDetailArticleBetweenOrderByQuantiteArticleAsc(Double sousTotalMin, Double sousTotalMax);

    List<Details_Commande> findBySousTotalDetailArticleApresPromoBetween(Double sousTotalMin, Double sousTotalMax);

    List<Details_Commande> findByQuantiteArticleGreaterThanEqualOrSousTotalDetailArticleGreaterThanEqual(Integer quantiteArticle, Double sousTotalDetailArticle);

    List<Details_Commande> findTop5ByOrderBySousTotalDetailArticleDesc();

    List<Details_Commande> findByQuantiteArticleIsNull();

    List<Details_Commande> findBySousTotalDetailArticleApresPromoIsNotNull();

    @EntityGraph(attributePaths = {"commande", "article"})
    @Query("select distinct d from Details_Commande d left join fetch d.commande c left join fetch d.article a")
    List<Details_Commande> findAllWithCommandeAndArticle();
}

