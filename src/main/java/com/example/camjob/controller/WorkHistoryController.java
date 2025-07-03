package com.example.camjob.controller;

import com.example.camjob.dto.EducationDto;
import com.example.camjob.dto.WorkDto;
import com.example.camjob.entity.Education;
import com.example.camjob.entity.Work;
import com.example.camjob.service.EducationService;
import com.example.camjob.service.WorkHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user/works")
public class WorkHistoryController {

    private final WorkHistoryService workHistoryService;

    public WorkHistoryController(WorkHistoryService workHistoryService) {
        this.workHistoryService = workHistoryService;
    }

    @GetMapping
    public List<Work> getUserWorks(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("인증된 사용자가 아닙니다.");
        }

        Map<String, String> userInfo = (Map<String, String>) authentication.getPrincipal();

        String email = userInfo.get("email");

        return workHistoryService.getByUserId(email);
    }

    @PostMapping
    public Work createUserWork(Authentication authentication, @RequestBody WorkDto dto) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("인증된 사용자가 아닙니다.");
        }

        Map<String, String> userInfo = (Map<String, String>) authentication.getPrincipal();

        String email = userInfo.get("email");
        return workHistoryService.save(email, dto);
    }
}