package com.example.PolyverTest;

import com.example.PolyverTest.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Admin, Long> {
    Admin findByUsername(String username);
}