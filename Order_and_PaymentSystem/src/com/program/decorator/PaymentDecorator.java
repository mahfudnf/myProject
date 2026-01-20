package com.program.decorator;

public abstract class PaymentDecorator implements DecoratorPayment{
    protected DecoratorPayment payment;

    public PaymentDecorator(DecoratorPayment payment){
        this.payment = payment;
    }

    @Override
    public double getTotalPrice() {
        return payment.getTotalPrice();
    }
}
