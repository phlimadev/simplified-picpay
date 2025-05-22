package br.com.phlimadev.simplified_picpay.transaction;

import br.com.phlimadev.simplified_picpay.exceptions.ApplicationException;
import br.com.phlimadev.simplified_picpay.exceptions.IdNotFoundException;
import br.com.phlimadev.simplified_picpay.user.UserModel;
import br.com.phlimadev.simplified_picpay.user.UserRepository;
import br.com.phlimadev.simplified_picpay.user.UserType;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    private void validateTransaction(UserModel payer, BigDecimal value) {
        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ApplicationException("Transaction value must be greater than zero");
        }

        if (payer.getBalance().compareTo(value) < 0) {
            throw new ApplicationException("Insufficient balance");
        }

        if (payer.getUserType() == UserType.MERCHANT) {
            throw new ApplicationException("Merchant type users cannot make transactions.");
        }
    }

    private void updateBalances(UserModel payer, UserModel payee, BigDecimal value) {
        payer.setBalance(payer.getBalance().subtract(value));
        payee.setBalance(payee.getBalance().add(value));

        userRepository.save(payer);
        userRepository.save(payee);
    }

    @Transactional
    public void createTransaction(TransactionDTO transactionDTO) {
        UserModel payer = userRepository.findById(transactionDTO.payer()).orElseThrow(() -> new IdNotFoundException("Payer not found"));
        UserModel payee = userRepository.findById(transactionDTO.payee()).orElseThrow(() -> new IdNotFoundException("Payee not found"));
        BigDecimal value = transactionDTO.value();

        validateTransaction(payer, value);
        updateBalances(payer, payee, value);

        TransactionModel newTransaction = new TransactionModel(null, payer, payee, value, null, null);

        transactionRepository.save(newTransaction);
    }
}