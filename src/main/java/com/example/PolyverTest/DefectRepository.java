package com.example.PolyverTest;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DefectRepository extends JpaRepository<DefectReport, Long> {
    // Du kan lägga till metoder här för att söka och hämta defekter
}
