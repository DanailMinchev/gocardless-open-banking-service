package com.example.gocardlessopenbanking.institution.service;

import com.example.gocardlessopenbanking.gocardless.client.GoCardlessOpenBankingClientFacade;
import com.example.gocardlessopenbanking.gocardless.client.GoCardlessOpenBankingClientFactory;
import com.example.gocardlessopenbanking.gocardless.service.GoCardlessOpenBankingTokenService;
import com.example.gocardlessopenbanking.institution.model.Institution;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class InstitutionService {

    private final GoCardlessOpenBankingTokenService goCardlessOpenBankingTokenService;
    private final GoCardlessOpenBankingClientFactory goCardlessOpenBankingClientFactory;

    public List<Institution> findAll(@NotBlank String countryCode) {
        GoCardlessOpenBankingClientFacade clientFacade =
                goCardlessOpenBankingClientFactory.getClientFor(goCardlessOpenBankingTokenService.getAccessToken());

        return clientFacade.getInstitutions(countryCode);
    }

}
