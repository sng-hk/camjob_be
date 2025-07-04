package com.example.camjob.controller;


import com.example.camjob.dto.VolunteerPostResponseDto;
import com.example.camjob.entity.User;
import com.example.camjob.repository.UserRepository;
import com.example.camjob.repository.VolunteerPostRepository;
import com.example.camjob.service.VolunteerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/volunteer")
public class VolunteerController {

    private final VolunteerPostRepository volunteerPostRepository;
    private final UserRepository userRepository;
    private final VolunteerService volunteerService;

    @GetMapping
    public ResponseEntity<List<VolunteerPostResponseDto>> getList() {
        List<VolunteerPostResponseDto> result = volunteerPostRepository.findAllWithMajor();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/major")
    public ResponseEntity<List<VolunteerPostResponseDto>> getListByMajor(@RequestParam(required = false) String major, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("인증된 사용자가 아닙니다.");
        }
        Map<String, String> userInfo = (Map<String, String>) authentication.getPrincipal();
        String email = userInfo.get("email");
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("해당 이메일의 사용자가 없습니다."));

        if (major == null || major.isBlank()) {
            return ResponseEntity.ok(volunteerPostRepository.findAllByMajor(user.getMajor().getName()));
        } else { // 필터 조회
            return ResponseEntity.ok(volunteerPostRepository.findAllByMajor(major));
        }
    }

    // TODO : 봉사 정보 스크랩 추가 및 삭제
    @PostMapping("/scrap/{postId}")
    public ResponseEntity<?> scrap(Authentication authentication, @PathVariable Long volunteerPostId) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("인증된 사용자가 아닙니다.");
        }
        Map<String, String> userInfo = (Map<String, String>) authentication.getPrincipal();
        String email = userInfo.get("email");
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("해당 이메일의 사용자가 없습니다."));

        volunteerService.scrap(user, volunteerPostId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/scrap/{postId}")
    public ResponseEntity<?> unscrap(Authentication authentication, @PathVariable Long postId) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("인증된 사용자가 아닙니다.");
        }
        Map<String, String> userInfo = (Map<String, String>) authentication.getPrincipal();
        String email = userInfo.get("email");
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("해당 이메일의 사용자가 없습니다."));

        volunteerService.unscrap(user, postId);
        return ResponseEntity.ok().build();
    }


//
//    @DeleteMapping("/scrap")
//    public ResponseEntity<>
}
