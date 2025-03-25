package com.example.gocardlessopenbanking.gocardless.client;

import com.example.gocardlessopenbanking.gocardless.client.dto.InstitutionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.util.List;

@RequiredArgsConstructor
class GoCardlessOpenBankingClientWrapper {

    private final RestClient apiClient;

    public List<InstitutionResponse> getInstitutions(String countryCode) {
        return apiClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v2/institutions/")
                        .queryParam("country", countryCode)
                        .build()
                )
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

}
