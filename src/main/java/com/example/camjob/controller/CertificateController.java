package com.example.camjob.controller;

import com.example.camjob.dto.CertificateDto;
import com.example.camjob.entity.Certificate;
import com.example.camjob.service.CertificateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/certificates")
public class CertificateController {
    private final CertificateService service;

    public CertificateController(CertificateService service) {
        this.service = service;
    }

    @GetMapping
    public List<Certificate> getAll(@RequestParam Long userId) {
        return service.getByUserId(userId);
    }

    @PostMapping
    public Certificate create(@RequestParam Long userId, @RequestBody CertificateDto dto) {
        return service.save(userId, dto);
    }
}