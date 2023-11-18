package com.example.tiendaeqiopostecnologicos.services;

import com.example.tiendaeqiopostecnologicos.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
public interface UserService {
    Long saveUser(User user);
    List<User> findAll();
    Optional<User> findOne(String username);
    User findById(Long id);
    User findByEmail(String email);
    Optional<User> findUserByUsername(String username);

    Optional<User> validateUser(String username, String password);
}
