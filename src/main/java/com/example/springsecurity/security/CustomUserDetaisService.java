package com.example.springsecurity.security;


import com.example.springsecurity.Entity.Etudiant;
import com.example.springsecurity.service.UserService;
import com.example.springsecurity.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetaisService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Etudiant user = userService.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username) );
        Set<GrantedAuthority> authorities =
        new HashSet<>(Arrays.asList(SecurityUtils.convertToAuthority(user.getRole().name())));

        return UserPrincipal.builder()
                .user(user)
                .id(user.getIdEtudiant())
                .email(username)
                .active(user.getActive())
                .password(user.getPassword())
                .authoroties(authorities)
                .build();
    }
}
