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

//    @GetMapping("/c")
//    public String defaultTemp() {
//        return "default";
//    }

    @GetMapping("/developers")
    public String developers() {
        return "developers";
    }

    @GetMapping("/resources")
    public String resources() {return "resources"; }

    @GetMapping("/behind-scenes")
    public String behindscenes() {
        return "behind-scenes";
    }

//    @GetMapping("/lightbox")
//    public String lightbox() {
//        return "lightbox";
//    }
//
//    @GetMapping("/test")
//    public String test() {
//        return "test";
//    }
}
