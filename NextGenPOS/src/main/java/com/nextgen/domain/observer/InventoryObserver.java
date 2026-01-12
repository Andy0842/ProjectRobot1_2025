package com.nextgen.domain.observer;

import com.nextgen.data.Inventory;
import com.nextgen.domain.Sale;
import com.nextgen.domain.SalesLineItem;

public class InventoryObserver implements Observer {
    @Override
    public void update(Sale sale) {
        System.out.println("=== [Inventory Observer] Start updating inventory ===");
        // Obtain global inventory instances
        Inventory inventory = Inventory.getInstance();
        // Traverse the sales details and update the inventory of each item
        for (SalesLineItem item : sale.getLineItems()) {
            String itemId = item.getProductDesc().getItemId();
            int quantity = item.getQuantity();
            // Print inventory before and after update
            int stockBefore = inventory.getStock(itemId);
            inventory.reduceStock(itemId, quantity);
            int stockAfter = inventory.getStock(itemId);
            System.out.println("Product ID: " + itemId + ", Name: " + item.getProductDesc().getName() +
                    ", Stock reduced: " + quantity + ", Before update: " + stockBefore + ", After update: " + stockAfter);
        }
        System.out.println("=== [Inventory Observer] Inventory update completed ===");
    }
}