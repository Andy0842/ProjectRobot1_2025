package com.nextgen.service.external;

import com.nextgen.domain.Payment;
import com.nextgen.domain.Sale;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

public class WeChatPayAdapter implements Payment {
    // Hold the adapted object (external wechat Pay SDK)
    private WeChatPaySDK weChatPaySDK = new WeChatPaySDK();

    // Simulate merchant ID
    private static final String MERCHANT_ID = "CS_SUPERMARKET_001";

    @Override
    public boolean validateAmount(BigDecimal amount) {
        // Wechat Pay: The amount must be greater than 0
        return amount.compareTo(BigDecimal.ZERO) > 0;
    }

    @Override
    public boolean completePayment(Sale sale) {
        // 1. Conversion parameters (Convert the project Sale object to the parameters required by the third-party SDK)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String orderId = "ORDER_" + sale.getSaleTime().format(formatter);
        BigDecimal amount = sale.getTotal();

        // 2. Invoke the payment method of the third-party SDK
        String result = weChatPaySDK.doPay(MERCHANT_ID, orderId, amount);

        // 3. Parse the results returned by third parties and convert them into the Boolean values required by the project
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