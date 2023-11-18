package com.example.tiendaeqiopostecnologicos.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    @Column(name="user_id")
    private Long id;

    @Column(name="user_name")
    private String username;

    @Column(name="user_passwd")
    private String password;

    @Column(name="user_email")
    private String email;

    @ElementCollection(fetch= FetchType.EAGER)
    @CollectionTable(
            name="roles",
            joinColumns = @JoinColumn(name="user_id")
    )
    @Column(name="user_role")
    private List<String> roles;
}
