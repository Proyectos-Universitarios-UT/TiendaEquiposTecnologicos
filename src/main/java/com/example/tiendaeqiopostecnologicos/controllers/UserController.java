package com.example.tiendaeqiopostecnologicos.controllers;

import com.example.tiendaeqiopostecnologicos.entities.User;
import com.example.tiendaeqiopostecnologicos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users/register")
    public String register(Model model){
        return "users/registerUser";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute User user, Model model){
        Long id = userService.saveUser(user);
        String message = "User "+id+" saved successfully!";
        model.addAttribute("msg", message);
        return "registerUser";
    }

    @GetMapping("/users/list")
    public String listUsers(Model model){
        model.addAttribute("users", userService.findAll());
        System.out.println(userService.findAll());
        return "users/list_users";
    }
}
