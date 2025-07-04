package com.example.camjob.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class WorkPostResponseDto {
    private String title;
    private String department;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isClosed;
    private boolean isScrapped;
}
