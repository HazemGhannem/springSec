package com.example.springsecurity.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Tache implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="IdTache")
    private Long IdTache; // Clé primaire

    private String name;

    @Enumerated(EnumType.STRING)
    private TachType type;

    @Temporal(TemporalType.DATE)
    private Date StartDate;

    @Temporal(TemporalType.DATE)
    private Date EndDate;

    private String Description;

    @ManyToOne
    private Etudiant etudiant;
}
