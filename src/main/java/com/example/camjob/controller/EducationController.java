package com.example.camjob.controller;

import com.example.camjob.dto.EducationDto;
import com.example.camjob.entity.Education;
import com.example.camjob.service.EducationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/educations")
public class EducationController {

    private final EducationService educationService;

    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @GetMapping
    public List<Education> getEducations(@RequestParam Long userId) {
        return educationService.getByUserId(userId);
    }

    @PostMapping
    public Education createEducation(@RequestParam Long userId, @RequestBody EducationDto dto) {
        return educationService.save(userId, dto);
    }
}