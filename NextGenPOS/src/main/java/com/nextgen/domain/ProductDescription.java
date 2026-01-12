package com.nextgen.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@AllArgsConstructor // all-argument constructor
@Getter // getters for all properties
@ToString // Generate toString method for debugging
public class ProductDescription {
    // Product ID
    private String itemId;
    // Product name
    private String name;
    // Unit price
    private BigDecimal price;
    // Product description
    private String description;
}