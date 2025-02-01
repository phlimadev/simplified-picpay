package com.example.simplified_picpay.services;

import com.example.simplified_picpay.dtos.TransactionDTO;
import com.example.simplified_picpay.entities.Transaction;
import com.example.simplified_picpay.entities.User;
import com.example.simplified_picpay.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
//import java.util.Map;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private RestTemplate restTemplate;

    public Transaction createTransaction(TransactionDTO transaction) throws Exception {
        User payer = userService.findUserById(transaction.payerId());
        User payee = userService.findUserById(transaction.payeeId());

        userService.validateTransaction(payer, transaction.value());

//        boolean isAuthorized = authorizingService();
//
//        if (!isAuthorized) {
//            throw new Exception("Unauthorized transaction.");
//        }

        Transaction newTransaction = new Transaction();
        newTransaction.setPayer(payer);
        newTransaction.setPayee(payee);
        newTransaction.setAmount(transaction.value());
        newTransaction.setTimestamp(LocalDateTime.now());

        transactionRepository.save(newTransaction);

        payer.setBalance(payer.getBalance().subtract(transaction.value()));
        payee.setBalance(payee.getBalance().add(transaction.value()));

        userService.saveUser(payer);
        userService.saveUser(payee);

        return newTransaction;
    }

//    private boolean authorizingService() {
//        ResponseEntity<Map> authorization = restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize", Map.class);
//
//        if (authorization.getBody().get("status") == "success") {
//            return true;
//        }
//
//        return false;
//    }
}
