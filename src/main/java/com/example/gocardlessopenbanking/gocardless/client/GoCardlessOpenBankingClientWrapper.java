package com.example.gocardlessopenbanking.gocardless.client;

import com.example.gocardlessopenbanking.gocardless.client.dto.CreateRequisitionRequest;
import com.example.gocardlessopenbanking.gocardless.client.dto.CreateRequisitionResponse;
import com.example.gocardlessopenbanking.gocardless.client.dto.GetInstitutionResponse;
import com.example.gocardlessopenbanking.gocardless.client.dto.GetRequisitionDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
class GoCardlessOpenBankingClientWrapper {

    private final RestClient apiClient;

    public List<GetInstitutionResponse> getInstitutions(String countryCode) {
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

    public CreateRequisitionResponse createRequisition(String institutionId,
                                                       String redirectUrl,
                                                       UUID reference) {
        return apiClient
                .post()
                .uri("/api/v2/requisitions/")
                .body(CreateRequisitionRequest.builder()
                        .institutionId(institutionId)
                        .redirectUrl(redirectUrl)
                        .reference(reference.toString())
                        .build())
                .retrieve()
                .body(CreateRequisitionResponse.class);
    }

    public GetRequisitionDetailResponse getRequisition(String requisitionId) {
        return apiClient
                .get()
                .uri("/api/v2/requisitions/{requisitionId}/", requisitionId)
                .retrieve()
                .body(GetRequisitionDetailResponse.class);
    }

}
