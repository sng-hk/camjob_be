package com.example.camjob.service;

import com.example.camjob.dto.TimeTableRequestDTO;
import com.example.camjob.dto.TimeTableResponseDTO;
import com.example.camjob.entity.TimeTable;
import com.example.camjob.repository.TimeTableRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimeTableService {

    private final TimeTableRepository timetableRepository;

    public TimeTableService(TimeTableRepository timetableRepository) {
        this.timetableRepository = timetableRepository;
    }

    public TimeTableResponseDTO saveTimetable(String userEmail, TimeTableRequestDTO dto) {
        boolean exists = timetableRepository.existsByUserEmailAndSemesterAndDayOfWeekAndStartTimeAndEndTime(
                userEmail,
                dto.getSemester(),
                dto.getDayOfWeek(),
                dto.getStartTime(),
                dto.getEndTime()
        );

        if (exists) {
            throw new IllegalArgumentException("해당 요일/시간대에 이미 시간표가 존재합니다.");
        }

        TimeTable timetable = new TimeTable(
                userEmail,
                dto.getSemester(),
                dto.getSubjectName(),
                dto.getDayOfWeek(),
                dto.getStartTime(),
                dto.getEndTime()
        );

        TimeTable saved = timetableRepository.save(timetable);
        return toResponseDTO(saved);
    }

    public List<TimeTableResponseDTO> getTimetablesByUserEmailAndSemester(String userEmail, String semester) {
        return timetableRepository.findByUserEmailAndSemester(userEmail, semester)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public TimeTableResponseDTO updateTimetable(Long id, TimeTableRequestDTO dto) {
        TimeTable timetable = timetableRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 시간표가 존재하지 않습니다."));

        timetable.setSubjectName(dto.getSubjectName());
        timetable.setDayOfWeek(dto.getDayOfWeek());
        timetable.setStartTime(dto.getStartTime());
        timetable.setEndTime(dto.getEndTime());
        // userEmail과 semester는 수정하지 않는다고 가정

        TimeTable updated = timetableRepository.save(timetable);
        return toResponseDTO(updated);
    }

    public void deleteTimetable(Long id) {
        timetableRepository.deleteById(id);
    }

    private TimeTableResponseDTO toResponseDTO(TimeTable timetable) {
        return new TimeTableResponseDTO(
                timetable.getId(),
                timetable.getUserEmail(),
                timetable.getSemester(),
                timetable.getSubjectName(),
                timetable.getDayOfWeek(),
                timetable.getStartTime(),
                timetable.getEndTime()
        );
    }
}

