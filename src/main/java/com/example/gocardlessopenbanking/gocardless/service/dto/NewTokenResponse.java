package com.example.gocardlessopenbanking.gocardless.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class NewTokenResponse {

    @NotBlank
    private String access;

    @Positive
    @JsonProperty("access_expires")
    private Long accessExpires;

    @NotBlank
    private String refresh;

    @Positive
    @JsonProperty("refresh_expires")
    private Long refreshExpires;

}
