package org.example.tpcafe_marwalabidi.services;

import org.example.tpcafe_marwalabidi.entities.Details_Commande;

import java.util.List;

public interface IDetails_CommandeService {
    Details_Commande addDetailCommande(Details_Commande d);

    List<Details_Commande> saveDetailsCommande(List<Details_Commande> details);

    Details_Commande selectDetailCommandeById(long id);

    List<Details_Commande> selectAllDetailsCommande();

    void deleteDetailCommande(Details_Commande d);

    void deleteAllDetailsCommande();

    void deleteDetailCommandeById(long id);

    long countingDetailsCommande();

    boolean verifDetailCommandeById(long id);
}
