package org.example.tpcafe_marwalabidi.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.tpcafe_marwalabidi.entities.CarteFidelite;
import org.example.tpcafe_marwalabidi.entities.Client;
import org.example.tpcafe_marwalabidi.entities.Promotion;
import org.example.tpcafe_marwalabidi.repositories.ClientRepository;
import org.example.tpcafe_marwalabidi.repositories.CarteFideliteRepository;
import org.example.tpcafe_marwalabidi.repositories.PromotionRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class ScheduledTasks {

    private final ClientRepository clientRepository;
    private final CarteFideliteRepository carteFideliteRepository;
    private final PromotionRepository promotionRepository;

    /**
     * Chaque jour à minuit : +10% de points pour les clients dont c'est l'anniversaire.
     */
    @Transactional
    @Scheduled(cron = "0 0 0 * * *")
    public void bonusAnniversaire() {
        LocalDate today = LocalDate.now();
        List<Client> anniversaires = clientRepository.findClientsBirthday(today);

        if (anniversaires.isEmpty()) {
            log.info("Aucun anniversaire aujourd'hui ({})", today);
            return;
        }

        anniversaires.forEach(client -> {
            CarteFidelite carte = client.getCarteFidelite();
            if (carte == null) {
                log.warn("Client {} {} sans carte, bonus ignoré", client.getNom(), client.getPrenom());
                return;
            }
            Integer points = carte.getPointsAccumules() == null ? 0 : carte.getPointsAccumules();
            int newPoints = (int) Math.ceil(points * 1.10);
            carte.setPointsAccumules(newPoints);
            carteFideliteRepository.save(carte);
            log.info("Bonus anniversaire pour {} {} (id={}): {} -> {}", client.getNom(), client.getPrenom(), client.getIdClient(), points, newPoints);
        });
    }

    /**
     * Début de chaque mois à minuit : log des articles en promotion sur le mois en cours.
     */
    @Scheduled(cron = "0 0 0 1 * *")
    public void promotionsDuMois() {
        LocalDate now = LocalDate.now();
        LocalDate debutMois = now.withDayOfMonth(1);
        LocalDate finMois = now.withDayOfMonth(now.lengthOfMonth());

        // Récupérer les promotions avec leurs articles puis filtrer sur l'intervalle du mois.
        List<Promotion> promotions = promotionRepository.findAllWithArticles().stream()
                .filter(p -> p.getDateDebutPromo() != null && !p.getDateDebutPromo().isAfter(finMois))
                .filter(p -> p.getDateFinPromo() == null || !p.getDateFinPromo().isBefore(debutMois))
                .collect(Collectors.toList());

        if (promotions.isEmpty()) {
            log.info("Aucune promotion active pour le mois {} {}", now.getMonth(), now.getYear());
            return;
        }

        promotions.forEach(promo -> {
            String articles = promo.getArticles() == null ? "aucun article lié"
                    : promo.getArticles().stream()
                    .map(a -> a.getNomArticle() + " (id=" + a.getIdArticle() + ")")
                    .collect(Collectors.joining(", "));
            log.info("Promo '{}' du {} au {} : articles {}", promo.getPourcentagePromo(), promo.getDateDebutPromo(), promo.getDateFinPromo(), articles);
        });
    }
}

