package com.example.camjob.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class VolunteerScrap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 사용자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // 외래키 이름 명시
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "volunteer_post_id")
    private VolunteerPost volunteerPost;

    // 생성자
    public VolunteerScrap(User user, VolunteerPost volunteerPost) {
        this.user = user;
        this.volunteerPost = volunteerPost;
    }
}
