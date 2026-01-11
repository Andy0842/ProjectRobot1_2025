package com.nextgen.domain.observer;

import com.nextgen.data.Inventory;
import com.nextgen.domain.Sale;
import com.nextgen.domain.SalesLineItem;

/**
 * 库存更新观察者（观察者模式：具体观察者）
 * 作用：销售完成后，自动减少对应商品的库存
 */
public class InventoryObserver implements Observer {
    @Override
    public void update(Sale sale) {
        System.out.println("=== [Inventory Observer] Start updating inventory ===");
        // 获取全局库存实例
        Inventory inventory = Inventory.getInstance();
        // 遍历销售明细，更新每个商品的库存
        for (SalesLineItem item : sale.getLineItems()) {
            String itemId = item.getProductDesc().getItemId();
            int quantity = item.getQuantity();
            // 打印更新前后库存
            int stockBefore = inventory.getStock(itemId);
            inventory.reduceStock(itemId, quantity);
            int stockAfter = inventory.getStock(itemId);
            System.out.println("Product ID: " + itemId + ", Name: " + item.getProductDesc().getName() +
                    ", Stock reduced: " + quantity + ", Before update: " + stockBefore + ", After update: " + stockAfter);
        }
        System.out.println("=== [Inventory Observer] Inventory update completed ===");
    }
}