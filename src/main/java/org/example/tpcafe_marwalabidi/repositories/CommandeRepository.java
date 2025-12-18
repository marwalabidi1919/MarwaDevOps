package org.example.tpcafe_marwalabidi.repositories;

import org.example.tpcafe_marwalabidi.entities.Commande;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CommandeRepository extends JpaRepository<Commande, Long> {

    @Query(value = "SELECT * FROM Commande c WHERE c.status_commande = :status", nativeQuery = true)
    List<Commande> findByStatusCommande(@Param("status") String statusCommande);

    @Query(value = "SELECT * FROM Commande c WHERE c.date_commande = :dateCommande", nativeQuery = true)
    List<Commande> findByDateCommande(@Param("dateCommande") LocalDate dateCommande);

    @Query(value = "SELECT COUNT(*) FROM Commande c WHERE c.status_commande = :status", nativeQuery = true)
    long countByStatusCommande(@Param("status") String statusCommande);

    @Query(value = "DELETE FROM Commande WHERE date_commande < :dateLimite", nativeQuery = true)
    long deleteByDateCommandeBefore(@Param("dateLimite") LocalDate dateCommande);

    @Query(value = "SELECT * FROM Commande c WHERE c.date_commande BETWEEN :start AND :end AND c.status_commande = :status", nativeQuery = true)
    List<Commande> findByDateCommandeBetweenAndStatusCommande(@Param("start") LocalDate dateDebut, @Param("end") LocalDate dateFin, @Param("status") String statusCommande);

    @Query(value = "SELECT * FROM Commande c WHERE c.total_commande > :total AND c.status_commande <> :status", nativeQuery = true)
    List<Commande> findByTotalCommandeGreaterThanAndStatusCommandeNot(@Param("total") Double totalCommande, @Param("status") String statusCommande);

    @Query(value = "SELECT * FROM Commande c WHERE c.status_commande IN (:statuses) ORDER BY c.date_commande ASC", nativeQuery = true)
    List<Commande> findByStatusCommandeInOrderByDateCommandeAsc(@Param("statuses") List<String> statusCommandes);

    @Query(value = "SELECT * FROM Commande c WHERE c.date_commande < :dateLimite AND c.total_commande BETWEEN :min AND :max", nativeQuery = true)
    List<Commande> findByDateCommandeBeforeAndTotalCommandeBetween(@Param("dateLimite") LocalDate dateLimite, @Param("min") Double totalMin, @Param("max") Double totalMax);

    @Query(value = "SELECT * FROM Commande c WHERE c.status_commande LIKE %:suffixe", nativeQuery = true)
    List<Commande> findByStatusCommandeEndingWith(@Param("suffixe") String suffixeStatus);

    @Query(value = "SELECT * FROM Commande c WHERE c.status_commande IS NULL", nativeQuery = true)
    List<Commande> findByStatusCommandeIsNull();

    @Query(value = "SELECT * FROM Commande c WHERE c.total_commande IS NOT NULL", nativeQuery = true)
    List<Commande> findByTotalCommandeIsNotNull();

    @EntityGraph(attributePaths = {"client", "detailsCommande", "detailsCommande.article"})
    @Query(value = "SELECT distinct c.* FROM Commande c LEFT JOIN Client cl ON c.client_id = cl.id_client LEFT JOIN detail_commande dc ON dc.commande_id = c.id_commande LEFT JOIN article a ON a.id_article = dc.article_id", nativeQuery = true)
    List<Commande> findAllWithClientAndDetails();

    @Query(value = "SELECT * FROM Commande c ORDER BY c.date_commande DESC LIMIT 3", nativeQuery = true)
    List<Commande> findTop3ByOrderByDateCommandeDesc();
}

