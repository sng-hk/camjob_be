package com.example.camjob.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "volunteerPost", cascade = CascadeType.ALL)
    private List<VolunteerPostMajorMapping> majorMappings = new ArrayList<>();
}