package com.example.gocardlessopenbanking.transaction.web.rest;

import com.example.gocardlessopenbanking.transaction.model.Transaction;
import com.example.gocardlessopenbanking.transaction.service.TransactionService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/transactions/{accountId}")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/booked")
    public ResponseEntity<List<Transaction>> findAllBooked(@PathVariable @NotBlank String accountId) {
        return ResponseEntity.ok(transactionService.findAllBooked(accountId));
    }

    @GetMapping("/booked/export")
    public ResponseEntity<Void> exportAllBooked(@PathVariable @NotBlank String accountId) {
        transactionService.exportAllBooked(accountId);

        return ResponseEntity.noContent().build();
    }

}
