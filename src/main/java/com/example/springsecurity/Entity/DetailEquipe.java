package com.example.springsecurity.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="DetailEquipe")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DetailEquipe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idDetailEquipe")
    private Long idDetailEquipe; // Clé primaire

    private int salle;
    private String thematique;

    @JsonBackReference
    @OneToOne(mappedBy = "detailequipe")
    private Equipe equipe;


}
