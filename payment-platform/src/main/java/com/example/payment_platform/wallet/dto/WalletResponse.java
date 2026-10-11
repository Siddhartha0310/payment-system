package com.example.payment_platform.wallet.dto;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;

public record WalletResponse(
    UUID walletId,
    UUID userId,
    Currency currency,
    BigDecimal balance
) {

}
