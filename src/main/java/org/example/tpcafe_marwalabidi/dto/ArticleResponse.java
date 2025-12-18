package org.example.tpcafe_marwalabidi.dto;

import lombok.*;
import org.example.tpcafe_marwalabidi.entities.Article;


import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleResponse {

    private long idArticle;
    private String nomArticle;
    private float prixArticle;
    private Article typeArticle;

    private List<PromotionResponse> promotions;
    private List<Details_CommandeResponse> detailCommandes;
}


