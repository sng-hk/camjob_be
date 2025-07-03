package com.example.camjob.repository;

import com.example.camjob.dto.VolunteerPostResponseDto;
import com.example.camjob.entity.VolunteerPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VolunteerPostRepository extends JpaRepository<VolunteerPost, Long> {
    boolean existsByExternalId(Long externalId);

    @Query("SELECT new com.example.camjob.dto.VolunteerPostResponseDto(" +
            "vpm.volunteerPost.id, " +
            "vpm.volunteerPost.location, " +
            "vpm.major.name, " +
            "vpm.volunteerPost.title, " +
            "vpm.volunteerPost.organization, " +
            "vpm.volunteerPost.activityDate, " +
            "vpm.volunteerPost.place) " +
            "FROM VolunteerPostMajorMapping vpm")
    List<VolunteerPostResponseDto> findAllWithMajor();

    @Query("SELECT new com.example.camjob.dto.VolunteerPostResponseDto(" +
            "vpm.volunteerPost.id, " +
            "vpm.volunteerPost.location, " +
            "vpm.major.name, " +
            "vpm.volunteerPost.title, " +
            "vpm.volunteerPost.organization, " +
            "vpm.volunteerPost.activityDate, " +  // ⚠️ 컬럼명 & 타입 확인
            "vpm.volunteerPost.place) " +
            "FROM VolunteerPostMajorMapping vpm " +
            "WHERE vpm.major.name = :majorName")
    List<VolunteerPostResponseDto> findAllByMajor(@Param("majorName") String majorName);
}
