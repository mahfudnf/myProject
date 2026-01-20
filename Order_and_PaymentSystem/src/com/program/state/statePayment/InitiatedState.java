package com.program.state.statePayment;

import com.program.model.order.Payment;
import com.program.model.orderEnum.StatusPayment;

public class InitiatedState implements PaymentState{
    @Override
    public void initiated(Payment payment) {
        throw new IllegalStateException("PAYMENT ALREADY INITIATED");
    }

    @Override
    public void success(Payment payment) {
        System.out.println("PAYMENT SUCCESS");
        payment.setPaymentState(new SuccessState());
    }

    @Override
    public void failed(Payment payment) {
        System.out.println("PAYMENT FAILED");
        payment.setPaymentState(new FailedState());
    }

    @Override
    public StatusPayment getStatus() {
        return StatusPayment.INITIATED;
    }
}
