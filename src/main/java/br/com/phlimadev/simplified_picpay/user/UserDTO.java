package br.com.phlimadev.simplified_picpay.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record UserDTO(
        Long id,
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        @NotBlank
        String document,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String password,
        @NotNull
        @PositiveOrZero
        BigDecimal balance,
        @NotNull
        UserType userType
) {
    public UserDTO(UserModel userModel) {
        this(userModel.getId(), userModel.getFirstName(), userModel.getLastName(), userModel.getDocument(),
                userModel.getEmail(), userModel.getPassword(), userModel.getBalance(), userModel.getUserType());
    }
}
