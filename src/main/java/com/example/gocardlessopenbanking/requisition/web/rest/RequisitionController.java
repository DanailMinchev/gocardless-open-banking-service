package com.example.gocardlessopenbanking.requisition.web.rest;

import com.example.gocardlessopenbanking.requisition.model.Requisition;
import com.example.gocardlessopenbanking.requisition.service.RequisitionService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/api/v1/requisition")
@RequiredArgsConstructor
public class RequisitionController {

    private final RequisitionService requisitionService;

    @PostMapping("/{institutionId}")
    public ResponseEntity<Void> createRequisition(@PathVariable @NotBlank String institutionId) {
        log.info("Received a requisition request for institutionId: {}", institutionId);

        Requisition requisition = requisitionService.createRequisition(institutionId);

        return ResponseEntity.created(
                URI.create(requisition.getRequisitionLink())
        ).build();
    }

}
