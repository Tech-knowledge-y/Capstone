package com.capstone.tech.controllers;

import com.capstone.tech.models.Comments;
import com.capstone.tech.models.Post;
import com.capstone.tech.repositories.CommentsRepo;
import com.capstone.tech.repositories.PostRepo;
import com.capstone.tech.repositories.UserRepo;
import com.capstone.tech.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {

    PostRepo postDao;
    UserRepo userDao;
    CommentsRepo commentsDao;
    UserService userSvc;

    private PostController(PostRepo postDao, UserRepo userDao, CommentsRepo commentsDao, UserService userSvc) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.commentsDao = commentsDao;
        this.userSvc = userSvc;
    }


    // Show all posts
    @GetMapping("/posts")
    private String showAllPosts(Model model) {
        model.addAttribute("posts", postDao.findAll());
        return "posts/all-posts";
    }


    // Show an individual post
    @GetMapping("/posts/{id}")
    private String show(@PathVariable long id, Model model) {
        model.addAttribute("post", postDao.findOne(id));
        model.addAttribute("comment", new Comments());
        model.addAttribute("allComments", commentsDao.findAll());
        if(postDao.findOne(id).getUser()==userSvc.loggedInUser()){
            model.addAttribute("isOwner", true);
        }
        return "posts/show";
    }

    // Commenting
    @PostMapping("/posts/{pId}/{cId}")
    public String commenting(@PathVariable long pId, @ModelAttribute Post post, @ModelAttribute Comments comments) {
        comments.setUser(userSvc.loggedInUser());
        comments.setPost(postDao.findOne(pId));
        commentsDao.save(comments);
        return "redirect:/posts/" + pId;
    }

    @GetMapping("/posts/create")
    private String createPost(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    private String insertPost(@ModelAttribute Post post) {
        post.setUser(userSvc.loggedInUser());
        postDao.save(post);
        return "redirect:/posts/" + post.getId();
    }

    @GetMapping("/posts/{id}/edit")
    private String postEditForm(@PathVariable long id, Model model) {
        model.addAttribute("post", postDao.findOne(id));
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    private String updatePost(@ModelAttribute Post post) {
        postDao.save(post);
        return "redirect:/posts/" + post.getId();
    }

    @PostMapping("/posts/delete")
    public String deletePost(@RequestParam(name = "id") long id){
        postDao.delete(id);
        return "redirect:/posts";
    }
}