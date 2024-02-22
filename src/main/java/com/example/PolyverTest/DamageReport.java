package com.example.PolyverTest;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Data
@Entity
@Table(name="DAMAGE_REPORT")
public class DamageReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDate timeStamp;

    private String product;

    private int size;

    private String damage;


    @ManyToOne
    @JoinColumn(name = "reported_by")
    private Admin reportedBy;


}