package com.program.strategy;

public class CashPayment implements PaymentStrategy{
    @Override
    public void pay(double amount) {
        System.out.println("USER MEMILIH PEMBAYARAN CASH SEBESAR : " + amount);
    }
}
