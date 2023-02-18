package com.example.springsecurity.service;


import com.example.springsecurity.Entity.Etudiant;
import com.example.springsecurity.security.UserPrincipal;
import com.example.springsecurity.security.jwt.IJwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthenticationService implements IAuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IJwtProvider jwtProvider;

    @Override
    @Transactional
    public Etudiant SignInAndReturnJWT(Etudiant signInRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        String jwt = jwtProvider.generateToken(userPrincipal);
        Etudiant signInUser = userPrincipal.getUser();
        signInUser.setActive(true);
        signInUser.setToken(jwt);

    return signInUser;
    }
}
