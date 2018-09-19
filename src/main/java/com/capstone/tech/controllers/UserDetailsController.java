package com.capstone.tech.controllers;

import com.capstone.tech.models.UserDetails;
import com.capstone.tech.repositories.UserDetailsRepo;
import com.capstone.tech.repositories.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserDetailsController {
    UserDetailsRepo userDetailsdao;
    UserRepo userDao;

    public UserDetailsController(UserDetailsRepo userDetailsdao, UserRepo userDao) {

        this.userDetailsdao = userDetailsdao;
        this.userDao = userDao;
    }


/////users and userDetails





    @GetMapping("/editProfile")
    public String showEditProfileForm() {
        return "users/editProfileForm";
    }

    @PostMapping("/editProfile")
    public String updateProfile(@ModelAttribute UserDetails userDetails) {
        userDetailsdao.save(userDetails);
        return "users/profile";
    }

//    @PostMapping("/posts/delete")
//    public String deleteUserDetails(@RequestParam(firstName = "id") long id){
//        userDetailsdao.deleteUserDetailsByLNameMatches()
////        return "redirect:/posts";
//    }



}
