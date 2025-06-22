package com.example.camjob.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class VolunteerPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long externalId; // API의 seq 값
    private String title;
    private String description;
    private String organization;
    private String location;
    private String place;
    private String activityDate;
    private String status;
}