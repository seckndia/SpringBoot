package com.filrouge.wari.repository;

import com.filrouge.wari.model.Compte;
import com.filrouge.wari.model.Partenaire;
import com.filrouge.wari.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface CompteRepository  extends JpaRepository<Compte, Integer> {
  // Compte finBy(String numcompt);
   // Compte findByNumCompt(String numcompt);

    Optional<Compte> findCompteByNumCompt(String numCompt);
}
