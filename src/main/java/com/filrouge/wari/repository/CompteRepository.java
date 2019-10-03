package com.filrouge.wari.repository;

import com.filrouge.wari.model.Compte;
import com.filrouge.wari.model.Partenaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface CompteRepository  extends JpaRepository<Compte, Integer> {

}
