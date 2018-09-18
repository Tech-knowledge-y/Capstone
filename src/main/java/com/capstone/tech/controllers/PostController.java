package com.capstone.tech.controllers;

import com.capstone.tech.models.Post;
import com.capstone.tech.models.User;
import com.capstone.tech.repositories.PostRepo;
import com.capstone.tech.repositories.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    PostRepo postDao;
    UserRepo userDao;

    public PostController(PostRepo postDao, UserRepo userDao) {
        this.postDao = postDao;
        this.userDao = userDao;
    }

    @GetMapping("/posts/all-posts")
    private String showAllPosts(Model model) {
        model.addAttribute("posts", postDao.findAll());
        return "posts/all-posts";
    }


    @GetMapping("/posts/{id}")
    public String show(@PathVariable long id, Model viewModel) {
        viewModel.addAttribute("posts", postDao.findOne(id));
        return "posts/show";
    }

    @GetMapping("/posts/create")
    private String postCreateForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    private String insertPost(@ModelAttribute Post post) {
        post.setUser(userDao.findOne(2L));
        postDao.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    private String postEditForm(@PathVariable long id, Model model) {
        model.addAttribute("post", postDao.findOne(id));
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    private String updatePost(@ModelAttribute Post post) {
        postDao.save(post);
        return "redirect:/posts";
    }

    @PostMapping("/posts/delete")
    private String deletePost(@RequestParam(name = "id") long id){
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






