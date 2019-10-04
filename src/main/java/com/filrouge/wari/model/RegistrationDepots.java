package com.filrouge.wari.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

public class RegistrationDepots {

    private String numCompt;
    private  Integer solde;
    private Partenaire partenaire;

    private Integer montant;

    private Date dateDepot;
    private Integer soldeInitial;
    private User user;

    public String getNumCompt() {
        return numCompt;
    }

    public void setNumCompt(String numCompt) {
        this.numCompt = numCompt;
    }

    public Integer getSolde() {
        return solde;
    }

    public void setSolde(Integer solde) {
        this.solde = solde;
    }

    public Partenaire getPartenaire() {
        return partenaire;
    }

    public void setPartenaire(Partenaire partenaire) {
        this.partenaire = partenaire;
    }

    public Integer getMontant() {
        return montant;
    }

    public void setMontant(Integer montant) {
        this.montant = montant;
    }

    public Date getDateDepot() {
        return dateDepot;
    }

    public void setDateDepot(Date dateDepot) {
        this.dateDepot = dateDepot;
    }

    public Integer getSoldeInitial() {
        return soldeInitial;
    }

    public void setSoldeInitial(Integer soldeInitial) {
        this.soldeInitial = soldeInitial;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

