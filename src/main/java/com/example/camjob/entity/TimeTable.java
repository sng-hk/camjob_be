package com.example.camjob.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "timetables", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"semester", "subjectName", "dayOfWeek", "startTime", "endTime"})
})
public class TimeTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String semester;
    private String subjectName;
    private String dayOfWeek;
    private String startTime;
    private String endTime;

    // 기본 생성자
    public TimeTable() {}

    // 전체 생성자
    public TimeTable(String semester, String subjectName, String dayOfWeek, String startTime, String endTime) {
        this.semester = semester;
        this.subjectName = subjectName;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // getter/setter
    public Long getId() { return id; }
    public String getSemester() { return semester; }
    public void setSemester(String semester) { this.semester = semester; }

    public String getSubjectName() { return subjectName; }
    public void setSubjectName(String subjectName) { this.subjectName = subjectName; }

    public String getDayOfWeek() { return dayOfWeek; }
    public void setDayOfWeek(String dayOfWeek) { this.dayOfWeek = dayOfWeek; }

    public String getStartTime() { return startTime; }
    public void setStartTime(String startTime) { this.startTime = startTime; }

    public String getEndTime() { return endTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }
}
