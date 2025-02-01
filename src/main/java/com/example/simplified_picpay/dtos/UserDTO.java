package com.example.simplified_picpay.dtos;

import com.example.simplified_picpay.entities.UserType;

import java.math.BigDecimal;

public record UserDTO(Long id, String firstName, String lastName, String document, String email, String password, UserType type, BigDecimal balance) {
}
