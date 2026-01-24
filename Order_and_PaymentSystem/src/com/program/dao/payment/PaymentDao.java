package com.program.dao.payment;

import com.program.model.payment.Payment;

public interface PaymentDao {
    Payment save(Payment payment);
}
