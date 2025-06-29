package com.example.camjob.service;

import com.example.camjob.dto.ActivityDto;
import com.example.camjob.entity.Activity;
import com.example.camjob.repository.ActivityRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public List<Activity> getByUserId(Long userId) {
        return activityRepository.findByUserId(userId);
    }

    public Activity save(Long userId, ActivityDto dto) {
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