package com.nextgen.domain.factory;

import com.nextgen.domain.CashPayment;
import com.nextgen.domain.Payment;

/**
 * 现金支付工厂（工厂方法模式：具体工厂，创建现金支付对象）
 * 封装支付对象的创建逻辑，降低上层代码耦合
 */
public class CashPaymentFactory implements PaymentFactory {
    @Override
    public Payment createPayment() {
        System.out.println("✅ Cash Payment Factory: Creating cash payment object");
        return new CashPayment();
    }
}