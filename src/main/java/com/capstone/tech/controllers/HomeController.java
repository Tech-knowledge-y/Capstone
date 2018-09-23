package com.capstone.tech.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "home";
    }

    @GetMapping("/home")
        public String home() {
            return "home";
    }

    @GetMapping("/c")
    public String defaultTemp() {
        return "default";
    }

    @GetMapping("/developers")
    public String developers() {
        return "developers";
    }

    @GetMapping("/resources")
    public String resources() {return "resources"; }

    @GetMapping("/availability")
    public String availability() {
        return "users/availability";
    }

    @GetMapping("/work")
    public String work() {
        return "work";
    }
}
