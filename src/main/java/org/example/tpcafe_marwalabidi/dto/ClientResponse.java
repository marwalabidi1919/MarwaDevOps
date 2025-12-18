package org.example.tpcafe_marwalabidi.dto;

import lombok.*;


import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientResponse {
    private long idClient;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;

    private CarteFideliteResponse carte;
    private AdresseResponse adresse;
    private List<CommandeResponse> commandes;
}
