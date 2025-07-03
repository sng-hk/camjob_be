package com.example.camjob.controller;

import com.example.camjob.dto.ActivityDto;
import com.example.camjob.dto.EducationDto;
import com.example.camjob.entity.Activity;
import com.example.camjob.entity.Education;
import com.example.camjob.service.ActivityService;
import com.example.camjob.service.EducationService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/user/activities")
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping
    public List<Activity> getActivities(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("인증된 사용자가 아닙니다.");
        }

        Map<String, String> userInfo = (Map<String, String>) authentication.getPrincipal();

        String email = userInfo.get("email");

        return activityService.getByUserId(email);
    }

    @PostMapping
    public Activity createActivity(Authentication authentication, @RequestBody ActivityDto dto) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("인증된 사용자가 아닙니다.");
        }

        Map<String, String> userInfo = (Map<String, String>) authentication.getPrincipal();

        String email = userInfo.get("email");
        return activityService.save(email, dto);
    }
}