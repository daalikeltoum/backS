package com.example.newprojectoption.service;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.example.newprojectoption.bean.Userr;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Service;


public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String email;
    private GrantedAuthority authoritie;

    @JsonIgnore
    private String password;

    public GrantedAuthority getAuthoritie() {
        return authoritie;
    }

    public void setAuthoritie(GrantedAuthority authoritie) {
        this.authoritie = authoritie;
    }


    public UserDetailsImpl(Long id, String username, String email, String password,
                            GrantedAuthority authoritie) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authoritie = authoritie;
    }

    public static UserDetailsImpl build(Userr user) {
        GrantedAuthority authoritie=  new SimpleGrantedAuthority(user.getRole().getName());
        /*List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());*/

        return new UserDetailsImpl(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                authoritie);
    }

   /* @Override
    public  GrantedAuthority getAuthorities() {
        return authoritie;
    }*/
   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
       return null;


   }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }



    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}
