package org.example.tpcafe_marwalabidi.services;

import lombok.AllArgsConstructor;
import org.example.tpcafe_marwalabidi.entities.Client;
import org.example.tpcafe_marwalabidi.entities.Commande;
import org.example.tpcafe_marwalabidi.repositories.ClientRepository;
import org.example.tpcafe_marwalabidi.repositories.CommandeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDate;

@Service
@AllArgsConstructor
public class CommandeService implements ICommandeService {

    private final CommandeRepository commandeRepository;
    private final ClientRepository clientRepository;
    @Override
    public Commande addCommande(Commande c) {
        return commandeRepository.save(c);
    }

    @Override
    public List<Commande> saveCommandes(List<Commande> commandes) {
        return commandeRepository.saveAll(commandes);
    }

    @Override
    public Commande selectCommandeById(long id) {
        return commandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande not found with id: " + id));
    }

    @Override
    public List<Commande> selectAllCommandes() {
        return commandeRepository.findAll();
    }

    @Override
    public void deleteCommande(Commande c) {
        commandeRepository.delete(c);
    }

    @Override
    public void deleteAllCommandes() {
        commandeRepository.deleteAll();
    }

    @Override
    public void deleteCommandeById(long id) {
        commandeRepository.deleteById(id);
    }

    @Override
    public long countingCommandes() {
        return commandeRepository.count();
    }

    @Override
    public boolean verifCommandeById(long id) {
        return commandeRepository.existsById(id);
    }

    @Override
    public void affecterCommandeAClient(long idCommande, long idClient) {
        Commande commande = selectCommandeById(idCommande);
        Client client = clientRepository.findById(idClient)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + idClient));
        commande.setClient(client);
        commandeRepository.save(commande);
    }

    @Override
    public void affecterCommandeAClient(LocalDate dateCommande, String nomClient, String prenomClient) {
        Client client = clientRepository.findByNomAndPrenom(nomClient, prenomClient)
                .orElseThrow(() -> new RuntimeException("Client not found: " + nomClient + " " + prenomClient));
        Commande commande = commandeRepository.findByDateCommande(dateCommande)
                .stream().findFirst()
                .orElseThrow(() -> new RuntimeException("Commande not found for date: " + dateCommande));
        commande.setClient(client);
        commandeRepository.save(commande);
    }

    @Override
    public void desaffecterClientDeCommande(long idCommande) {
        Commande commande = selectCommandeById(idCommande);
        commande.setClient(null);
        commandeRepository.save(commande);
    }
}


