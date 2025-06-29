package com.example.camjob.service;

import com.example.camjob.dto.EducationDto;
import com.example.camjob.entity.Education;
import com.example.camjob.repository.EducationRepository;
import com.example.camjob.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EducationService {
    private final EducationRepository educationRepository;
    private final UserRepository userRepository;

    public List<Education> getByUserId(String email) {
        Long userId = userRepository.findByEmail(email).get().getId();
        return educationRepository.findByUserId(userId);
    }

    public Education save(String email, EducationDto dto) {
        Long userId = userRepository.findByEmail(email).get().getId();
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