package org.example.tpcafe_marwalabidi.dto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdresseRequest {
    private String rue;
    private String ville;
    private int codePostal;
}
