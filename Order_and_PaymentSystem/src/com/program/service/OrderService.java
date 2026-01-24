package com.program.service;

import com.program.appConfig.Config;
import com.program.dao.customer.CustomerDao;
import com.program.dao.customer.CustomerDaoImpl;
import com.program.dao.delivery.DeliveryDao;
import com.program.dao.delivery.DeliveryDaoImpl;
import com.program.dao.order.OrderDao;
import com.program.dao.order.OrderDaoImpl;
import com.program.dao.orderItem.OrderItemDao;
import com.program.dao.orderItem.OrderItemDaoImpl;
import com.program.dao.payment.PaymentDao;
import com.program.dao.payment.PaymentDaoImpl;
import com.program.model.order.Order;
import com.program.model.order.OrderItem;
import java.sql.Connection;
import java.util.List;

public class OrderService {

    private final Connection connection;
    private final CustomerDao customerDao;
    private final OrderDao orderDao;
    private final OrderItemDao orderItemDao;
    private final PaymentDao paymentDao;
    private final DeliveryDao deliveryDao;

    public OrderService(){
        this.connection = Config.myConnection();
        this.customerDao = new CustomerDaoImpl(connection);
        this.orderDao = new OrderDaoImpl(connection);
        this.orderItemDao = new OrderItemDaoImpl(connection);
        this.paymentDao = new PaymentDaoImpl(connection);
        this.deliveryDao = new DeliveryDaoImpl(connection);
    }

    public void saveFullOrder(Order order){
        try {
            connection.setAutoCommit(false);

            customerDao.save(order.getCustomer());
            paymentDao.save(order.getPayment());
            deliveryDao.save(order.getDelivery());
            orderDao.save(order);

            for (OrderItem item : order.getItems()){
                item.setOrder(order);
                orderItemDao.save(item);
            }

            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException("Gagal menyimpan order", e);
        }
    }

    public void tampilkanOrder(String keywords){
        Order param = new Order(keywords,null);
        List<Order> orders = orderDao.search(param);

        for (Order o : orders){
            System.out.println("Order ID : " + o.getOrderId());
            System.out.println("Customer : " + o.getCustomer().getNama());
            System.out.println("Platform : " + o.getPlatform().publish());
            System.out.println("Status   : " + o.getStatusOrder());

            if (o.getPayment() != null) {
                System.out.println("Payment  : " + o.getPayment().getStatusPayment());
            }

            if (o.getDelivery() != null) {
                System.out.println("Address  : " + o.getDelivery().getAddress());
            }
        }
    }

}
