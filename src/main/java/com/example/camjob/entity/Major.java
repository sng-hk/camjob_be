package com.example.camjob.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Major {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "major", cascade = CascadeType.ALL)
    private List<VolunteerPostMajorMapping> volunteerPosts = new ArrayList<>();

    public Major(Long id, String name) {
        this.name = name;
    }
}

