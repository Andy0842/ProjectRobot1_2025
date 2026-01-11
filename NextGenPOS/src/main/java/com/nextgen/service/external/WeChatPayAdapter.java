package com.nextgen.service.external;

import com.nextgen.domain.Payment;
import com.nextgen.domain.Sale;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

/**
 * 微信支付适配器（适配器模式：对象适配器）
 * 作用：将外部微信支付SDK接口适配为项目Payment接口，解决接口不兼容问题
 */
public class WeChatPayAdapter implements Payment {
    // 持有被适配者对象（外部微信支付SDK）
    private WeChatPaySDK weChatPaySDK = new WeChatPaySDK();

    // 模拟商户ID
    private static final String MERCHANT_ID = "CS_SUPERMARKET_001";

    @Override
    public boolean validateAmount(BigDecimal amount) {
        // 微信支付：金额必须大于0
        return amount.compareTo(BigDecimal.ZERO) > 0;
    }

    @Override
    public boolean completePayment(Sale sale) {
        // 1. 转换参数（将项目Sale对象转换为第三方SDK所需参数）
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String orderId = "ORDER_" + sale.getSaleTime().format(formatter);
        BigDecimal amount = sale.getTotal();

        // 2. 调用第三方SDK的支付方法
        String result = weChatPaySDK.doPay(MERCHANT_ID, orderId, amount);

        // 3. 解析第三方返回结果，转换为项目所需布尔值
        if (result.startsWith("SUCCESS")) {
            System.out.println("✅ WeChat Pay Adapter: " + result);
            return true;
        } else {
            System.out.println("❌ WeChat Pay Adapter: " + result);
            return false;
        }
    }

    @Override
    public String getPaymentType() {
        return "WeChat Pay (Adapted by third-party SDK)";
    }
}