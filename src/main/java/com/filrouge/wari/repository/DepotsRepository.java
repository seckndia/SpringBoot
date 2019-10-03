package com.filrouge.wari.repository;

import com.filrouge.wari.model.Depots;
import com.filrouge.wari.model.Partenaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepotsRepository extends JpaRepository<Depots, Integer> {
}
