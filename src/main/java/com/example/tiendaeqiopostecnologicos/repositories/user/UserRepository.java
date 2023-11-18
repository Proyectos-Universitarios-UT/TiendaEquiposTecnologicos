package com.example.tiendaeqiopostecnologicos.repositories.user;

import com.example.tiendaeqiopostecnologicos.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);
    boolean existsById(Long id);
    User findByEmail(String email);
    Optional<User> findByUsername(String username);
}
