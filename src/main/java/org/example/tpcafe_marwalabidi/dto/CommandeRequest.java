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
public class CommandeRequest {
    private LocalDate dateCommande;
    private float totalCommande;
    private StatusCommande statusCommande;

    private Long clientId;
    private List<Long> detailCommandeIds;
}

