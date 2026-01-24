package com.program.dao.delivery;

import com.program.model.order.Delivery;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DeliveryDaoImpl implements DeliveryDao{
    private Connection connection;

    public DeliveryDaoImpl(Connection connection){
        this.connection = connection;
    }

    @Override
    public Delivery save(Delivery delivery) {
        String sql = "INSERT INTO delivery (deliveryId,address)" +
                "VALUES (?,?)";

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, delivery.getDeliveryId());
            ps.setString(2, delivery.getAddress());
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("gagal menyimpan delivery",e);
        }
        return delivery;
    }
}
