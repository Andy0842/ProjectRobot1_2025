package com.nextgen.domain.template;

import com.nextgen.domain.Payment;
import com.nextgen.domain.Sale;

/**
 * 销售交易子类（模板方法模式：具体子类）
 * 实现销售交易的可变步骤，完成销售流程
 */
public class SaleTransaction extends AbstractTransaction {
    // 销售对象和支付对象（构造器注入，降低耦合）
    private Sale sale;
    private Payment payment;

    /**
     * 构造器：注入依赖
     * @param sale 销售对象
     * @param payment 支付对象
     */
    public SaleTransaction(Sale sale, Payment payment) {
        this.sale = sale;
        this.payment = payment;
    }

    // 可变步骤1：执行交易（检查销售明细是否非空）
    @Override
    protected boolean executeTransaction() {
        System.out.println("=== [Sale Transaction] Execute transaction: Check sale line items ===");
        if (sale.getLineItems().isEmpty()) {
            System.out.println("=== [Sale Transaction] Sale line items are empty, execution failed ===");
            return false;
        }
        System.out.println("=== [Sale Transaction] Sale line items are not empty, execution succeeded ===");
        return true;
    }

    // 可变步骤2：验证交易（完成支付，判断支付是否成功）
    @Override
    protected boolean validateTransaction() {
        System.out.println("=== [Sale Transaction] Validate transaction: Complete payment ===");
        // 调用支付对象，执行支付
        return payment.completePayment(sale);
    }
}