package com.example.camjob.repository;

import com.example.camjob.entity.TimeTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimeTableRepository extends JpaRepository<TimeTable, Long> {

    // 학기별 시간표 조회
    List<TimeTable> findBySemester(String semester);

    // 동일한 요일/시간대 중복 방지 체크 (정확히 같은 시간)
    boolean existsBySemesterAndDayOfWeekAndStartTimeAndEndTime(
            String semester, String dayOfWeek, String startTime, String endTime
    );

    // ✅ 해당 학기 & 요일에서 시간이 겹치는 수업이 존재하는지 확인
    boolean existsBySemesterAndDayOfWeekAndStartTimeLessThanAndEndTimeGreaterThan(
            String semester, String dayOfWeek, String endTime, String startTime
    );
}
