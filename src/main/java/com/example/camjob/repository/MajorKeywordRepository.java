package com.example.camjob.repository;

import com.example.camjob.entity.Major;
import com.example.camjob.entity.MajorKeyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MajorKeywordRepository extends JpaRepository<MajorKeyword, Long> {
    List<MajorKeyword> findByMajor(Major major);
}
