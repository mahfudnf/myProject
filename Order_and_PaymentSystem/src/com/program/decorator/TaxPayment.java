package com.program.decorator;

public class TaxPayment extends PaymentDecorator{
    private static final double tax = 0.11;

    public TaxPayment(DecoratorPayment payment) {
        super(payment);
    }

    @Override
    public double getTotalPrice() {
        return payment.getTotalPrice() * (1+tax) ;
    }
}
