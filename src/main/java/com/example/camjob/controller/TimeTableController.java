package com.example.camjob.controller;

import com.example.camjob.entity.TimeTable;
import com.example.camjob.service.TimeTableService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/timetable")
public class TimeTableController {

    private final TimeTableService timetableService;

    public TimeTableController(TimeTableService timetableService) {
        this.timetableService = timetableService;
    }

    // ✅ 시간표 등록
    @PostMapping
    public TimeTable createTimetable(@RequestBody TimeTable timetable) {
        return timetableService.saveTimetable(timetable);
    }

    // ✅ 학기별 시간표 조회
    @GetMapping
    public List<TimeTable> getTimetables(@RequestParam String semester) {
        return timetableService.getTimetablesBySemester(semester);
    }

    // ✅ 시간표 수정
    @PutMapping("/{id}")
    public TimeTable updateTimetable(@PathVariable Long id, @RequestBody TimeTable timetable) {
        return timetableService.updateTimetable(id, timetable);
    }

    // ✅ 시간표 삭제
    @DeleteMapping("/{id}")
    public void deleteTimetable(@PathVariable Long id) {
        timetableService.deleteTimetable(id);
    }
}

