package com.example.camjob.dto;


import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
public class VolunteerPostResponseDto {
    Long id; // 봉사 신청 외부 링크 접속, 상세페이지 조회 용도
    String location; // 지역
    String major; // 전공
    String title; // 제목
    String organization; // 주최 기관
    String applyClosedDate; // 마감일
    String activityPlace; // 봉사장소
}
