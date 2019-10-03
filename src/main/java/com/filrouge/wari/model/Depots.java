package com.filrouge.wari.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@EqualsAndHashCode(exclude = "user,compte")
public class Depots {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
   private Integer montant;
    @DateTimeFormat(pattern ="yyyy-MM-dd")
   private Date dateDepot;
    private Integer soldeInitial;

    @JoinColumn(name = "caissier_id",referencedColumnName ="id")
    @ManyToOne(optional = false)
    //@JsonManagedReference
    @JsonIgnoreProperties("depots")
    private User user;

    @JoinColumn(name = "compte_id",referencedColumnName ="id")
    @ManyToOne(optional = false)
    //@JsonManagedReference
    @JsonIgnoreProperties("depots")
    private Compte compte;

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
