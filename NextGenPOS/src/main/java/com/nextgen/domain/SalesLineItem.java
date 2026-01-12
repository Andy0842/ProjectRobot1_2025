package com.nextgen.domain;

import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@ToString
public class SalesLineItem {
    // Product description
    private ProductDescription productDesc;
    // Purchase quantity
    private int quantity;
    // Detailed subtotal amount
    private BigDecimal subtotal;

    public SalesLineItem(ProductDescription productDesc, int quantity) {
        this.productDesc = productDesc;
        this.quantity = quantity;
        // Calculate subtotals: unit price × quantity
        this.subtotal = productDesc.getPrice().multiply(new BigDecimal(quantity));
    }
}