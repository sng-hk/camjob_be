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

    // 🟢 시간표 등록
    public TimeTableResponseDTO saveTimetable(TimeTableRequestDTO dto) {
        // 시간 겹침 여부 확인
        boolean overlaps = timetableRepository
                .existsBySemesterAndDayOfWeekAndStartTimeLessThanAndEndTimeGreaterThan(
                        dto.getSemester(),
                        dto.getDayOfWeek(),
                        dto.getEndTime(),
                        dto.getStartTime()
                );

        if (overlaps) {
            throw new IllegalArgumentException("해당 시간대에 겹치는 수업이 이미 존재합니다.");
        }

        TimeTable timetable = new TimeTable(
                dto.getSemester(),
                dto.getSubjectName(),
                dto.getDayOfWeek(),
                dto.getStartTime(),
                dto.getEndTime()
        );

        TimeTable saved = timetableRepository.save(timetable);
        return toResponseDTO(saved);
    }

    // 🟢 학기별 시간표 전체 조회
    public List<TimeTableResponseDTO> getTimetablesBySemester(String semester) {
        List<TimeTable> timetables = timetableRepository.findBySemester(semester);
        return timetables.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    // 🟢 시간표 수정
    public TimeTableResponseDTO updateTimetable(Long id, TimeTableRequestDTO dto) {
        TimeTable timetable = timetableRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 시간표가 존재하지 않습니다."));

        // 나 자신(id 제외)을 제외하고 시간 겹침 여부 확인
        List<TimeTable> all = timetableRepository.findBySemester(dto.getSemester());
        boolean overlaps = all.stream()
                .anyMatch(t -> !t.getId().equals(id)
                        && t.getDayOfWeek().equals(dto.getDayOfWeek())
                        && t.getStartTime().compareTo(dto.getEndTime()) < 0
                        && t.getEndTime().compareTo(dto.getStartTime()) > 0);

        if (overlaps) {
            throw new IllegalArgumentException("수정하려는 시간대에 겹치는 수업이 존재합니다.");
        }

        timetable.setSubjectName(dto.getSubjectName());
        timetable.setDayOfWeek(dto.getDayOfWeek());
        timetable.setStartTime(dto.getStartTime());
        timetable.setEndTime(dto.getEndTime());

        TimeTable updated = timetableRepository.save(timetable);
        return toResponseDTO(updated);
    }

    // 🟢 시간표 삭제
    public void deleteTimetable(Long id) {
        timetableRepository.deleteById(id);
    }

    // 🛠️ 내부 메서드: Entity → DTO
    private TimeTableResponseDTO toResponseDTO(TimeTable timetable) {
        return new TimeTableResponseDTO(
                timetable.getId(),
                timetable.getSemester(),
                timetable.getSubjectName(),
                timetable.getDayOfWeek(),
                timetable.getStartTime(),
                timetable.getEndTime()
        );
    }
}
