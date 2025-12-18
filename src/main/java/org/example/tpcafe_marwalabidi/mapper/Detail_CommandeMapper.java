package org.example.tpcafe_marwalabidi.mapper;

import org.example.tpcafe_marwalabidi.dto.Details_CommandeRequest;
import org.example.tpcafe_marwalabidi.dto.Details_CommandeResponse;
import org.example.tpcafe_marwalabidi.entities.Details_Commande;
import org.mapstruct.Mapper;

import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface Detail_CommandeMapper {

    Details_Commande toEntity(Details_CommandeRequest dto);
    @Mapping(source = "article.idArticle", target = "articleId")
    @Mapping(source = "commande.idCommande", target = "commandeId")
    Details_CommandeResponse toDto(Details_Commande detailCommande);

}