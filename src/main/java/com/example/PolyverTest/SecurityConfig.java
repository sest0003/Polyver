package com.example.PolyverTest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails adminUser = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
               // .password("admin")
                .roles("ADMIN")
                .build();

        UserDetails regularUser = User.builder()
                .username("user")
                //.password("user")
                .password(passwordEncoder().encode("user"))
                .roles("USER")
                .build();




        System.out.println("Lösenord för användaren 'user': " + regularUser.getPassword());
        return new InMemoryUserDetailsManager(adminUser, regularUser);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/h2/**").permitAll()
                        .requestMatchers("/login.css").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/user/**").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2/**") // Ignorera CSRF för H2 Console
                )
                .headers(headers -> headers
                        .frameOptions().sameOrigin() // Tillåt ramverk för H2 Console
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout(logout -> logout
                        .permitAll());


        return http.build();
    }
}