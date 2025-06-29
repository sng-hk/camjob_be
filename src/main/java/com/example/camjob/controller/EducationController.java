package com.example.camjob.controller;

import com.example.camjob.dto.EducationDto;
import com.example.camjob.entity.Education;
import com.example.camjob.service.EducationService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user/educations")
public class EducationController {

    private final EducationService educationService;

    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @GetMapping
    public List<Education> getEducations(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("인증된 사용자가 아닙니다.");
        }

        Map<String, String> userInfo = (Map<String, String>) authentication.getPrincipal();

        String email = userInfo.get("email");

        return educationService.getByUserId(email);
    }

    @PostMapping
    public Education createEducation(Authentication authentication, @RequestBody EducationDto dto) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("인증된 사용자가 아닙니다.");
        }

        Map<String, String> userInfo = (Map<String, String>) authentication.getPrincipal();

        String email = userInfo.get("email");
        return educationService.save(email, dto);
    }
}