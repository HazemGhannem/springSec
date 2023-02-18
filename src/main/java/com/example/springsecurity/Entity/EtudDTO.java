package com.example.springsecurity.Entity;


import lombok.Data;

import java.util.Date;

@Data
public class EtudDTO {
    private String nom;
    private String prenom;
    private Date dateDebutContrat;
    private Date dateFinContrat;
    private String email;
    private Boolean banned;
    private Boolean verified;

}
