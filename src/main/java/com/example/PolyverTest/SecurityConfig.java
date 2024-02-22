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

        UserDetails defaultUser = User.builder()
                .username("user")
                //.password("user")
                .password(passwordEncoder().encode("user"))
                .roles("USER")
                .build();

        UserDetails workstation1 = User.builder()
                .username("ws1")
                //.password("user")
                .password(passwordEncoder().encode("1234"))
                .roles("USER")
                .build();

        UserDetails workstation2 = User.builder()
                .username("ws2")
                //.password("user")
                .password(passwordEncoder().encode("2345"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(adminUser, defaultUser, workstation1, workstation2);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/h2/**", "/images/**", "/css/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/user/**").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2/**") // Ignorera CSRF för H2 Console
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