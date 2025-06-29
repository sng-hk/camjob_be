package com.example.camjob.controller;

import com.example.camjob.dto.WorkDto;
import com.example.camjob.entity.Work;
import com.example.camjob.service.WorkService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/works")
public class WorkController {
    private final WorkService service;

    public WorkController(WorkService service) {
        this.service = service;
    }

    @GetMapping
    public List<Work> getAll(@RequestParam Long userId) {
        return service.getByUserId(userId);
    }

    @PostMapping
    public Work create(@RequestParam Long userId, @RequestBody WorkDto dto) {
        return service.save(userId, dto);
    }
}