package com.filrouge.wari.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = "partenaire,depots")
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),

        @UniqueConstraint(columnNames = {
                "cni"
        }),
        @UniqueConstraint(columnNames = {
                "tel"
        })
})
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min=3, max = 50)
    @Column(nullable = false)
    private String nom;

    @NotBlank
    @Size(min=3, max = 50)
    @Column(nullable = false)
    private String username;



    @NotBlank

    @Column(nullable = false)
    private String password;
    @Size(min=3, max = 50)
    @Column(nullable = false)
    private String tel;
    @NotBlank
    @Size(min=3, max = 50)
    @Column(nullable = false)
    private String adresse;


    @NotBlank
    @Size(min=3, max = 50)
    @Column(nullable = false)
    private String cni;
    @NotBlank
    @Size(min=3, max = 50)
    @Column(nullable = false)
    private String status;

    @JoinColumn(name = "partenaire_id",referencedColumnName ="id",nullable = true)
    @ManyToOne(optional = false)
    //@JsonManagedReference
    @JsonIgnoreProperties("users")

    private Partenaire partenaire;

    @JoinColumn(name = "numCompte_id",referencedColumnName ="id",nullable = true)
    @ManyToOne(optional = true)
    //@JsonManagedReference
    @JsonIgnoreProperties("users")

    private Compte compte;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy ="user")
    //@JsonBackReference
    @JsonIgnoreProperties("user")
    private List<Depots> depots;

    public List<Depots> getDepots() {
        return depots;
    }

    public void setDepots(List<Depots> depots) {
        this.depots = depots;
    }

    public User() {}

    public User(String nom, String username,  String password,String tel,String adresse,String cni,String status) {
        this.nom = nom;
        this.username = username;

        this.password = password;
        this.tel = tel;
        this.adresse = adresse;
        this.cni = cni;
        this.status = status;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCni() {
        return cni;
    }

    public void setCni(String cni) {
        this.cni = cni;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Partenaire getPartenaire() {
        return partenaire;
    }

    public void setPartenaire(Partenaire partenaire) {
        this.partenaire = partenaire;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }
}