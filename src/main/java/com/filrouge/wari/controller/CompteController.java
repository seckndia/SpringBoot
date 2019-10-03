package com.filrouge.wari.controller;

import com.filrouge.wari.model.Compte;
import com.filrouge.wari.model.Partenaire;
import com.filrouge.wari.model.RegistationUser;
import com.filrouge.wari.model.User;
import com.filrouge.wari.repository.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
 //Ajout Compte
    @PostMapping(value = "/add",consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Compte add(@RequestBody(required = false) Compte compte){
     SimpleDateFormat formater = null;

        formater = new SimpleDateFormat("ssyyyyMMddHHmm");
        Date now=new Date();
        String numcompt = formater.format(now);

        compte.setNumCompt(numcompt);
        return compteRepository.save(compte);

    }
}
