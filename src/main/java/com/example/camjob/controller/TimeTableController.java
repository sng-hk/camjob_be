package com.example.camjob.controller;

import com.example.camjob.dto.TimeTableRequestDTO;
import com.example.camjob.dto.TimeTableResponseDTO;
import com.example.camjob.service.TimeTableService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/timetable")
public class TimeTableController {

    private final TimeTableService timetableService;

    public TimeTableController(TimeTableService timetableService) {
        this.timetableService = timetableService;
    }

    // 시간표 등록
    @PostMapping
    public TimeTableResponseDTO createTimetable(Authentication authentication, @RequestBody TimeTableRequestDTO dto) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("인증된 사용자가 아닙니다.");
        }

        Map<String, String> userInfo = (Map<String, String>) authentication.getPrincipal();
        String email = userInfo.get("email");

        return timetableService.saveTimetable(email, dto);
    }

    // 사용자 + 학기별 시간표 조회
    @GetMapping
    public List<TimeTableResponseDTO> getTimetables(
            Authentication authentication,
            @RequestParam String semester
    ) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("인증된 사용자가 아닙니다.");
        }

        Map<String, String> userInfo = (Map<String, String>) authentication.getPrincipal();
        String email = userInfo.get("email");

        return timetableService.getTimetablesByUserEmailAndSemester(email, semester);
    }

    // 시간표 수정
    @PutMapping("/{id}")
    public TimeTableResponseDTO updateTimetable(@PathVariable Long id, @RequestBody TimeTableRequestDTO dto) {
        return timetableService.updateTimetable(id, dto);
    }

    // 시간표 삭제
    @DeleteMapping("/{id}")
    public void deleteTimetable(@PathVariable Long id) {
        timetableService.deleteTimetable(id);
    }
}



