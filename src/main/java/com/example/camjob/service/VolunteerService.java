package com.example.camjob.service;

import com.example.camjob.dto.VolunteerPostResponseDto;
import com.example.camjob.entity.User;
import com.example.camjob.entity.VolunteerPost;
import com.example.camjob.entity.VolunteerScrap;
import com.example.camjob.repository.VolunteerPostRepository;
import com.example.camjob.repository.VolunteerScrapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VolunteerService {

    private final VolunteerScrapRepository volunteerScrapRepository;
    private final VolunteerPostRepository volunteerPostRepository;

    public List<VolunteerPostResponseDto> getScrapList(Long userId) {
        return volunteerPostRepository.findScrappedPostsByUserId(userId);
    }

    public void scrap(User user, Long postId) {
        VolunteerPost post = volunteerPostRepository.findById(postId).orElseThrow();
        VolunteerScrap scrap = new VolunteerScrap(user, post);
        volunteerScrapRepository.save(scrap);
    }

    public void unscrap(User user, Long postId) {
        VolunteerPost post = volunteerPostRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("봉사글을 찾을 수 없습니다."));

        VolunteerScrap scrap = volunteerScrapRepository.findByUserAndVolunteerPost(user, post)
                .orElseThrow(() -> new RuntimeException("스크랩된 기록이 없습니다."));
        volunteerScrapRepository.delete(scrap);
    }
}
