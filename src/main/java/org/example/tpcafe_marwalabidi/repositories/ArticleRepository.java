package org.example.tpcafe_marwalabidi.repositories;

import org.example.tpcafe_marwalabidi.entities.Article;
import org.example.tpcafe_marwalabidi.entities.TypeArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("select a from Article a where a.nomArticle = :nom")
    List<Article> findByNomArticle(@Param("nom") String nomArticle);

    @Query("select a from Article a where a.typeArticle = :type")
    List<Article> findByTypeArticle(@Param("type") TypeArticle typeArticle);

    @Query("select a from Article a where a.prixArticle = :prix")
    List<Article> findByPrixArticle(@Param("prix") Double prixArticle);

    @Query("select case when count(a)>0 then true else false end from Article a where a.nomArticle = :nom")
    boolean existsByNomArticle(@Param("nom") String nomArticle);

    @Query("select count(a) from Article a where a.typeArticle = :type")
    long countByTypeArticle(@Param("type") TypeArticle typeArticle);

    @Query("select a from Article a where lower(a.nomArticle) like lower(concat('%',:motCle,'%')) and a.typeArticle = :type")
    List<Article> findByNomArticleContainingIgnoreCaseAndTypeArticle(@Param("motCle") String motCle, @Param("type") TypeArticle typeArticle);

    @Query("select a from Article a where a.prixArticle between :min and :max and a.typeArticle in :types")
    List<Article> findByPrixArticleBetweenAndTypeArticleIn(@Param("min") Double prixMin, @Param("max") Double prixMax, @Param("types") List<TypeArticle> types);

    @Query("select a from Article a where lower(a.nomArticle) like lower(concat(:prefix,'%')) order by a.prixArticle asc")
    List<Article> findByNomArticleStartingWithIgnoreCaseOrderByPrixArticleAsc(@Param("prefix") String prefixNom);

    @Query("select a from Article a where a.typeArticle = :type order by a.prixArticle desc limit 1")
    Article findTopByTypeArticleOrderByPrixArticleDesc(@Param("type") TypeArticle typeArticle);

    @Query("select a from Article a where a.nomArticle = :nom or a.typeArticle = :type order by a.prixArticle desc")
    List<Article> findByNomArticleOrTypeArticleOrderByPrixArticleDesc(@Param("nom") String nomArticle, @Param("type") TypeArticle typeArticle);

    @Query("select a from Article a where a.nomArticle like concat(:prefix,'%')")
    List<Article> findByNomArticleStartingWith(@Param("prefix") String prefixNom);

    @Query("select a from Article a where a.nomArticle like concat('%',:suffixe)")
    List<Article> findByNomArticleEndingWith(@Param("suffixe") String suffixeNom);

    @Query("select a from Article a where a.typeArticle is null")
    List<Article> findByTypeArticleIsNull();

    @Query("select a from Article a where a.prixArticle is not null")
    List<Article> findByPrixArticleIsNotNull();

    @Query("select distinct a from Article a join a.promotions p where p.dateDebutPromo < :debut and p.dateFinPromo > :fin")
    List<Article> findDistinctByPromotions_DateDebutPromoBeforeAndPromotions_DateFinPromoAfter(@Param("debut") LocalDate dateDebut, @Param("fin") LocalDate dateFin);

    @Query("select a from Article a where lower(a.nomArticle) like lower(concat('%',:chaine,'%')) and a.prixArticle between :min and :max")
    List<Article> findByNomArticleContainingIgnoreCaseAndPrixArticleBetween(@Param("chaine") String chaineNom, @Param("min") Double prixMin, @Param("max") Double prixMax);
}

