package com.capstone.tech.controllers;

import com.capstone.tech.models.*;
import com.capstone.tech.repositories.*;
import com.capstone.tech.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;


@Controller
public class UserController {

    UserRepo userDao;
    UserDetailRepo userDetailDao;
    AvailabilityRepo availabilityRepo;
    UserAvailabilityRepo userAvailabilityRepo;
    UserRoles userRoles;
    UserService userSvc;
    private PasswordEncoder passwordEncoder;
    UserLanguagesRepo userLanguagesRepo;
    LanguagesRepo languagesRepo;


    public UserController(UserRepo userDao, UserDetailRepo userDetailDao, AvailabilityRepo availabilityRepo, UserAvailabilityRepo userAvailabilityRepo, UserRoles userRoles, UserService userSvc, PasswordEncoder passwordEncoder, UserLanguagesRepo userLanguagesRepo, LanguagesRepo languagesRepo) {
        this.userDao = userDao;
        this.userDetailDao = userDetailDao;
        this.availabilityRepo = availabilityRepo;
        this.userAvailabilityRepo = userAvailabilityRepo;
        this.userRoles = userRoles;
        this.userSvc = userSvc;
        this.passwordEncoder = passwordEncoder;
        this.userLanguagesRepo = userLanguagesRepo;
        this.languagesRepo = languagesRepo;
    }



    /**======= User Registration =======**/

    @GetMapping("/register")
    private String showRegisterForm(Model viewModel){
        viewModel.addAttribute("user", new User());
        return "users/register";
    }

    @PostMapping("/register")
    public String saveUser(@Valid User user, Errors validation, Model viewModel){

        String username = user.getUsername();
        User existingUsername = userDao.findByUsername(username);
        User existingEmail = userDao.findByEmail(user.getEmail());

        System.out.println(existingEmail);
        System.out.println(existingUsername);


        // Custom validation if the username is taken
        if(existingUsername != null){
            validation.rejectValue("username", "user.username", username + " is already registered - please login or choose another username");
        }

        // Custom validation if the email is taken
        if(existingEmail != null){
            validation.rejectValue("email", "user.email", user.getEmail() + " is already registered - please login or choose another email");
        }

        if (validation.hasErrors()) {
            viewModel.addAttribute("errors", validation);
            viewModel.addAttribute("user", user);
            return "users/register";
        }

        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userDao.save(user);

        User newUser = userDao.save(user);

        UserRole ur = new UserRole();
        ur.setRole("ROLE_USER");
        ur.setUserId(newUser.getId());
        userRoles.save(ur);

        // Programmatic login after registering a user
        userSvc.authenticate(user);

        viewModel.addAttribute("user", user);
        return "redirect:/posts";

    }

    // Show all users
    @GetMapping("/users")
    private String index(Model viewModel) {
        List<User> allUsers = userDao.findAll();
        HashMap<Long, Long> usersTotalPosts = new HashMap<>();
        HashMap<Long, Long> usersTotalComments = new HashMap<>();

        for (User user : allUsers) {
            usersTotalPosts.put(user.getId(), userDao.findNumberOfPostsById(user.getId()));
            usersTotalComments.put(user.getId(), userDao.findNumberOfCommentsById(user.getId()));
        }

        viewModel.addAttribute("users", userDao.findAll());
        viewModel.addAttribute("usersPosts", usersTotalPosts);
        viewModel.addAttribute("usersComments", usersTotalComments);
        viewModel.addAttribute("users", userDao.findAll());
        return "users/all-users";
    }

    // Show individual user profile
    @GetMapping("/users/{id}")
    private String show(@PathVariable long id, Model viewModel) {
        User user = userDao.findOne(id);
        viewModel.addAttribute("user", user);
        viewModel.addAttribute("showEditControls", userSvc.canEditProfile(user));
        viewModel.addAttribute("languages", new Languages());
        viewModel.addAttribute("availability", new Availability());
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
    // Adding user coding languages
    @PostMapping("/users/show-user/add-language")
    private String addLanguage( @ModelAttribute UserLanguages userLanguages, @ModelAttribute Languages languages) {
        languagesRepo.save(languages);
        userLanguages.setUser(userSvc.loggedInUser());
        userLanguages.setLanguages(languagesRepo.findOne(languages.getId()));
        userLanguagesRepo.save(userLanguages);
        return "redirect:/users/" + userSvc.loggedInUser().getId();
    }

    // Adding user availability
    @PostMapping("/users/show-user/availability-add")
    private String addAvailability(@ModelAttribute UserAvailability userAvailability, @ModelAttribute Availability availability) {
        availabilityRepo.save(availability);
        userAvailability.setUser(userSvc.loggedInUser());
        userAvailability.setAvailability(availabilityRepo.findOne(availability.getId()));
        userAvailabilityRepo.save(userAvailability);
        return "redirect:/users/" + userSvc.loggedInUser().getId();
    }
}






























