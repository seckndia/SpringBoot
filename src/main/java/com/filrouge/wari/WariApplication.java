package com.filrouge.wari;

import com.filrouge.wari.model.Role;
import com.filrouge.wari.model.User;
import com.filrouge.wari.repository.UserRepository;
import com.filrouge.wari.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class WariApplication extends SpringBootServletInitializer implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(WariApplication.class, args);
	}
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	UserDetailsServiceImpl userDetailsService;
	@Autowired
	private UserRepository userRepository;
	@Override
	public void run(String... args) throws Exception {
		System.out.println(encoder.encode("passer"));
		//User u = new User();


		//u.setUsername("cheikh");
		//u.setNom("seck tidiane");

		//u.setPassword(encoder.encode("passer"));
		//u.setCni("02369874516");
		//u.setTel("778963215");
		//u.setAdresse("medina");
		//u.setStatus("Activer");
		//Set<Role> roles = new HashSet<>();
		//Role role = new Role();
		//role.setId((long) 1);
		//roles.add(role);
		//u.setRoles(roles);
		 //userRepository.save(u);


	}

}
