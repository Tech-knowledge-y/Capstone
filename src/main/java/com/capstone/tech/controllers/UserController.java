package com.capstone.tech.controllers;

import com.capstone.tech.repositories.UserRepo;
import org.springframework.stereotype.Controller;


@Controller
public class UserController {

    UserRepo userDao;


    public UserController(UserRepo userDao) {
        this.userDao = userDao;
    }

}
