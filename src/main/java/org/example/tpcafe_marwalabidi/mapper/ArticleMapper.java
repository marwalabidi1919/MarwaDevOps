package org.example.tpcafe_marwalabidi.mapper;
import org.example.tpcafe_marwalabidi.dto.ArticleRequest;
import org.example.tpcafe_marwalabidi.dto.ArticleResponse;
import org.example.tpcafe_marwalabidi.entities.Article;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArticleMapper {

    Article toEntity(ArticleRequest dto);

    ArticleResponse toDto(Article article);

}