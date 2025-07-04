package com.example.camjob.service;

import com.example.camjob.entity.WorkApplication;
import com.example.camjob.entity.WorkPost;
import com.example.camjob.entity.WorkScrap;
import com.example.camjob.repository.UserRepository;
import com.example.camjob.repository.WorkApplicationRepository;
import com.example.camjob.repository.WorkPostRepository;
import com.example.camjob.repository.WorkScrapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkPostService {

    private final WorkPostRepository postRepo;
    private final WorkApplicationRepository appRepo;
    private final WorkScrapRepository scrapRepo;
    private final UserRepository userRepository;

    public List<WorkPost> getAllPosts() {
        return postRepo.findAll();
    }

    public List<WorkPost> getPostsByDepartment(String department) {
        return postRepo.findAllByDepartment(department).orElseThrow(() -> new RuntimeException("해당 분야 공고 없음"));
    }

    public WorkPost getPost(Long workId) {
        return postRepo.findById(workId)
                .orElseThrow(() -> new RuntimeException("해당 근로 공고가 없습니다."));
    }

    public WorkApplication applyByEmail(String email, Long workId) {
        Long userId = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("해당 이메일의 사용자가 없습니다."))
                .getId();

        WorkPost post = getPost(workId);

        WorkApplication app = WorkApplication.builder()
                .userId(userId)
                .workPost(post)
                .appliedAt(LocalDate.now())
                .build();

        return appRepo.save(app);
    }

    public WorkScrap scrapByEmail(String email, Long workId) {
        Long userId = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("해당 이메일의 사용자가 없습니다."))
                .getId();

        WorkPost post = getPost(workId);

        WorkScrap scrap = WorkScrap.builder()
                .userId(userId)
                .workPost(post)
                .scrappedAt(LocalDate.now())
                .build();

        return scrapRepo.save(scrap);
    }

    public List<WorkPost> getScrappedPostsByEmail(String email) {
        Long userId = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("해당 이메일의 사용자가 없습니다."))
                .getId();

        return scrapRepo.findByUserId(userId).stream()
                .map(WorkScrap::getWorkPost)
                .toList();
    }
}