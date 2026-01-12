package com.nextgen.domain.template;

import com.nextgen.domain.Payment;
import com.nextgen.domain.Sale;

public class SaleTransaction extends AbstractTransaction {
    // Sell object and pay object (constructor injection to reduce coupling)
    private Sale sale;
    private Payment payment;

    public SaleTransaction(Sale sale, Payment payment) {
        this.sale = sale;
        this.payment = payment;
    }

    // Variable Step 1: Execute the transaction (check if the sales details are not empty)
    @Override
    protected boolean executeTransaction() {
        System.out.println("=== [Sale Transaction] Execute transaction: Check sale line items ===");
        if (sale.getLineItems().isEmpty()) {
            System.out.println("=== [Sale Transaction] Sale line items are empty, execution failed ===");
            return false;
        }
        System.out.println("=== [Sale Transaction] Sale line items are not empty, execution succeeded ===");
        return true;
    }

    // Variable Step 2: Validate transaction (complete payment, check if payment was successful)
    @Override
    protected boolean validateTransaction() {
        System.out.println("=== [Sale Transaction] Validate transaction: Complete payment ===");
        // Call the payment object to perform the payment
        return payment.completePayment(sale);
    }
}