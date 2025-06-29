package com.example.camjob.service;

import com.example.camjob.dto.EducationDto;
import com.example.camjob.entity.Education;
import com.example.camjob.repository.EducationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EducationService {
    private final EducationRepository educationRepository;

    public EducationService(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    public List<Education> getByUserId(Long userId) {
        return educationRepository.findByUserId(userId);
    }

    public Education save(Long userId, EducationDto dto) {
        Education edu = Education.builder()
                .userId(userId)
                .schoolName(dto.getSchoolName())
                .major(dto.getMajor())
                .degree(dto.getDegree())
                .startDate(LocalDate.parse(dto.getStartDate()))
                .endDate(LocalDate.parse(dto.getEndDate()))
                .build();
        return educationRepository.save(edu);
    }
}