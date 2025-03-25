package com.example.gocardlessopenbanking.transaction.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class TransactionsMessage {

    private List<Transaction> transactions;

}
