package org.example.tpcafe_marwalabidi.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientRequest {
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;

    private Long carteId;
    private Long adresseId;
}

