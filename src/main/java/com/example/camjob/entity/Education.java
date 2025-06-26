package com.example.camjob.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId; // 사용자 ID

    private String schoolName;   // 학교명
    private String major;        // 전공
    private String degree;       // 학위 (예: 학사, 석사)

    private LocalDate startDate;
    private LocalDate endDate;
}