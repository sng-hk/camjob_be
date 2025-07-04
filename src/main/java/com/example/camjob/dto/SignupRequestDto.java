package com.example.camjob.dto;

import com.example.camjob.entity.Major;
import com.example.camjob.entity.User;
import com.example.camjob.entity.role.UserRole;
import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Getter
public class SignupRequestDto {
    private String email;
    private String nickname;
    private String birthDate;
    private String phoneNumber;
    private String major; // 전공 이름

    public User toEntity(Major major) {
        LocalDate parsedBirthDate;
        try {
            parsedBirthDate = LocalDate.parse(this.birthDate);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("생년월일 형식이 잘못되었습니다. yyyy-MM-dd 형식이어야 합니다.");
        }

        return User.builder()
                .email(this.email)
                .nickname(this.nickname)
                .phoneNumber(this.phoneNumber)
                .birthDate(parsedBirthDate)
                .role(UserRole.ROLE_USER) // TODO: 추후 수정 필요 (ex. role을 requestbody로 받아오는 방식)
                .build();
    }

}
