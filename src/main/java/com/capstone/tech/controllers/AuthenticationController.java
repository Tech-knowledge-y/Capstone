package com.capstone.tech.controllers;

import com.capstone.tech.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {
    @GetMapping("/login")
    public String showLoginForm(Model viewModel) {
        viewModel.addAttribute("user", new User());
        return "users/login";
    }
}