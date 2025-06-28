package com.example.camjob.controller;

import com.example.camjob.dto.TimeTableRequestDTO;
import com.example.camjob.dto.TimeTableResponseDTO;
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

    // 시간표 등록 (POST)
    @PostMapping
    public TimeTableResponseDTO createTimetable(@RequestBody TimeTableRequestDTO dto) {
        return timetableService.saveTimetable(dto);
    }

    // 시간표 조회 (GET)
    @GetMapping
    public List<TimeTableResponseDTO> getTimetables(@RequestParam String semester) {
        return timetableService.getTimetablesBySemester(semester);
    }

    // 시간표 수정 (PUT)
    @PutMapping("/{id}")
    public TimeTableResponseDTO updateTimetable(@PathVariable Long id, @RequestBody TimeTableRequestDTO dto) {
        return timetableService.updateTimetable(id, dto);
    }

    // 시간표 삭제 (DELETE)
    @DeleteMapping("/{id}")
    public void deleteTimetable(@PathVariable Long id) {
        timetableService.deleteTimetable(id);
    }
}

