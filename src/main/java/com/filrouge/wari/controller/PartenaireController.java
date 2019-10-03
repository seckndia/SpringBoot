package com.filrouge.wari.controller;

import com.filrouge.wari.model.*;
import com.filrouge.wari.repository.CompteRepository;
import com.filrouge.wari.repository.PartenaireRepository;
import com.filrouge.wari.repository.UserRepository;
import com.filrouge.wari.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
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
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
//permet de lister les partenaires
    public List<Partenaire> liste(){
        return  partenaireRepository.findAll();

    }
    @Autowired
    PasswordEncoder encoder;
    @Autowired //etape 4
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private CompteRepository compteRepository;
    @PostMapping(value = "/add",consumes = {MediaType.APPLICATION_JSON_VALUE})
   // @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public  Partenaire add(@RequestBody(required = false) RegistrationPartenaire registrationPartenaire){

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
        u.setName(registrationPartenaire.getName());

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
        u.setPartenaire(p);
        u.setCompte(c);
        userRepository.save(u);
   return partenaireRepository.save(p);
    }


}
