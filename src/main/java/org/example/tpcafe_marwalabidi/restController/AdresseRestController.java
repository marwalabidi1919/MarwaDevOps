package org.example.tpcafe_marwalabidi.restController;


import org.example.tpcafe_marwalabidi.entities.Adresse;
import org.example.tpcafe_marwalabidi.services.IAdresseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adresses")

public class AdresseRestController {
    @Autowired
    private IAdresseService adresseService;


    @GetMapping
    public List<Adresse> GETallAdresse() {
        return adresseService.selectAllAdresses();
    }

    @PostMapping
    public Adresse POSTAdresse(@RequestBody Adresse adresse) {
        return adresseService.addAdresse(adresse);
    }

    @PostMapping("addlist")
    public List<Adresse> PutAllAdresse(@RequestBody List<Adresse> adresse) {
        return adresseService.saveAdresses(adresse);
    }

    @GetMapping("{id}") //url de param
    public Adresse GETAdresseById(@PathVariable Long id) {
        return adresseService.selectAdresseById(id);
    }

    @GetMapping("/selectById")
    public Adresse GetAdresseById(@RequestParam Long id) {
        return adresseService.selectAdresseById(id);
    }

    @DeleteMapping("{id}")
    public void deleteAdresseById(@PathVariable Long id) {
        adresseService.deleteAdresseById(id);
    }
    @DeleteMapping("/all")
    public void deleteAllAdresse() {
        adresseService.deleteAllAdresses();
    }
    @DeleteMapping("del")
    public void deleteAllAdresseById(@RequestParam Long id) {
        adresseService.deleteAdresseById(id);
    }
}



