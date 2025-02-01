package com.example.simplified_picpay.dtos;

import java.math.BigDecimal;

public record TransactionDTO(Long payerId, Long payeeId, BigDecimal value) {
}
