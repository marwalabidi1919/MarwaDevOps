package org.example.tpcafe_marwalabidi.repositories;

import org.example.tpcafe_marwalabidi.entities.Client;
import org.example.tpcafe_marwalabidi.entities.TypeArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findByNom(String nom);

    List<Client> findByPrenom(String prenom);

    Optional<Client> findByNomAndPrenom(String nom, String prenom);

    boolean existsByNom(String nom);

    long countByDateNaissanceAfter(LocalDate dateNaissance);

    List<Client> findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCase(String nom, String prenom);

    List<Client> findByNomContainingIgnoreCaseAndPrenomContainingIgnoreCase(String nom, String prenom);

    List<Client> findByDateNaissanceBetween(LocalDate dateDebut, LocalDate dateFin);

    List<Client> findByNomStartingWithIgnoreCaseAndDateNaissanceBefore(String prefixNom, LocalDate dateLimite);

    List<Client> findByAdresse_Ville(String ville);

    List<Client> findByNomContainingIgnoreCaseOrderByPrenomAsc(String chaine);

    List<Client> findByNomContainingIgnoreCaseOrderByPrenomDesc(String chaine);

    List<Client> findByNomStartingWithIgnoreCase(String prefixNom);

    List<Client> findByPrenomEndingWithIgnoreCase(String suffixePrenom);

    List<Client> findByDateNaissanceIsNull();

    List<Client> findByAdresseIsNotNull();

    List<Client> findByAdresse_VilleIn(List<String> villes);

    List<Client> findByCarteFidelite_PointsAccumulesGreaterThan(Integer points);

    List<Client> findByCarteFidelite_PointsAccumulesGreaterThanEqual(Integer points);

    List<Client> findByCarteFidelite_PointsAccumulesBetween(Integer minPoints, Integer maxPoints);

    List<Client> findDistinctByCommandes_DetailsCommande_Article_NomArticleIgnoreCase(String nomArticle);

    List<Client> findDistinctByNomContainingIgnoreCaseAndCommandes_DetailsCommande_Article_TypeArticle(String nom, TypeArticle typeArticle);

    @Query("select c from Client c where c.dateNaissance is not null and month(c.dateNaissance) = month(:today) and day(c.dateNaissance) = day(:today)")
    List<Client> findClientsBirthday(@Param("today") LocalDate today);
}

