package com.nextgen.main;

import com.nextgen.data.ProductRepository;
import com.nextgen.domain.*;
import com.nextgen.domain.factory.CashPaymentFactory;
import com.nextgen.domain.factory.PaymentFactory;
import com.nextgen.domain.observer.InventoryObserver;
import com.nextgen.domain.observer.ReceiptObserver;
import com.nextgen.domain.template.SaleTransaction;
import com.nextgen.service.external.WeChatPayAdapter;

public class POSMain {
    public static void main(String[] args) {
        Register register = Register.getInstance();
        ProductRepository productRepo = ProductRepository.getInstance();
        Sale sale;

        register.startNewSale();
        sale = register.getCurrentSale();

        sale.attach(new InventoryObserver());
        sale.attach(new ReceiptObserver());

        ProductDescription apple = productRepo.getProductById("001");
        ProductDescription water = productRepo.getProductById("002");

        sale.addLineItem(apple, 2);
        sale.addLineItem(water, 3);

        PaymentFactory cashFactory = new CashPaymentFactory();
        Payment payment = cashFactory.createPayment();

        SaleTransaction saleTransaction = new SaleTransaction(sale, payment);
        boolean isTransactionSuccess = saleTransaction.processTransaction();

        if (isTransactionSuccess) {
            sale.finishSale(payment.getPaymentType());
            register.completeSale();
        } else {
            System.out.println("=== Sale transaction failed, cannot complete the sale ===");
        }
    }
}