package com.example.gocardlessopenbanking.gocardless.client.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class GetInstitutionResponse {

    @NotBlank
    private String id;

    @NotBlank
    private String name;

    @NotBlank
    private String logo;

}
