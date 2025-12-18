package org.example.tpcafe_marwalabidi.restController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.example.tpcafe_marwalabidi.entities.Promotion;
import org.example.tpcafe_marwalabidi.services.IPromotionService;

import java.util.List;

@RestController
@RequestMapping("/promotion")
@RequiredArgsConstructor
public class PromotionRestController {

    private final IPromotionService promotionService;

    // ✅ GET — récupérer toutes les promotions
    @GetMapping
    public List<Promotion> getAllPromotions() {
        return promotionService.selectAllPromotions();
    }
    @DeleteMapping
    public void deleteAllPromotions() {
        promotionService.deleteAllPromotions();
    }
    // ✅ POST — ajouter une promotion
    @PostMapping
    public Promotion addPromotion(@RequestBody Promotion promotion) {
        return promotionService.addPromotion(promotion);
    }

    // ✅ PUT — modifier une promotion existante
    @PutMapping("/{id}")
    public void updatePromotion(@PathVariable Long id, @RequestBody Promotion promotion) {
        promotionService.deletePromotion(promotion);
    }

    // ✅ DELETE — supprimer une promotion par ID
    @DeleteMapping("/{id}")
    public void deletePromotion(@PathVariable Long id) {
        promotionService.deletePromotionById(id);
    }

    // ✅ GET — récupérer une promotion par ID
    @GetMapping("/{id}")
    public Promotion getPromotionById(@PathVariable Long id) {
        return promotionService.selectPromotionById(id);
    }
}
