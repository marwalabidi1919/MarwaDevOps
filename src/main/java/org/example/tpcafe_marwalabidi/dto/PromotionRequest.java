package org.example.tpcafe_marwalabidi.dto;

import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PromotionRequest {
    private String pourcentagePromo;
    private LocalDate dateDebutPromo;
    private LocalDate dateFinPromo;

    private List<Long> articleIds;
}
