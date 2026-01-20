package com.program.strategy;

public class PaymentContext {
    private PaymentStrategy strategy;

    public void setStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void executePayment(int amount){
        if (strategy == null) {
            throw new IllegalStateException("Payment strategy belum dipilih!");
        }
        strategy.pay(amount);
    }
}
