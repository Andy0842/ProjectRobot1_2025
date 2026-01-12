package com.nextgen.domain.template;

public abstract class AbstractTransaction {
    public final boolean processTransaction() {
        // 1. start transaction (fixed step)
        startTransaction();

        // 2. Execute transaction (variable step, subclass implementation)
        boolean isExecuteSuccess = executeTransaction();
        if (!isExecuteSuccess) {
            handleTransactionFailure(); // Transaction failure handling (fixed step)
            return false;
        }

        // 3. validate transaction (variable step, subclass implementation)
        boolean isValidateSuccess = validateTransaction();
        if (!isValidateSuccess) {
            handleTransactionFailure(); // Transaction failure handling (fixed step)
            return false;
        }

        // 4. Complete the transaction (fixed steps)
        completeTransaction();

        // 5. Follow-up (fixed steps)
        postTransaction();

        return true;
    }

    // Fixed Step 1: Start transaction
    protected void startTransaction() {
        System.out.println("\n=== [Template Method] Start transaction ===");
    }

    // Variable Step 1: Perform transaction (subclass implementation)
    protected abstract boolean executeTransaction();

    // Fixed Step 2: Transaction failure handling
    protected void handleTransactionFailure() {
        System.out.println("=== [Template Method] Transaction failed, terminate process ===");
    }

    // Variable Step 2: Validate transaction (subclass implementation)
    protected abstract boolean validateTransaction();

    // Fixed Step 3: Complete transaction
    protected void completeTransaction() {
        System.out.println("=== [Template Method] Transaction completed ===");
    }

    // Fixed Step 4: Trade follow-up
    protected void postTransaction() {
        System.out.println("=== [Template Method] Execute post-transaction operations ===");
    }
}