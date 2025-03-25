package com.example.gocardlessopenbanking.transaction.service;

import com.example.gocardlessopenbanking.gocardless.client.GoCardlessOpenBankingClientFacade;
import com.example.gocardlessopenbanking.gocardless.client.GoCardlessOpenBankingClientFactory;
import com.example.gocardlessopenbanking.gocardless.service.GoCardlessOpenBankingTokenService;
import com.example.gocardlessopenbanking.transaction.model.Transaction;
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
public class TransactionService {

    private final GoCardlessOpenBankingTokenService goCardlessOpenBankingTokenService;
    private final GoCardlessOpenBankingClientFactory goCardlessOpenBankingClientFactory;

    public List<Transaction> findAllBooked(@NotBlank String accountId) {
        GoCardlessOpenBankingClientFacade clientFacade =
                goCardlessOpenBankingClientFactory.getClientFor(goCardlessOpenBankingTokenService.getAccessToken());

        return clientFacade.getBookedTransactions(accountId);
    }

}
