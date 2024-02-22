package com.example.PolyverTest;

import com.example.PolyverTest.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findByUsername(String username);
}