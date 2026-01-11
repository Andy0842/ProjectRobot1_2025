package com.nextgen.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 商品描述类（存储商品固定信息：ID、名称、单价、描述）
 * 使用Lombok注解简化代码，无需手动编写getter/构造器
 */
@AllArgsConstructor // 全参构造器
@Getter // 生成所有属性的getter方法
@ToString // 生成toString方法，方便调试
public class ProductDescription {
    // 商品ID
    private String itemId;
    // 商品名称
    private String name;
    // 商品单价（BigDecimal保证金额计算精度）
    private BigDecimal price;
    // 商品描述
    private String description;
}