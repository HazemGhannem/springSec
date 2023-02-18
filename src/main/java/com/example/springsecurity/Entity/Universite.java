package com.example.springsecurity.Entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="Universite")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Universite implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUniv")
    private Long idUniv;
    private String nomUniv;


    @OneToMany
    private Set<Departement> department;


}
