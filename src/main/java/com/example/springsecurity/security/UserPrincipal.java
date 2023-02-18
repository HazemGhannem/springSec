package com.example.springsecurity.security;


import com.example.springsecurity.Entity.Etudiant;
import com.example.springsecurity.Entity.Rolee;
import com.example.springsecurity.util.SecurityUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
public class UserPrincipal implements UserDetails {

    private Long id;
    private String email;
    private Boolean active;

    transient private String password;
    transient private Etudiant user;
    private Set<GrantedAuthority> authoroties;

    public static UserPrincipal createSuperUser(){
        Set<GrantedAuthority> authorities =
        new HashSet<>(Arrays.asList(SecurityUtils.convertToAuthority(Rolee.MANAGER.name())));

        return UserPrincipal.builder().id(-1L).email("system-administrator").authoroties(authorities).build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authoroties;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
       //check for banned
        if(user.getBanned() == Boolean.TRUE){
            log.info("User account is Banned");
        return false;}
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        if(user.getVerified() == Boolean.FALSE){
            log.info("User account is not verified");
            return false;}
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }



}
