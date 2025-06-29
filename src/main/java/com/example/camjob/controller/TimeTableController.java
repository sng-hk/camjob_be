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

    @PostMapping
    public TimeTableResponseDTO createTimetable(@RequestBody TimeTableRequestDTO dto) {
        return timetableService.saveTimetable(dto);
    }

    @GetMapping
    public List<TimeTableResponseDTO> getTimetables(@RequestParam String semester) {
        return timetableService.getTimetablesBySemester(semester);
    }

    @PutMapping("/{id}")
    public TimeTableResponseDTO updateTimetable(@PathVariable Long id, @RequestBody TimeTableRequestDTO dto) {
        return timetableService.updateTimetable(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteTimetable(@PathVariable Long id) {
        timetableService.deleteTimetable(id);
    }
}

