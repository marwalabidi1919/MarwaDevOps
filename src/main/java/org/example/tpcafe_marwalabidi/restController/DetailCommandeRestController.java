package org.example.tpcafe_marwalabidi.restController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.example.tpcafe_marwalabidi.entities.Details_Commande;
import org.example.tpcafe_marwalabidi.services.IDetails_CommandeService;

import java.util.List;

@RestController
@RequestMapping("/dc")
@RequiredArgsConstructor
public class DetailCommandeRestController {

    private final IDetails_CommandeService details_CommandeService;

    @GetMapping
    public List<Details_Commande> getAllDetailsCommande() {
        return details_CommandeService.selectAllDetailsCommande();
    }

    @GetMapping("/{id}")
    public Details_Commande getDetailCommandeById(@PathVariable long id) {
        return details_CommandeService.selectDetailCommandeById(id);
    }

    @PostMapping
    public Details_Commande addDetailCommande(@RequestBody Details_Commande detailCommande) {
        return details_CommandeService.addDetailCommande(detailCommande);
    }

    @PostMapping("/addlist")
    public List<Details_Commande> addDetailsCommande(@RequestBody List<Details_Commande> details) {
        return details_CommandeService.saveDetailsCommande(details);
    }

    @DeleteMapping("/{id}")
    public void deleteDetailCommandeById(@PathVariable long id) {
        details_CommandeService.deleteDetailCommandeById(id);
    }

    @DeleteMapping("/all")
    public void deleteAllDetailsCommande() {
        details_CommandeService.deleteAllDetailsCommande();
    }

    @GetMapping("/count")
    public long countDetailsCommande() {
        return details_CommandeService.countingDetailsCommande();
    }

    @GetMapping("/exists/{id}")
    public boolean existsDetailCommande(@PathVariable long id) {
        return details_CommandeService.verifDetailCommandeById(id);
    }
}
