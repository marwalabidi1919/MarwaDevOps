package org.example.tpcafe_marwalabidi.services;

import org.example.tpcafe_marwalabidi.dto.ClientRequest;
import org.example.tpcafe_marwalabidi.dto.ClientResponse;
import org.example.tpcafe_marwalabidi.entities.Client;

import java.util.List;
import java.time.LocalDate;

public interface IClientService {
    ClientResponse addClient(ClientRequest clientRequest);
    ClientResponse updateClient(long id ,ClientRequest clientRequest);

    ClientResponse selectClientById(long id);
    List<ClientResponse> selectAllClient();

    void deleteAllClient();
    void deleteClientById(long id);

    long countClient();
    boolean verifClientById(long id);

    String affecterAdresseAClient(String rue, long cin);

    void affecterCarteAClient(long idCarte, long idClient);

    void affecterCommandeAClient(long idCommande , long idClient);

    void affecterCommandeAClient(LocalDate dateCommande ,String nomClient, String prenomClient);

    void desaffecterClientDeCommande(long idCommande);

    Client ajouterClientEtCarteFidelite(Client client);
}
