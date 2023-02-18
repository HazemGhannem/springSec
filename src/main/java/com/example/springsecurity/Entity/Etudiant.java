package com.example.springsecurity.Entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table( name ="Etudiant")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Etudiant implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="idEtudiant")
    private Long idEtudiant; // Clé primaire
    private String prenomE;
    private String nomE;
    @Enumerated(EnumType.STRING)
    @Column(name="opp")
    private Option option;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false,unique = true)
    private String email;
    private Boolean banned;
    private Boolean verified;
    private String image;
    private String Username;
    @Column(nullable = false)
    private String phonenumber;
    @Transient
    private String Token;
    private LocalDateTime createdate;
    @Enumerated(EnumType.STRING)
    @Column(name="Roleee")
    private Rolee role;
    private Boolean active;
    @OneToMany(mappedBy = "etudiant")
    @JsonIgnore
    private Set<Contrat> contrat;

    @ManyToMany(mappedBy = "etudiant")
    @JsonIgnore
    private Set<Equipe> equipe;
    @ManyToMany(mappedBy = "etudiants")
    private Set<Role> Roles;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="idDepart")
    private Departement departement;

    @OneToMany(mappedBy = "etudiant")
    @JsonBackReference
    private Set<Tache> tahces;

    @OneToMany(mappedBy = "sender")
    @JsonIgnore
    private Set<ChatMessage> msg;

    @OneToMany(mappedBy = "reciver")
    @JsonIgnore
    private Set<ChatMessage> resivermsg;
    // Constructeur et accesseurs (getters) et mutateurs (setters)



}