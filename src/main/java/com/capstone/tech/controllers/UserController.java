package com.capstone.tech.controllers;

import com.capstone.tech.models.User;
import com.capstone.tech.repositories.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {

    UserRepo userDao;


    public UserController(UserRepo userDao) {
        this.userDao = userDao;
    }

    // User Registration
    @GetMapping("/register")
    private String showRegisterForm(Model viewModel){
        viewModel.addAttribute("user", new User());
        return "users/register";
    }

    @PostMapping("/register")
    private String saveUser(@ModelAttribute User user){
        userDao.save(user);
        return "redirect:/login";
    }

    // Show all users
    @GetMapping("/users")
    private String index(Model viewModel) {
        viewModel.addAttribute("users", userDao.findAll());
        return "users/all-users";
    }

    // Show individual user profile
    @GetMapping("/users/{id}")
    private String show(@PathVariable long id, Model viewModel) {
        viewModel.addAttribute("user", userDao.findOne(id));
        return "users/show-user";
    }

}
