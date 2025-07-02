package com.example.camjob.controller;

import com.example.camjob.dto.CertificateDto;
import com.example.camjob.dto.EducationDto;
import com.example.camjob.entity.Certificate;
import com.example.camjob.entity.Education;
import com.example.camjob.service.CertificateService;
import com.example.camjob.service.EducationService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user/certificates")
public class CertificateController {

    private final CertificateService certificateService;

    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @GetMapping
    public List<Certificate> getCertificates(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("인증된 사용자가 아닙니다.");
        }

        Map<String, String> userInfo = (Map<String, String>) authentication.getPrincipal();

        String email = userInfo.get("email");

        return certificateService.getByUserId(email);
    }

    @PostMapping
    public Certificate createCertificate(Authentication authentication, @RequestBody CertificateDto dto) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("인증된 사용자가 아닙니다.");
        }

        Map<String, String> userInfo = (Map<String, String>) authentication.getPrincipal();

        String email = userInfo.get("email");
        return certificateService.save(email, dto);
    }
}