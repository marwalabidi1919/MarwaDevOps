package org.example.tpcafe_marwalabidi.mapper;

import org.example.tpcafe_marwalabidi.dto.AdresseRequest;
import org.example.tpcafe_marwalabidi.dto.AdresseResponse;
import org.example.tpcafe_marwalabidi.entities.Adresse;
import org.mapstruct.Mapper;



@Mapper(componentModel = "spring")
public interface AdresseMapper {

    Adresse toEntity(AdresseRequest dto);
    AdresseResponse toDto(Adresse adresse);

}
