package com.capstone.tech.controllers;

import com.capstone.tech.models.Availability;
import com.capstone.tech.repositories.AvailabilityRepo;
import com.capstone.tech.repositories.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AvailabilityController {

    AvailabilityRepo availabilityDao;
    UserRepo userDao;

    private AvailabilityController(AvailabilityRepo availabilityDao, UserRepo userDao) {
        this.availabilityDao = availabilityDao;
        this.userDao = userDao;
    }


    // Schedule Availability


    // Show All
    @GetMapping("/availability")
    private String allAvailability(Model viewModel) {
        viewModel.addAttribute("availability", availabilityDao.findAll());
        return "availability";
    }

    // Show One
    @GetMapping("/availability/{id}")
    private String individualAvailability(@PathVariable long id, Model viewModel) {
        viewModel.addAttribute("availability", availabilityDao.findOne(id));
        return "availability/show-post";
    }

    // Create Availability
    @GetMapping("/availability/{id}/create")
    private String availabilityCreateForm(@PathVariable long id, Model viewModel) {
        viewModel.addAttribute("id", id);
        viewModel.addAttribute("availability", new Availability());
        return "availability/create";
    }

    @PostMapping("/availability/{id}/create")
    private String insertAvailability(@PathVariable long id, @ModelAttribute Availability availability) {
        availability.setUser(userDao.findOne(id));
        availabilityDao.save(availability);
        return "redirect:/availability/" + id;
    }

}
