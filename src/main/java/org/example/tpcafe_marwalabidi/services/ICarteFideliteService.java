package org.example.tpcafe_marwalabidi.services;

import org.example.tpcafe_marwalabidi.dto.CarteFideliteRequest;
import org.example.tpcafe_marwalabidi.dto.CarteFideliteResponse;

import java.util.List;

public interface ICarteFideliteService {
    CarteFideliteResponse addCarte(CarteFideliteRequest carteFideliteRequest);

    CarteFideliteResponse getCarteById(long id);
    List<CarteFideliteResponse> getAllCartes();

    CarteFideliteResponse updateCarte(long id, CarteFideliteRequest carteFideliteRequest);

    void deleteCarteById(long id);
    void deleteAllCartes();

    long countCartes();
    boolean verifCarteById(long id);
}
