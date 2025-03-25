package com.example.gocardlessopenbanking.gocardless.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class NewTokenRequest {

    @NotBlank
    @JsonProperty("secret_id")
    private String secretId;

    @NotBlank
    @JsonProperty("secret_key")
    private String secretKey;

}
