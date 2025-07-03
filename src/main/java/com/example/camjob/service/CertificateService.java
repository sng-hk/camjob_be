package com.example.camjob.service;

import com.example.camjob.dto.CertificateDto;
import com.example.camjob.dto.EducationDto;
import com.example.camjob.entity.Certificate;
import com.example.camjob.entity.Education;
import com.example.camjob.repository.CertificateRepository;
import com.example.camjob.repository.EducationRepository;
import com.example.camjob.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CertificateService {
    private final CertificateRepository certificateRepository;
    private final UserRepository userRepository;

    public List<Certificate> getByUserId(String email) {
        Long userId = userRepository.findByEmail(email).get().getId();
        return certificateRepository.findByUserId(userId);
    }

    public Certificate save(String email, CertificateDto dto) {
        Long userId = userRepository.findByEmail(email).get().getId();
        Certificate certificate = Certificate.builder()
                .userId(userId)
                .name(dto.getName())
                .issuer(dto.getIssuer())
                .issuedDate(LocalDate.parse(dto.getIssuedDate()))
                .build();

        return certificateRepository.save(certificate);
    }
}