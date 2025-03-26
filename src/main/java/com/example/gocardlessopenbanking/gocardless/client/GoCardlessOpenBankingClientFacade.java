package com.example.gocardlessopenbanking.gocardless.client;

import com.example.gocardlessopenbanking.gocardless.client.dto.CreateRequisitionResponse;
import com.example.gocardlessopenbanking.gocardless.client.dto.GetInstitutionResponse;
import com.example.gocardlessopenbanking.gocardless.client.dto.GetRequisitionDetailResponse;
import com.example.gocardlessopenbanking.gocardless.client.dto.GetTransactionsResponse;
import com.example.gocardlessopenbanking.institution.model.Institution;
import com.example.gocardlessopenbanking.requisition.model.Requisition;
import com.example.gocardlessopenbanking.requisition.model.RequisitionDetail;
import com.example.gocardlessopenbanking.transaction.model.Transaction;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
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

    public RequisitionDetail getRequisition(String requisitionId) {
        GetRequisitionDetailResponse getRequisitionDetailResponse = goCardlessOpenBankingClientWrapper.getRequisition(requisitionId);

        return RequisitionDetail.builder()
                .id(getRequisitionDetailResponse.getId())
                .accountIds(getRequisitionDetailResponse.getAccountIds())
                .build();
    }

    public List<Transaction> getBookedTransactions(String accountId) {
        GetTransactionsResponse getTransactionsResponse = goCardlessOpenBankingClientWrapper.getTransactions(accountId);

        return getTransactionsResponse
                .getTransactions()
                .getBooked()
                .stream()
                .map(transaction -> Transaction
                        .builder()
                        .transactionId(transaction.getTransactionId())
                        .internalTransactionId(transaction.getInternalTransactionId())
                        .checkId(transaction.getCheckId())
                        .bookingDate(LocalDate.parse(transaction.getBookingDate()))
                        .transactionAmount(Transaction.TransactionAmount.builder()
                                .amount(transaction.getTransactionAmount().getAmount())
                                .currency(transaction.getTransactionAmount().getCurrency())
                                .build()
                        )
                        .creditorName(transaction.getCreditorName())
                        .debtorName(transaction.getDebtorName())
                        .build())
                .toList();
    }

}
