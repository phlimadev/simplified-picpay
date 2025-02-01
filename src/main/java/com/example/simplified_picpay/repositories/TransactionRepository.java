package com.example.simplified_picpay.repositories;

import com.example.simplified_picpay.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
