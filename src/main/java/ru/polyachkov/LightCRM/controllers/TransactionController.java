package ru.polyachkov.LightCRM.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.polyachkov.LightCRM.entities.Transaction;
import ru.polyachkov.LightCRM.services.TransactionService;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        Transaction transaction = transactionService.getTransactionById(id);
        return transaction != null ? ResponseEntity.ok(transaction) : ResponseEntity.notFound().build();
    }


    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        Transaction createdTransaction = transactionService.createTransaction(transaction);
        return ResponseEntity.ok(createdTransaction);
    }


    @GetMapping("/seller/{sellerId}")
    public ResponseEntity<List<Transaction>> getTransactionsBySeller(@PathVariable Long sellerId) {
        List<Transaction> transactions = transactionService.getTransactionsBySeller(sellerId);
        return ResponseEntity.ok(transactions);
    }
}
