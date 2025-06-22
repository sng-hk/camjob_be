package com.example.camjob.repository;

import com.example.camjob.entity.VolunteerPostMajorMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolunteerPostMajorMappingRepository extends JpaRepository<VolunteerPostMajorMapping, Long> {

}
