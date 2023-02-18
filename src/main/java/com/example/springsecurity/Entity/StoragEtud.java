package com.example.springsecurity.Entity;

import lombok.Data;

@Data
public class StoragEtud {
    private String prenomE;
    private String nomE;
    private String email;
    private Boolean banned;
    private Boolean verified;
    private String Username;
    private Rolee role;
    private Boolean active;
    private String Token;
}
