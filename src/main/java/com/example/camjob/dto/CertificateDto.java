package com.example.camjob.dto;

import lombok.*;

@Data
public class CertificateDto {
    private String name;
    private String issuer;
    private String issuedDate;
}