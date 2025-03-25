package com.example.gocardlessopenbanking.requisition.web.rest;

import com.example.gocardlessopenbanking.requisition.model.Requisition;
import com.example.gocardlessopenbanking.requisition.model.RequisitionDetail;
import com.example.gocardlessopenbanking.requisition.service.RequisitionService;
import com.example.gocardlessopenbanking.requisition.web.rest.dto.CreateRequisitionRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/api/v1/requisition")
@RequiredArgsConstructor
public class RequisitionController {

    private final RequisitionService requisitionService;

    @PostMapping
    public ResponseEntity<Void> createRequisition(@Valid @RequestBody CreateRequisitionRequest createRequisitionRequest) {
        log.info("Received a create requisition request: {}", createRequisitionRequest);

        Requisition requisition = requisitionService.createRequisition(createRequisitionRequest.getInstitutionId());

        return ResponseEntity.created(
                URI.create(requisition.getRequisitionLink())
        ).build();
    }

    @GetMapping("/{requisitionId}")
    public ResponseEntity<RequisitionDetail> getRequisitionDetail(@NotBlank String requisitionId) {
        log.info("Received a get requisition detail request with requisitionId: {}", requisitionId);

        RequisitionDetail requisitionDetail = requisitionService.getRequisition(requisitionId);

        return ResponseEntity.ok(requisitionDetail);
    }

}
