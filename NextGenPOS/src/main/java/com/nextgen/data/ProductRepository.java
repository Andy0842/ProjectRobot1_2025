package com.nextgen.data;

import com.nextgen.domain.ProductDescription;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 商品仓库（模拟数据存储，无需数据库，单例模式）
 * 作用：提供商品信息查询，存储所有商品的固定信息
 */
@NoArgsConstructor
public class ProductRepository {
    // 单例实例：全局唯一商品仓库
    private static ProductRepository instance;
    // 存储商品：key=商品ID，value=商品描述对象
    private Map<String, ProductDescription> productMap;

    // 获取单例实例
    public static ProductRepository getInstance() {
        if (instance == null) {
            instance = new ProductRepository();
            instance.initProductData(); // 初始化商品数据
        }
        return instance;
    }

    /**
     * 初始化商品数据（模拟添加3件测试商品）
     */
    private void initProductData() {
        productMap = new HashMap<>();
        // 商品1：红富士苹果
        ProductDescription apple = new ProductDescription(
                "001",
                "Red Fuji apples",
                new BigDecimal("5.99"),
                "Freshly picked, crisp, sweet and juicy."
        );
        // 商品2：农夫山泉矿泉水
        ProductDescription water = new ProductDescription(
                "002",
                "Nongfu Spring Mineral Water",
                new BigDecimal("2.00"),
                "550ml, natural drinking water"
        );
        // 商品3：康师傅红烧牛肉面
        ProductDescription noodle = new ProductDescription(
                "003",
                "Master Kong Braised Beef Noodles",
                new BigDecimal("4.50"),
                "Classic flavor, convenient and fast"
        );
        // 存入商品仓库
        productMap.put(apple.getItemId(), apple);
        productMap.put(water.getItemId(), water);
        productMap.put(noodle.getItemId(), noodle);
    }

    /**
     * 根据商品ID查询商品信息
     * @param itemId 商品ID
     * @return 商品描述对象（无对应商品返回null）
     */
    public ProductDescription getProductById(String itemId) {
        return productMap.get(itemId);
    }
}