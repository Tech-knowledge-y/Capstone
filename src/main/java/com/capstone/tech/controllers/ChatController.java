package com.capstone.tech.controllers;

import com.capstone.tech.models.ChatMessage;
import com.capstone.tech.models.User;
import com.capstone.tech.repositories.UserRepo;
import com.capstone.tech.services.UserService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ChatController {

    UserRepo userDao;
    UserService userSvc;

    public ChatController(UserRepo userDao, UserService userSvc) {
        this.userDao = userDao;
        this.userSvc = userSvc;
    }



//    @GetMapping("/chat")
//    private String chat() {
//        return "chat";
//    }

    @GetMapping("/chat/{username}")
    private String chat(@PathVariable String username, Model viewModel) {
        viewModel.addAttribute("user", userDao.findByUsername(username));
        return "chat";
    }

    @PostMapping("/chat/{username}")
    private String chat(@ModelAttribute User user) {
        user.getUsername();
        userDao.save(user);
        return"chat" + userSvc.loggedInUser().getUsername();
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
}