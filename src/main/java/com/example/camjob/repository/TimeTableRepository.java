package com.example.camjob.repository;

import com.example.camjob.entity.TimeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TimeTableRepository extends JpaRepository<TimeTable, Long> {

    // ✅ 사용자(email) + 학기별 시간표 조회
    List<TimeTable> findByUserEmailAndSemester(String userEmail, String semester);

    // ✅ 사용자별 동일한 요일/시간대 중복 방지 체크
    boolean existsByUserEmailAndSemesterAndDayOfWeekAndStartTimeAndEndTime(
            String userEmail, String semester, String dayOfWeek, String startTime, String endTime
    );
}
