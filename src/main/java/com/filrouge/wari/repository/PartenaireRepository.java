package com.filrouge.wari.repository;


import com.filrouge.wari.model.Partenaire;
import com.filrouge.wari.model.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PartenaireRepository extends JpaRepository<Partenaire, Integer> {

    Optional<Partenaire> findById(Integer integer);

    //List findPartenaireByCreatedBy(User uConnect);


    //Optional<Partenaire> findPartenaireByCreatedBy(User uConnect);
}