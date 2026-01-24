package com.program.dao.orderItem;

import com.program.model.order.OrderItem;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class OrderItemDaoImpl implements OrderItemDao{
    private Connection connection;

    public OrderItemDaoImpl(Connection connection){
        this.connection = connection;
    }
    @Override
    public OrderItem save(OrderItem orderItem) {
        String sql = "INSERT INTO orderItem (productId,orderId,nama,quantity,price,finalPrice)" +
                "VALUES (?,?,?,?,?,?)";

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, orderItem.getProductId());
            ps.setString(2, orderItem.getOrder().getOrderId());
            ps.setString(3, orderItem.getName());
            ps.setInt(4, orderItem.getQuantity());
            ps.setInt(5, (int) orderItem.getPrice());
            ps.setInt(6, (int) orderItem.getOrder().getFinalPrice());
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("gagal menyimpan customer",e);
        }
        return orderItem;
    }
}
