package com.nextgen.data;

import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class Inventory {
    private static Inventory instance;
    private Map<String, Integer> stockMap;

    public static Inventory getInstance() {
        if (instance == null) {
            instance = new Inventory();
            instance.initStockData();
        }
        return instance;
    }

    private void initStockData() {
        stockMap = new HashMap<>();
        stockMap.put("001", 100);
        stockMap.put("002", 500);
        stockMap.put("003", 200);
    }

    public void reduceStock(String itemId, int quantity) {
        if (stockMap.containsKey(itemId)) {
            int currentStock = stockMap.get(itemId);
            if (currentStock >= quantity) {
                stockMap.put(itemId, currentStock - quantity);
            } else {
                System.out.println("[Inventory Tip] Product ID: " + itemId + " Insufficient inventory, current stock: " + currentStock);
            }
        } else {
            System.out.println("[Inventory Tip] Product ID: " + itemId + " does not exist, cannot update inventory.");
        }
    }

    public void increaseStock(String itemId, int quantity) {
        if (stockMap.containsKey(itemId)) {
            int currentStock = stockMap.get(itemId);
            stockMap.put(itemId, currentStock + quantity);
        } else {
            System.out.println("[Inventory Tip] Product ID: " + itemId + " does not exist, cannot update inventory.");
        }
    }

    public int getStock(String itemId) {
        return stockMap.getOrDefault(itemId, 0);
    }
}