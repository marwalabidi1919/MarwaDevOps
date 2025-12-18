package org.example.tpcafe_marwalabidi.services;


import lombok.AllArgsConstructor;
import org.example.tpcafe_marwalabidi.dto.CarteFideliteRequest;
import org.example.tpcafe_marwalabidi.dto.CarteFideliteResponse;
import org.example.tpcafe_marwalabidi.entities.CarteFidelite;
import org.example.tpcafe_marwalabidi.mapper.CarteFideliteMapper;
import org.example.tpcafe_marwalabidi.repositories.CarteFideliteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarteFideliteService implements ICarteFideliteService {

    CarteFideliteRepository carteFideliteRepository;
    CarteFideliteMapper carteFideliteMapper;

    @Override
    public CarteFideliteResponse addCarte(CarteFideliteRequest request) {
        CarteFidelite carte = carteFideliteMapper.toEntity(request);
        CarteFidelite saved = carteFideliteRepository.save(carte);
        return carteFideliteMapper.toDto(saved);
    }

    @Override
    public CarteFideliteResponse getCarteById(long id) {
        CarteFidelite carte = carteFideliteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carte fidélité introuvable : " + id));
        return carteFideliteMapper.toDto(carte);
    }

    @Override
    public List<CarteFideliteResponse> getAllCartes() {
        return carteFideliteRepository.findAll()
                .stream()
                .map(carteFideliteMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CarteFideliteResponse updateCarte(long id, CarteFideliteRequest request) {
        CarteFidelite carte = carteFideliteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carte fidélité introuvable : " + id));

        carte.setPointsAccumules(request.getPointsAccumules());
        carte.setDateCreation(request.getDateCreation());

        CarteFidelite updated = carteFideliteRepository.save(carte);
        return carteFideliteMapper.toDto(updated);
    }

    public CarteFidelite addCarteFidelite(CarteFidelite c) {
        return carteFideliteRepository.save(c);
    }

    public List<CarteFidelite> saveCartesFidelite(List<CarteFidelite> cartesFidelite) {
        return carteFideliteRepository.saveAll(cartesFidelite);
    }

    public CarteFidelite selectCarteFideliteById(long id) {
        return carteFideliteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CarteFidelite not found with id: " + id));
    }

    public List<CarteFidelite> selectAllCartesFidelite() {
        return carteFideliteRepository.findAll();
    }

    public void deleteCarteFidelite(CarteFidelite c) {
        carteFideliteRepository.delete(c);
    }

    public void deleteAllCartesFidelite() {
        carteFideliteRepository.deleteAll();
    }

    public void deleteCarteFideliteById(long id) {
        carteFideliteRepository.deleteById(id);
    }

    public long countingCartesFidelite() {
        return carteFideliteRepository.count();
    }

    public boolean verifCarteFideliteById(long id) {
        return carteFideliteRepository.existsById(id);
    }

    @Override
    public void deleteCarteById(long id) {
        carteFideliteRepository.deleteById(id);
    }

    @Override
    public void deleteAllCartes() {
        carteFideliteRepository.deleteAll();
    }

    @Override
    public long countCartes() {
        return carteFideliteRepository.count();
    }

    @Override
    public boolean verifCarteById(long id) {
        return carteFideliteRepository.existsById(id);
    }
}
