package com.codegym.service;

import com.codegym.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface ITransactionService {
    Optional<Transaction> findById(String id);
    List<Transaction> findAll();
    void save(Transaction transaction);
    void removeById(String id);
}
