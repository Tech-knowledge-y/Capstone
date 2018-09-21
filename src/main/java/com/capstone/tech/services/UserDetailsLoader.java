package com.capstone.tech.services;

import com.capstone.tech.models.User;
import com.capstone.tech.models.UserWithRoles;
import com.capstone.tech.repositories.UserRepo;
import com.capstone.tech.repositories.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("customUserDetailsService")
public class UserDetailsLoader implements UserDetailsService {
    private final UserRepo usersRepo;
    private final UserRoles roles;

    @Autowired
    public UserDetailsLoader(UserRepo usersRepository, UserRoles roles) {
        this.usersRepo = usersRepository;
        this.roles = roles;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usersRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user found for " + username);
        }

        List<String> userRoles = roles.ofUserWith(username);
        return new UserWithRoles(user, userRoles);
    }
}