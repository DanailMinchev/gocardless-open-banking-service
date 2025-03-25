package com.example.gocardlessopenbanking.gocardless.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class RefreshTokenResponse {

    @NotBlank
    private String access;

    @Positive
    @JsonProperty("access_expires")
    private Long accessExpires;

    private String refresh;

    @JsonProperty("refresh_expires")
    private Long refreshExpires;

}
