package com.filrouge.wari.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(exclude = "users,comptes")

@Table(name = "Partenaire",uniqueConstraints = {

        @UniqueConstraint(columnNames = {
                "ninea"
        }),
        @UniqueConstraint(columnNames = {
                "telphone"
        })
})

public class Partenaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 30)
    private String entreprise;
    @Column(length = 30)
    private String adresses;
    @Column(length = 15)
    private String telphone;
    @Column(length = 15)
    private String status;
    @Column(length = 30)
    private String ninea;

    @OneToMany(mappedBy ="partenaire")
    //@JsonBackReference
    @JsonIgnoreProperties("partenaire")
    private List <User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @OneToMany(mappedBy ="partenaire")
    //@JsonBackReference
    @JsonIgnoreProperties("partenaire")
    private List <Compte> comptes;

    public List<Compte> getComptes() {
        return comptes;
    }

    public void setComptes(List<Compte> comptes) {
        this.comptes = comptes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public String getAdresses() {
        return adresses;
    }

    public void setAdresses(String adresses) {
        this.adresses = adresses;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNinea() {
        return ninea;
    }

    public void setNinea(String ninea) {
        this.ninea = ninea;
    }
}
