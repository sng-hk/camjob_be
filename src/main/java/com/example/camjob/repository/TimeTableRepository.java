package com.example.camjob.repository;

import com.example.camjob.entity.TimeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TimeTableRepository extends JpaRepository<TimeTable, Long> {

    // 학기별 시간표 조회
    List<TimeTable> findBySemester(String semester);

    // 동일한 요일/시간대 중복 방지 체크
    boolean existsBySemesterAndDayOfWeekAndStartTimeAndEndTime(
            String semester, String dayOfWeek, String startTime, String endTime
    );
}