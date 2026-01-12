package com.nextgen.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Register {
    private static volatile Register instance;

    private Register() {}

    public static Register getInstance() {
        if (instance == null) {
            synchronized (Register.class) {
                if (instance == null) {
                    instance = new Register();
                }
            }
        }
        return instance;
    }

    // Cash register core property
    private Sale currentSale; // Currently an ongoing sale
    private List<Sale> saleHistory = new ArrayList<>(); // Sales history

    public void startNewSale() {
        this.currentSale = new Sale();
        System.out.println("=== Start a new sale ===");
    }

    public void completeSale() {
        if (currentSale != null && currentSale.isComplete()) {
            saleHistory.add(currentSale);
            System.out.println("=== Sale has been added to history. Total historical sales: " + saleHistory.size() + " ===");
            this.currentSale = null;
        } else {
            System.out.println("=== Current sale is not completed, cannot add to history. ===");
        }
    }
}