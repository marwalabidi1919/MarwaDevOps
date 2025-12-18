package org.example.tpcafe_marwalabidi.services;

import lombok.AllArgsConstructor;
import org.example.tpcafe_marwalabidi.entities.Promotion;
import org.example.tpcafe_marwalabidi.repositories.PromotionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PromotionService implements IPromotionService {

    private final PromotionRepository promotionRepository;

    @Override
    public Promotion addPromotion(Promotion p) {
        return promotionRepository.save(p);
    }

    @Override
    public List<Promotion> savePromotions(List<Promotion> promotions) {
        return promotionRepository.saveAll(promotions);
    }

    @Override
    public Promotion selectPromotionById(long id) {
        return promotionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Promotion not found with id: " + id));
    }

    @Override
    public List<Promotion> selectAllPromotions() {
        return promotionRepository.findAll();
    }

    @Override
    public void deletePromotion(Promotion p) {
        promotionRepository.delete(p);
    }

    @Override
    public void deleteAllPromotions() {
        promotionRepository.deleteAll();
    }

    @Override
    public void deletePromotionById(long id) {
        promotionRepository.deleteById(id);
    }

    @Override
    public long countingPromotions() {
        return promotionRepository.count();
    }

    @Override
    public boolean verifPromotionById(long id) {
        return promotionRepository.existsById(id);
    }
}
