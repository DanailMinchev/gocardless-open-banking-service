package com.example.gocardlessopenbanking.gocardless.client;

import com.example.gocardlessopenbanking.gocardless.client.dto.CreateRequisitionResponse;
import com.example.gocardlessopenbanking.gocardless.client.dto.GetInstitutionResponse;
import com.example.gocardlessopenbanking.institution.model.Institution;
import com.example.gocardlessopenbanking.requisition.model.Requisition;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class GoCardlessOpenBankingClientFacade {

    private final GoCardlessOpenBankingClientWrapper goCardlessOpenBankingClientWrapper;

    public List<Institution> getInstitutions(String countryCode) {
        List<GetInstitutionResponse> getInstitutionResponseList =
                goCardlessOpenBankingClientWrapper.getInstitutions(countryCode);

        return getInstitutionResponseList
                .stream()
                .map(getInstitutionResponse -> Institution
                        .builder()
                        .id(getInstitutionResponse.getId())
                        .name(getInstitutionResponse.getName())
                        .logo(getInstitutionResponse.getLogo())
                        .build())
                .toList();
    }

    public Requisition createRequisition(String institutionId,
                                         String redirectUrl,
                                         UUID reference) {
        CreateRequisitionResponse createRequisitionResponse =
                goCardlessOpenBankingClientWrapper.createRequisition(institutionId, redirectUrl, reference);

        return Requisition.builder()
                .requisitionId(createRequisitionResponse.getId())
                .requisitionStatus(createRequisitionResponse.getStatus())
                .requisitionLink(createRequisitionResponse.getLink())
                .build();
    }

}
