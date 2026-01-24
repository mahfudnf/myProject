package com.program.dao.order;

import com.program.bridge.MobilePlatform;
import com.program.bridge.WebPlatform;
import com.program.model.order.Customer;
import com.program.model.order.Delivery;
import com.program.model.order.Order;
import com.program.model.order.StatusOrder;
import com.program.model.payment.Payment;
import com.program.model.payment.StatusPayment;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao{
    private Connection connection;

    public OrderDaoImpl(Connection connection){
        this.connection = connection;
    }

    @Override
    public Order save(Order order) {
        String sql = "INSERT INTO orders (orderId,customerId,paymentId,deliveryId,statusOrder,platform,dateOrder)" +
                "VALUES (?,?,?,?,?,?,?)";

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, order.getOrderId());
            ps.setString(2, order.getCustomer().getCustomerId());
            ps.setString(3, order.getPayment().getPaymentId());
            ps.setString(4, order.getDelivery().getDeliveryId());
            ps.setString(5, String.valueOf(order.getStatusOrder()));
            ps.setString(6,order.getPlatform().publish());
            ps.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("gagal menyimpan customer",e);
        }
        return order;
    }

    @Override
    public List<Order> search(Order order) {
        List<Order> list = new ArrayList<>();

        String sql = """
        SELECT
         o.orderId,
         o.statusOrder,
         o.platform,
         o.dateOrder,
         
         c.customerId,
         c.nama,
         c.email,
         c.phone,
         
         p.paymentId,
         p.amount,
         p.statusPayment,
         
         d.deliveryId,
         d.address
         
         FROM orders o
         JOIN customer c ON o.customerId = c.customerId
         LEFT JOIN payment p ON o.paymentId = p.paymentId
         LEFT JOIN delivery d ON o.deliveryId = d.deliveryId
         WHERE o.orderId LIKE ? """;

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1,"%" + order.getOrderId() + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                // ORDER
                Order order1 = new Order(rs.getString("orderId"),null);
                order1.setStatusOrder(StatusOrder.valueOf(rs.getString("statusOrder")));

                // PLATFORM
                String platform = rs.getString("platform");
                if (platform.equalsIgnoreCase("WEB")){
                    order1.setPlatform(new WebPlatform());
                } else if (platform.equalsIgnoreCase("MOBILE")) {
                    order1.setPlatform(new MobilePlatform());
                }

                // CUSTOMER
                Customer customer = new Customer(
                        rs.getString("customerId"),
                        rs.getString("nama"),
                        rs.getString("email"),
                        rs.getString("phone")
                );
                order1.setCustomer(customer);

                // PAYMENT
                if (rs.getString("paymentId")!= null) {
                    Payment payment = new Payment(
                            rs.getString("paymentId"),
                            rs.getDouble("amount")
                    );
                    payment.setStatusPayment(StatusPayment.valueOf(rs.getString("statusPayment")));
                    order1.setPayment(payment);
                }

                // DELIVERY
                if (rs.getString("deliveryId")!= null){
                    Delivery delivery = new Delivery(
                            rs.getString("deliveryId"),
                            rs.getString("address")
                    );
                    order1.setDelivery(delivery);
                }
                list.add(order1);
            }

        } catch (Exception e) {
            throw new RuntimeException("gagal search order",e);
        }

        return list;
    }


}
