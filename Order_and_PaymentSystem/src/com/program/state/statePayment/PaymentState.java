package com.program.state.statePayment;

import com.program.model.payment.Payment;
import com.program.model.payment.StatusPayment;

public interface PaymentState {
    void initiated(Payment payment);
    void success(Payment payment);
    void failed(Payment payment);
    StatusPayment getStatus();
}
