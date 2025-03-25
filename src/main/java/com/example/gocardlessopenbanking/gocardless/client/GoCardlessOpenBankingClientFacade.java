package com.example.gocardlessopenbanking.gocardless.client;

import com.example.gocardlessopenbanking.gocardless.client.dto.InstitutionResponse;
import com.example.gocardlessopenbanking.institution.model.Institution;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GoCardlessOpenBankingClientFacade {

    private final GoCardlessOpenBankingClientWrapper goCardlessOpenBankingClientWrapper;

    public List<Institution> getInstitutions(String countryCode) {
        List<InstitutionResponse> institutionResponseList = goCardlessOpenBankingClientWrapper.getInstitutions(countryCode);

        return institutionResponseList
                .stream()
                .map(institutionResponse -> Institution
                        .builder()
                        .id(institutionResponse.getId())
                        .name(institutionResponse.getName())
                        .logo(institutionResponse.getLogo())
                        .build())
                .toList();
    }

}
