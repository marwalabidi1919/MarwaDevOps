package org.example.tpcafe_marwalabidi.restController;

import lombok.RequiredArgsConstructor;
import org.example.tpcafe_marwalabidi.entities.Client;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.example.tpcafe_marwalabidi.services.IClientService;

import java.time.LocalDate;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientRestController {

    private final IClientService clientService;

    @PostMapping("/affecter-adresse")
    public String affecterAdresse(@RequestParam String rue, @RequestParam long cin) {
        return clientService.affecterAdresseAClient(rue, cin);
    }

    @PostMapping("/affecter-carte")
    public void affecterCarte(@RequestParam long idCarte, @RequestParam long idClient) {
        clientService.affecterCarteAClient(idCarte, idClient);
    }

    @PostMapping("/affecter-commande")
    public void affecterCommande(@RequestParam long idCommande, @RequestParam long idClient) {
        clientService.affecterCommandeAClient(idCommande, idClient);
    }

    @PostMapping("/affecter-commande-date")
    public void affecterCommandeParDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateCommande,
            @RequestParam String nomClient,
            @RequestParam String prenomClient) {
        clientService.affecterCommandeAClient(dateCommande, nomClient, prenomClient);
    }

    @PostMapping("/desaffecter-commande")
    public void desaffecterCommande(@RequestParam long idCommande) {
        clientService.desaffecterClientDeCommande(idCommande);
    }

    @PostMapping("/ajouter-client-carte")
    public Client ajouterClientEtCarte(@RequestBody Client client) {
        return clientService.ajouterClientEtCarteFidelite(client);
    }
}
