package com.example.gocardlessopenbanking.gocardless.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateRequisitionResponse {

    @NotBlank
    private String id;

    @NotBlank
    private String status;

    @NotBlank
    private String link;

    // TODO: as per documentation this should be used, but it's wrong
//    @Data
//    @Builder(toBuilder = true)
//    public static class Status {
//
//        @NotBlank
//        @JsonProperty("short")
//        private String shortName;
//
//        @NotBlank
//        @JsonProperty("long")
//        private String longName;
//
//        @NotBlank
//        private String description;
//
//    }

}
