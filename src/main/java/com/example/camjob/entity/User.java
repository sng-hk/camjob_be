package com.example.camjob.entity;

import com.example.camjob.entity.role.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String nickname;
    private String phoneNumber;
    private LocalDate birthDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UserRole role; // Enum

    @OneToOne
    @JoinColumn(name = "major_id")
    private Major major;

    // 회원가입용 생성자
    public User(String email, String nickname, String phoneNumber, LocalDate birthDate, UserRole role) {
        this.email = email;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.role = role;
    }
}