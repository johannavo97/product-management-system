package com.johanna.productmanagementproject.security;

import com.johanna.productmanagementproject.data.RoleRepository;
import com.johanna.productmanagementproject.data.UserRepository;
import com.johanna.productmanagementproject.models.Role;
import com.johanna.productmanagementproject.models.User;
import com.johanna.productmanagementproject.services.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service @Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AppUserDetailsService implements UserDetailsService {

    RoleRepository roleRepository;

    UserService userService;

    @Autowired
    public AppUserDetailsService(RoleRepository roleRepository, UserService userService) {
        this.roleRepository = roleRepository;
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        try {
             user = userService.findByEmail(username);

        } catch (NoSuchElementException ex){
            log.warn("Couldn't find email: " + username);
            ex.printStackTrace();
        } catch(UsernameNotFoundException e){
            log.warn("Couldn't find email: " + username);
            e.printStackTrace();
        }
        List<Role> authGroups = roleRepository.findByaEmail(username);
        return new AppUserPrincipal(user, authGroups);
    }
}
