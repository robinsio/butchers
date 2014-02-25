package org.robins.io.butchers.persistence.domain;

import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jonrobins on 24/02/2014.
 */
public class User implements UserDetails
{
    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles = new HashSet<String>();


    protected User() {
    }


    public User(String username, String passwordHash) {

        this.username = username;
        this.password = passwordHash;
    }


    public Long getId() {

        return this.id;
    }


    public void setId(Long id) {

        this.id = id;
    }


    public Set<String> getRoles() {

        return this.roles;
    }


    public void setRoles(Set<String> roles) {

        this.roles = roles;
    }


    public void addRole(String role) {

        this.roles.add(role);
    }


    @Override
    public String getPassword() {

        return this.password;
    }


    public void setPassword(String password) {

        this.password = password;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Set<String> roles = this.getRoles();

        if (roles == null) {
            return Collections.emptyList();
        }

        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }

        return authorities;
    }


    @Override
    public String getUsername() {

        return this.username;
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
}
