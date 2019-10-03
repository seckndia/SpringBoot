package com.filrouge.wari.controller;

import com.filrouge.wari.model.*;
import com.filrouge.wari.repository.CompteRepository;
import com.filrouge.wari.repository.PartenaireRepository;
import com.filrouge.wari.repository.UserRepository;
import com.filrouge.wari.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping(value = "/partenaire")

public class PartenaireController {
    @Autowired
    private PartenaireRepository partenaireRepository;
    @Autowired
    private UserRepository userRepository;
    @GetMapping(value = "/liste")
    @PreAuthorize("hasAuthority('ROLE_SUPERADMIN')")
//permet de lister les partenaires
    public List<Partenaire> liste(){
        return  partenaireRepository.findAll();

    }
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private CompteRepository compteRepository;
    @PostMapping(value = "/add")
    @PreAuthorize("hasAuthority('ROLE_SUPERADMIN')")
    //AJOUT PARTENAIRE
    public  Partenaire add(RegistrationPartenaire registrationPartenaire){

        Partenaire p = new Partenaire();
        p.setEntreprise(registrationPartenaire.getEntreprise());
        p.setAdresses(registrationPartenaire.getAdresses());
        p.setNinea(registrationPartenaire.getNinea());
        p.setStatus("Activer");
        p.setTelphone(registrationPartenaire.getTelphone());

        partenaireRepository.save(p);

        Compte c = new Compte();
        c.setSolde(registrationPartenaire.getSolde());

        SimpleDateFormat formater = null;

        formater = new SimpleDateFormat("ssyyyyMMddHHmm");
        Date now=new Date();
        String numcompt = formater.format(now);
        c.setNumCompt(numcompt);
        c.setPartenaire(p);
        c.setSolde(0);
          compteRepository.save(c);

        User u=new User();

        u.setUsername(registrationPartenaire.getUsername());
        u.setNom(registrationPartenaire.getNom());

        u.setPassword(encoder.encode(registrationPartenaire.getPassword()));
        u.setCni(registrationPartenaire.getCni());
        u.setTel(registrationPartenaire.getTel());
        u.setAdresse(registrationPartenaire.getAdresse());

        u.setStatus("Activer");
        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        role.setId((long) 3);
        roles.add(role);
        u.setRoles(roles);
        u.setPartenaire(p);
        u.setCompte(c);
        userRepository.save(u);
   return partenaireRepository.save(p);

    }

//add user du partenaie

    @Autowired //etape 4
    private UserDetailsServiceImpl userDetailsService;
    @PostMapping(value = "/addUserPart")
     @PreAuthorize("hasAuthority('ROLE_ADMIN')")
public ResponseEntity<String> addUserPart(RegistrationPartenaire registrationPartenaire){

        User u=new User();

        u.setUsername(registrationPartenaire.getUsername());
        u.setNom(registrationPartenaire.getNom());

        u.setPassword(encoder.encode(registrationPartenaire.getPassword()));
        u.setCni(registrationPartenaire.getCni());
        u.setTel(registrationPartenaire.getTel());
        u.setAdresse(registrationPartenaire.getAdresse());

        u.setStatus("Activer");
        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        role.setId(registrationPartenaire.getProfil());
        roles.add(role);
        u.setRoles(roles);

        Partenaire partenaire = userDetailsService.getUserconnecte().getPartenaire();

        u.setPartenaire(partenaire);

          userRepository.save(u);
            return new ResponseEntity<>("Utilisateur Enregistrer", HttpStatus.OK);
    }
}
