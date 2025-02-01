package com.example.simplified_picpay.services;

import com.example.simplified_picpay.entities.User;
import com.example.simplified_picpay.entities.UserType;
import com.example.simplified_picpay.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void validateTransaction(User payer, BigDecimal amount) throws Exception {
        if (payer.getUserType() == UserType.MERCHANT) {
            throw new Exception("Merchant cannot carry out transactions.");
        }
        if (payer.getBalance().compareTo(amount) < 0) {
            throw new Exception("Insufficient balance.");
        }
    }

    public User findUserById(Long id) throws Exception {
        return this.userRepository.findUserById(id).orElseThrow(() -> new Exception("User not found."));
    }

    public void saveUser(User user) {
        this.userRepository.save(user);
    }
}
