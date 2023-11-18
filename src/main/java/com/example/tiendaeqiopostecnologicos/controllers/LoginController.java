package com.example.tiendaeqiopostecnologicos.controllers;

import com.example.tiendaeqiopostecnologicos.entities.User;
import com.example.tiendaeqiopostecnologicos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password) {

        // Validar usuario y contraseña
        Optional<User> user = userService.validateUser(username, password);

        if(user != null) {
            // Autenticar manualmente al usuario
            Authentication auth = new UsernamePasswordAuthenticationToken(user, null);
            SecurityContextHolder.getContext().setAuthentication(auth);

            // Redireccionar a homepage
            return "redirect:/home";

        } else {
            return "login"; // volver al formulario de login
        }

    }

}
