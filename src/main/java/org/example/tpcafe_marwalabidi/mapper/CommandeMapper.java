package org.example.tpcafe_marwalabidi.mapper;

import org.example.tpcafe_marwalabidi.dto.CommandeRequest;
import org.example.tpcafe_marwalabidi.dto.CommandeResponse;
import org.example.tpcafe_marwalabidi.entities.Commande;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommandeMapper {

    Commande toEntity(CommandeRequest dto);
    CommandeResponse toDto(Commande commande);
}
