package com.example.gocardlessopenbanking.transaction.service;

import com.example.gocardlessopenbanking.gocardless.client.GoCardlessOpenBankingClientFacade;
import com.example.gocardlessopenbanking.gocardless.client.GoCardlessOpenBankingClientFactory;
import com.example.gocardlessopenbanking.gocardless.service.GoCardlessOpenBankingTokenService;
import com.example.gocardlessopenbanking.transaction.model.Transaction;
import com.example.gocardlessopenbanking.transaction.model.TransactionsMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
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
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public List<Transaction> findAllBooked(@NotBlank String accountId) {
        GoCardlessOpenBankingClientFacade clientFacade =
                goCardlessOpenBankingClientFactory.getClientFor(goCardlessOpenBankingTokenService.getAccessToken());

        return clientFacade.getBookedTransactions(accountId);
    }

    public void exportAllBooked(@NotBlank String accountId) {
        GoCardlessOpenBankingClientFacade clientFacade =
                goCardlessOpenBankingClientFactory.getClientFor(goCardlessOpenBankingTokenService.getAccessToken());

        List<Transaction> transactions = clientFacade.getBookedTransactions(accountId);
        TransactionsMessage transactionsMessage = TransactionsMessage.builder()
                .transactions(transactions)
                .build();

        try {
            kafkaTemplate.send("booked-transactions", objectMapper.writeValueAsString(transactionsMessage));
        } catch (JsonProcessingException exception) {
            throw new RuntimeException(exception);
        }
    }

}
