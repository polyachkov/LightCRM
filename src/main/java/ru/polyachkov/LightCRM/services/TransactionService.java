package ru.polyachkov.LightCRM.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.polyachkov.LightCRM.entities.Transaction;
import ru.polyachkov.LightCRM.repositories.TransactionRepo;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepo transactionRepo;

    @Autowired
    public TransactionService(TransactionRepo transactionRepo) {
        this.transactionRepo = transactionRepo;
    }


    public List<Transaction> getAllTransactions() {
        return transactionRepo.findAll();
    }


    public Transaction getTransactionById(Long id) {
        return transactionRepo.findById(id).orElse(null);
    }


    public Transaction createTransaction(Transaction transaction) {
        return transactionRepo.save(transaction);
    }


    public List<Transaction> getTransactionsBySeller(Long sellerId) {
        return transactionRepo.findBySellerId(sellerId);
    }
}
