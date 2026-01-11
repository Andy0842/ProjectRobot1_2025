package com.nextgen.domain;

import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 销售明细类（对应单个商品的购买信息）
 * 符合GRASP Expert原则：拥有商品单价和数量，负责计算小计金额
 */
@Getter
@ToString
public class SalesLineItem {
    // 商品描述
    private ProductDescription productDesc;
    // 购买数量
    private int quantity;
    // 明细小计金额（单价×数量）
    private BigDecimal subtotal;

    /**
     * 构造器：创建销售明细时自动计算小计
     * @param productDesc 商品描述
     * @param quantity 购买数量
     */
    public SalesLineItem(ProductDescription productDesc, int quantity) {
        this.productDesc = productDesc;
        this.quantity = quantity;
        // 计算小计：单价 × 数量
        this.subtotal = productDesc.getPrice().multiply(new BigDecimal(quantity));
    }
}