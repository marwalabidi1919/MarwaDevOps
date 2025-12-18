package org.example.tpcafe_marwalabidi.services;

import org.example.tpcafe_marwalabidi.dto.AdresseResponse;
import org.example.tpcafe_marwalabidi.entities.Adresse;

import java.util.List;

public interface IAdresseService {
    Adresse addAdresse(Adresse a);

    List<Adresse> saveAdresses(List<Adresse> adresses);

    Adresse selectAdresseById(long id);

    List<Adresse> selectAllAdresses();

    void deleteAdresse(Adresse a);

    void deleteAllAdresses();

    void deleteAdresseById(long id);

    long countingAdresses();

    boolean verifAdresseById(long id);

    Adresse selectAdresseByIdWithOrElse(Long id);

    AdresseResponse recupererAdresseParId(long id);
}