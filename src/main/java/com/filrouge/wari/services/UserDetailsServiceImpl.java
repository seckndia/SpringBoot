package com.filrouge.wari.services;


import com.filrouge.wari.model.User;
import com.filrouge.wari.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
   //Pour récuperer id du user qui s'est connecter
    private User userconnect;

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found with -> username  : " + username));
       //etape 2
        this.userconnect = user;

        return UserPrinciple.build(user);
    }
    //etape 3
    public  User getUserconnecte(){
        return userconnect;
    }
}
