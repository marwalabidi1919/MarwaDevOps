package org.example.tpcafe_marwalabidi.restController;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.example.tpcafe_marwalabidi.services.ICommandeService;

import java.time.LocalDate;

@RestController
@RequestMapping("/commande")
@RequiredArgsConstructor
public class CommandeRestController {

    private final ICommandeService commandeService;

    @PostMapping("/affecter-client")
    public void affecterCommandeClient(@RequestParam long idCommande, @RequestParam long idClient) {
        commandeService.affecterCommandeAClient(idCommande, idClient);
    }

    @PostMapping("/affecter-client-date")
    public void affecterCommandeClientParDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateCommande,
            @RequestParam String nomClient,
            @RequestParam String prenomClient) {
        commandeService.affecterCommandeAClient(dateCommande, nomClient, prenomClient);
    }

    @PostMapping("/desaffecter-client")
    public void desaffecterClient(@RequestParam long idCommande) {
        commandeService.desaffecterClientDeCommande(idCommande);
    }
}
