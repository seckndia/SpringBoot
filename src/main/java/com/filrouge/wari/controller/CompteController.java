package com.filrouge.wari.controller;

import com.filrouge.wari.model.*;
import com.filrouge.wari.repository.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
  private   CompteRepository compteRepository;
 @GetMapping(value = "/liste")
 public List<Compte> liste(){
     return  compteRepository.findAll();

 }
 //Ajout Compte DU PARTENAIRE
    @PostMapping(value = "/add",consumes = {MediaType.APPLICATION_JSON_VALUE})
     @PreAuthorize("hasAuthority('ROLE_SUPERADMIN')")
    public Compte add(@RequestBody(required = false) Compte compte){
     SimpleDateFormat formater = null;

        formater = new SimpleDateFormat("ssyyyyMMddHHmm");
        Date now=new Date();
        String numcompt = formater.format(now);
        compte.setSolde(0);
        compte.setNumCompt(numcompt);
        return compteRepository.save(compte);

    }
    //Depots

/*
    @PostMapping(value = "/depot",consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> adddepot(@RequestBody(required = false) Depots depots){

     depots.setDateDepot(new Date());
     depots.setMontant(depots.getMontant());


    }
*/
@PostMapping("/yaya")
    public Compte yaya(String nom){
   Compte c=compteRepository.findByNumCompt(nom);
   return c;
}
}
