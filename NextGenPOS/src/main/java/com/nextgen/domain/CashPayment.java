package com.nextgen.domain;

import java.math.BigDecimal;

/**
 * 现金支付实现类（策略模式：具体支付策略）
 * 作用：实现现金支付的核心逻辑，无复杂校验，基础版本友好
 */
public class CashPayment implements Payment {
    @Override
    public boolean validateAmount(BigDecimal amount) {
        // 现金支付：金额非负即可（大于等于0）
        return amount.compareTo(BigDecimal.ZERO) >= 0;
    }

    @Override
    public boolean completePayment(Sale sale) {
        BigDecimal total = sale.getTotal();
        // 简化逻辑：现金支付直接完成
        System.out.println("✅ Cash payment completed successfully. Amount due: " + total + " USD, Amount paid: " + total + " USD");
        return true;
    }

    @Override
    public String getPaymentType() {
        return "Cash Payment";
    }
}