package com.capstone.tech.controllers;

import com.capstone.tech.repositories.AvailabilityRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AvailabilityController {

    AvailabilityRepo availabilityDao;

    public AvailabilityController(AvailabilityRepo availabilityDao) {
        this.availabilityDao = availabilityDao;
    }



    @GetMapping("/availability")
    public String allAvailability(Model viewModel) {
        viewModel.addAttribute("availability", availabilityDao.findAll());
        return "availability";
    }

    @GetMapping("/availability/{id}")
    private String individualAvailability(Model viewModel) {
        view
        return "availability/show-post";
    }

}
