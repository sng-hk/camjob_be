package com.example.camjob.service;

import com.example.camjob.dto.WorkPostResponseDto;
import com.example.camjob.entity.User;
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
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkPostService {

    private final WorkPostRepository postRepo;
    private final WorkApplicationRepository appRepo;
    private final WorkScrapRepository scrapRepo;
    private final UserRepository userRepository;

    /**
     * 전체 게시글 + 로그인한 유저가 스크랩했는지 여부 포함
     */
    public List<WorkPostResponseDto> getAllPosts(String email) {
        Long userId = getUserIdByEmail(email);
        Set<Long> scrappedIds = getScrappedWorkPostIds(userId);

        return postRepo.findAll().stream()
                .map(post -> toDto(post, scrappedIds.contains(post.getId())))
                .toList();
    }

    /**
     * 부서별 게시글 + 로그인한 유저가 스크랩했는지 여부 포함
     */
    public List<WorkPostResponseDto> getPostsByDepartment(String email, String department) {
        Long userId = getUserIdByEmail(email);
        Set<Long> scrappedIds = getScrappedWorkPostIds(userId);

        List<WorkPost> postsByDept = postRepo.findAllByDepartment(department)
                .orElseThrow(() -> new RuntimeException("해당 분야 공고 없음"));

        return postsByDept.stream()
                .map(post -> toDto(post, scrappedIds.contains(post.getId())))
                .toList();
    }

    /**
     * 게시글 상세 조회
     */
    public WorkPost getPost(Long workId) {
        return postRepo.findById(workId)
                .orElseThrow(() -> new RuntimeException("해당 근로 공고가 없습니다."));
    }

    /**
     * 근로 신청
     */
    public WorkApplication applyByEmail(String email, Long workId) {
        Long userId = getUserIdByEmail(email);
        WorkPost post = getPost(workId);

        WorkApplication app = WorkApplication.builder()
                .userId(userId)
                .workPost(post)
                .appliedAt(LocalDate.now())
                .build();

        return appRepo.save(app);
    }

    /**
     * 게시글 스크랩
     */
    public WorkScrap scrapByEmail(String email, Long workId) {
        Long userId = getUserIdByEmail(email);
        WorkPost post = getPost(workId);

        WorkScrap scrap = WorkScrap.builder()
                .userId(userId)
                .workPost(post)
                .scrappedAt(LocalDate.now())
                .build();

        return scrapRepo.save(scrap);
    }

    /**
     * 스크랩한 게시글 목록 조회
     */
    public List<WorkPost> getScrappedPostsByEmail(String email) {
        Long userId = getUserIdByEmail(email);

        return scrapRepo.findByUserId(userId).stream()
                .map(WorkScrap::getWorkPost)
                .toList();
    }

    /**
     * 유저 이메일 → userId
     */
    private Long getUserIdByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("해당 이메일의 사용자가 없습니다."))
                .getId();
    }

    /**
     * 유저가 스크랩한 work_post의 id들
     */
    private Set<Long> getScrappedWorkPostIds(Long userId) {
        return scrapRepo.findByUserId(userId).stream()
                .map(scrap -> scrap.getWorkPost().getId())
                .collect(Collectors.toSet());
    }

    /**
     * 게시글 + 스크랩 여부 → DTO
     */
    private WorkPostResponseDto toDto(WorkPost post, boolean isScrapped) {
        WorkPostResponseDto dto = new WorkPostResponseDto();
        dto.setTitle(post.getTitle());
        dto.setDepartment(post.getDepartment());
        dto.setDescription(post.getDescription());
        dto.setStartDate(post.getStartDate());
        dto.setEndDate(post.getEndDate());
        dto.setClosed(post.isClosed());      // DB에 저장된 마감여부
        dto.setScrapped(isScrapped);        // work_scrap 테이블에서 동적으로 계산
        return dto;
    }
}
