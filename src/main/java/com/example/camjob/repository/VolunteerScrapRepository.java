package com.example.camjob.repository;

import com.example.camjob.entity.User;
import com.example.camjob.entity.VolunteerPost;
import com.example.camjob.entity.VolunteerScrap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VolunteerScrapRepository extends JpaRepository<VolunteerScrap, Long> {
    public Optional<VolunteerScrap> findByUserAndVolunteerPost(User user, VolunteerPost volunteerPost);
}
