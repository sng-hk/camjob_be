package com.example.camjob.repository;

import com.example.camjob.entity.WorkApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkApplicationRepository extends JpaRepository<WorkApplication, Long> {
    List<WorkApplication> findByUserId(Long userId);
}