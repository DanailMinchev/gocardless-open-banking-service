package com.example.gocardlessopenbanking.gocardless.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class CreateRequisitionRequest {

    @NotBlank
    @JsonProperty("institution_id")
    private String institutionId;

    @NotBlank
    @JsonProperty("redirect")
    private String redirectUrl;

    @NotBlank
    private String reference;

}
