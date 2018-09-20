package com.capstone.tech.controllers;

import com.capstone.tech.models.User;
import com.capstone.tech.models.UserDetail;
import com.capstone.tech.repositories.UserDetailRepo;
import com.capstone.tech.repositories.UserRepo;
import com.capstone.tech.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {

    @Autowired
    UserRepo userDao;

    @Autowired
    UserDetailRepo userDetailDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Autowired
//    UserRoles userRoles;

    @Autowired
    UserService userSvc;

    // User Registration
    @GetMapping("/register")
    private String showRegisterForm(Model viewModel){
        viewModel.addAttribute("user", new User());
        return "users/register";
    }

    @PostMapping("/register")
    private String saveUser(@ModelAttribute User user){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
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







    //Users and User Details

    @GetMapping("/users/details/create")
    private String createUserDetails(Model viewModel) {
        viewModel.addAttribute("userDetail", new UserDetail());
        return "users/details-create";
    }

    @PostMapping("/users/details/create")
    private String insertUserDetails(@ModelAttribute UserDetail userDetail) {
        userDetail.setUser(userSvc.loggedInUser());
//        System.out.println(userDetail.getUser());
//        System.out.println(userDetail.getCity());
//        System.out.println(userDetail.getId());
        userDetailDao.save(userDetail);
//        System.out.println(userDetail.getId());
        return "redirect:/users/" + userSvc.loggedInUser().getId();
    }




    //========================== Not working ==========================//
    @GetMapping("/users/{id}/details/edit")
    private String editUserDetails(@PathVariable long id, Model viewModel) {
        viewModel.addAttribute("userDetail", userDetailDao.findOne(id));
//        viewModel.addAttribute("showEditControls", userSvc.canEditProfile(userSvc.loggedInUser()));
        return "users/details-create";
    }

    @PostMapping("/users/{id}/details/edit")
    private String updateUserDetails(@ModelAttribute UserDetail userDetail) {
        userDetail.setUser(userSvc.loggedInUser());
        userDetailDao.save(userDetail);
        return "redirect:/users";
    }





    @PostMapping("/users/{id}/details/delete")
    private String deleteUserDetails(@PathVariable long id) {
        userDetailDao.delete(userDao.findOne(id).getUserDetail().getId());
        return "redirect:/users/" + id;
    }








//    // Create Availability
//    @GetMapping("/users/{id}/availability/create")
//    private String createUserAvailability(@PathVariable long id, Model viewModel) {
//        viewModel.addAttribute("id", id);
//        viewModel.addAttribute("availability", new Availability());
//        return "users/availability-create";
//    }
//
//    @PostMapping("/users/{id}/availability/create")
//    private String insertUserAvailability(@PathVariable long id, @ModelAttribute Availability availability) {
//        availability.setUser(userDao.findOne(id));
//        availabilityDao.save(availability);
//        return "redirect:/users/" + id;
//    }
//
//    // Edit Availability
//    @GetMapping("users/{id}/details/edit")
//    private String editUserAvailability(@PathVariable long id, Model viewModel) {
//        viewModel.addAttribute("availability", availabilityDao.findOne(id));
//        return "users/availability-edit";
//    }
//
//    @PostMapping("/users/{id}/availability/edit")
//    private String updateUserAvailability(@PathVariable long id, @ModelAttribute Availability availability) {
//        availability.setUser(userDao.findOne(id));
//        availabilityDao.save(availability);
//        return "redirect:/users/" + id;
//    }
//
//    @PostMapping("/users/{id}/availability/delete")
//    private String deleteUserAvailability(@PathVariable long id) {
//        availabilityDao.delete(userDao.findOne(id).getAvailability());
//        return "redirect:/users/" + id;
//    }

}