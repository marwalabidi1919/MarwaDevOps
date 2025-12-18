package org.example.tpcafe_marwalabidi.dto;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarteFideliteRequest {
    private int pointsAccumules;
    private LocalDate dateCreation;
}

