package com.example.gocardlessopenbanking.gocardless.client.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder(toBuilder = true)
public class GetTransactionsResponse {

    private Transactions transactions;

    @Data
    @Builder(toBuilder = true)
    public static class Transactions {

        private List<Transaction> booked;
        private List<Transaction> pending;

    }

    @Data
    @Builder(toBuilder = true)
    public static class Transaction {

        private String transactionId;
        private String internalTransactionId;
        private String checkId;
        private String bookingDate;
        private TransactionAmount transactionAmount;
        private String creditorName;
        private String debtorName;

        @Data
        @Builder(toBuilder = true)
        public static class TransactionAmount {

            private BigDecimal amount;
            private String currency;

        }

    }

}
