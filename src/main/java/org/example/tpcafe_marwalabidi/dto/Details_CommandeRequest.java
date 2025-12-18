package org.example.tpcafe_marwalabidi.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Details_CommandeRequest {

    private int quantiteArticle;
    private float sousTotalDetailArticle;
    private float sousTotalDetailArticleApresPromo;

    private Long articleId;
    private Long commandeId;

}