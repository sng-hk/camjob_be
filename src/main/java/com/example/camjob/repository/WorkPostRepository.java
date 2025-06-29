package com.example.camjob.repository;

import com.example.camjob.entity.WorkPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkPostRepository extends JpaRepository<WorkPost, Long> {}