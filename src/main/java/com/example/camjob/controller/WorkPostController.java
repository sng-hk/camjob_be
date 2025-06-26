package com.example.camjob.controller;

import com.example.camjob.entity.WorkApplication;
import com.example.camjob.entity.WorkPost;
import com.example.camjob.entity.WorkScrap;
import com.example.camjob.service.WorkPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/works")
@RequiredArgsConstructor
public class WorkPostController {

    private final WorkPostService workPostService;

    @GetMapping
    public List<WorkPost> list() {
        return workPostService.getAllPosts();
    }

    @GetMapping("/{workId}")
    public WorkPost detail(@PathVariable Long workId) {
        return workPostService.getPost(workId);
    }

    @PostMapping("/{workId}/apply")
    public WorkApplication apply(@PathVariable Long workId, @RequestParam Long userId) {
        return workPostService.apply(userId, workId);
    }

    @PostMapping("/{workId}/scrap")
    public WorkScrap scrap(@PathVariable Long workId, @RequestParam Long userId) {
        return workPostService.scrap(userId, workId);
    }

    @GetMapping("/scrap")
    public List<WorkPost> getScraps(@RequestParam Long userId) {
        return workPostService.getScrappedPosts(userId);
    }
}