package com.capstone.tech.controllers;

import com.capstone.tech.models.Post;
import com.capstone.tech.repositories.PostRepo;
import com.capstone.tech.repositories.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
    private String show(@PathVariable long id, Model viewModel) {
        viewModel.addAttribute("post", postsRepo.findOne(id));
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String postCreateForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String insertPost(@ModelAttribute Post post) {
        post.setUser(UserRepo.findOne(2L));
        Post.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String postEditForm(@PathVariable long id, Model model) {
        model.addAttribute("post", PostRepo.findOne(id));
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String updatePost(@ModelAttribute Post post) {
        PostRepo.save(post);
        return "redirect:/posts";
    }








}


