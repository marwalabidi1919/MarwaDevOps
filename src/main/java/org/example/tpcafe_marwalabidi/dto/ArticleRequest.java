package org.example.tpcafe_marwalabidi.dto;

import lombok.*;
import org.example.tpcafe_marwalabidi.entities.Article;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleRequest {
    private String nomArticle;
    private float prixArticle;
    private Article Article;
}

