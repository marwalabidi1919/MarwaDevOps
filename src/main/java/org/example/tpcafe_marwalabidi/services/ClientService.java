package org.example.tpcafe_marwalabidi.services;


import lombok.AllArgsConstructor;
import org.example.tpcafe_marwalabidi.dto.ClientRequest;
import org.example.tpcafe_marwalabidi.dto.ClientResponse;
import org.example.tpcafe_marwalabidi.entities.Adresse;
import org.example.tpcafe_marwalabidi.entities.CarteFidelite;
import org.example.tpcafe_marwalabidi.entities.Client;
import org.example.tpcafe_marwalabidi.entities.Commande;
import org.example.tpcafe_marwalabidi.mapper.ClientMapper;
import org.example.tpcafe_marwalabidi.repositories.AdresseRepository;
import org.example.tpcafe_marwalabidi.repositories.CarteFideliteRepository;
import org.example.tpcafe_marwalabidi.repositories.ClientRepository;
import org.example.tpcafe_marwalabidi.repositories.CommandeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientService implements IClientService {

    ClientRepository clientRepository;
    ClientMapper clientMapper;

    CarteFideliteRepository carteFideliteRepository;
    AdresseRepository adresseRepository;
    CommandeRepository commandeRepository;

    @Override
    public ClientResponse addClient(ClientRequest clientRequest) {
        Client client = clientMapper.toEntity(clientRequest);

        // Lier la carte si un ID est fourni
        if (clientRequest.getCarteId() != null) {
            CarteFidelite carte = carteFideliteRepository.findById(clientRequest.getCarteId())
                    .orElseThrow(() -> new RuntimeException("Carte fidélité introuvable : " + clientRequest.getCarteId()));
            client.setCarteFidelite(carte);
        }

        // Lier l'adresse si un ID est fourni
        if (clientRequest.getAdresseId() != null) {
            Adresse adresse = adresseRepository.findById(clientRequest.getAdresseId())
                    .orElseThrow(() -> new RuntimeException("Adresse introuvable : " + clientRequest.getAdresseId()));
            client.setAdresse(adresse);
        }

        // Initialiser la liste des commandes
        client.setCommandes(new ArrayList<>());

        Client saved = clientRepository.save(client);
        return clientMapper.toDto(saved);
    }

    @Override
    public ClientResponse selectClientById(long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client introuvable : " + id));

        return clientMapper.toDto(client);
    }

    @Override
    public List<ClientResponse> selectAllClient() {
        return clientRepository.findAll()
                .stream()
                .map(clientMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClientResponse updateClient(long id, ClientRequest request) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client introuvable : " + id));

        client.setNom(request.getNom());
        client.setPrenom(request.getPrenom());
        client.setDateNaissance(request.getDateNaissance());
        Client updated = clientRepository.save(client);
        return clientMapper.toDto(updated);
    }

    @Override
    public void deleteAllClient() {
        clientRepository.deleteAll();
    }

    @Override
    public void deleteClientById(long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public long countClient() {
        return clientRepository.count();
    }

    @Override
    public boolean verifClientById(long id) {
        return clientRepository.existsById(id);
    }

    @Override
    public String affecterAdresseAClient(String rue, long cin) {
        Adresse adresse = adresseRepository.findByRueStartingWith(rue)
                .stream().findFirst()
                .orElseThrow(() -> new RuntimeException("Adresse introuvable pour la rue : " + rue));
        Client client = clientRepository.findById(cin)
                .orElseThrow(() -> new RuntimeException("Client introuvable : " + cin));
        client.setAdresse(adresse);
        adresse.setClient(client);
        clientRepository.save(client);
        return "Adresse affectée au client " + client.getNom() + " " + client.getPrenom();
    }

    @Override
    public void affecterCarteAClient(long idCarte, long idClient) {
        CarteFidelite carte = carteFideliteRepository.findById(idCarte)
                .orElseThrow(() -> new RuntimeException("Carte fidélité introuvable : " + idCarte));
        Client client = clientRepository.findById(idClient)
                .orElseThrow(() -> new RuntimeException("Client introuvable : " + idClient));
        carte.setClient(client);
        client.setCarteFidelite(carte);
        carteFideliteRepository.save(carte);
        clientRepository.save(client);
    }

    @Override
    public void affecterCommandeAClient(long idCommande, long idClient) {
        Commande commande = commandeRepository.findById(idCommande)
                .orElseThrow(() -> new RuntimeException("Commande introuvable : " + idCommande));
        Client client = clientRepository.findById(idClient)
                .orElseThrow(() -> new RuntimeException("Client introuvable : " + idClient));
        commande.setClient(client);
        commandeRepository.save(commande);
    }

    @Override
    public void affecterCommandeAClient(java.time.LocalDate dateCommande, String nomClient, String prenomClient) {
        Client client = clientRepository.findByNomAndPrenom(nomClient, prenomClient)
                .orElseThrow(() -> new RuntimeException("Client introuvable : " + nomClient + " " + prenomClient));
        Commande commande = commandeRepository.findByDateCommande(dateCommande)
                .stream().findFirst()
                .orElseThrow(() -> new RuntimeException("Commande introuvable pour la date : " + dateCommande));
        commande.setClient(client);
        commandeRepository.save(commande);
    }

    @Override
    public void desaffecterClientDeCommande(long idCommande) {
        Commande commande = commandeRepository.findById(idCommande)
                .orElseThrow(() -> new RuntimeException("Commande introuvable : " + idCommande));
        commande.setClient(null);
        commandeRepository.save(commande);
    }

    @Override
    public Client ajouterClientEtCarteFidelite(Client client) {
        CarteFidelite carte = client.getCarteFidelite();
        if (carte == null) {
            carte = CarteFidelite.builder().pointsAccumules(0).client(client).build();
            client.setCarteFidelite(carte);
        } else {
            carte.setClient(client);
        }
        carteFideliteRepository.save(carte);
        return clientRepository.save(client);
    }
}
