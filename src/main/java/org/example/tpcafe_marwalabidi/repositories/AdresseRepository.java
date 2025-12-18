package org.example.tpcafe_marwalabidi.repositories;

import org.example.tpcafe_marwalabidi.entities.Adresse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdresseRepository extends JpaRepository<Adresse, Long> {

    List<Adresse> findByVille(String ville);

    List<Adresse> findByCodePostal(int codePostal);

    long countByVille(String ville);

    long deleteByVille(String ville);

    List<Adresse> findByVilleAndCodePostal(String ville, int codePostal);

    List<Adresse> findByRueContainingIgnoreCaseAndVilleIgnoreCase(String motRue, String ville);

    List<Adresse> findByVilleIn(List<String> villes);

    List<Adresse> findByCodePostalBetween(int codePostalMin, int codePostalMax);

    List<Adresse> findByCodePostalGreaterThan(int codePostal);

    List<Adresse> findByCodePostalGreaterThanEqual(int codePostal);

    List<Adresse> findByCodePostalLessThan(int codePostal);

    List<Adresse> findByCodePostalLessThanEqual(int codePostal);

    List<Adresse> findByRueStartingWithAndVilleOrderByCodePostalAsc(String debutRue, String ville);

    List<Adresse> findByRueStartingWith(String debutRue);

    List<Adresse> findByVilleEndingWith(String suffixeVille);

    List<Adresse> findByRueIsNull();

    List<Adresse> findByVilleIsNotNull();
}

