package com.program.dao.payment;

import com.program.model.payment.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class PaymentDaoImpl implements PaymentDao{
    private Connection connection;

    public PaymentDaoImpl(Connection connection){
        this.connection = connection;
    }
    @Override
    public Payment save(Payment payment) {
        String sql = "INSERT INTO payment (paymentId,amount,statusPayment)" +
                "VALUES (?,?,?)";

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, payment.getPaymentId());
            ps.setInt(2, (int) payment.getAmount());
            ps.setString(3, String.valueOf(payment.getStatusPayment()));
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("gagal menyimpan payment",e);
        }
        return payment;
    }
}
