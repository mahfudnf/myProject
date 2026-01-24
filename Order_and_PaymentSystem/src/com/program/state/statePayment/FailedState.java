package com.program.state.statePayment;

import com.program.model.payment.Payment;
import com.program.model.payment.StatusPayment;

public class FailedState implements PaymentState {
    @Override
    public void initiated(Payment payment) {
        throw new IllegalStateException("PAYMENT FAILED");
    }

    @Override
    public void success(Payment payment) {
        throw new IllegalStateException("PAYMENT FAILED");
    }

    @Override
    public void failed(Payment payment) {
        throw new IllegalStateException("PAYMENT ALREADY FAILED");
    }

    @Override
    public StatusPayment getStatus() {
        return StatusPayment.FAILED;
    }
}
