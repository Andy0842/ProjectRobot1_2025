package com.nextgen.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 收银机类（单例模式：全局唯一收银机）
 * 符合GRASP Creator原则：负责创建Sale对象，管理当前销售和历史销售记录
 */
@Getter
public class Register {
    // 单例实例：volatile保证多线程可见性
    private static volatile Register instance;

    // 私有构造器：禁止外部通过new创建实例
    private Register() {}

    // 获取全局唯一实例（双重检查锁定，兼顾线程安全和效率）
    public static Register getInstance() {
        if (instance == null) {
            synchronized (Register.class) {
                if (instance == null) {
                    instance = new Register();
                }
            }
        }
        return instance;
    }

    // 收银机核心属性
    private Sale currentSale; // 当前正在进行的销售
    private List<Sale> saleHistory = new ArrayList<>(); // 历史销售记录

    /**
     * 启动新销售（创建Sale对象，作为当前销售）
     */
    public void startNewSale() {
        this.currentSale = new Sale();
        System.out.println("=== Start a new sale ===");
    }

    /**
     * 完成销售（将当前销售加入历史记录，清空当前销售）
     */
    public void completeSale() {
        if (currentSale != null && currentSale.isComplete()) {
            saleHistory.add(currentSale);
            System.out.println("=== Sale has been added to history. Total historical sales: " + saleHistory.size() + " ===");
            this.currentSale = null; // 清空当前销售，准备下一次交易
        } else {
            System.out.println("=== Current sale is not completed, cannot add to history. ===");
        }
    }
}