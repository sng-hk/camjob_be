package com.example.camjob.service;

import com.example.camjob.dto.ActivityDto;
import com.example.camjob.dto.EducationDto;
import com.example.camjob.entity.Activity;
import com.example.camjob.entity.Education;
import com.example.camjob.repository.ActivityRepository;
import com.example.camjob.repository.EducationRepository;
import com.example.camjob.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityService {
    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;

    public List<Activity> getByUserId(String email) {
        Long userId = userRepository.findByEmail(email).get().getId();
        return activityRepository.findByUserId(userId);
    }

    public Activity save(String email, ActivityDto dto) {
        Long userId = userRepository.findByEmail(email).get().getId();
        Activity activity = Activity.builder()
                .userId(userId)
                .name(dto.getName())
                .description(dto.getDescription())
                .startDate(LocalDate.parse(dto.getStartDate()))
                .endDate(LocalDate.parse(dto.getEndDate()))
                .build();
        return activityRepository.save(activity);

    }
}