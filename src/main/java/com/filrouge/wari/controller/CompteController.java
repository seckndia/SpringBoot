package com.filrouge.wari.controller;

import com.filrouge.wari.model.*;
import com.filrouge.wari.repository.CompteRepository;
import com.filrouge.wari.repository.DepotsRepository;
import com.filrouge.wari.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin

@RequestMapping(value = "/compte")
public class CompteController {
    @Autowired
    private CompteRepository compteRepository;

    @GetMapping(value = "/liste")
    public List<Compte> liste() {
        return compteRepository.findAll();

    }

    //Ajout Compte DU PARTENAIRE
    @PostMapping(value = "/add", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @PreAuthorize("hasAuthority('ROLE_SUPERADMIN')")
    public Compte add(@RequestBody(required = false) Compte compte) {
        SimpleDateFormat formater = null;

        formater = new SimpleDateFormat("ssyyyyMMddHHmm");
        Date now = new Date();
        String numcompt = formater.format(now);
        compte.setSolde(0);
        compte.setNumCompt(numcompt);
        return compteRepository.save(compte);

    }

    // effectuer des Depots

    @Autowired
    DepotsRepository depotsRepository;
    @Autowired //etape 4
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping(value = "/depot")
    @PreAuthorize("hasAuthority('ROLE_CAISSIER')")
    public ResponseEntity<String> depot(RegistrationDepots registrationDepots)throws Exception {
        if (registrationDepots.getMontant() < 75000) {
            throw new Exception("le Montantant doit etre superieur ou egal à 75000");
        }
        Depots depots = new Depots();

        depots.setDateDepot(new Date());
        User user = userDetailsService.getUserconnecte();
        depots.setUser(user);


        Compte compte = compteRepository.findCompteByNumCompt(registrationDepots.getNumCompt()).orElseThrow(
                ()->new Exception("compte not found")
        );
        if (registrationDepots.getNumCompt().equals(compte.getNumCompt())) {
            depots.setCompte(compte);
        }

        compte.setSolde(compte.getSolde() + registrationDepots.getMontant());

        compteRepository.save(compte);

        depots.setSoldeInitial(compte.getSolde());

        depots.setMontant(registrationDepots.getMontant());
        depotsRepository.save(depots);

        return new ResponseEntity<>("dépot réussit ", HttpStatus.OK);
    }



}



