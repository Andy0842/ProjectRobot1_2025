package com.nextgen.domain;

import com.nextgen.domain.observer.Observer;
import com.nextgen.domain.observer.Subject;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Sale implements Subject {
    // List of observers
    private List<Observer> observers = new ArrayList<>();

    // Sell core attributes
    private List<SalesLineItem> lineItems = new ArrayList<>(); // Sales breakdown list
    private BigDecimal total = BigDecimal.ZERO; // Total sales
    @Setter
    private boolean isComplete = false; // Whether the sale has been completed
    private LocalDateTime saleTime; // Sales time
    @Setter
    private String paymentType; // Payment method

    public Sale() {
        this.saleTime = LocalDateTime.now();
    }

    public void addLineItem(ProductDescription productDesc, int quantity) {
        SalesLineItem lineItem = new SalesLineItem(productDesc, quantity);
        lineItems.add(lineItem);
        // Add up the total sales
        this.total = this.total.add(lineItem.getSubtotal());
        System.out.println("✅ Product added: " + productDesc.getName() + ", Quantity: " + quantity + ", Current total amount: " + this.total + " USD");
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
        System.out.println("✅ Observer registered：" + observer.getClass().getSimpleName());
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
        System.out.println("✅ Observer removed：" + observer.getClass().getSimpleName());
    }

    @Override
    public void notifyObservers() {
        System.out.println("\n=== Start notifying all observers ===");
        for (Observer observer : observers) {
            observer.update(this); // Notify each observer, passing the current sale object
        }
        System.out.println("=== All observers have been notified ===\n");
    }

    public void finishSale(String paymentType) {
        this.isComplete = true;
        this.paymentType = paymentType;
        this.notifyObservers(); // Sales completed, notify all subscribers (update inventory, print receipt)
    }
}