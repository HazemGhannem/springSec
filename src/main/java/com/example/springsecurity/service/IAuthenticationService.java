package com.example.springsecurity.service;

import com.example.springsecurity.Entity.Etudiant;

public interface IAuthenticationService {
    Etudiant SignInAndReturnJWT(Etudiant signInRequest);
}
