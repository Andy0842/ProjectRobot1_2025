package com.nextgen.service.external;

import java.math.BigDecimal;

/**
 * 模拟外部微信支付SDK（第三方提供，接口固定，无法修改）
 * 作用：模拟真实第三方支付接口，展示适配器模式的应用场景
 */
public class WeChatPaySDK {
    /**
     * 第三方支付接口：发起支付
     * @param merchantId 商户ID
     * @param orderId 订单ID
     * @param amount 支付金额
     * @return 支付结果
     */
    public String doPay(String merchantId, String orderId, BigDecimal amount) {
        // 模拟微信支付逻辑：金额大于0则支付成功
        if (amount.compareTo(BigDecimal.ZERO) > 0) {
            return "SUCCESS：WeChat Pay Order " + orderId + " paid successfully, Amount: " + amount + " USD";
        } else {
            return "FAIL：Invalid payment amount, Amount: " + amount + " USD";
        }
    }

    /**
     * 第三方支付接口：查询支付结果
     * @param orderId 订单ID
     * @return 支付结果
     */
    public String queryPayResult(String orderId) {
        return "SUCCESS：WeChat Pay Order" + orderId + " completed, Status: Paid";
    }
}