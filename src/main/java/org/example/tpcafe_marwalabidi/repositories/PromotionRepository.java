package org.example.tpcafe_marwalabidi.repositories;

import org.example.tpcafe_marwalabidi.entities.Promotion;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    @Query(value = "SELECT * FROM promotion p WHERE p.pourcentage_promo = :pourcentage", nativeQuery = true)
    List<Promotion> findByPourcentagePromo(@Param("pourcentage") String pourcentagePromo);

    @Query(value = "SELECT * FROM promotion p WHERE p.date_debut_promo = :debut", nativeQuery = true)
    List<Promotion> findByDateDebutPromo(@Param("debut") LocalDate dateDebutPromo);

    @Query(value = "SELECT * FROM promotion p WHERE p.date_fin_promo = :fin", nativeQuery = true)
    List<Promotion> findByDateFinPromo(@Param("fin") LocalDate dateFinPromo);

    @Query(value = "SELECT CASE WHEN COUNT(*)>0 THEN true ELSE false END FROM promotion p WHERE p.pourcentage_promo = :pourcentage", nativeQuery = true)
    boolean existsByPourcentagePromo(@Param("pourcentage") String pourcentagePromo);

    @Query(value = "SELECT COUNT(*) FROM promotion p WHERE p.date_debut_promo > :debut", nativeQuery = true)
    long countByDateDebutPromoAfter(@Param("debut") LocalDate dateDebutPromo);

    @Query(value = "SELECT * FROM promotion p WHERE p.date_debut_promo < :debut AND p.date_fin_promo > :fin", nativeQuery = true)
    List<Promotion> findByDateDebutPromoBeforeAndDateFinPromoAfter(@Param("debut") LocalDate dateDebut, @Param("fin") LocalDate dateFin);

    @Query(value = "SELECT * FROM promotion p WHERE p.pourcentage_promo = :pourcentage AND p.date_debut_promo BETWEEN :start AND :end", nativeQuery = true)
    List<Promotion> findByPourcentagePromoAndDateDebutPromoBetween(@Param("pourcentage") String pourcentagePromo, @Param("start") LocalDate dateDebut, @Param("end") LocalDate dateFin);

    @Query(value = "SELECT * FROM promotion p WHERE p.date_debut_promo <= :debut AND p.date_fin_promo >= :fin", nativeQuery = true)
    List<Promotion> findByDateDebutPromoLessThanEqualAndDateFinPromoGreaterThanEqual(@Param("debut") LocalDate dateDebut, @Param("fin") LocalDate dateFin);

    @Query(value = "SELECT * FROM promotion p WHERE p.pourcentage_promo IN (:pourcentages) ORDER BY p.date_debut_promo ASC", nativeQuery = true)
    List<Promotion> findByPourcentagePromoInOrderByDateDebutPromoAsc(@Param("pourcentages") List<String> pourcentages);

    @Query(value = "SELECT * FROM promotion p WHERE p.date_debut_promo < :debut AND p.date_fin_promo > :fin ORDER BY p.pourcentage_promo ASC", nativeQuery = true)
    List<Promotion> findByDateDebutPromoBeforeAndDateFinPromoAfterOrderByPourcentagePromoAsc(@Param("debut") LocalDate dateDebut, @Param("fin") LocalDate dateFin);

    @Query(value = "SELECT * FROM promotion p WHERE p.date_fin_promo IS NULL", nativeQuery = true)
    List<Promotion> findByDateFinPromoIsNull();

    @Query(value = "SELECT * FROM promotion p WHERE p.pourcentage_promo IS NOT NULL", nativeQuery = true)
    List<Promotion> findByPourcentagePromoIsNotNull();

    @EntityGraph(attributePaths = "articles")
    @Query(value = "SELECT DISTINCT p.* FROM promotion p LEFT JOIN promotion_article pa ON pa.promotion_id = p.id_promotion LEFT JOIN article a ON pa.article_id = a.id_article", nativeQuery = true)
    List<Promotion> findAllWithArticles();

    @Query(value = "SELECT * FROM promotion p WHERE p.date_fin_promo < :fin", nativeQuery = true)
    List<Promotion> findByDateFinPromoBefore(@Param("fin") LocalDate dateFin);
}

