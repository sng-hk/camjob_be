package com.example.camjob.dto;

import com.example.camjob.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoResponseDto {
    private String email;
    private String nickname;
    private String phoneNumber;
    private String birthDate;
    private String age;

    public UserInfoResponseDto(User user, String age) {
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.phoneNumber = user.getPhoneNumber();
        this.birthDate = user.getBirthDate().toString();
        this.age = age;
    }
}
