package com.nextgen.domain.factory;

import com.nextgen.domain.CashPayment;
import com.nextgen.domain.Payment;

public class CashPaymentFactory implements PaymentFactory {
    @Override
    public Payment createPayment() {
        System.out.println("✅ Cash Payment Factory: Creating cash payment object");
        return new CashPayment();
    }
}