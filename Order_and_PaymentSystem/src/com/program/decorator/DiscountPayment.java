package com.program.decorator;

public class DiscountPayment extends PaymentDecorator{
    private static final double discount = 0.20;

    public DiscountPayment(DecoratorPayment payment) {
        super(payment);
    }

    @Override
    public double getTotalPrice() {
        return payment.getTotalPrice() * (1-discount);
    }
}
