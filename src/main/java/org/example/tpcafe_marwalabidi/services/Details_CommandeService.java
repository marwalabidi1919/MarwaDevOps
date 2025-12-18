package org.example.tpcafe_marwalabidi.services;

import lombok.AllArgsConstructor;
import org.example.tpcafe_marwalabidi.entities.Details_Commande;
import org.example.tpcafe_marwalabidi.repositories.DetailCommandeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class Details_CommandeService implements IDetails_CommandeService{

    private final DetailCommandeRepository detailCommandeRepository;
    @Override
    public Details_Commande addDetailCommande(Details_Commande d) {
        return detailCommandeRepository.save(d);
    }

    @Override
    public List<Details_Commande> saveDetailsCommande(List<Details_Commande> details) {
        return detailCommandeRepository.saveAll(details);
    }

    @Override
    public Details_Commande selectDetailCommandeById(long id) {
        return detailCommandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Details_Commande not found with id: " + id));
    }

    @Override
    public List<Details_Commande> selectAllDetailsCommande() {
        return detailCommandeRepository.findAll();
    }

    @Override
    public void deleteDetailCommande(Details_Commande d) {
        detailCommandeRepository.delete(d);
    }

    @Override
    public void deleteAllDetailsCommande() {
        detailCommandeRepository.deleteAll();
    }

    @Override
    public void deleteDetailCommandeById(long id) {
        detailCommandeRepository.deleteById(id);
    }

    @Override
    public long countingDetailsCommande() {
        return detailCommandeRepository.count();
    }

    @Override
    public boolean verifDetailCommandeById(long id) {
        return detailCommandeRepository.existsById(id);
    }
}

