package org.example.tpcafe_marwalabidi.services;

import jakarta.persistence.EntityNotFoundException;
import org.example.tpcafe_marwalabidi.dto.AdresseResponse;
import org.example.tpcafe_marwalabidi.entities.Adresse;
import org.example.tpcafe_marwalabidi.mapper.AdresseMapper;
import org.example.tpcafe_marwalabidi.repositories.AdresseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdresseService implements IAdresseService {


    @Autowired
    private AdresseRepository adresseRepository;
    @Autowired
    private AdresseMapper adresseMapper;


    @Override
    public Adresse addAdresse(Adresse adresse) {
        return adresseRepository.save(adresse);
    }

    @Override
    public List<Adresse> saveAdresses(List<Adresse> adresses) {
        return adresseRepository.saveAll(adresses);
    }

    @Override
    public Adresse selectAdresseById(long id) {
        return adresseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Adresse not found with id: " + id));

        /*   second  methode  {
           return adresseRepository.findById(id).get()
        */


    }

    @Override
    public List<Adresse> selectAllAdresses() {
        return adresseRepository.findAll();
    }

    @Override
    public void deleteAdresse(Adresse adresse) {
        adresseRepository.delete(adresse);


    }

    @Override
    public void deleteAllAdresses() {
        adresseRepository.deleteAll();

    }

    @Override
    public void deleteAdresseById(long id) {
        adresseRepository.deleteById(id);
    }

    @Override
    public long countingAdresses() {
        return adresseRepository.count();
    }

    @Override
    public boolean verifAdresseById(long id) {
        return adresseRepository.existsById(id);
    }

    @Override
    public Adresse selectAdresseByIdWithOrElse(Long id) {
        return adresseRepository.findById(id).orElse(null);
    }

    @Override
    public AdresseResponse recupererAdresseParId(long id) {
        Adresse a = adresseRepository.findById(id).get();
        return adresseMapper.toDto(a);
    }
}