package com.example.springsecurity.Entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name ="Contrat")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class Contrat implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="idContrat")
    private Long idContrat; // Cling



    @Temporal(TemporalType.DATE)
    private Date dateDebutContrat;

    @Temporal(TemporalType.DATE)
    private Date dateFinContrat;

    @Enumerated(EnumType.STRING)
    private Specialite specialite;

    private boolean archive;

    private int montantContrat;

    @JsonBackReference
    @ManyToOne
    private Etudiant etudiant;


}
