package org.example.tpcafe_marwalabidi.mapper;

import org.example.tpcafe_marwalabidi.dto.PromotionRequest;
import org.example.tpcafe_marwalabidi.dto.PromotionResponse;
import org.example.tpcafe_marwalabidi.entities.Promotion;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface PromotionMapper {

    Promotion toEntity(PromotionRequest dto);
    PromotionResponse toDto(Promotion promotion);

}
