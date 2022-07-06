package com.johanna.productmanagementproject.security;


import com.johanna.productmanagementproject.models.Role;
import com.johanna.productmanagementproject.models.User;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/*
        Step 4: define a user to spring security
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppUserPrincipal implements UserDetails {

    User user;
    List<Role> role;


    public AppUserPrincipal(User user, List<Role> role) {
        this.user = user;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // check if list is empty
        if(role == null) return Collections.emptyList();
        // init a Set to disallow duplications
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        // loop through roleGroup list and adding each role to spring security Simple Granted Authority object
         role.forEach(auth -> authorities.add(new SimpleGrantedAuthority(auth.getRole())));
         // return the authorities wrapped in SimpleGrantedAuthority
         return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
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
