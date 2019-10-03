package com.filrouge.wari.repository;


import com.filrouge.wari.model.Partenaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PartenaireRepository extends JpaRepository<Partenaire, Integer> {

}