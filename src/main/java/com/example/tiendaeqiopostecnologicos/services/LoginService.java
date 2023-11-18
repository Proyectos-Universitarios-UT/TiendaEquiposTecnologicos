package com.example.tiendaeqiopostecnologicos.services;

import com.example.tiendaeqiopostecnologicos.entities.User;
import com.example.tiendaeqiopostecnologicos.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService{

    @Autowired
    private UserRepository userRepository;
    public String login(String username, String password) {
    Optional<User> user = userRepository.findByUsername(username);

        if(user.isPresent()) {
            Authentication auth = new UsernamePasswordAuthenticationToken(user, null);
            SecurityContextHolder.getContext().setAuthentication(auth);
            return "redirect:/home";
        } else {
            return "login";
        }
    }
}
