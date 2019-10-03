package com.filrouge.wari.controller;

import com.filrouge.wari.model.RegistationUser;
import com.filrouge.wari.model.Role;
import com.filrouge.wari.model.User;
import com.filrouge.wari.repository.UserRepository;
import com.filrouge.wari.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin

@RequestMapping(value = "/user")
public class UserController {
    @Autowired //etape 4
    private UserDetailsServiceImpl userDetailsService;

    @GetMapping(value = "/recup")
    public User testid(){
        User user = userDetailsService.getUserconnecte();
        return user;
    }

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;
    @GetMapping(value = "/liste")
    public List<User> liste(){
        return  userRepository.findAll();

    }

    //permet de ajouter des utilisateurs du superadmin
    @PostMapping(value = "/add")
    @PreAuthorize("hasAuthority('ROLE_SUPERADMIN')")

    public User add(RegistationUser registationUser){
        User u=new User();
        u.setUsername(registationUser.getUsername());
        u.setNom(registationUser.getNom());

        u.setPassword(encoder.encode(registationUser.getPassword()));
        u.setCni(registationUser.getCni());
        u.setTel(registationUser.getTel());
        u.setAdresse(registationUser.getAdresse());
        u.setStatus("Activer");
        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        role.setId(registationUser.getProfil());
        roles.add(role);
        u.setRoles(roles);
        return userRepository.save(u);

    }

}
