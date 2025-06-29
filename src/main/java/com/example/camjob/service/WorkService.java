package com.example.camjob.service;

import com.example.camjob.dto.WorkDto;
import com.example.camjob.entity.Work;
import com.example.camjob.repository.WorkRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class WorkService {

    private final WorkRepository workRepository;

    public WorkService(WorkRepository workRepository) {
        this.workRepository = workRepository;
    }

    public List<Work> getByUserId(Long userId) {
        return workRepository.findByUserId(userId);
    }

    public Work save(Long userId, WorkDto dto) {
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