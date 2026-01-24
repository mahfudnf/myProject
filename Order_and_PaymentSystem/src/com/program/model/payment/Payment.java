package com.program.model.payment;

import com.program.state.statePayment.InitiatedState;
import com.program.state.statePayment.PaymentState;

public class Payment {
    private final String paymentId;
    private final double amount;
    private StatusPayment statusPayment;
    private PaymentState paymentState;

    public Payment(String paymentTipe, double amount) {
        this.paymentId = paymentTipe;
        this.amount = amount;
        setPaymentState(new InitiatedState());
    }

    public void setPaymentState(PaymentState paymentState) {
        this.paymentState = paymentState;
        this.statusPayment = paymentState.getStatus();
    }

    public void markSuccess(){
        paymentState.success(this);
    }

    public void markFailed(){
        paymentState.failed(this);
    }

    public String getPaymentId() {
        return paymentId;
    }

    public double getAmount() {
        return amount;
    }

    public StatusPayment getStatusPayment() {
        return statusPayment;
    }

    public void setStatusPayment(StatusPayment statusPayment) {
        this.statusPayment = statusPayment;
    }
}
