package com.example.camjob.repository;

import com.example.camjob.entity.WorkPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkPostRepository extends JpaRepository<WorkPost, Long> {
    Optional<List<WorkPost>> findAllByDepartment(String department);
}