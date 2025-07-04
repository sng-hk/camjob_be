package com.example.camjob.controller;

import com.example.camjob.dto.UserInfoResponseDto;
import com.example.camjob.entity.User;
import com.example.camjob.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Period;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserRepository userRepository;
    @GetMapping("/info")
    public ResponseEntity<UserInfoResponseDto> info(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("인증된 사용자가 아닙니다.");
        }
        Map<String, String> userInfo = (Map<String, String>) authentication.getPrincipal();
        String email = userInfo.get("email");
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("해당 이메일의 사용자가 없습니다."));
        String age = Period.between(LocalDate.now(), user.getBirthDate()).getYears() + "";
        return ResponseEntity.ok(new UserInfoResponseDto(user, age));
    }
}
