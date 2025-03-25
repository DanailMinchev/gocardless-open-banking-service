package com.example.gocardlessopenbanking.requisition.web.rest.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class CreateRequisitionRequest {

    @NotBlank
    private String institutionId;

}
