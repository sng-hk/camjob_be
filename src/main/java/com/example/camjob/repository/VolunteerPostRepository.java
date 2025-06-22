package com.example.camjob.repository;

import com.example.camjob.entity.VolunteerPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolunteerPostRepository extends JpaRepository<VolunteerPost, Long> {
    boolean existsByExternalId(Long externalId);
}
