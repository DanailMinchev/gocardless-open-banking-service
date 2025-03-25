package com.example.gocardlessopenbanking.gocardless.service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class RefreshTokenRequest {

    @NotBlank
    private String refresh;

}
