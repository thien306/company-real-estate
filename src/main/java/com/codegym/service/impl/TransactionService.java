package com.codegym.service.impl;

import com.codegym.model.Transaction;
import com.codegym.repository.ITransactionRepository;
import com.codegym.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService implements ITransactionService {
    @Autowired
    ITransactionRepository transactionRepository;

    @Override
    public Optional<Transaction> findById(String id) {
        return transactionRepository.findById(id);
    }


    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    @Override
    public void removeById(String id) {
        transactionRepository.deleteById(id);
    }
}
