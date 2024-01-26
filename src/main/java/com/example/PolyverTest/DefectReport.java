package com.example.PolyverTest;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Data
@Entity
public class DefectReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDate timeStamp;

    private int size;
    private String defect;


//    public LocalDate getTimeStamp() {
//        return timeStamp;
//    }
//
//    public void setTimeStamp(LocalDate timeStamp) {
//        this.timeStamp = timeStamp;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public int getSize() {
//        return size;
//    }
//
//    public void setSize(int size) {
//        this.size = size;
//    }
//
//    public String getDefect() {
//        return defect;
//    }
//
//    public void setDefect(String defect) {
//        this.defect = defect;
//    }
}