package com.nextgen.domain.factory;

import com.nextgen.domain.Payment;

/**
 * 支付工厂接口（工厂方法模式：定义创建支付对象的统一规范）
 */
public interface PaymentFactory {
    // 创建支付对象
    Payment createPayment();
}