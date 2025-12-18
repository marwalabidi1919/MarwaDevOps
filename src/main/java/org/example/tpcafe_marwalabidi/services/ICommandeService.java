package org.example.tpcafe_marwalabidi.services;

import org.example.tpcafe_marwalabidi.entities.Commande;

import java.time.LocalDate;
import java.util.List;

public interface ICommandeService {

    List<Commande> saveCommandes(List<Commande> commandes);

    Commande addCommande(Commande c);

    Commande selectCommandeById(long id);

    List<Commande> selectAllCommandes();

    void deleteCommande(Commande c);

    void deleteAllCommandes();

    void deleteCommandeById(long id);

    long countingCommandes();

    boolean verifCommandeById(long id);

    void affecterCommandeAClient(long idCommande , long idClient);

    void affecterCommandeAClient(LocalDate dateCommande ,String nomClient, String prenomClient);

    void desaffecterClientDeCommande(long idCommande);
}