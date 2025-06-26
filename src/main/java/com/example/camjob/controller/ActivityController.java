package com.example.camjob.controller;

import com.example.camjob.dto.ActivityDto;
import com.example.camjob.entity.Activity;
import com.example.camjob.service.ActivityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/activities")
public class ActivityController {
    private final ActivityService service;

    public ActivityController(ActivityService service) {
        this.service = service;
    }

    @GetMapping
    public List<Activity> getAll(@RequestParam Long userId) {
        return service.getByUserId(userId);
    }

    @PostMapping
    public Activity create(@RequestParam Long userId, @RequestBody ActivityDto dto) {
        return service.save(userId, dto);
    }
}