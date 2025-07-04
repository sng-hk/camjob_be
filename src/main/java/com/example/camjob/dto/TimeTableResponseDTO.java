package com.example.camjob.dto;

public class TimeTableResponseDTO {

    private Long id;
    private String userEmail;   // 👈 userEmail로 변경
    private String semester;
    private String subjectName;
    private String dayOfWeek;
    private String startTime;
    private String endTime;

    public TimeTableResponseDTO(Long id, String userEmail, String semester, String subjectName, String dayOfWeek, String startTime, String endTime) {
        this.id = id;
        this.userEmail = userEmail;
        this.semester = semester;
        this.subjectName = subjectName;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Long getId() { return id; }

    public String getUserEmail() { return userEmail; }

    public String getSemester() { return semester; }

    public String getSubjectName() { return subjectName; }

    public String getDayOfWeek() { return dayOfWeek; }

    public String getStartTime() { return startTime; }

    public String getEndTime() { return endTime; }
}


