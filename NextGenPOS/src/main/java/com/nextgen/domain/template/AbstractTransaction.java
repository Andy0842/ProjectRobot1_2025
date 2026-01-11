package com.nextgen.domain.template;

/**
 * 交易抽象模板类（模板方法模式：封装固定交易流程）
 * 定义销售/退货的固定流程，可变步骤延迟到子类实现
 */
public abstract class AbstractTransaction {
    // 模板方法：固定交易流程（final修饰，禁止子类重写，保证流程规范）
    public final boolean processTransaction() {
        // 1. 启动交易（固定步骤）
        startTransaction();

        // 2. 执行交易（可变步骤，子类实现）
        boolean isExecuteSuccess = executeTransaction();
        if (!isExecuteSuccess) {
            handleTransactionFailure(); // 交易失败处理（固定步骤）
            return false;
        }

        // 3. 验证交易（可变步骤，子类实现）
        boolean isValidateSuccess = validateTransaction();
        if (!isValidateSuccess) {
            handleTransactionFailure(); // 交易失败处理（固定步骤）
            return false;
        }

        // 4. 完成交易（固定步骤）
        completeTransaction();

        // 5. 后续操作（固定步骤）
        postTransaction();

        return true;
    }

    // 固定步骤1：启动交易
    protected void startTransaction() {
        System.out.println("\n=== [Template Method] Start transaction ===");
    }

    // 可变步骤1：执行交易（子类实现）
    protected abstract boolean executeTransaction();

    // 固定步骤2：交易失败处理
    protected void handleTransactionFailure() {
        System.out.println("=== [Template Method] Transaction failed, terminate process ===");
    }

    // 可变步骤2：验证交易（子类实现）
    protected abstract boolean validateTransaction();

    // 固定步骤3：完成交易
    protected void completeTransaction() {
        System.out.println("=== [Template Method] Transaction completed ===");
    }

    // 固定步骤4：交易后续操作
    protected void postTransaction() {
        System.out.println("=== [Template Method] Execute post-transaction operations ===");
    }
}