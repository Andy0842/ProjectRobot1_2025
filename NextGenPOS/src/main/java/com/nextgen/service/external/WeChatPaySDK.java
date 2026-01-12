package com.nextgen.service.external;

import java.math.BigDecimal;

public class WeChatPaySDK {
    public String doPay(String merchantId, String orderId, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) > 0) {
            return "SUCCESS：WeChat Pay Order " + orderId + " paid successfully, Amount: " + amount + " USD";
        } else {
            return "FAIL：Invalid payment amount, Amount: " + amount + " USD";
        }
    }

    public String queryPayResult(String orderId) {
        return "SUCCESS：WeChat Pay Order" + orderId + " completed, Status: Paid";
    }
}