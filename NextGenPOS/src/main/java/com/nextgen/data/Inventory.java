package com.nextgen.data;

import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * 库存类（模拟数据存储，单例模式）
 * 作用：管理商品库存，提供库存增减/查询方法
 */
@NoArgsConstructor
public class Inventory {
    // 单例实例：全局唯一库存
    private static Inventory instance;
    // 存储库存：key=商品ID，value=库存数量
    private Map<String, Integer> stockMap;

    // 获取单例实例
    public static Inventory getInstance() {
        if (instance == null) {
            instance = new Inventory();
            instance.initStockData(); // 初始化库存数据
        }
        return instance;
    }

    /**
     * 初始化库存数据（对应商品仓库的3件商品）
     */
    private void initStockData() {
        stockMap = new HashMap<>();
        stockMap.put("001", 100); // 苹果库存100
        stockMap.put("002", 500); // 矿泉水库存500
        stockMap.put("003", 200); // 方便面库存200
    }

    /**
     * 减少商品库存（销售后调用）
     * @param itemId 商品ID
     * @param quantity 减少的数量
     */
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

    /**
     * 增加商品库存（退货时可调用，预留功能）
     * @param itemId 商品ID
     * @param quantity 增加的数量
     */
    public void increaseStock(String itemId, int quantity) {
        if (stockMap.containsKey(itemId)) {
            int currentStock = stockMap.get(itemId);
            stockMap.put(itemId, currentStock + quantity);
        } else {
            System.out.println("[Inventory Tip] Product ID: " + itemId + " does not exist, cannot update inventory.");
        }
    }

    /**
     * 查询商品当前库存
     * @param itemId 商品ID
     * @return 库存数量
     */
    public int getStock(String itemId) {
        return stockMap.getOrDefault(itemId, 0);
    }
}