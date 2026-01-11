package com.nextgen.domain.observer;

import com.nextgen.domain.Sale;
import com.nextgen.domain.SalesLineItem;

import java.time.format.DateTimeFormatter;

/**
 * 小票打印观察者（观察者模式：具体观察者）
 * 作用：销售完成后，自动打印收银小票（控制台输出，模拟真实小票）
 */
public class ReceiptObserver implements Observer {
    @Override
    public void update(Sale sale) {
        // 时间格式化，美化销售时间
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatTime = sale.getSaleTime().format(formatter);

        System.out.println("=== [Receipt Observer] Start printing receipt ===");
        System.out.println("======================================");
        System.out.println("          XX Supermarket Cash Receipt");
        System.out.println("        Sale Time: " + formatTime);
        System.out.println("======================================");
        // 遍历销售明细，打印商品信息
        for (SalesLineItem item : sale.getLineItems()) {
            String name = item.getProductDesc().getName();
            String price = item.getProductDesc().getPrice().toString();
            int quantity = item.getQuantity();
            String subtotal = item.getSubtotal().toString();
            System.out.println(name + "    " + price + "USD × " + quantity + " = " + subtotal + "USD");
        }
        System.out.println("======================================");
        System.out.println("          Total: " + sale.getTotal() + "USD");
        System.out.println("          Payment Method: " + sale.getPaymentType());
        System.out.println("======================================");
        System.out.println("        Thank you for your patronage!");
        System.out.println("=== [Receipt Observer] Receipt printing completed ===");
    }
}