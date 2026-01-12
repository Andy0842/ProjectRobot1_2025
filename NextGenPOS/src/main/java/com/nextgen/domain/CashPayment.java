package com.nextgen.domain;

import java.math.BigDecimal;

public class CashPayment implements Payment {
    @Override
    public boolean validateAmount(BigDecimal amount) {
        // Cash: non-negative amount (greater than or equal to 0)
        return amount.compareTo(BigDecimal.ZERO) >= 0;
    }

    @Override
    public boolean completePayment(Sale sale) {
        BigDecimal total = sale.getTotal();
        // Simplify logic: Cash payments are made directly
        System.out.println("✅ Cash payment completed successfully. Amount due: " + total + " USD, Amount paid: " + total + " USD");
        return true;
    }

    @Override
    public String getPaymentType() {
        return "Cash Payment";
    }
}