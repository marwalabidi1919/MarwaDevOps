package org.example.tpcafe_marwalabidi.mapper;

import org.example.tpcafe_marwalabidi.dto.ClientRequest;
import org.example.tpcafe_marwalabidi.dto.ClientResponse;
import org.example.tpcafe_marwalabidi.entities.Client;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ClientMapper {

    Client toEntity(ClientRequest dto);
    ClientResponse toDto(Client client);

}