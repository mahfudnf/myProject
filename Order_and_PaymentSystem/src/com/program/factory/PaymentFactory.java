package com.program.factory;

import com.program.model.order.Payment;

public class PaymentFactory {
    public static Payment createPayment(String id,double amount){
        return new Payment(id,amount);
    }
}
