package com.example.camjob.controller;

import com.example.camjob.dto.WorkDto;
import com.example.camjob.entity.Work;
import com.example.camjob.service.WorkHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/works")
@RequiredArgsConstructor
public class WorkHistoryController {

    private final WorkHistoryService workHistoryService;

    @GetMapping
    public List<Work> getUserWorks(@RequestParam Long userId) {
        return workHistoryService.getByUserId(userId);
    }

    @PostMapping
    public Work createUserWork(@RequestParam Long userId, @RequestBody WorkDto dto) {
        return workHistoryService.save(userId, dto);
    }
}