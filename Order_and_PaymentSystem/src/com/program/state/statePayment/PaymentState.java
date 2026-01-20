package com.program.state.statePayment;

import com.program.model.order.Payment;
import com.program.model.orderEnum.StatusPayment;

public interface PaymentState {
    void initiated(Payment payment);
    void success(Payment payment);
    void failed(Payment payment);
    StatusPayment getStatus();
}
