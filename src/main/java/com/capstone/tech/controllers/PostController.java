package com.capstone.tech.controllers;

import com.capstone.tech.models.Post;
import com.capstone.tech.models.User;
import com.capstone.tech.repositories.PostRepo;
import com.capstone.tech.repositories.UserRepo;
import com.capstone.tech.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    PostRepo postDao;
    UserRepo userDao;
    UserService userSvc;


    private PostController(PostRepo postDao, UserRepo userDao, UserService userSvc) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.userSvc = userSvc;
    }



    @GetMapping("/posts")
    private String showAllPosts(Model model) {
        model.addAttribute("posts", postDao.findAll());
        return "posts/all-posts";
    }



    @GetMapping("/posts/{id}")
    private String show(@PathVariable long id, Model model) {
        model.addAttribute("post", postDao.findOne(id));

        if(postDao.findOne(id).getUser()==userSvc.currentUser()){
            model.addAttribute("isOwner", true);
        }

        return "posts/show";
    }


    @GetMapping("/posts/create")
    private String createPost(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    private String insertPost(@ModelAttribute Post post) {
        post.setUser(userSvc.currentUser());
        postDao.save(post);
        return "redirect:/posts/" + post.getId();
    }



    @GetMapping("/posts/edit/{id}")
    private String postEditForm(@PathVariable long id, Model model) {
        model.addAttribute("post", postDao.findOne(id));
        return "posts/edit";
    }

    @PostMapping("/posts/edit")
    private String updatePost(@ModelAttribute Post post) {
        postDao.save(post);

        return "redirect:/posts/" + post.getId();
    }

    @GetMapping("/posts/delete/{id}")
    private String deletePost(@PathVariable long id){
        postDao.delete(id);
        return "redirect:/posts";
    }

//    @GetMapping("/find-user/{query}")
//    @ResponseBody
//    private String findUser(@PathVariable String query){
//
//        User user = userDao.findByUsername(query);
//
//        System.out.println("user.getEmail() = " + user.getEmail());
//
//        return "testing find by username";
//
//    }


}






