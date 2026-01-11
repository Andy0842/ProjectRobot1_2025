package com.nextgen.domain;

import java.math.BigDecimal;

/**
 * 支付接口（策略模式：定义所有支付方式的统一规范）
 * 所有支付方式（现金、微信、支付宝）都必须实现该接口
 */
public interface Payment {
    // 验证支付金额是否有效
    boolean validateAmount(BigDecimal amount);

    // 完成支付核心逻辑
    boolean completePayment(Sale sale);

    // 获取支付类型名称（如“现金支付”“微信支付”）
    String getPaymentType();
}