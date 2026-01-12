package com.nextgen.data;

import com.nextgen.domain.ProductDescription;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class ProductRepository {
    private static ProductRepository instance;
    private Map<String, ProductDescription> productMap;

    public static ProductRepository getInstance() {
        if (instance == null) {
            instance = new ProductRepository();
            instance.initProductData();
        }
        return instance;
    }

    private void initProductData() {
        productMap = new HashMap<>();
        ProductDescription apple = new ProductDescription(
                "001",
                "Red Fuji apples",
                new BigDecimal("5.99"),
                "Freshly picked, crisp, sweet and juicy."
        );
        ProductDescription water = new ProductDescription(
                "002",
                "Nongfu Spring Mineral Water",
                new BigDecimal("2.00"),
                "550ml, natural drinking water"
        );
        ProductDescription noodle = new ProductDescription(
                "003",
                "Master Kong Braised Beef Noodles",
                new BigDecimal("4.50"),
                "Classic flavor, convenient and fast"
        );
        // Store in the commodity warehouse
        productMap.put(apple.getItemId(), apple);
        productMap.put(water.getItemId(), water);
        productMap.put(noodle.getItemId(), noodle);
    }

    public ProductDescription getProductById(String itemId) {
        return productMap.get(itemId);
    }
}