package com.example.tiendaeqiopostecnologicos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/css/**").permitAll()
                        .requestMatchers("/fonts/**").permitAll()
                        .requestMatchers("/img/**").permitAll()
                        .requestMatchers("/js/**").permitAll()
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/home").authenticated()
                        .requestMatchers("/**").hasAuthority("ADMIN")
                        .requestMatchers("/api/products/show","/api/seles/**","/api/cart/**").hasAuthority("SELLER")
                        .requestMatchers("/api/products/**").hasAuthority("WINEMAKER")
                        .requestMatchers("api/reports/**","/api/users/register", "/api/saveUser", "/api/users")
                        .hasAnyAuthority("MANAGER", "ADMIN", "SELLER","WINEMAKER")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form

                        .permitAll().defaultSuccessUrl("/home", true)
                )
                .logout((logout) -> logout.permitAll())
                .authenticationProvider(authenticationProvider());

        return http.build();

    }

    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return authenticationProvider;
    }
}
