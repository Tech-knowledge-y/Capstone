package com.capstone.tech.services;

import com.capstone.tech.models.User;
import com.capstone.tech.repositories.UserRepo;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;



@Service
public class UserService {

    private UserRepo userRepo;

    public UserService (UserRepo userRepo){
        this.userRepo = userRepo;
    }


//this checks to see if there is a logged in authenticated user
    public boolean isLoggedIn (){
        if(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) {
            return false;
        }
        return true;
    }

    public User currentUser(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepo.findOne(user.getId());
    }



}
