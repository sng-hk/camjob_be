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
public class Work {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String workTitle;     // 근로명
    private String department;    // 부서 또는 기관
    private LocalDate startDate;
    private LocalDate endDate;
}