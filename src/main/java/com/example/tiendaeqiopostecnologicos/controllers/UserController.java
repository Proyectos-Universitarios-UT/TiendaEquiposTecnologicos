package com.example.tiendaeqiopostecnologicos.controllers;

import com.example.tiendaeqiopostecnologicos.entities.User;
import com.example.tiendaeqiopostecnologicos.services.LoginService;
import com.example.tiendaeqiopostecnologicos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private LoginService loginService;


    @GetMapping("/register")
    public String register(){
        return "registerUser";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute User user, Model model){
        Long id = userService.saveUser(user);
        String message = "User "+id+" saved successfully!";
        model.addAttribute("msg", message);
        return "registerUser";
    }
}
