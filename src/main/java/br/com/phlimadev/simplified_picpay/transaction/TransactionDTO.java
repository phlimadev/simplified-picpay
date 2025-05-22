package br.com.phlimadev.simplified_picpay.transaction;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransactionDTO(
        @NotNull
        BigDecimal value,
        @NotNull
        Long payer,
        @NotNull
        Long payee
) {
}
