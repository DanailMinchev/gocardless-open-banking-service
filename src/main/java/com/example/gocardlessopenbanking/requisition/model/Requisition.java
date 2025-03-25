package com.example.gocardlessopenbanking.requisition.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Requisition {

    @NotBlank
    private String requisitionId;

    @NotBlank
    private String requisitionStatus;

    @NotBlank
    private String requisitionLink;

}
