package com.nextgen.domain;

import com.nextgen.domain.observer.Observer;
import com.nextgen.domain.observer.Subject;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 销售类（核心业务对象，观察者模式：被观察者）
 * 符合GRASP Expert原则：拥有销售明细，负责计算销售总额
 */
@Getter
public class Sale implements Subject {
    // 观察者列表（存储所有订阅该销售的观察者）
    private List<Observer> observers = new ArrayList<>();

    // 销售核心属性
    private List<SalesLineItem> lineItems = new ArrayList<>(); // 销售明细列表
    private BigDecimal total = BigDecimal.ZERO; // 销售总额
    @Setter
    private boolean isComplete = false; // 销售是否完成
    private LocalDateTime saleTime; // 销售时间
    @Setter
    private String paymentType; // 支付方式

    /**
     * 构造器：创建销售对象时初始化销售时间
     */
    public Sale() {
        this.saleTime = LocalDateTime.now();
    }

    /**
     * 添加销售明细（录入商品，自动累加销售总额）
     * @param productDesc 商品描述
     * @param quantity 购买数量
     */
    public void addLineItem(ProductDescription productDesc, int quantity) {
        SalesLineItem lineItem = new SalesLineItem(productDesc, quantity);
        lineItems.add(lineItem);
        // 累加销售总额
        this.total = this.total.add(lineItem.getSubtotal());
        System.out.println("✅ Product added: " + productDesc.getName() + ", Quantity: " + quantity + ", Current total amount: " + this.total + " USD");
    }

    // ==================== 观察者模式：被观察者接口实现 ====================
    @Override
    public void attach(Observer observer) {
        observers.add(observer);
        System.out.println("✅ Observer registered：" + observer.getClass().getSimpleName());
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
        System.out.println("✅ Observer removed：" + observer.getClass().getSimpleName());
    }

    @Override
    public void notifyObservers() {
        System.out.println("\n=== Start notifying all observers ===");
        for (Observer observer : observers) {
            observer.update(this); // 通知每个观察者，传递当前销售对象
        }
        System.out.println("=== All observers have been notified ===\n");
    }

    /**
     * 完成销售（标记为完成，触发观察者通知）
     * @param paymentType 支付方式
     */
    public void finishSale(String paymentType) {
        this.isComplete = true;
        this.paymentType = paymentType;
        this.notifyObservers(); // 销售完成，通知所有订阅者（更新库存、打印小票）
    }
}