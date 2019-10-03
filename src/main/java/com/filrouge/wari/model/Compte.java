package com.filrouge.wari.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(exclude = "partenaire")
@Table(name = "Compte", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "numCompt"
        })
})
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 30,nullable = true)
    private String numCompt;
    private  Integer solde;
    @JoinColumn(name = "partenaire_id",referencedColumnName ="id")
    @ManyToOne(optional = false)
    //@JsonManagedReference
    @JsonIgnoreProperties("comptes")
    private Partenaire partenaire;

    @OneToMany(mappedBy ="compte")
    //@JsonBackReference
    @JsonIgnoreProperties("compte")
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
    
}
