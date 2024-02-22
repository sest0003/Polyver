package com.example.PolyverTest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface DamageRepository extends JpaRepository<DamageReport, Long> {
    @Query("SELECT r FROM DamageReport r WHERE r.timeStamp BETWEEN :startDate AND :endDate")
    List<DamageReport> findReportsBetweenDates(LocalDate startDate, LocalDate endDate);
}
