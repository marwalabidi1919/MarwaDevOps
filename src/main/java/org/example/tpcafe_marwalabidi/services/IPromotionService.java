package org.example.tpcafe_marwalabidi.services;

import org.example.tpcafe_marwalabidi.entities.Promotion;

import java.util.List;

public interface IPromotionService {
    Promotion addPromotion(Promotion p);

    List<Promotion> savePromotions(List<Promotion> promotions);

    Promotion selectPromotionById(long id);

    List<Promotion> selectAllPromotions();

    void deletePromotion(Promotion p);

    void deleteAllPromotions();

    void deletePromotionById(long id);

    long countingPromotions();

    boolean verifPromotionById(long id);
}