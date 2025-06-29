package com.example.camjob.repository;

import com.example.camjob.entity.WorkScrap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkScrapRepository extends JpaRepository<WorkScrap, Long> {
    List<WorkScrap> findByUserId(Long userId);
}