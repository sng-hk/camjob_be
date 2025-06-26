package com.example.camjob.scheduler;

import com.example.camjob.entity.User;
import com.example.camjob.entity.role.UserRole;
import com.example.camjob.repository.UserRepository;
import com.example.camjob.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Slf4j
@RequiredArgsConstructor
public class TestUserInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Override
    public void run(String... args) throws Exception {
        User testUser = userRepository.findByEmail("test@example.com")
                .orElseGet(() -> {
                    User newUser = User.builder()
                            .nickname("test_user")
                            .email("test@example.com")
                            .role(UserRole.ROLE_USER)
                            .phoneNumber("010-0000-0000")
                            .birthDate(LocalDate.of(2000, 1, 1))
                            .build();
                    User saved = userRepository.save(newUser);
                    log.info("✅ 테스트 유저 생성 완료: {}", saved.getNickname());
                    return saved;
                });

        String token = jwtUtil.generateToken(testUser.getEmail(), testUser.getNickname());
        log.info("🔑 테스트 유저 JWT 토큰: {}", token);
    }
}
