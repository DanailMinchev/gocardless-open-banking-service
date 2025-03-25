package com.example.gocardlessopenbanking.gocardless.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class GetRequisitionDetailResponse {

    @NotBlank
    private String id;

    @NotEmpty
    @JsonProperty("accounts")
    private List<String> accountIds;

}
