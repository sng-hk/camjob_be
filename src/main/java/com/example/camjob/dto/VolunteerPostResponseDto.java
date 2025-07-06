package com.example.camjob.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class VolunteerPostResponseDto {
    private Long id; // 봉사 신청 외부 링크 접속, 상세페이지 조회 용도
    private Long externalId;
    private String location; // 지역
    private String major; // 전공
    private String title; // 제목
    private String organization; // 주최 기관
    private String applyClosedDate; // 마감일
    private String activityPlace; // 봉사장소
    private boolean isScrapped;
}
