package com.nextgen.main;

import com.nextgen.data.ProductRepository;
import com.nextgen.domain.*;
import com.nextgen.domain.factory.CashPaymentFactory;
import com.nextgen.domain.factory.PaymentFactory;
import com.nextgen.domain.observer.InventoryObserver;
import com.nextgen.domain.observer.ReceiptObserver;
import com.nextgen.domain.template.SaleTransaction;
import com.nextgen.service.external.WeChatPayAdapter;

/**
 * POS系统主类（程序入口，运行完整销售流程）
 * 基础薄弱学生：直接运行main方法，即可看到完整运行结果
 */
public class POSMain {
    public static void main(String[] args) {
        // ==================== 步骤1：初始化所需对象 ====================
        Register register = Register.getInstance(); // 收银机单例
        ProductRepository productRepo = ProductRepository.getInstance(); // 商品仓库单例
        Sale sale; // 销售对象

        // ==================== 步骤2：启动新销售，注册观察者 ====================
        register.startNewSale();
        sale = register.getCurrentSale();

        // 注册观察者（库存更新、小票打印）
        sale.attach(new InventoryObserver());
        sale.attach(new ReceiptObserver());

        // ==================== 步骤3：录入商品（从商品仓库查询，添加到销售明细） ====================
        ProductDescription apple = productRepo.getProductById("001"); // 红富士苹果
        ProductDescription water = productRepo.getProductById("002"); // 农夫山泉矿泉水

        sale.addLineItem(apple, 2); // 录入2个苹果
        sale.addLineItem(water, 3); // 录入3瓶矿泉水

        // ==================== 步骤4：选择支付方式（可选：现金支付/微信支付） ====================
        // 方式1：现金支付（工厂模式创建）
        PaymentFactory cashFactory = new CashPaymentFactory();
        Payment payment = cashFactory.createPayment();

        // 方式2：微信支付（适配器模式，取消注释即可切换）
        // Payment payment = new WeChatPayAdapter();

        // ==================== 步骤5：执行销售交易（模板方法模式） ====================
        SaleTransaction saleTransaction = new SaleTransaction(sale, payment);
        boolean isTransactionSuccess = saleTransaction.processTransaction();

        // ==================== 步骤6：完成销售，触发观察者通知 ====================
        if (isTransactionSuccess) {
            sale.finishSale(payment.getPaymentType());
            register.completeSale();
        } else {
            System.out.println("=== Sale transaction failed, cannot complete the sale ===");
        }
    }
}