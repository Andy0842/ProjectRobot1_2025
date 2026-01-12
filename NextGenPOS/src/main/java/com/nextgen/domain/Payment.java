package com.nextgen.domain;

import java.math.BigDecimal;

public interface Payment {
    // Verify the payment amount is valid
    boolean validateAmount(BigDecimal amount);

    // Complete the core payment logic
    boolean completePayment(Sale sale);

    // Get the payment type name (e.g. "Cash Pay", "wechat Pay")
    String getPaymentType();
}