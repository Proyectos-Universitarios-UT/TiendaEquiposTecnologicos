package com.example.tiendaeqiopostecnologicos.services.impl;

import com.example.tiendaeqiopostecnologicos.entities.User;
import com.example.tiendaeqiopostecnologicos.repositories.user.UserRepository;
import com.example.tiendaeqiopostecnologicos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.unbescape.properties.PropertiesKeyEscapeLevel;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public Long saveUser(User user) {
        String pwd = user.getPassword();
        String pwdEncoded = passwordEncoder.encode(pwd);
        user.setPassword(pwdEncoded);
        user = userRepository.save(user);
        return user.getId();
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findOne(String username) {
        return Optional.empty();
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public Optional<User> validateUser(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        org.springframework.security.core.userdetails.User springUser = null;

        if (optionalUser.isEmpty()){
            throw new UsernameNotFoundException("User with usename "+ username+ " not found");
        }else {
            User user = optionalUser.get();
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    user.getRoles()
                            .stream()
                            .map(role -> new SimpleGrantedAuthority(role))
                            .collect(Collectors.toSet())
            );
        }

    }

}
