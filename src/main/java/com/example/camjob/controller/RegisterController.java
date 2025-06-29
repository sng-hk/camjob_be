package com.example.camjob.controller;

import com.example.camjob.dto.SignupRequestDto;
import com.example.camjob.entity.Major;
import com.example.camjob.repository.MajorRepository;
import com.example.camjob.repository.UserRepository;
import com.example.camjob.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class RegisterController {

    private final UserRepository userRepository;
    private final MajorRepository majorRepository;
    private final JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody SignupRequestDto request) {
        try {
            // 전공 조회 또는 생성
            Major major = majorRepository.findByName(request.getMajor())
                    .orElseGet(() -> majorRepository.save(new Major(null, request.getMajor())));

            userRepository.save(request.toEntity(major));

            // 회원가입 성공 → 토큰 발급
            String token = jwtUtil.generateToken(request.getEmail(), request.getNickname());

            return ResponseEntity.ok(Map.of("token", token));
        } catch (IllegalArgumentException e) {
            // 유효성 오류 등 클라이언트 잘못
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            // 서버 내부 오류
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "회원가입 처리 중 오류가 발생했습니다."));
        }
    }
}
