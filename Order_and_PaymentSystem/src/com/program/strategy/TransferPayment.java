package com.program.strategy;

public class TransferPayment implements PaymentStrategy{
    @Override
    public void pay(double amount) {
        System.out.println("USER MEMILIH PEMBAYARAN TRANSFER SEBESAR : " + amount);
    }
}
