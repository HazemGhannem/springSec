package com.example.springsecurity.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="Equipe")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Equipe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idEquipe")
    private Long idEquipe;
    private String nomEquipe;

    @Enumerated(EnumType.STRING)
    @Column(name="Niveau")
    private Niveau niveau;
    @ManyToMany
    @JsonBackReference
    private Set<Etudiant> etudiant;
    @OneToOne
    private DetailEquipe detailequipe;


}
