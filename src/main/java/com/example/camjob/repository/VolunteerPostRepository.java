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

    @Query("""
    SELECT new com.example.camjob.dto.VolunteerPostResponseDto(
      vpm.volunteerPost.id,
      vpm.volunteerPost.externalId,
      vpm.volunteerPost.location,
      vpm.major.name,
      vpm.volunteerPost.title,
      vpm.volunteerPost.organization,
      vpm.volunteerPost.activityDate,
      vpm.volunteerPost.place,
      CASE WHEN vs.id IS NOT NULL THEN true ELSE false END
    )
    FROM VolunteerPostMajorMapping vpm
    JOIN vpm.volunteerPost vp
    LEFT JOIN VolunteerScrap vs
      ON vp.id = vs.volunteerPost.id
      AND vs.user.id = :userId
    """)
    List<VolunteerPostResponseDto> findAll(@Param("userId") Long userId);

    @Query("""
    SELECT new com.example.camjob.dto.VolunteerPostResponseDto(
      vpm.volunteerPost.id,
      vpm.volunteerPost.externalId,
      vpm.volunteerPost.location,
      vpm.major.name,
      vpm.volunteerPost.title,
      vpm.volunteerPost.organization,
      vpm.volunteerPost.activityDate,
      vpm.volunteerPost.place,
      CASE WHEN vs.id IS NOT NULL THEN true ELSE false END
    )
    FROM VolunteerPostMajorMapping vpm
    JOIN vpm.volunteerPost vp
    LEFT JOIN VolunteerScrap vs
      ON vp.id = vs.volunteerPost.id
      AND vs.user.id = :userId
    """)
    List<VolunteerPostResponseDto> findAllByMajor(@Param("majorName") String majorName);

    @Query("""
SELECT new com.example.camjob.dto.VolunteerPostResponseDto(
  vp.id,
  vp.externalId,
  vp.location,
  vpm.major.name,
  vp.title,
  vp.organization,
  vp.activityDate,
  vp.place,
  true
)
FROM VolunteerScrap vs
JOIN vs.volunteerPost vp
JOIN VolunteerPostMajorMapping vpm
ON vp.id = vpm.volunteerPost.id
WHERE vs.user.id = :userId
""")
    List<VolunteerPostResponseDto> findScrappedPostsByUserId(@Param("userId") Long userId);
}
