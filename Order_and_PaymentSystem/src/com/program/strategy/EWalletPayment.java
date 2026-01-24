package com.program.strategy;

public class EWalletPayment implements PaymentStrategy{
    @Override
    public void pay(double amount) {
        System.out.println("USER MEMILIH PEMBAYARAN E-Wallet SEBESAR : " + amount);
    }
}
