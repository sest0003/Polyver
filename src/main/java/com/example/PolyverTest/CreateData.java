package com.example.PolyverTest;


import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class CreateData {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initData() {
//        Admin adminUser = new Admin();
//        adminUser.setUsername("admin");
//        adminUser.setPassword(passwordEncoder.encode("admin"));
//        adminUser.setRole("ADMIN");
//        userRepository.save(adminUser);
//
//        Admin workstation1 = new Admin();
//        workstation1.setUsername("ws1");
//        workstation1.setPassword(passwordEncoder.encode("1234"));
//        workstation1.setRole("USER");
//        userRepository.save(workstation1);
//
//
//        Admin workstation2 = new Admin();
//        workstation2.setUsername("ws2");
//        workstation2.setPassword(passwordEncoder.encode("2345"));
//        workstation2.setRole("USER");
//        userRepository.save(workstation2);

    }
}