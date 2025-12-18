package org.example.tpcafe_marwalabidi.mapper;

import org.example.tpcafe_marwalabidi.dto.CarteFideliteRequest;
import org.example.tpcafe_marwalabidi.dto.CarteFideliteResponse;
import org.example.tpcafe_marwalabidi.entities.CarteFidelite;
import org.mapstruct.Mapper;



@Mapper(componentModel = "spring")
public interface CarteFideliteMapper {

    CarteFidelite toEntity(CarteFideliteRequest dto);
    CarteFideliteResponse toDto(CarteFidelite carteFidelite);

}
