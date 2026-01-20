package com.program.state.statePayment;
import com.program.model.order.Payment;
import com.program.model.orderEnum.StatusPayment;

public class SuccessState implements PaymentState {
    @Override
    public void initiated(Payment payment) {
        throw new IllegalStateException("PAYMENT ALREADY SUCCESS");
    }

    @Override
    public void success(Payment payment) {
        throw new IllegalStateException("PAYMENT ALREADY SUCCESS");
    }

    @Override
    public void failed(Payment payment) {
        throw new IllegalStateException("PAYMENT ALREADY SUCCESS");
    }

    @Override
    public StatusPayment getStatus() {
        return StatusPayment.SUCCESS;
    }
}
