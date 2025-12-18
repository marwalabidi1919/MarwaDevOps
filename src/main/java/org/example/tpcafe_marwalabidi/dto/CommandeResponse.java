package org.example.tpcafe_marwalabidi.dto;

import lombok.*;
import org.example.tpcafe_marwalabidi.entities.StatusCommande;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommandeResponse {
    private long idCommande;
    private LocalDate dateCommande;
    private float totalCommande;
    private StatusCommande statusCommande;

    private ClientResponse client;
    private List<Details_CommandeResponse> detailCommandes;
}

