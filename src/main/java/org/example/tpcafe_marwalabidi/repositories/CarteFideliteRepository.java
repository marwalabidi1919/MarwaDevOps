package org.example.tpcafe_marwalabidi.repositories;

import org.example.tpcafe_marwalabidi.entities.CarteFidelite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CarteFideliteRepository extends JpaRepository<CarteFidelite, Long> {

    @Query("select c from CarteFidelite c where c.pointsAccumules = :points")
    List<CarteFidelite> findByPointsAccumules(@Param("points") Integer pointsAccumules);

    @Query("select c from CarteFidelite c where c.dateCreation = :dateCreation")
    List<CarteFidelite> findByDateCreation(@Param("dateCreation") LocalDate dateCreation);

    @Query("select count(c) from CarteFidelite c where c.pointsAccumules > :points")
    long countByPointsAccumulesGreaterThan(@Param("points") Integer pointsAccumules);

    @Query("delete from CarteFidelite c where c.dateCreation < :dateLimite")
    long deleteByDateCreationBefore(@Param("dateLimite") LocalDate dateLimite);

    @Query("select c from CarteFidelite c where c.pointsAccumules between :min and :max and c.dateCreation > :afterDate")
    List<CarteFidelite> findByPointsAccumulesBetweenAndDateCreationAfter(@Param("min") Integer minPoints, @Param("max") Integer maxPoints, @Param("afterDate") LocalDate dateCreation);

    @Query("select c from CarteFidelite c where c.pointsAccumules >= :points order by c.dateCreation asc")
    List<CarteFidelite> findByPointsAccumulesGreaterThanEqualOrderByDateCreationAsc(@Param("points") Integer pointsAccumules);

    @Query("select c from CarteFidelite c where c.dateCreation between :start and :end")
    List<CarteFidelite> findByDateCreationBetween(@Param("start") LocalDate dateDebut, @Param("end") LocalDate dateFin);

    @Query("select c from CarteFidelite c where c.pointsAccumules <= :points or c.dateCreation < :dateLimite")
    List<CarteFidelite> findByPointsAccumulesLessThanEqualOrDateCreationBefore(@Param("points") Integer pointsAccumules, @Param("dateLimite") LocalDate dateLimite);

    @Query("select c from CarteFidelite c order by c.pointsAccumules desc limit 1")
    CarteFidelite findTopByOrderByPointsAccumulesDesc();

    @Query("select c from CarteFidelite c where c.dateCreation is null")
    List<CarteFidelite> findByDateCreationIsNull();

    @Query("select c from CarteFidelite c where c.pointsAccumules is not null")
    List<CarteFidelite> findByPointsAccumulesIsNotNull();

    @Query("select c from CarteFidelite c where c.client.nom = :nom and c.client.prenom = :prenom")
    List<CarteFidelite> findByClient_NomAndClient_Prenom(@Param("nom") String nomClient, @Param("prenom") String prenomClient);

    @Query("select c from CarteFidelite c order by c.pointsAccumules desc limit 5")
    List<CarteFidelite> findTop5ByOrderByPointsAccumulesDesc();
}

