package com.example.gocardlessopenbanking.institution.web.rest;

import com.example.gocardlessopenbanking.institution.model.Institution;
import com.example.gocardlessopenbanking.institution.service.InstitutionService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/institutions")
@RequiredArgsConstructor
public class InstitutionController {

    private final InstitutionService institutionService;

    @GetMapping("/{countryCode}")
    public ResponseEntity<List<Institution>> findAll(@PathVariable @NotBlank String countryCode) {
        return ResponseEntity.ok(institutionService.findAll(countryCode));
    }

}
