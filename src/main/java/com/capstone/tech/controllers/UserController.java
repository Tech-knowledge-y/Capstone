package com.capstone.tech.controllers;

import com.capstone.tech.models.Availability;
import com.capstone.tech.models.User;
import com.capstone.tech.models.UserDetail;
import com.capstone.tech.repositories.AvailabilityRepo;
import com.capstone.tech.repositories.UserDetailRepo;
import com.capstone.tech.repositories.UserRepo;
import com.capstone.tech.repositories.UserRoles;
import com.capstone.tech.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {

    UserRepo userDao;
    UserDetailRepo userDetailDao;
    AvailabilityRepo availabilityDao;
    UserRoles userRoles;
    UserService userSvc;
    private PasswordEncoder passwordEncoder;


    public UserController(UserRepo userDao, UserDetailRepo userDetailDao, AvailabilityRepo availabilityDao, UserRoles userRoles, UserService userSvc, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.userDetailDao = userDetailDao;
        this.availabilityDao = availabilityDao;
        this.userRoles = userRoles;
        this.userSvc = userSvc;
        this.passwordEncoder = passwordEncoder;
    }



    /**======= User Registration =======**/

    @GetMapping("/register")
    private String showRegisterForm(Model viewModel){
        viewModel.addAttribute("user", new User());
        return "users/register";
    }

    @PostMapping("/register")
    private String saveUser(@ModelAttribute User user, Model model){
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
//        viewModel.addAttribute("user", userDao.findOne(id));
        User user = userDao.findOne(id);
        viewModel.addAttribute("user", user);
        viewModel.addAttribute("showEditControls", userSvc.canEditProfile(user));
        return "users/show-user";
    }



    /**======= Users and User Details =======**/

    @GetMapping("/users/details/create")
    private String createUserDetails(Model viewModel) {
        viewModel.addAttribute("userDetail", new UserDetail());
        return "users/details-create";
    }

    @PostMapping("/users/details/create")
    private String insertUserDetails(@ModelAttribute UserDetail userDetail) {
        userDetail.setUser(userSvc.loggedInUser());
        userDetailDao.save(userDetail);
        return "redirect:/users/" + userSvc.loggedInUser().getId();
    }

    @GetMapping("/users/{id}/details/edit")
    private String editUserDetails(@PathVariable long id, Model viewModel) {
        viewModel.addAttribute("userDetail", userDetailDao.findOne(id));
//        viewModel.addAttribute("isOwner", userSvc.isOwner());
        return "users/details-edit";
    }

    @PostMapping("/users/{id}/details/edit")
    private String updateUserDetails(@ModelAttribute UserDetail userDetail) {
        userDetail.setUser(userSvc.loggedInUser());
        userDetailDao.save(userDetail);
        return "redirect:/users/" + userSvc.loggedInUser().getId();
    }

    @PostMapping("/users/{id}/details/delete")
    private String deleteUserDetails(@PathVariable long id) {
        userDetailDao.delete(userDao.findOne(id).getUserDetail().getId());
        return "redirect:/users/" + id;
    }



    /**======= Users and User Availability =======**/

    @GetMapping("/users/availability/create")
    private String createUserAvailability(Model viewModel) {
        viewModel.addAttribute("availability", new Availability());
        return "users/availability-create";
    }

    @PostMapping("/users/availability/create")
    private String insertUserAvailability(@ModelAttribute Availability availability) {
        availability.setUser(userSvc.loggedInUser());
        availabilityDao.save(availability);
        return "redirect:/users/" + userSvc.loggedInUser().getId();
    }

    @GetMapping("/users/{id}/availability/edit")
    private String editUserAvailability(@PathVariable long id, Model viewModel) {
        viewModel.addAttribute("availability", availabilityDao.findOne(id));
//        viewModel.addAttribute("showEditControls", userSvc.canEditProfile(userSvc.loggedInUser()));
        return "users/availability-edit";
    }

    @PostMapping("/users/{id}/availability/edit")
    private String updateUserAvailability(@ModelAttribute Availability availability) {
        availability.setUser(userSvc.loggedInUser());
        availabilityDao.save(availability);
        return "redirect:/users/" + userSvc.loggedInUser().getId();
    }

    @PostMapping("/users/{id}/availability/delete")
    private String deleteUserAvailability(@PathVariable long id) {
        availabilityDao.delete(userDao.findOne(id).getAvailability());
        return "redirect:/users/" + id;
    }

}






























