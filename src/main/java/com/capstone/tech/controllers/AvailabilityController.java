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


    // Create Availability
    @GetMapping("/users/{id}/availability/create")
    private String createUserAvailability(@PathVariable long id, Model viewModel) {
        viewModel.addAttribute("id", id);
        viewModel.addAttribute("availability", new Availability());
        return "users/availability-create";
    }

    @PostMapping("/users/{id}/availability/create")
    private String insertUserAvailability(@PathVariable long id, @ModelAttribute Availability availability) {
        availability.setUser(userDao.findOne(id));
        availabilityDao.save(availability);
        return "redirect:/users/" + id;
    }



    // Edit Availability
    @GetMapping("users/{id}/details/edit")
    private String editUserAvailability(@PathVariable long id, Model viewModel) {
        viewModel.addAttribute("availability", availabilityDao.findOne(id));
        return "users/availability-edit";
    }

    @PostMapping("/users/{id}/availability/edit")
    private String updateUserAvailability(@PathVariable long id, @ModelAttribute Availability availability) {
        availability.setUser(userDao.findOne(id));
        availabilityDao.save(availability);
        return "redirect:/users/" + id;
    }




    @PostMapping("/users/{id}/availability/delete")
    private String deleteUserAvailability(@PathVariable long id) {
        availabilityDao.delete(userDao.findOne(id).getAvailability());
        return "redirect:/users/" + id;
    }
}
