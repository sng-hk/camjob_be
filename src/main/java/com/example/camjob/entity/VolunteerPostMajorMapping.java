package com.example.camjob.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VolunteerPostMajorMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "volunteer_post_id")
    private VolunteerPost volunteerPost;

    @ManyToOne
    @JoinColumn(name = "major_id")
    private Major major;
}
