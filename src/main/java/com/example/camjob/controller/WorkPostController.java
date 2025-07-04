package com.example.camjob.controller;

import com.example.camjob.dto.EducationDto;
import com.example.camjob.entity.Education;
import com.example.camjob.entity.WorkApplication;
import com.example.camjob.entity.WorkPost;
import com.example.camjob.entity.WorkScrap;
import com.example.camjob.service.EducationService;
import com.example.camjob.service.WorkPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/works")
@RequiredArgsConstructor
public class WorkPostController {

    private final WorkPostService workPostService;

    // 게시글 전체 조회 (비인증)
    @GetMapping
    public List<WorkPost> list(@RequestParam(required = false) String department) {
        if(department != null) {
            return workPostService.getPostsByDepartment(department);
        }
        return workPostService.getAllPosts();
    }

    // 게시글 상세 조회 (비인증)
    @GetMapping("/{workId}")
    public WorkPost detail(@PathVariable Long workId) {
        return workPostService.getPost(workId);
    }

    // 게시글 신청
    @PostMapping("/{workId}/apply")
    public WorkApplication apply(@PathVariable Long workId, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("인증된 사용자가 아닙니다.");
        }

        Map<String, String> userInfo = (Map<String, String>) authentication.getPrincipal();
        String email = userInfo.get("email");

        return workPostService.applyByEmail(email, workId);
    }

    // 게시글 스크랩
    @PostMapping("/{workId}/scrap")
    public WorkScrap scrap(@PathVariable Long workId, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("인증된 사용자가 아닙니다.");
        }

        Map<String, String> userInfo = (Map<String, String>) authentication.getPrincipal();
        String email = userInfo.get("email");

        return workPostService.scrapByEmail(email, workId);
    }

    // 스크랩한 게시글 목록 조회
    @GetMapping("/scrap")
    public List<WorkPost> getScraps(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("인증된 사용자가 아닙니다.");
        }

        Map<String, String> userInfo = (Map<String, String>) authentication.getPrincipal();
        String email = userInfo.get("email");

        return workPostService.getScrappedPostsByEmail(email);
    }
}

