package com.example.camjob.service;

import com.example.camjob.dto.CertificateDto;
import com.example.camjob.entity.Certificate;
import com.example.camjob.repository.CertificateRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class CertificateService {

    private final CertificateRepository certificateRepository;

    public CertificateService(CertificateRepository certificateRepository) {
        this.certificateRepository = certificateRepository;
    }

    public List<Certificate> getByUserId(Long userId) {
        return certificateRepository.findByUserId(userId);
    }

    public Certificate save(Long userId, CertificateDto dto) {
        Certificate certificate = Certificate.builder()
                .userId(userId)
                .name(dto.getName())
                .issuer(dto.getIssuer())
                .issuedDate(LocalDate.parse(dto.getIssuedDate()))
                .build();

        return certificateRepository.save(certificate);
    }
}