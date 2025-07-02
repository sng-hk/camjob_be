package com.example.camjob.service;

import com.example.camjob.dto.EducationDto;
import com.example.camjob.dto.WorkDto;
import com.example.camjob.entity.Education;
import com.example.camjob.entity.Work;
import com.example.camjob.repository.EducationRepository;
import com.example.camjob.repository.UserRepository;
import com.example.camjob.repository.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor
public class WorkHistoryService {
    private final WorkRepository workRepository;
    private final UserRepository userRepository;

    public List<Work> getByUserId(String email) {
        Long userId = userRepository.findByEmail(email).get().getId();
        return workRepository.findByUserId(userId);
    }

    public Work save(String email, WorkDto dto) {
        Long userId = userRepository.findByEmail(email).get().getId();
        Work work = Work.builder()
                .userId(userId)
                .workTitle(dto.getWorkTitle())
                .department(dto.getDepartment())
                .startDate(LocalDate.parse(dto.getStartDate()))
                .endDate(LocalDate.parse(dto.getEndDate()))
                .build();

        return workRepository.save(work);
    }
}