package com.example.camjob.service;

import com.example.camjob.entity.TimeTable;
import com.example.camjob.repository.TimeTableRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeTableService {

    private final TimeTableRepository timetableRepository;

    public TimeTableService(TimeTableRepository timetableRepository) {
        this.timetableRepository = timetableRepository;
    }

    // 시간표 저장
    public TimeTable saveTimetable(TimeTable timetable) {
        // 중복 시간 확인 (제약조건)
        boolean exists = timetableRepository.existsBySemesterAndDayOfWeekAndStartTimeAndEndTime(
                timetable.getSemester(),
                timetable.getDayOfWeek(),
                timetable.getStartTime(),
                timetable.getEndTime()
        );

        if (exists) {
            throw new IllegalArgumentException("해당 요일/시간대에 이미 시간표가 존재합니다.");
        }

        return timetableRepository.save(timetable);
    }

    // 학기별 시간표 불러오기
    public List<TimeTable> getTimetablesBySemester(String semester) {
        return timetableRepository.findBySemester(semester);
    }

    // 시간표 수정
    public TimeTable updateTimetable(Long id, TimeTable updatedTimetable) {
        TimeTable timetable = timetableRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 시간표가 존재하지 않습니다."));

        timetable.setSubjectName(updatedTimetable.getSubjectName());
        timetable.setDayOfWeek(updatedTimetable.getDayOfWeek());
        timetable.setStartTime(updatedTimetable.getStartTime());
        timetable.setEndTime(updatedTimetable.getEndTime());

        return timetableRepository.save(timetable);
    }

    // 시간표 삭제
    public void deleteTimetable(Long id) {
        timetableRepository.deleteById(id);
    }
}
