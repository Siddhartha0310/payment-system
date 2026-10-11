package com.example.payment_platform.wallet.dto;

import java.util.Currency;

import jakarta.validation.constraints.NotNull;

public record CreateWalletRequest(
    @NotNull 
    Currency currency
) {
    
    
}
